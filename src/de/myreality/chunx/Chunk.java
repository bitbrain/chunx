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

import java.io.Serializable;

import de.myreality.chunx.util.Indexable;
import de.myreality.chunx.util.Observable;

/**
 * Single chunk which is managed by a certain chunk system. 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface Chunk extends Indexable, Serializable, Iterable<ChunkTarget>, Observable<ChunkListener> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the current x position
	 * 
	 * @return x position
	 */
	float getX();
	
	/**
	 * Returns the current y position
	 * 
	 * @return y position
	 */
	float getY();
	
	/**
	 * Returns the width of this chunk
	 * 
	 * @return current width
	 */
	float getWidth();
	
	/**
	 * Returns the height of this chunk
	 * 
	 * @return current height
	 */
	float getHeight();
	
	/**
	 * Returns the first added chunk target and removes
	 * it from this chunk instance
	 * 
	 * @return first added chunk target
	 */
	ChunkTarget retrieve();
	
	/**
	 * Adds a new chunk target to this chunk
	 * 
	 * @param target chunk target to add
	 */
	void add(ChunkTarget target);
	
	/**
	 * Determines if the target is saved in the chunk
	 * 
	 * @param target target to check
	 * @return true when contained
	 */
	boolean contains(ChunkTarget target);
	
	/**
	 * Returns the current size of contained elements
	 * 
	 * @return amount of elements
	 */
	int size();
	
	/**
	 * Determines if the chunk has elements
	 * 
	 * @return true when empty
	 */
	boolean isEmpty();
	
	/**
	 * Clears this chunk
	 */
	void clear();
}
