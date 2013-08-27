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

import de.myreality.chunx.ChunkConfiguration;
import de.myreality.chunx.ConfigurationProvider;

/**
 * Translates positions between indexing and global world coordinates
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimplePositionInterpreter implements PositionInterpreter {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private ConfigurationProvider provider;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimplePositionInterpreter(ConfigurationProvider provider) {
		this.provider = provider;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public float translateIndexX(int indexX) {		
		ChunkConfiguration configuration = provider.getConfiguration();
		return translateIndex(indexX, configuration.getChunkWidth());
	}

	@Override
	public float translateIndexY(int indexY) {
		ChunkConfiguration configuration = provider.getConfiguration();
		return translateIndex(indexY, configuration.getChunkHeight());
	}

	@Override
	public int translateX(float x) {
		ChunkConfiguration configuration = provider.getConfiguration();
		return translatePosition(x, configuration.getChunkWidth());
	}

	@Override
	public int translateY(float y) {
		ChunkConfiguration configuration = provider.getConfiguration();
		return translatePosition(y, configuration.getChunkHeight());
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	private float translateIndex(int index, int size) {
		return index * size;
	}
	
	private int translatePosition(float value, int size) {
		return (int) Math.floor(value / (float)size);
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
