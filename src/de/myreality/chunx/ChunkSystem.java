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
package de.myreality.chunx;

import java.util.Collection;

import de.myreality.chunx.io.ChunkLoader;
import de.myreality.chunx.io.ChunkSaver;
import de.myreality.chunx.util.Manageable;

/**
 * Chunk system which handles automatic chunk loading and saving. Additionally
 * it stores chunks which are loaded and frees old chunks.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface ChunkSystem extends Manageable, ConfigurationProvider {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the current active chunk. Returns <code>null</code> if
	 * the possible active chunk hasn't been loaded yet.
	 * 
	 * @return current chunk which is active by default
	 */
	Chunk getActiveChunk();
	
	/**
	 * Returns the currently loaded chunks
	 * 
	 * @return current chunks
	 */
	Collection<Chunk> getChunks();
	
	/**
	 * Returns the chunk at the given index. Returns also <code>null</code>
	 * if the index is invalid or the chunk hasn't been loaded yet.
	 * 
	 * @param indexX x index of the chunk
	 * @param indexY y index of the chunk
	 * @return the chunk at the given index
	 */
	Chunk getChunk(int indexX, int indexY);
	
	/**
	 * Returns the amount of currently loaded chunks
	 * 
	 * @return current chunk amount
	 */
	int getCurrentChunkCount();
	
	/**
	 * Adds a new listener to the system
	 * 
	 * @param listener new listener to add
	 */
	void addListener(ChunkListener listener);
	
	/**
	 * Removes an existing listener from the system
	 * 
	 * @param listener existing listener to remove
	 */
	void removeListener(ChunkListener listener);
	
	/**
	 * Sets a new loader
	 * 
	 * @param chunkLoader new chunk loader
	 */
	void setLoader(ChunkLoader chunkLoader);
	
	/**
	 * Returns the current loader
	 * 
	 * @return current loader
	 */
	ChunkLoader getLoader();
	
	/**
	 * Sets a new saver 
	 * 
	 * @param chunkSaver new saver
	 */
	void setSaver(ChunkSaver chunkSaver);
	
	/**
	 * Returns the current saver
	 * 
	 * @return current saver
	 */
	ChunkSaver getSaver();
	
}
