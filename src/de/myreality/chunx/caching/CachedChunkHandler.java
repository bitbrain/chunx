/*
 * chunx is a Java 2D chunk engine to generate "infinite" worlds.
 * Copyright (C) 2013  Miguel Gonzalez
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package de.myreality.chunx.caching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ChunkFactory;
import de.myreality.chunx.ChunkHandler;
import de.myreality.chunx.ChunkSystemListener;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.ContentProvider;
import de.myreality.chunx.SimpleChunkFactory;
import de.myreality.chunx.io.ChunkSaver;
import de.myreality.chunx.moving.MoveEvent;
import de.myreality.chunx.util.BoundableAdapter;
import de.myreality.chunx.util.IndexBoundable;
import de.myreality.chunx.util.MatrixList;
import de.myreality.chunx.util.PositionInterpreter;
import de.myreality.chunx.util.PositionableBinder;
import de.myreality.chunx.util.SimplePositionInterpreter;

/**
 * Simple implementation of {@link ChunkHandler}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
class CachedChunkHandler implements ChunkHandler {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkFactory chunkFactory;
	
	private PositionInterpreter positionInterpreter;
	
	private CachedChunkSystem chunkSystem;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CachedChunkHandler(CachedChunkSystem chunkSystem) {
		this.chunkSystem = chunkSystem;
		chunkFactory = new SimpleChunkFactory(chunkSystem.getConfiguration());
		positionInterpreter = new SimplePositionInterpreter(chunkSystem.getConfiguration());
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public void handleChunks(MatrixList<Chunk> chunks) {
		
		MatrixList<Chunk> removeChunks = chunks.copy();
		IndexBoundable cache = chunkSystem.getPreCache();	
		ContentProvider provider = chunkSystem.getConfiguration().getContentProvider();
		
		for (int indexX = cache.getIndexLeft(); indexX <= cache.getIndexRight(); ++indexX) {
			for (int indexY = cache.getIndexTop(); indexY <= cache.getIndexBottom(); ++indexY) {
				
				Chunk chunk = null;
				
				// Chunk is valid and should remain
				if (chunks.contains(indexX, indexY)) {
					removeChunks.remove(indexX, indexY);
					chunk = chunks.get(indexX, indexY);
					
				// Chunk doesn't exists, add it
				} else {
					chunk = getChunk(indexX, indexY);
					chunks.add(chunk);
				}
				
				// Move all entities from the chunk to
				// the current content provider
				while (!chunk.isEmpty() && provider != null) {
					provider.add(chunk.retrieve());
				}
			}
		}
		
		// Remove all other chunks with aren't in the cache anymore
		unloadChunks(removeChunks, chunks);
	}

	@Override
	public void onMove(MoveEvent event) {
		
		ChunkTarget target = event.getTarget();
		
		int indexX = event.getNewIndexX();
		int indexY = event.getNewIndexY();		
		IndexBoundable preCache = chunkSystem.getPreCache();		
		ChunkTarget focused = chunkSystem.getConfiguration().getFocused();
		boolean isNotFocused = focused == null || !target.equals(chunkSystem.getConfiguration().getFocused());
		if (isNotFocused && !preCache.containsIndex(indexX, indexY)) {
			
			// Bind target to the current cache
			PositionableBinder binder = new PositionableBinder(new BoundableAdapter(preCache, chunkSystem.getConfiguration()));
			PositionInterpreter interpreter = new SimplePositionInterpreter(chunkSystem.getConfiguration());
			
			binder.bind(target, event.getLastX(), event.getLastY());			
			
			// Update position
			indexX = interpreter.translateX(target.getX());
			indexY = interpreter.translateY(target.getY());
			
			Chunk chunk = chunkSystem.getChunk(indexX, indexY);
			ContentProvider contentProvider = chunkSystem.getConfiguration().getContentProvider();
			
			if (chunk != null) {
				chunk.add(target);
			}
			
			if (contentProvider != null) {
				contentProvider.remove(target);		
			}
		}
	}
	
	@Override
	public void saveChunks(MatrixList<Chunk> chunks) {
		for (Chunk chunk : chunks) {
			saveChunk(chunk);
		}
	}

	@Override
	public void saveChunk(Chunk chunk) {
		saveChunk(chunk, false);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void saveChunk(Chunk chunk, boolean remove) {
		
		ChunkSaver saver = chunkSystem.getSaver();		
		
		List<Object> targets = getTargetsForChunk(chunk);
		
		for (Object target : targets) {		
			if (target instanceof ChunkTarget) {
				chunk.add((ChunkTarget)target);
			}
		}
		
		beforeSaveChunk(chunk);
		
		try {
			saver.save(chunk);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		afterSaveChunk(chunk);
		
		chunk.clear();
	}
	
	private void unloadChunk(Chunk chunk) {
		
		ChunkConfiguration configuration = chunkSystem.getConfiguration();
		ContentProvider contentProvider = configuration.getContentProvider();
		
		List<Object> targets = getTargetsForChunk(chunk);
		
		beforeRemoveChunk(chunk);		
		saveChunk(chunk, true);

		if (contentProvider != null) {
			for (Object target : targets) {
				contentProvider.remove(target);
			}
		}
		
		afterRemoveChunk(chunk.getIndexX(), chunk.getIndexY());
	}
	
	private void unloadChunks(MatrixList<Chunk> removeables, MatrixList<Chunk> chunks) {
		for (Chunk chunk : removeables) {
			unloadChunk(chunk);
			chunks.remove(chunk);
		}
	}
	
	private Chunk getChunk(int indexX, int indexY) {
		
		Chunk chunk = null;
		
		try {
			beforeLoadChunk(indexX, indexY);
			chunk = chunkSystem.getLoader().load(indexX, indexY);
			afterLoadChunk(chunk);
		} catch (IOException e) {
			beforeCreateChunk(indexX, indexY);
			chunk = chunkFactory.createChunk(indexX, indexY);
			afterCreateChunk(chunk);
		}
		
		return chunk;
	}
	private List<Object> getTargets() {
		List<Object> targets = new CopyOnWriteArrayList<Object>();
		ChunkConfiguration configuration = chunkSystem.getConfiguration();
		ContentProvider contentProvider = configuration.getContentProvider();

		if (contentProvider != null) {
			targets.addAll(contentProvider.getContent());
		}
		
		return targets;
	}
	
	private List<Object> getTargetsForChunk(Chunk chunk) {
		
		List<Object> targets = getTargets();
		List<Object> results = new ArrayList<Object>();
		
		for (Object target : targets) {
			if (target instanceof ChunkTarget) {
				int indexX = positionInterpreter.translateX(((ChunkTarget)target).getX());
				int indexY = positionInterpreter.translateY(((ChunkTarget)target).getY());
				
				if (chunk.getIndexX() == indexX && chunk.getIndexY() == indexY) {
					results.add(target);
				}
			}
		}
		
		return results;		
	}
	
	// ===========================================================
	// Event methods
	// ===========================================================
	
	private void beforeCreateChunk(int indexX, int indexY) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.beforeCreateChunk(indexX, indexY, chunkSystem);
		}
	}
	
	private void afterCreateChunk(Chunk chunk) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.afterCreateChunk(chunk, chunkSystem);
		}
	}
	
	private void beforeLoadChunk(int indexX, int indexY) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.beforeLoadChunk(indexX, indexY, chunkSystem);
		}
	}
	
	private void afterLoadChunk(Chunk chunk) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.afterLoadChunk(chunk, chunkSystem);
		}
	}
	
	private void beforeRemoveChunk(Chunk chunk) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.beforeRemoveChunk(chunk, chunkSystem);
		}
	}
	
	private void afterRemoveChunk(int indexX, int indexY) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.afterRemoveChunk(indexX, indexY, chunkSystem);
		}
	}
	
	private void beforeSaveChunk(Chunk chunk) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.beforeSaveChunk(chunk, chunkSystem);
		}
	}
	
	private void afterSaveChunk(Chunk chunk) {
		for (ChunkSystemListener listener : chunkSystem.getListeners()) {
			listener.afterSaveChunk(chunk, chunkSystem);
		}
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
