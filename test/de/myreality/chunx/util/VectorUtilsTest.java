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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link VectorUtils}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class VectorUtilsTest {
	
	// ===========================================================
	// Definitions
	// ===========================================================
	
	Vector3f p1, p2, p3, p4, p5, p6;
	
	// ===========================================================
	// Setup
	// ===========================================================

	@Before
	public void setUp() throws Exception {
		p1 = new Vector3f(4, 1);
		p2 = new Vector3f(9, 9);
		p3 = new Vector3f(3, 10);
		p4 = new Vector3f(7, 2);
		p5 = new Vector3f(10, 6);
		p6 = new Vector3f(8, 1);
	}
	
	// ===========================================================
	// Test cases
	// ===========================================================
	
	@Test
	public void testGetIntersection() {
		Vector3f intersection = VectorUtils.getIntersection(p1, p2, p3, p4);
		assertTrue("x of intersection has to be 6 instead of " + intersection.x, Math.round(intersection.x)  == 6);
		assertTrue("y of intersection has to be 4 instead of " + intersection.y, Math.round(intersection.y)  == 4);
	}
	
	
}
