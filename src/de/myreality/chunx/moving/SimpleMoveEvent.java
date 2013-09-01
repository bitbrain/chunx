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
package de.myreality.chunx.moving;

import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.util.PositionInterpreter;
import de.myreality.chunx.util.SimplePositionInterpreter;

/**
 * Simple implementation of {@link MoveEvent}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleMoveEvent implements MoveEvent {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkTarget target;
	
	private float lastX, lastY, newX, newY;
	
	private PositionInterpreter positionInterpreter;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	SimpleMoveEvent(ChunkTarget target, ChunkConfiguration configuration, float lastX, float lastY, float newX, float newY) {
		this.lastX = lastX;
		this.lastY = lastY;
		this.newX = newX;
		this.newY = newY;
		positionInterpreter = new SimplePositionInterpreter(configuration);
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public ChunkTarget getTarget() {
		return target;
	}

	@Override
	public int getLastIndexX() {
		return positionInterpreter.translateX(lastX);
	}

	@Override
	public int getLastIndexY() {
		return positionInterpreter.translateY(lastY);
	}

	@Override
	public float getLastX() {
		return lastX;
	}

	@Override
	public float getLastY() {
		return lastY;
	}

	@Override
	public int getNewIndexX() {
		return positionInterpreter.translateX(newX);
	}

	@Override
	public int getNewIndexY() {
		return positionInterpreter.translateY(newY);
	}

	@Override
	public float getNewX() {
		return newX;
	}

	@Override
	public float getNewY() {
		return newY;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
