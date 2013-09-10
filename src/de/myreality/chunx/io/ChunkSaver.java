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

import java.io.IOException;

import de.myreality.chunx.Chunk;

/**
 * Saves chunks by considering an output provider
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public interface ChunkSaver extends FileConfiguration {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Sets a new input provider
	 * 
	 * @param provider new output provider
	 */
	void setProvider(OutputStreamProvider provider);
	
	/**
	 * Determines if the loader currently loads a chunk
	 * 
	 * @return
	 */
	boolean isSaving();
	
	/**
	 * Saves a new chunk 
	 * 
	 * @throws IOException is thrown when the chunk does not exists or the chunk file 
	 * is corrupted
	 */
	void save(Chunk chunk) throws IOException;
}
