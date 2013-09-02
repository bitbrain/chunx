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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ChunkHandler;
import de.myreality.chunx.ChunkTarget;
import de.myreality.chunx.ContentProvider;
import de.myreality.chunx.moving.MoveableChunkTarget;
import de.myreality.chunx.moving.MovementDetector;
import de.myreality.chunx.moving.SimpleMovementDetector;

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
	
	CachedChunkSystem cachedChunkSystem;
	
	CachedChunkConfiguration configuration;
	
	MockTarget player, target1, target2;
	
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
		
		cachedChunkSystem = new SimpleCachedChunkSystem(configuration);		
		cachedChunkSystem.start();
	}
	
	

	// ===========================================================
	// Test cases
	// ===========================================================
	
	@Test
	public void testGetTotalChunkCount() {
		int expected = (int) Math.pow((SIZE * 2 + 1) + 2, 2);
		assertTrue("Chunk count should be " + expected + " instead of " + cachedChunkSystem.getTotalChunkCount(), cachedChunkSystem.getTotalChunkCount() == expected);
	}
	
	@Test
	public void testUpdate() {
		System.out.println(cachedChunkSystem.getCurrentChunkCount());
		cachedChunkSystem.update();
		System.out.println(cachedChunkSystem.getCurrentChunkCount());
	}
	
	// ===========================================================
	// Mocks
	// ===========================================================
	
	class MockTarget implements MoveableChunkTarget {
		
		private static final long serialVersionUID = 1L;
		
		private float x, y;
		
		private MovementDetector movementDetector;
		
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
		
	}
	
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
