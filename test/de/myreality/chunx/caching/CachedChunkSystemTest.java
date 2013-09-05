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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.myreality.chunx.Chunk;
import de.myreality.chunx.ChunkHandler;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.ContentProvider;
import de.myreality.chunx.util.PositionInterpreter;
import de.myreality.chunx.util.SimplePositionInterpreter;

/**
 * Test case for {@link SimpleCachedChunkSystem}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class CachedChunkSystemTest {

	// ===========================================================
	// Definitions
	// ===========================================================

	CachedChunkSystem system;

	CachedChunkConfiguration configuration;

	MockTarget player, target1, target2;
	
	PositionInterpreter interpreter;

	World world;

	static final int SIZE = 1;

	// ===========================================================
	// Setup
	// ===========================================================

	@Before
	public void setUp() throws Exception {
		configuration = new SimpleCachedChunkConfiguration();
		player = new MockTarget(0, 0, configuration);
		target1 = new MockTarget(0, 0, configuration);
		target2 = new MockTarget(0, 0, configuration);
		world = new World();

		configuration.setCacheSize(1);
		configuration.setContentProvider(world);
		configuration.setFocused(player);

		world.add(target1);
		world.add(target2);
		
		interpreter = new SimplePositionInterpreter(configuration);

		system = new SimpleCachedChunkSystem(configuration);
		system.start();
	}

	// ===========================================================
	// Test cases
	// ===========================================================

	@Test
	public void testGetTotalChunkCount() {
		int expected = (int) Math.pow((SIZE * 2 + 1) + 2, 2);
		assertTrue("Chunk count should be " + expected + " instead of "
				+ system.getTotalChunkCount(),
				system.getTotalChunkCount() == expected);
	}

	@Test
	public void testUpdate() {
		
		ChunkHandler handler = system.getHandler();
		
		assertTrue("Current chunk size has to be 25", system.getCurrentChunkCount() == 25);
		system.update();
		assertTrue("Target1 needs a handler", target1.getMovementDetector().contains(handler));
		assertTrue("Target2 needs a handler", target2.getMovementDetector().contains(handler));
		Chunk chunk1 = system.getChunk(interpreter.translateX(target1.getX()),
				                       interpreter.translateY(target1.getY()));
		Chunk chunk2 = system.getChunk(interpreter.translateX(target2.getX()),
                					   interpreter.translateY(target2.getY()));
		assertFalse("Chunk1 shouldn't contain target1", chunk1.contains(target1));
		assertFalse("Chunk2 shouldn't contain target2", chunk2.contains(target2));
		
		target2.setX(2000f);
		target2.update();
		chunk2 = system.getChunk(interpreter.translateX(target2.getX()),
				   interpreter.translateY(target2.getY()));
		system.update();
		assertFalse("World should not contain target2 anymore.", world.contains(target2));
		assertTrue("Chunk2 should contain target2", chunk2.contains(target2));
		
		// Move target 1 to the right to reload target 2
		player.setX(513f);
		player.update();
		system.update();
		assertTrue("World should contain target2 again.", world.contains(target2));
		assertFalse("Chunk2 should not contain target2", chunk2.contains(target2));
	}
	
	// ===========================================================
	// Mocks
	// ===========================================================

	class World implements ContentProvider {

		private List<ChunkTarget> targets;

		public World() {
			targets = new ArrayList<ChunkTarget>();
		}

		@Override
		public void add(ChunkTarget target) {
			if (!contains(target)) {
				targets.add(target);
			}
		}

		@Override
		public void remove(ChunkTarget target) {
			targets.remove(target);
		}

		@Override
		public Collection<ChunkTarget> getContent() {
			return targets;
		}

		public int size() {
			return targets.size();
		}

		public boolean contains(ChunkTarget target) {
			return targets.contains(target);
		}

	}
}
