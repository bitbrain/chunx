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
/**
 * 
 */
package de.myreality.chunx.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for {@link MatrixList}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class MatrixListTest {
	
	// ===========================================================
	// Definitions
	// ===========================================================
	
	MatrixList<Indexable> matrixList;
	
	Indexable indexable;
	
	final int X = 10, Y = 10;
	
	// ===========================================================
	// Setup
	// ===========================================================

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		matrixList = new ConcurrentMatrixList<Indexable>();
		indexable = new Indexable() {

			@Override
			public int getIndexX() {
				return X;
			}

			@Override
			public int getIndexY() {
				return Y;
			}
			
		};
	}
	
	// ===========================================================
	// Test cases
	// ===========================================================

	/**
	 * Test method for {@link de.myreality.chunx.util.MatrixList#remove(int, int)}.
	 */
	@Test
	public void testRemoveIntInt() {
		matrixList.add(indexable);
		assertTrue("Element can't be removed.", matrixList.remove(X, Y));
	}

	/**
	 * Test method for {@link de.myreality.chunx.util.MatrixList#copy()}.
	 */
	@Test
	public void testCopy() {
		matrixList.add(indexable);
		MatrixList<Indexable> copy = matrixList.copy();
		
		assertTrue("The indexable must be part of the copy", copy.contains(indexable));
		assertTrue("The index must be part of the copy", copy.contains(X,  Y));
		
		matrixList.clear();
		
		assertFalse("The copy shouldn't be affected by its origin.", copy.isEmpty());
	}

	/**
	 * Test method for {@link de.myreality.chunx.util.MatrixList#contains(int, int)}.
	 */
	@Test
	public void testContainsIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.myreality.chunx.util.MatrixList#get(int, int)}.
	 */
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.myreality.chunx.util.MatrixList#set(de.myreality.chunx.util.MatrixList)}.
	 */
	@Test
	public void testSet() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#size()}.
	 */
	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#contains(java.lang.Object)}.
	 */
	@Test
	public void testContainsObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#toArray()}.
	 */
	@Test
	public void testToArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#toArray(T[])}.
	 */
	@Test
	public void testToArrayTArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#add(java.lang.Object)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#containsAll(java.util.Collection)}.
	 */
	@Test
	public void testContainsAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#addAll(java.util.Collection)}.
	 */
	@Test
	public void testAddAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#removeAll(java.util.Collection)}.
	 */
	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#retainAll(java.util.Collection)}.
	 */
	@Test
	public void testRetainAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#clear()}.
	 */
	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.util.Collection#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}
}
