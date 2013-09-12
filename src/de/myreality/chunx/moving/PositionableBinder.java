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
package de.myreality.chunx.moving;

import de.myreality.chunx.util.Boundable;
import de.myreality.chunx.util.Positionable;
import de.myreality.chunx.util.Vector3f;
import de.myreality.chunx.util.VectorUtils;

/**
 * Binds a {@see Positionable} to a {@see Boundable}
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public class PositionableBinder {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	
	private Boundable boundable;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public PositionableBinder(Boundable boundable) {
		this.boundable = boundable;
	}

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	/**
	 * Binds the positionable to the given boundable. If the positionable
	 * is completely outside (last position is not in the boundable as well), then
	 * the center point of the boundable is considered. 
	 * 
	 * This method ensures that the target positionable will be inside of the boundable again.
	 * 
	 * @param positionable positionable to bind
	 * @param lastX last x position of the positionable
	 * @param lastY last y position of the positionable
	 */
	public void bind(Positionable positionable, float lastX, float lastY) {
		if (!boundable.contains(positionable.getX(), positionable.getY())) {
			
			Vector3f target = new Vector3f(positionable.getX(), positionable.getY());
			Vector3f center = new Vector3f(lastX, lastY);
			
			if (!boundable.contains(lastX, lastY)) {
				center.set((boundable.getRight() - boundable.getLeft()) / 2f, 
						   (boundable.getBottom() - boundable.getTop()) / 2f);
			}
			
			// Build corners of the boundable
			Vector3f topLeft = new Vector3f(boundable.getLeft(), boundable.getTop());
			Vector3f topRight = new Vector3f(boundable.getRight(), boundable.getTop());
			Vector3f bottomLeft = new Vector3f(boundable.getLeft(), boundable.getBottom());
			Vector3f bottomRight = new Vector3f(boundable.getRight(), boundable.getBottom());
			
			Vector3f newPosition = null;
			
			// Check border: TOP
			newPosition = VectorUtils.getIntersection(topLeft, topRight, center, target);
			
			// Check border: RIGHT
			if (newPosition == null) {				
				newPosition = VectorUtils.getIntersection(topRight, bottomRight, center, target);
			}
			
			// Check border: BOTTOM
			if (newPosition == null) {				
				newPosition = VectorUtils.getIntersection(bottomRight, bottomLeft, center, target);
			}
			
			// Check border: LEFT
			if (newPosition == null) {				
				newPosition = VectorUtils.getIntersection(bottomLeft, topLeft, center, target);
			}
			
			if (newPosition != null) {				
				positionable.setX(newPosition.x);
				positionable.setY(newPosition.y);
			} else {
				positionable.setX(lastX);
				positionable.setY(lastY);
			}
		}
	}

	// ===========================================================
	// Inner classes
	// ===========================================================
}
