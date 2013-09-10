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
package de.myreality.chunx.moving;

import de.myreality.chunx.ChunkTarget;

/**
 * Is called by a {@link MovementDetector} whenever a chunk target has been moved
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface MoveEvent {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the target which has been moved
	 * 
	 * @return target which has been moved
	 */
	ChunkTarget getTarget();
	
	/**
	 * Returns the last horizontal index
	 * 
	 * @return last horizontal index
	 */
	int getLastIndexX();
	
	/**
	 * Returns the last vertical index
	 * 
	 * @return last vertical index
	 */
	int getLastIndexY();
	
	/**
	 * Returns the last x position
	 * 
	 * @return last x position
	 */
	float getLastX();
	
	/**
	 * Returns the last y position
	 * 
	 * @return last y position
	 */
	float getLastY();
	
	/**
	 * Returns the new horizontal index, after movement
	 * 
	 * @return new horizontal index
	 */
	int getNewIndexX();
	
	/**
	 * Returns the new vertical index, after movement
	 * 
	 * @return new vertical index
	 */
	int getNewIndexY();
	
	/**
	 * Returns the new x position, after movement
	 * 
	 * @return new x position
	 */
	float getNewX();
	
	/**
	 * Returns the new y position, after movement
	 * 
	 * @return new y position
	 */
	float getNewY();
}
