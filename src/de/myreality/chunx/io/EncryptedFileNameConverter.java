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
package de.myreality.chunx.io;

import de.myreality.chunx.util.Crypter;

/**
 * Encrypts the file name of the chunk
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class EncryptedFileNameConverter implements FileNameConverter {

	// ===========================================================
	// Constants
	// ===========================================================
	
	public static final String FILE_PREFIX = "ch";
	
	public static final String DEFAULT_KEY = "z89a7s207s2938ft28736g782g";

	// ===========================================================
	// Fields
	// ===========================================================
	
	private FileNameConverter original;
	
	private Crypter crypter;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public EncryptedFileNameConverter(FileNameConverter original, String key) {
		this.original = original;
		crypter = new Crypter(key);
	}

	public EncryptedFileNameConverter(FileNameConverter original) {
		this(original, DEFAULT_KEY);
	}
	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public String convert(int indexX, int indexY) {		
		String base = original.convert(indexX, indexY);		
		return FILE_PREFIX + crypter.md5(base);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
