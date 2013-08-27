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

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ConfigurationProvider;
import de.myreality.chunx.SimpleChunkConfiguration;
import de.myreality.chunx.SimpleConfigurationProvider;

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
	
	// ===========================================================
	// Setup
	// ===========================================================

	@Before
	public void setUp() throws Exception {
		ChunkConfiguration config = new SimpleChunkConfiguration();
		ConfigurationProvider provider = new SimpleConfigurationProvider(config);		
		interpreter = new SimplePositionInterpreter(provider);
	}
	
	// ===========================================================
	// Test cases
	// ===========================================================
	
	@Test
	public void testTranslateIndexX() {
		fail("Not yet implemented");
	}

	@Test
	public void testTranslateIndexY() {
		fail("Not yet implemented");
	}

	@Test
	public void testTranslateX() {
		fail("Not yet implemented");
	}

	@Test
	public void testTranslateY() {
		fail("Not yet implemented");
	}
}
