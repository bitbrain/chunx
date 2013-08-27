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

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of a horizontal iterator, which basically reads
 * each "row" of a matrix.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class MatrixIterator<Type> implements Iterator<Type> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Iterator<? extends Map<Integer, Type> > iteratorX;
	
	private Iterator<Type> iteratorY;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public MatrixIterator(Iterator<? extends Map<Integer, Type> > chunks) {
		this.iteratorX = chunks;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public boolean hasNext() {
		return iteratorY != null ? iteratorY.hasNext() || iteratorX.hasNext() : iteratorX.hasNext();
	}

	@Override
	public Type next() {
		if (iteratorY == null || !iteratorY.hasNext())
			iteratorY = iteratorX.next().values().iterator();		
		return iteratorY.hasNext() ? iteratorY.next() : null;		
	}

	@Override
	public void remove() {
		// TODO: Not implemented yet
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
