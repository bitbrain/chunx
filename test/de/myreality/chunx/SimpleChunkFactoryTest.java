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

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link SimpleChunkFactory}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleChunkFactoryTest {

	// ===========================================================
		// Definitions
		// ===========================================================
		
		ChunkFactory factory;
		
		ChunkConfiguration configuration;

		// ===========================================================
		// Setup
		// ===========================================================

		@Before
		public void setUp() throws Exception {
			configuration = new SimpleChunkConfiguration();
			factory = new SimpleChunkFactory(configuration);
		}

		// ===========================================================
		// Test cases
		// ===========================================================
		
		@Test
		public void testCreateChunk() {
			
			final int INDEX_X = 10, INDEX_Y = 82;
			
			
			
			Chunk chunk = factory.createChunk(INDEX_X, INDEX_Y);
			
			assertTrue("Chunk should not be null", chunk != null);	
			assertTrue("Chunk x index doesn't match.", chunk.getIndexX() == INDEX_X);	
			assertTrue("Chunk y index doesn't match.", chunk.getIndexY() == INDEX_Y);	
			assertTrue("Chunk x position doesn't match.", chunk.getX() == INDEX_X * configuration.getChunkWidth());
			assertTrue("Chunk y position doesn't match.", chunk.getY() == INDEX_Y * configuration.getChunkHeight());
		}

}
