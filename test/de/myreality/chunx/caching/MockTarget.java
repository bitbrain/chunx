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

import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.moving.MoveableChunkTarget;
import de.myreality.chunx.moving.MovementDetector;
import de.myreality.chunx.moving.SimpleMovementDetector;

/**
 * 
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since
 * @version
 */
class MockTarget implements MoveableChunkTarget {

	private static final long serialVersionUID = 1L;

	private float x, y;

	private MovementDetector movementDetector;

	public MockTarget() {

	}

	public MockTarget(float x, float y, ChunkConfiguration configuration) {
		this.x = x;
		this.y = y;
		movementDetector = new SimpleMovementDetector(this, configuration);
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
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
	public void update() {
		update(0.0f);
	}

	@Override
	public void update(float delta) {
		movementDetector.update();
	}

	@Override
	public MovementDetector getMovementDetector() {
		return movementDetector;
	}

	@Override
	public void setMovementDetector(MovementDetector movementDetector) {
		this.movementDetector = movementDetector;
	}

}
