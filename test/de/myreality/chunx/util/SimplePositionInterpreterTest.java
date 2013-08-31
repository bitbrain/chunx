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

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.SimpleChunkConfiguration;

/**
 * Test case for {@link SimplePositionInterpreter}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimplePositionInterpreterTest {
	
	// ===========================================================
	// Definitions
	// ===========================================================
	
	PositionInterpreter interpreter;
	
	ChunkConfiguration configuration;
	
	// ===========================================================
	// Setup
	// ===========================================================

	@Before
	public void setUp() throws Exception {
		configuration = new SimpleChunkConfiguration();	
		interpreter = new SimplePositionInterpreter(configuration);
	}
	
	// ===========================================================
	// Test cases
	// ===========================================================
	
	@Test
	public void testTranslateIndexX() {
		assertTrue("IndexX has not been interpreted correctly here", interpreter.translateIndexX(10) == 10 * configuration.getChunkWidth());
	}

	@Test
	public void testTranslateIndexY() {
		assertTrue("IndexY has not been interpreted correctly here", interpreter.translateIndexY(10) == 10 * configuration.getChunkHeight());
	}

	@Test
	public void testTranslateX() {
		assertTrue("x position has not been interpreted correctly here", interpreter.translateX(129.38f) == 0);
		assertTrue("x position has not been interpreted correctly here", interpreter.translateX(-2f) == -1);
		assertTrue("x position has not been interpreted correctly here", interpreter.translateX(-configuration.getChunkWidth() - 1) == -2);
	}

	@Test
	public void testTranslateY() {
		assertTrue("y position has not been interpreted correctly here", interpreter.translateY(129.38f) == 0);
		assertTrue("y position has not been interpreted correctly here", interpreter.translateY(-2f) == -1);
		assertTrue("y position has not been interpreted correctly here", interpreter.translateY(-configuration.getChunkHeight() - 1) == -2);
	}
}
