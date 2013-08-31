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
	
	private CachedChunkConfiguration configuration;
	
	private int offsetX, offsetY;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleCache(int indexX, int indexY, CachedChunkConfiguration configuration) {
		this.configuration = configuration;
		align(indexX, indexY);
	}
	
	public SimpleCache(CachedChunkConfiguration configuration) {
		this(0, 0, configuration);
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public boolean containsIndex(int indexX, int indexY) {
		
		final boolean topLeftRange = indexX < getIndexLeft()
				|| indexY < getIndexTop();
		final boolean bottomRightRange = indexX > getIndexRight()
				|| indexY > getIndexBottom();

		return !(topLeftRange || bottomRightRange);
	}

	@Override
	public boolean containsIndex(Indexable indexable) {
		return containsIndex(indexable.getIndexX(), indexable.getIndexY());
	}

	@Override
	public int getIndexTop() {
		return -configuration.getCacheSizeY() + offsetX;
	}

	@Override
	public int getIndexBottom() {
		return configuration.getCacheSizeY() + offsetY;
	}

	@Override
	public int getIndexLeft() {
		return -configuration.getCacheSizeX() + offsetX;
	}

	@Override
	public int getIndexRight() {
		return configuration.getCacheSizeY() + offsetY;
	}

	@Override
	public void align(int indexX, int indexY) {
		this.offsetX = indexX;
		this.offsetY = indexY;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
