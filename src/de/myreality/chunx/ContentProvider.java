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

import java.util.Collection;

/**
 * Provides content for chunk systems to manage objects. 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface ContentProvider {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Is called automatically when a chunk target has to be added to
	 * the content provider
	 * 
	 * @param target target to add
	 */
	void add(Object target);
	
	/**
	 * Is called automatically when a chunk target has to be removed
	 * from the content provider
	 * 
	 * @param target target to remove
	 */
	void remove(Object target);
	
	/**
	 * Returns all current chunk targets that have to be managed
	 * by a chunk system
	 * 
	 * @return current collection of chunk targets
	 */
	Collection<Object> getContent();
}
