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
package de.myreality.chunx.util;

/**
 * Provides real borders for bounds
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface Boundable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the top side of this boundable
	 * 
	 * @return top side of the cache
	 */
	float getTop();
	
	/**
	 * Returns the bottom side of this boundable
	 * 
	 * @return bottom side of the cache
	 */
	float getBottom();
	
	/**
	 * Returns the left side of this boundable
	 * 
	 * @return left side of the cache
	 */
	float getLeft();
	
	/**
	 * Returns the right side of this boundable 
	 * 
	 * @return right side of the cache
	 */
	float getRight();
	
	boolean contains(float x, float y);
}
