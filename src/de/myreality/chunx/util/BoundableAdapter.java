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

import de.myreality.chunx.ChunkConfiguration;

/**
 * Adapter which maps between indexes and real positions
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class BoundableAdapter implements Boundable, IndexBoundable {	

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private IndexBoundable indexBoundable;
	
	private PositionInterpreter interpreter;
	
	private ChunkConfiguration configuration;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public BoundableAdapter(IndexBoundable indexBoundable, ChunkConfiguration configuration) {
		this.indexBoundable = indexBoundable;
		interpreter = new SimplePositionInterpreter(configuration);
		this.configuration = configuration;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================
	
	public void setConfiguration(ChunkConfiguration configuration) {
		interpreter = new SimplePositionInterpreter(configuration);
	}

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public int getIndexTop() {
		return indexBoundable.getIndexTop();
	}

	@Override
	public int getIndexBottom() {
		return indexBoundable.getIndexBottom();
	}

	@Override
	public int getIndexLeft() {
		return indexBoundable.getIndexLeft();
	}

	@Override
	public int getIndexRight() {
		return indexBoundable.getIndexRight();
	}

	@Override
	public float getTop() {
		return interpreter.translateIndexY(getIndexTop());
	}

	@Override
	public float getBottom() {
		return interpreter.translateIndexY(getIndexBottom()) + configuration.getChunkHeight();
	}

	@Override
	public float getLeft() {
		return interpreter.translateIndexX(getIndexLeft());
	}

	@Override
	public float getRight() {
		return interpreter.translateIndexX(getIndexRight()) + configuration.getChunkWidth();
	}

	@Override
	public boolean containsIndex(int indexX, int indexY) {
		return indexBoundable.containsIndex(indexX, indexY);
	}

	@Override
	public boolean containsIndex(Indexable indexable) {
		return indexBoundable.containsIndex(indexable);
	}

	@Override
	public boolean contains(float x, float y) {
		return BoundableUtils.contains(this, x, y);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
