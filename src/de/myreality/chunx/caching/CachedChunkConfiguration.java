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

import de.myreality.chunx.ChunkConfiguration;

/**
 * Provides caching information additionally to its normal chunk data
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface CachedChunkConfiguration extends ChunkConfiguration {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the total amount of chunks
	 * 
	 * @return total chunk amount
	 */
	int getTotalChunkCount();
	
	/**
	 * Returns the current available chunk amount
	 * 
	 * @return current chunk amount
	 */
	int getCurrentChunkCount();
	
	/**
	 * Sets a new horizontal size
	 * 
	 * @param sizeX new horizontal size
	 */
	void setCacheSizeX(int sizeX);
	
	/**
	 * Sets a new vertical size
	 *  
	 * @param sizeY new vertical size
	 */
	void setCacheSizeY(int sizeY);
	
	/**
	 * Sets a new cache size (vertically and horizontally)
	 * 
	 * @param size new cache size
	 */
	void setCacheSize(int size);
	
	/**
	 * Returns the current horizontal cache size
	 * 
	 * @return current horizontal cache size
	 */
	int getCacheSizeX();
	
	/**
	 * Returns the current vertical cache size
	 * 
	 * @return current vertical cache size
	 */
	int getCacheSizeY();
}
