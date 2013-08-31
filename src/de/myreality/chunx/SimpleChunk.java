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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.myreality.chunx.util.PositionInterpreter;
import de.myreality.chunx.util.SimplePositionInterpreter;

/**
 * Simple implementation of {@link Chunk}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleChunk implements Chunk {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================

	private int indexX, indexY;
	
	private List<ChunkTarget> targets;
	
	private ChunkConfiguration configuration;
	
	private transient PositionInterpreter positionInterpreter;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleChunk(int indexX, int indexY, ChunkConfiguration configuration) {
		this.indexX = indexX;
		this.indexY = indexY;
		this.configuration = configuration;
		positionInterpreter = new SimplePositionInterpreter(configuration);
		targets = new CopyOnWriteArrayList<ChunkTarget>();
	}
	
	public SimpleChunk() {
		if (configuration != null) {
			positionInterpreter = new SimplePositionInterpreter(configuration);
		}
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
		return positionInterpreter.translateIndexX(indexX);
	}

	@Override
	public float getY() {
		return positionInterpreter.translateIndexY(indexY);
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
		if (targets.isEmpty()) {
			ChunkTarget last = targets.get(targets.size() - 1);
			targets.remove(last);
			return last;
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

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
