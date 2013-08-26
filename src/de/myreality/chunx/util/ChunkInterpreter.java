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
 * Calculates positions between chunk indexing and "real" world coordinates
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface ChunkInterpreter {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Translates the x index to a fitting x position by considering the
	 * width of each chunk.
	 * 
	 * @param indexX x index to translate
	 * @return x position which fits to the x index
	 */
	float translateIndexX(int indexX);
	
	/**
	 * Translates the y index to a fitting y position by considering the
	 * height of each chunk.
	 * 
	 * @param indexY y index to translate
	 * @return y position which fits to the y index
	 */
	float translateIndexY(int indexY);
	
	/**
	 * Translates the x position to a fitting index by considering the 
	 * width of each chunk.
	 * 
	 * @param x x position to translate
	 * @return x index which fits to the x position
	 */
	int translateX(float x);
	
	
	/**
	 * Translates the y position to a fitting index by considering the 
	 * height of each chunk.
	 * 
	 * @param y y position to translate
	 * @return y index which fits to the y position
	 */
	int translateY(float y);
}
