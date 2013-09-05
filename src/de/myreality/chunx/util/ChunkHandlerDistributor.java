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

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkHandler;
import de.myreality.chunx.ChunkListener;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.moving.MoveableChunkTarget;
import de.myreality.chunx.moving.MovementDetector;

/**
 * Distributes the handler of a chunk system to chunk targets and vise versa
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ChunkHandlerDistributor implements ChunkListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkHandler handler;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChunkHandlerDistributor(ChunkHandler handler) {
		this.handler = handler;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================
	
	public void setHandler(ChunkHandler handler) {
		this.handler = handler;
	}
	
	public ChunkHandler getHandler() {
		return handler;
	}

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public void beforeCreateChunk(int indexX, int indexY) {
		
	}

	@Override
	public void afterCreateChunk(Chunk chunk) {
		add(chunk);
	}

	@Override
	public void beforeLoadChunk(int indexX, int indexY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterLoadChunk(Chunk chunk) {
		add(chunk);
	}

	@Override
	public void beforeSaveChunk(Chunk chunk) {
		remove(chunk);
	}

	@Override
	public void afterSaveChunk(Chunk chunk) {
		add(chunk);
	}

	@Override
	public void beforeRemoveChunk(Chunk chunk) {
	}

	@Override
	public void afterRemoveChunk(int indexX, int indexY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnterChunk(Chunk chunk) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLeaveChunk(Chunk chunk) {
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
				detector.removeListener(handler);
			}
		}
	}
	
	private void add(Chunk chunk) {
		for (ChunkTarget target : chunk) {
			if (target instanceof MoveableChunkTarget) {
				MoveableChunkTarget moveable = (MoveableChunkTarget)target;
				MovementDetector detector = moveable.getMovementDetector();
				detector.addListener(handler);
			}
		}
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
