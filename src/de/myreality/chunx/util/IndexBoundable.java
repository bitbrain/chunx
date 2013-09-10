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
 * Provides index bounds
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface IndexBoundable {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	/**
	 * Returns the top index of this boundable
	 * 
	 * @return top index of the cache
	 */
	int getIndexTop();
	
	/**
	 * Returns the bottom index of this boundable
	 * 
	 * @return bottom index of the cache
	 */
	int getIndexBottom();
	
	/**
	 * Returns the left index of this boundable
	 * 
	 * @return left index of the cache
	 */
	int getIndexLeft();
	
	/**
	 * Returns the right index of this boundable 
	 * 
	 * @return right index of the cache
	 */
	int getIndexRight();
	
	/**
	 * Returns true when the index is inside of the cache
	 * 
	 * @param indexX x index
	 * @param indexY y index
	 * @return True when in cache
	 */
	boolean containsIndex(int indexX, int indexY);
	
	/**
	 * Returns true when the indexable is inside of the cache
	 * 
	 * @param indexable indexable to check
	 * @return True when in cache
	 */
	boolean containsIndex(Indexable indexable);
}
