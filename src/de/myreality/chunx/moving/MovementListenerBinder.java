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

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkSystem;
import de.myreality.chunx.ChunkSystemListener;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.util.SimpleObservable;

/**
 * Distributes the handler of a chunk system to chunk targets and vise versa
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class MovementListenerBinder extends SimpleObservable<MovementListener> implements ChunkSystemListener {

	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1L;
	
	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkSystem system;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public MovementListenerBinder(ChunkSystem system) {
		this.system = system;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public void beforeCreateChunk(int indexX, int indexY, ChunkSystem system) {
		
	}

	@Override
	public void afterCreateChunk(Chunk chunk, ChunkSystem system) {
		add(chunk);
	}

	@Override
	public void beforeLoadChunk(int indexX, int indexY, ChunkSystem system) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterLoadChunk(Chunk chunk, ChunkSystem system) {
		add(chunk);
	}

	@Override
	public void beforeSaveChunk(Chunk chunk, ChunkSystem system) {
		remove(chunk);
	}

	@Override
	public void afterSaveChunk(Chunk chunk, ChunkSystem system) {
		add(chunk);
	}

	@Override
	public void beforeRemoveChunk(Chunk chunk, ChunkSystem system) {
	}

	@Override
	public void afterRemoveChunk(int indexX, int indexY, ChunkSystem system) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnterChunk(Chunk chunk, ChunkSystem system) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeaveChunk(Chunk chunk, ChunkSystem system) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private void remove(Chunk chunk) {

		for (ChunkTarget target : chunk) {
			
			if (target instanceof MoveableChunkTarget) {
				MoveableChunkTarget moveable = (MoveableChunkTarget)target;
				MovementDetector detector = moveable.getMovementDetector();
				
				for (MovementListener listener : getListeners()) {
					detector.removeListener(listener);
				}
				
				moveable.setMovementDetector(null);
			}
		}
	}
	
	private void add(Chunk chunk) {
		for (ChunkTarget target : chunk) {
			if (target instanceof MoveableChunkTarget) {
				MoveableChunkTarget moveable = (MoveableChunkTarget)target;
				MovementDetector detector = new SimpleMovementDetector(target, system.getConfiguration());
				for (MovementListener listener : getListeners()) {
					detector.addListener(listener);
				}
				moveable.setMovementDetector(detector);
			}
		}
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
