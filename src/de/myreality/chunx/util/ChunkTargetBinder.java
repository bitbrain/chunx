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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkListener;
import de.myreality.chunx.ChunkSystemListener;
import de.myreality.chunx.ChunkTarget;

/**
 * Binds a chunk target to a given chunk. If a target would "spawn" outside, it
 * will be aligned automatically to the chunk.
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ChunkTargetBinder implements ChunkListener, ChunkSystemListener {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Map<Chunk, PositionableBinder> binders;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ChunkTargetBinder() {
		binders = new ConcurrentHashMap<Chunk, PositionableBinder>();
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public void beforeCreateChunk(int indexX, int indexY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCreateChunk(Chunk chunk) {
		chunk.addListener(this);
		addBinder(chunk);
	}

	@Override
	public void beforeLoadChunk(int indexX, int indexY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterLoadChunk(Chunk chunk) {
		chunk.addListener(this);
		addBinder(chunk);
	}

	@Override
	public void beforeSaveChunk(Chunk chunk) {
		chunk.removeListener(this);
	}

	@Override
	public void afterSaveChunk(Chunk chunk) {
		chunk.addListener(this);
	}

	@Override
	public void beforeRemoveChunk(Chunk chunk) {
		chunk.removeListener(this);
		removeBinder(chunk);
	}

	@Override
	public void afterRemoveChunk(int indexX, int indexY) {
		
	}

	@Override
	public void onEnterChunk(Chunk chunk) {
		
	}

	@Override
	public void onLeaveChunk(Chunk chunk) {
		
	}

	@Override
	public void onAdd(ChunkTarget target, Chunk chunk) {
		
		PositionableBinder binder = binders.get(chunk);
		
		if (binder != null) {
			float lastX = chunk.getLeft() + chunk.getWidth() / 2f;
			float lastY = chunk.getTop() + chunk.getHeight() / 2f;
			binder.bind(target, lastX, lastY);
		}
		
	}

	@Override
	public void onRemove(ChunkTarget target, Chunk chunk) {
		// TODO Auto-generated method stub

	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void addBinder(Chunk chunk) {
		binders.put(chunk, new PositionableBinder(chunk));
	}
	
	private void removeBinder(Chunk chunk) {
		binders.remove(chunk);
	}
	
	// ===========================================================
	// Inner classes
	// ===========================================================
}
