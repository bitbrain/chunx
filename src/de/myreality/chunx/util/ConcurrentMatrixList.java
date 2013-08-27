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

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concurrent, thread-save implementation of {@link MatrixList}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class ConcurrentMatrixList<Type extends Indexable> implements MatrixList<Type> {	

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private int elementSize;

	private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Type>> chunks;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public ConcurrentMatrixList() {
		elementSize = 0;
		chunks = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Type>>();
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================
	
	@Override
	public boolean add(Type element) {
		if (chunks.containsKey(element.getIndexX())) {
			ConcurrentHashMap<Integer, Type> yMap = chunks.get(element
					.getIndexX());
			if (!yMap.containsKey(element.getIndexY())) {
				yMap.put(element.getIndexY(), element);
				elementSize++;
				return true;
			} else {
				return false;
			}
		} else {
			ConcurrentHashMap<Integer, Type> yChunkMap = new ConcurrentHashMap<Integer, Type>();
			yChunkMap.put(element.getIndexY(), element);
			chunks.put(element.getIndexX(), yChunkMap);
			elementSize++;
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends Type> objects) {
		
		boolean changed = false;
		
		for (Type object : objects) {
			if (!changed) {
				changed = add(object);
			}
		}
		
		return changed;
	}

	@Override
	public void clear() {
		chunks.clear();
	}

	@Override
	public boolean contains(Object object) {
		if (object instanceof Indexable) {
			Indexable indexable = (Indexable)object;
			return contains(indexable.getIndexX(), indexable.getIndexY());
		} else {
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> objects) {
		for (Type elem : this) {
			if (!objects.contains(elem)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean isEmpty() {
		return chunks.isEmpty();
	}

	@Override
	public Iterator<Type> iterator() {
		return new MatrixIterator<Type>(chunks.values().iterator());
	}

	@Override
	public boolean remove(Object object) {
		if (object instanceof Indexable) {
			Indexable indexable = (Indexable)object;
			return remove(indexable.getIndexX(), indexable.getIndexY());
		} else {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> objects) {
		boolean changed = false;
		
		for (Object object : objects) {
			if (!changed) {
				changed = remove(object);
			}
		}
		
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> objects) {
		// TODO: Implement retainAll
		return false;
	}

	@Override
	public int size() {
		return elementSize;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] objects) {
		if (objects.length != size()) {
			objects = (T[]) new Object[size()];
		}
		
		int index = 0;
		
		for (Type type : this) {
			objects[index++] = (T) type;
		}
		
		return objects;
	}

	@Override
	public boolean remove(int indexX, int indexY) {
		ConcurrentHashMap<Integer, Type> yChunkMap = chunks.get(indexX);

		if (yChunkMap != null) {
			yChunkMap.remove(indexY);
			// X axis
			if (yChunkMap.isEmpty()) {
				chunks.remove(indexX);
			} else {
				return false;
			}
			elementSize--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public MatrixList<Type> copy() {
		MatrixList<Type> copyList = new ConcurrentMatrixList<Type>();
		for (Type element : this) {
			copyList.add(element);
		}
		return copyList;
	}

	@Override
	public boolean contains(int indexX, int indexY) {
		return get(indexX, indexY) != null;
	}

	@Override
	public Type get(int indexX, int indexY) {
		ConcurrentHashMap<Integer, Type> yChunkMap = chunks.get(indexX);

		if (yChunkMap != null) {
			Type element = yChunkMap.get(indexY);

			if (element != null) {
				return element;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public void set(MatrixList<Type> matrixList) {
		this.elementSize = matrixList.size();
		clear();
		for (Type elem : matrixList) {
			add(elem);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner classes
	// ===========================================================
}
