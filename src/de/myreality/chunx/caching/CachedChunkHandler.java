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

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ChunkFactory;
import de.myreality.chunx.ChunkHandler;
import de.myreality.chunx.ChunkListener;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.ContentProvider;
import de.myreality.chunx.SimpleChunkFactory;
import de.myreality.chunx.io.ChunkSaver;
import de.myreality.chunx.moving.MoveEvent;
import de.myreality.chunx.moving.PositionableBinder;
import de.myreality.chunx.util.BoundableAdapter;
import de.myreality.chunx.util.MatrixList;
import de.myreality.chunx.util.PositionInterpreter;
import de.myreality.chunx.util.SimplePositionInterpreter;

/**
 * Simple implementation of {@link ChunkHandler}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CachedChunkHandler implements ChunkHandler {

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
		Cache cache = chunkSystem.getPreCache();	
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
				while (!chunk.isEmpty()) {
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
		Cache preCache = chunkSystem.getPreCache();		
		boolean isNotFocused = !target.equals(chunkSystem.getConfiguration().getFocused());
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
			} else {
				System.out.println("Chunk at index " + indexX + "|" + indexY + " does not exist");
			}
			contentProvider.remove(target);			
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
		
		List<ChunkTarget> targets = getTargetsForChunk(chunk);
		
		for (ChunkTarget target : targets) {			
			chunk.add(target);
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
		
		List<ChunkTarget> targets = getTargetsForChunk(chunk);
		
		beforeRemoveChunk(chunk);		
		saveChunk(chunk, true);
		
		for (ChunkTarget target : targets) {
			contentProvider.remove(target);
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
	
	private List<ChunkTarget> getTargets() {
		List<ChunkTarget> targets = new ArrayList<ChunkTarget>();
		
		ChunkConfiguration configuration = chunkSystem.getConfiguration();
		ContentProvider contentProvider = configuration.getContentProvider();
		
		targets.addAll(contentProvider.getContent());
		
		return targets;
	}
	
	private List<ChunkTarget> getTargetsForChunk(Chunk chunk) {
		
		List<ChunkTarget> targets = getTargets();
		List<ChunkTarget> results = new ArrayList<ChunkTarget>();
		
		for (ChunkTarget target : targets) {
			int indexX = positionInterpreter.translateX(target.getX());
			int indexY = positionInterpreter.translateY(target.getY());
			
			if (chunk.getIndexX() == indexX && chunk.getIndexY() == indexY) {
				results.add(target);
			}
		}
		
		return results;		
	}
	
	// ===========================================================
	// Event methods
	// ===========================================================
	
	private void beforeCreateChunk(int indexX, int indexY) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.beforeCreateChunk(indexX, indexY);
		}
	}
	
	private void afterCreateChunk(Chunk chunk) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.afterCreateChunk(chunk);
		}
	}
	
	private void beforeLoadChunk(int indexX, int indexY) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.beforeLoadChunk(indexX, indexY);
		}
	}
	
	private void afterLoadChunk(Chunk chunk) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.afterLoadChunk(chunk);
		}
	}
	
	private void beforeRemoveChunk(Chunk chunk) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.beforeRemoveChunk(chunk);
		}
	}
	
	private void afterRemoveChunk(int indexX, int indexY) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.afterRemoveChunk(indexX, indexY);
		}
	}
	
	private void beforeSaveChunk(Chunk chunk) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.beforeSaveChunk(chunk);
		}
	}
	
	private void afterSaveChunk(Chunk chunk) {
		for (ChunkListener listener : chunkSystem.getListeners()) {
			listener.afterSaveChunk(chunk);
		}
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
