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
package de.myreality.chunx.concurrent;

import java.util.Collection;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ChunkHandler;
import de.myreality.chunx.ChunkListener;
import de.myreality.chunx.ChunkSystem;
import de.myreality.chunx.io.ChunkLoader;
import de.myreality.chunx.io.ChunkSaver;

/**
 * Concurrent implementation of {@link ChunkSystem}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ConcurrentChunkSystem implements ChunkSystem, Runnable {

	
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ChunkSystem system;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ConcurrentChunkSystem(ChunkSystem system) {
		this.system = system;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public void start() {
		system.start();
	}

	@Override
	public void shutdown() {
		system.shutdown();
	}

	@Override
	public void update() {
		system.update();
	}

	@Override
	public boolean isRunning() {
		return system.isRunning();
	}

	@Override
	public void update(float delta) {
		system.update(delta);
	}

	@Override
	public ChunkConfiguration getConfiguration() {
		return system.getConfiguration();
	}

	@Override
	public void setConfiguration(ChunkConfiguration configuration) {
		system.setConfiguration(configuration);
	}

	@Override
	public Chunk getActiveChunk() {
		return system.getActiveChunk();
	}

	@Override
	public Collection<Chunk> getChunks() {
		return system.getChunks();
	}

	@Override
	public Chunk getChunk(int indexX, int indexY) {
		return system.getChunk(indexX, indexY);
	}

	@Override
	public int getCurrentChunkCount() {
		return system.getCurrentChunkCount();
	}

	@Override
	public void addListener(ChunkListener listener) {
		system.addListener(listener);
	}

	@Override
	public void removeListener(ChunkListener listener) {
		system.removeListener(listener);
	}

	@Override
	public Collection<ChunkListener> getListeners() {
		return system.getListeners();
	}

	@Override
	public void setHandler(ChunkHandler handler) {
		system.setHandler(handler);
	}

	@Override
	public ChunkHandler getHandler() {
		return system.getHandler();
	}

	@Override
	public void setLoader(ChunkLoader chunkLoader) {
		system.setLoader(chunkLoader);
	}

	@Override
	public ChunkLoader getLoader() {
		return system.getLoader();
	}

	@Override
	public void setSaver(ChunkSaver chunkSaver) {
		system.setSaver(chunkSaver);
	}

	@Override
	public ChunkSaver getSaver() {
		return system.getSaver();
	}

	@Override
	public void run() {
		update();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
