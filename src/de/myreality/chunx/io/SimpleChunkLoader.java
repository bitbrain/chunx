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
import java.io.ObjectInputStream;

import de.myreality.chunx.Chunk;

/**
 * Simple implementation of {@link ChunkLoader}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleChunkLoader extends SimpleFileConfiguration implements
		ChunkLoader {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private InputStreamProvider provider;
	
	private boolean loading;
	
	private FileNameConverter nameConverter;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleChunkLoader(InputStreamProvider provider) {
		this.provider = provider;
		nameConverter = new EncryptedFileNameConverter(new SimpleFileNameConverter());
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public void setProvider(InputStreamProvider provider) {
		this.provider = provider;
	}

	@Override
	public boolean isLoading() {
		return loading;
	}

	@Override
	public Chunk load(int indexX, int indexY) throws IOException {
		
		try {
			if (provider != null) {
				String fileName = getPath() + nameConverter.convert(indexX, indexY);
				ObjectInputStream in = new ObjectInputStream(provider.getInputStream(fileName));
				Chunk chunk = (Chunk) in.readObject();
				in.close();
				return chunk;
			} else {
				throw new IOException("InputStreamProvider is not set yet");
			}
		} catch (ClassNotFoundException e) {
			throw new IOException("Target file does not contain any chunk instance");
		} finally {
			loading = false;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
