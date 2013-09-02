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

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import de.myreality.chunx.Chunk;

/**
 * Saves chunks by considering the stream provider
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleChunkSaver extends SimpleFileConfiguration implements
		ChunkSaver {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private OutputStreamProvider provider;
	
	private boolean saving;
	
	private FileNameConverter nameConverter;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleChunkSaver(OutputStreamProvider provider) {
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
	public void setProvider(OutputStreamProvider provider) {
		if (provider != null) {
			this.provider = provider;
		}
	}

	@Override
	public boolean isSaving() {
		return saving;
	}

	@Override
	public void save(Chunk chunk) throws IOException {
		
		try {
			if (provider != null) {			
				saving = true;
				
				File file = new File(getPath());
				if (!file.exists()) {
					file.mkdirs();
				}
				String fileName = getPath() + nameConverter.convert(chunk.getIndexX(), chunk.getIndexY());
				ObjectOutputStream out = new ObjectOutputStream(provider.getOutputStream(fileName));	
				out.writeObject(chunk);		
				out.close();
			} else {
				throw new IOException("OutputStreamProvider is not set yet");
			}
		} finally {
			saving = false;
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
