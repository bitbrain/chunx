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

import java.util.Collection;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ChunkListener;
import de.myreality.chunx.ChunkSystem;
import de.myreality.chunx.io.ChunkLoader;
import de.myreality.chunx.io.ChunkSaver;
import de.myreality.chunx.util.AbstractManageable;

/**
 * Cached implementation of {@see ChunkSystem}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CachedChunkSystem extends AbstractManageable implements ChunkSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private CachedChunkConfiguration configuration;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public CachedChunkSystem(CachedChunkConfiguration configuration) {
		this.configuration = configuration;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public void update() {
		update(0.0f);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChunkConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	public void setConfiguration(ChunkConfiguration configuration) {
		if (configuration instanceof CachedChunkConfiguration) {
			this.configuration = (CachedChunkConfiguration)configuration;
		}
	}

	@Override
	public Chunk getActiveChunk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Chunk> getChunks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chunk getChunk(int indexX, int indexY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCurrentChunkCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addListener(ChunkListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(ChunkListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoader(ChunkLoader chunkLoader) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChunkLoader getLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSaver(ChunkSaver chunkSaver) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChunkSaver getSaver() {
		// TODO Auto-generated method stub
		return null;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
