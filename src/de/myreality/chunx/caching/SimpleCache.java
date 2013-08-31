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

import de.myreality.chunx.util.Indexable;

/**
 * Simple implementation of a cache
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleCache implements Cache {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public boolean containsIndex(int indexX, int indexY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsIndex(Indexable indexable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getIndexTop() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndexBottom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndexLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIndexRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void align(int indexX, int indexY) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
