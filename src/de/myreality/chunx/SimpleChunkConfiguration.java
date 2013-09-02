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
package de.myreality.chunx;

/**
 * Simple implementation of {@link ChunkConfiguration}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class SimpleChunkConfiguration implements ChunkConfiguration {

	// ===========================================================
	// Constants
	// ===========================================================

	public static final long serialVersionUID = 1L;

	// ===========================================================
	// Fields
	// ===========================================================

	private int chunkWidth, chunkHeight;
	
	private transient ContentProvider contentProvider;
	
	private transient ChunkTarget focused;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public SimpleChunkConfiguration(int chunkWidth, int chunkHeight,
			ContentProvider contentProvider, ChunkTarget focused) {
		super();
		this.chunkWidth = chunkWidth;
		this.chunkHeight = chunkHeight;
		this.contentProvider = contentProvider;
		this.focused = focused;
	}
	
	public SimpleChunkConfiguration(int chunkWidth, int chunkHeight,
			ContentProvider contentProvider) {
		this(chunkWidth, chunkHeight, contentProvider, null);
	}
	
	public SimpleChunkConfiguration(int chunkWidth, int chunkHeight) {
		this(chunkWidth, chunkHeight, null, null);
	}
	
	public SimpleChunkConfiguration() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, null, null);
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	@Override
	public int getChunkWidth() {
		return chunkWidth;
	}

	@Override
	public int getChunkHeight() {
		return chunkHeight;
	}

	@Override
	public void setChunkWidth(int width) {
		this.chunkWidth = width;
	}

	@Override
	public void setChunkHeight(int height) {
		this.chunkHeight = height;
	}

	@Override
	public void setChunkSize(int size) {
		setChunkWidth(size);
		setChunkHeight(size);
	}

	@Override
	public void setContentProvider(ContentProvider contentProvider) {
		if (contentProvider != null) {
			this.contentProvider = contentProvider;
		}
	}

	@Override
	public ContentProvider getContentProvider() {
		return contentProvider;
	}

	@Override
	public void setFocused(ChunkTarget target) {
		this.focused = target;
	}

	@Override
	public ChunkTarget getFocused() {
		return focused;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chunkHeight;
		result = prime * result + chunkWidth;
		result = prime * result
				+ ((contentProvider == null) ? 0 : contentProvider.hashCode());
		result = prime * result + ((focused == null) ? 0 : focused.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleChunkConfiguration other = (SimpleChunkConfiguration) obj;
		if (chunkHeight != other.chunkHeight)
			return false;
		if (chunkWidth != other.chunkWidth)
			return false;
		if (contentProvider == null) {
			if (other.contentProvider != null)
				return false;
		} else if (!contentProvider.equals(other.contentProvider))
			return false;
		if (focused == null) {
			if (other.focused != null)
				return false;
		} else if (!focused.equals(other.focused))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleChunkConfiguration [chunkWidth=" + chunkWidth
				+ ", chunkHeight=" + chunkHeight + ", contentProvider="
				+ contentProvider + ", focused=" + focused + "]";
	}
	
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
