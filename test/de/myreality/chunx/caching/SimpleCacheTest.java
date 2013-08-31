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

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link SimpleCache}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleCacheTest {

	// ===========================================================
	// Definitions
	// ===========================================================
	
	Cache cache;
	
	CachedChunkConfiguration configuration;
	
	int SIZE = 2;

	// ===========================================================
	// Setup
	// ===========================================================

	@Before
	public void setUp() throws Exception {
		configuration = new SimpleCachedChunkConfiguration();
		configuration.setCacheSize(SIZE);
		cache = new SimpleCache(configuration);
	}

	// ===========================================================
	// Test cases
	// ===========================================================
	
	@Test
	public void testContainsIndex() {
		// Valid indexes
		assertTrue("Index1 should be contained", cache.containsIndex(SIZE, -SIZE));
		assertTrue("Index2 should be contained", cache.containsIndex(SIZE, SIZE));
		assertTrue("Index3 should be contained", cache.containsIndex(-SIZE, -SIZE));
		assertTrue("Index4 should be contained", cache.containsIndex(-SIZE, SIZE));
		assertFalse("Index5 should not be contained", cache.containsIndex(SIZE + 1, -(SIZE + 1)));
		assertFalse("Index6 should not be contained", cache.containsIndex(-(SIZE + 1), -(SIZE + 1)));
		assertFalse("Index7 should not be contained", cache.containsIndex(SIZE + 1, SIZE + 1));
		assertFalse("Index8 should not be contained", cache.containsIndex(SIZE + 1, -(SIZE + 1)));
	}
	
	@Test
	public void testAlign() {
		
		final int NEW_X = 5;
		final int NEW_Y = 299;
		
		assertTrue("Index1 should be contained", cache.containsIndex(SIZE + NEW_X, -(SIZE + NEW_Y)));
		assertTrue("Index2 should be contained", cache.containsIndex(SIZE + NEW_X, SIZE + NEW_Y));
		assertTrue("Index3 should be contained", cache.containsIndex(-(SIZE + NEW_X), -(SIZE + NEW_Y)));
		assertTrue("Index4 should be contained", cache.containsIndex(-(SIZE + NEW_X), SIZE + NEW_Y));
	}
	
	@Test
	public void testOffset() {
		configuration.setOffset(1);
		assertTrue("Index1 should be contained", cache.containsIndex(SIZE, -SIZE));
		assertTrue("Index2 should be contained", cache.containsIndex(SIZE, SIZE));
		assertTrue("Index3 should be contained", cache.containsIndex(-SIZE, -SIZE));
		assertTrue("Index4 should be contained", cache.containsIndex(-SIZE, SIZE));
		assertTrue("Index5 should be contained", cache.containsIndex(SIZE + 1, -(SIZE + 1)));
		assertTrue("Index6 should be contained", cache.containsIndex(-(SIZE + 1), -(SIZE + 1)));
		assertTrue("Index7 should be contained", cache.containsIndex(SIZE + 1, SIZE + 1));
		assertTrue("Index8 should be contained", cache.containsIndex(SIZE + 1, -(SIZE + 1)));
	}
}
