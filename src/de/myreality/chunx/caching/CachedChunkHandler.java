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

import de.myreality.chunx.AbstractChunkHandler;
import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ChunkFactory;
import de.myreality.chunx.ChunkHandler;
import de.myreality.chunx.ChunkListener;
import de.myreality.chunx.ContentProvider;
import de.myreality.chunx.SimpleChunkFactory;
import de.myreality.chunx.io.ChunkLoader;
import de.myreality.chunx.io.ChunkSaver;
import de.myreality.chunx.util.MatrixList;

/**
 * Simple implementation of {@link ChunkHandler}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CachedChunkHandler extends AbstractChunkHandler {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkFactory chunkFactory;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CachedChunkHandler(ChunkConfiguration configuration, ChunkLoader chunkLoader, ChunkSaver chunkSaver) {
		super(chunkLoader, chunkSaver);		
		chunkFactory = new SimpleChunkFactory(configuration);
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public void handleChunks(MatrixList<Chunk> chunks, CachedChunkSystem system) {
		synchronized (chunks) {
			MatrixList<Chunk> removeList = chunks.copy();
			Cache cache = system.getPreCache();	
			ContentProvider provider = system.getConfiguration().getContentProvider();
			
			for (int indexX = cache.getIndexLeft(); indexX <= cache.getIndexRight(); ++indexX) {
				for (int indexY = cache.getIndexTop(); indexY <= cache.getIndexBottom(); ++indexY) {
					if (chunks.contains(indexX, indexY)) {
						removeList.remove(indexX, indexY);
						
					} else {
						Chunk chunk = getChunk(indexX, indexY, system);
						chunks.add(chunk);
						
						int size = chunk.size();
						for (int i = 0; i < size; ++i) {
							provider.add(chunk.retrieve());
						}
						
						saveChunk(chunk, false);
					}
				}
			}
			
			for (Chunk chunk : removeList) {
				saveChunk(chunk, true);
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void saveChunk(Chunk chunk, boolean remove) {
		
	}
	
	private Chunk getChunk(int indexX, int indexY, CachedChunkSystem system) {
		
		Chunk chunk = null;
		
		try {
			beforeLoadChunk(indexX, indexY, system);
			chunk = getLoader().load(indexX, indexY);
			afterLoadChunk(chunk, system);
		} catch (IOException e) {
			beforeCreateChunk(indexX, indexY, system);
			chunk = chunkFactory.createChunk(indexX, indexY);
			afterCreateChunk(chunk, system);
		}
		
		return chunk;
	}
	
	private void beforeCreateChunk(int indexX, int indexY, CachedChunkSystem system) {
		for (ChunkListener listener : system.getListeners()) {
			listener.beforeCreateChunk(indexX, indexY);
		}
	}
	
	private void afterCreateChunk(Chunk chunk, CachedChunkSystem system) {
		for (ChunkListener listener : system.getListeners()) {
			listener.afterCreateChunk(chunk);
		}
	}
	
	private void beforeLoadChunk(int indexX, int indexY, CachedChunkSystem system) {
		for (ChunkListener listener : system.getListeners()) {
			listener.beforeLoadChunk(indexX, indexY);
		}
	}
	
	private void afterLoadChunk(Chunk chunk, CachedChunkSystem system) {
		for (ChunkListener listener : system.getListeners()) {
			listener.afterLoadChunk(chunk);
		}
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
