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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.myreality.chunx.util.PositionInterpreter;
import de.myreality.chunx.util.SimpleObservable;
import de.myreality.chunx.util.SimplePositionInterpreter;

/**
 * Simple implementation of {@link Chunk}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleChunk extends SimpleObservable<ChunkListener> implements Chunk {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================

	private int indexX, indexY;
	
	private float x, y;
	
	private List<ChunkTarget> targets;
	
	private ChunkConfiguration configuration;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleChunk(int indexX, int indexY, ChunkConfiguration configuration) {
		this.indexX = indexX;
		this.indexY = indexY;
		this.configuration = configuration;
		PositionInterpreter positionInterpreter = new SimplePositionInterpreter(configuration);
		targets = new ArrayList<ChunkTarget>();
		x = positionInterpreter.translateIndexX(indexX);
		y = positionInterpreter.translateIndexY(indexY);
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public int getIndexX() {
		return indexX;
	}

	@Override
	public int getIndexY() {
		return indexY;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}
	
	@Override
	public float getWidth() {
		return configuration.getChunkWidth();
	}

	@Override
	public float getHeight() {
		return configuration.getChunkHeight();
	}
	
	@Override
	public ChunkTarget retrieve() {		
		if (!targets.isEmpty()) {
			ChunkTarget first = targets.get(0);
			targets.remove(first);
			return first;
		} else {
			return null;
		}
	}

	@Override
	public void add(ChunkTarget target) {
		if (!targets.contains(target)) {
			targets.add(target);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((configuration == null) ? 0 : configuration.hashCode());
		result = prime * result + indexX;
		result = prime * result + indexY;
		result = prime * result + ((targets == null) ? 0 : targets.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleChunk other = (SimpleChunk) obj;
		if (configuration == null) {
			if (other.configuration != null)
				return false;
		} else if (!configuration.equals(other.configuration))
			return false;
		if (indexX != other.indexX)
			return false;
		if (indexY != other.indexY)
			return false;
		if (targets == null) {
			if (other.targets != null)
				return false;
		} else if (!targets.equals(other.targets))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleChunk [indexX=" + indexX + ", indexY=" + indexY
				+ ", targets=" + targets + ", configuration=" + configuration
				+ "]";
	}

	@Override
	public Iterator<ChunkTarget> iterator() {
		return targets.iterator();
	}

	@Override
	public boolean contains(ChunkTarget target) {
		return targets.contains(target);
	}

	@Override
	public int size() {
		return targets.size();
	}

	@Override
	public boolean isEmpty() {
		return targets.isEmpty();
	}

	@Override
	public void clear() {
		targets.clear();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
