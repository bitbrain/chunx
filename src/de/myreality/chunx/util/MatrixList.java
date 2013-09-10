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

import java.util.Collection;

/**
 * Provides a two dimensional data storage with internal
 * indexing (x,y)
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface MatrixList<Type> extends Collection<Type> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Removes the element at the given index
	 * 
	 * @param indexX x index
	 * @param indexY y index
	 * @return true when element can be removed, otherwise false
	 */
	boolean remove(int indexX, int indexY);
	
	/**
	 * Creates a copy of this matrix list and returns it
	 * 
	 * @return copy of this matrix list
	 */
	MatrixList<Type> copy();
	
	/**
	 * Checks if the given index already exists
	 * 
	 * @param indexX x index 
	 * @param indexY y index
	 * @return True when the index exists
	 */
	boolean contains(int indexX, int indexY);
	
	/**
	 * Returns the current type at the given position. If there
	 * is no type, <code>null</code> will be returned.
	 * 
	 * @param indexX x index of the type
	 * @param indexY y index of the type
	 * @return type at the given index
	 */
	Type get(int indexX, int indexY);
	
	/**
	 * Sets a new matrix list
	 * 
	 * @param matrixList new list to apply
	 */
	void set(MatrixList<Type> matrixList);
}
