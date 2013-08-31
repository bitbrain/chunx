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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link SimpleChunk}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleChunkTest {

	// ===========================================================
	// Definitions
	// ===========================================================
	
	Chunk chunk;
	
	ChunkConfiguration configuration;

	final int INDEX_X = 10;
	
	final int INDEX_Y = 100;

	// ===========================================================
	// Setup
	// ===========================================================

	@Before
	public void setUp() throws Exception {
		configuration = new SimpleChunkConfiguration();
		chunk = new SimpleChunk(INDEX_X, INDEX_Y, configuration);
	}

	// ===========================================================
	// Test cases
	// ===========================================================
	
	@Test
	public void testGetX() {
		final float expected = configuration.getChunkWidth() * INDEX_X;
		assertTrue("X has to be " + expected, chunk.getX() == expected);
	}
	
	@Test
	public void testGetY() {
		final float expected = configuration.getChunkHeight() * INDEX_Y;
		assertTrue("Y has to be " + expected, chunk.getY() == expected);
	}
	
	@Test
	public void testGetWidth() {
		final float expected = configuration.getChunkWidth();
		assertTrue("Width has to be " + expected, chunk.getWidth() == expected);
	}
	
	@Test
	public void testGetHeight() {
		final float expected = configuration.getChunkHeight();
		assertTrue("Height has to be " + expected, chunk.getHeight() == expected);
	}
	
	@Test
	public void testGetIndexX() {
		assertTrue("IndexX has to be " + INDEX_X, chunk.getIndexX() == INDEX_X);
	}
	
	@Test
	public void testGetIndexY() {
		assertTrue("IndexY has to be " + INDEX_Y, chunk.getIndexY() == INDEX_Y);
	}
	
	@Test
	public void testRetrieve() {
		MockChunkTarget ct1 = new MockChunkTarget();
		MockChunkTarget ct2 = new MockChunkTarget();
		
		chunk.add(ct1);
		chunk.add(ct2);
		
		ChunkTarget result1 = chunk.retrieve();
		
	}
	
	class MockChunkTarget implements ChunkTarget {

		private static final long serialVersionUID = 1L;

		@Override
		public float getX() {
			return 0;
		}

		@Override
		public float getY() {
			return 0;
		}
		
	}
}
