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
package de.myreality.chunx.io;


/**
 * Holds information for managing a location in order to load and save chunks
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface FileConfiguration {

	// ===========================================================
	// Constants
	// ===========================================================
	
	/**
	 * Path which is set by default
	 */
	public static final String DEFAULT_PATH = "chunks/";

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Returns the current path
	 * 
	 * @return current path
	 */
	String getPath();
	
	/**
	 * Sets a new path
	 * 
	 * @param file new path
	 */
	void setPath(String file);
	
	/**
	 * Returns the current file converter
	 * 
	 * @return current converter
	 */
	FileNameConverter getConverter();
	
	/**
	 * Sets a new converter
	 * 
	 * @param converter new converter
	 */
	void setConverter(FileNameConverter converter);
}
