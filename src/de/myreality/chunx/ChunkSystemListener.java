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

/**
 * Provides observing for chunk systems. A {@link ChunkSystemListener} is called
 * whenever a chunk is loaded, created, saved or removed.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface ChunkSystemListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Is called before a chunk will be created
	 * 
	 * @param indexX x index of the chunk
	 * @param indexY y index of the chunk
	 */
	void beforeCreateChunk(int indexX, int indexY);

	/**
	 * Is called after a chunk has been created
	 * 
	 * @param chunk new chunk
	 */
	void afterCreateChunk(Chunk chunk);

	/**
	 * Is called before a chunk will be loaded
	 * 
	 * @param indexX x index of the chunk
	 * @param indexY y index of the chunk
	 */
	void beforeLoadChunk(int indexX, int indexY);

	/**
	 * Is called after an existing chunk is loaded from disk
	 * 
	 * @param chunk existing chunk from disk
	 */
	void afterLoadChunk(Chunk chunk);

	/**
	 * Is called before an existing chunk is saved
	 * 
	 * @param chunk chunk to save
	 */
	void beforeSaveChunk(Chunk chunk);

	/**
	 * Is called after saving an existing chunk
	 * 
	 * @param chunk chunk which has been saved
	 */
	void afterSaveChunk(Chunk chunk);

	/**
	 * Is called before removing an existing chunk
	 * 
	 * @param chunk chunk to remove
	 */
	void beforeRemoveChunk(Chunk chunk);

	/**
	 * Is called after a chunk gets removed
	 * 
	 * @param indexX x index of the chunk
	 * @param indexY y index of the chunk
	 */
	void afterRemoveChunk(int indexX, int indexY);
	
	/**
	 * Is called when a new chunk has been entered by the focused object
	 * 
	 * @param chunk chunk which has been entered
	 */
	void onEnterChunk(Chunk chunk);
	
	/**
	 * Is called when the current chunk has been leaved by the focused object
	 * 
	 * @param chunk chunk which has been leaved
	 */
	void onLeaveChunk(Chunk chunk);
}
