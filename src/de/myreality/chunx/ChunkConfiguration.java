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
 * Chunk configuration which holds information about for chunk systems
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface ChunkConfiguration {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the current chunk width
	 * 
	 * @return chunk width
	 */
	int getChunkWidth();
	
	/**
	 * Returns the current chunk height
	 * 
	 * @return chunk height
	 */
	int getChunkHeight();
	
	/**
	 * Sets a new chunk width
	 * 
	 * @param width new width
	 */
	void setChunkWidth(int width);
	
	/**
	 * Sets a new chunk height
	 * 
	 * @param height new height
	 */
	void setChunkHeight(int height);
	
	/**
	 * Sets a new chunk size
	 * 
	 * @param size new size
	 */
	void setChunkSize(int size);
	
	/**
	 * Sets a new content provider
	 * 
	 * @param contentProvider new content provider
	 */
	void setContentProvider(ContentProvider contentProvider);
	
	/**
	 * Returns the current content provider
	 * 
	 * @return current content provider
	 */
	ContentProvider getContentProvider();
	
	/**
	 * Sets the focus on a new target
	 * 
	 * @param target new focus
	 */
	void setFocused(ChunkTarget target);
	
	/**
	 * Returns the current chunk focus
	 * 
	 * @return current focus
	 */
	ChunkTarget getFocused();
}
