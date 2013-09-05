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

/**
 * Utility class which contains basic vector logic
 * 
 * @author Miguel Gonzalez <miguel-gonzalez@gmx.de>
 * @since 1.0
 * @version 1.0
 */
public final class VectorUtils {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getters and Setters
	// ===========================================================

	// ===========================================================
	// Methods from Superclass
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	
	public static boolean doesIntersect(Vector3f pointA1, Vector3f pointA2, Vector3f pointB1, Vector3f pointB2) {
		return doesIntersect(pointA1.x, pointA1.y, pointA2.x, pointA2.y, pointB1.x, pointB1.y, pointB2.x, pointB2.y);
	}

	public static boolean doesIntersect(double l1x1, double l1y1, double l1x2, double l1y2, double l2x1, double l2y1, double l2x2,
          double l2y2) {
       double denom = ((l2y2 - l2y1) * (l1x2 - l1x1)) - ((l2x2 - l2x1) * (l1y2 - l1y1));
 
       if (denom == 0.0f) {
          return false;
       }
 
       double ua = (((l2x2 - l2x1) * (l1y1 - l2y1)) - ((l2y2 - l2y1) * (l1x1 - l2x1))) / denom;
       double ub = (((l1x2 - l1x1) * (l1y1 - l2y1)) - ((l1y2 - l1y1) * (l1x1 - l2x1))) / denom;
 
       return ((ua >= 0.0d) && (ua <= 1.0d) && (ub >= 0.0d) && (ub <= 1.0d));
     } 
	
	public static Vector3f getIntersection(Vector3f pointA1, Vector3f pointA2, Vector3f pointB1, Vector3f pointB2) {
		return getIntersection(pointA1.x, pointA1.y, pointA2.x, pointA2.y, pointB1.x, pointB1.y, pointB2.x, pointB2.y);
	}

    public static Vector3f getIntersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
       double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
       if (denom == 0.0) { // Lines are parallel.
          return null;
       }
       double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
       double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
       if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
          // Get the intersection point.
          return new Vector3f((float)(x1 + ua * (x2 - x1)), (float)(y1 + ua * (y2 - y1)));
       }

       return null;
    }

	// ===========================================================
	// Inner classes
	// ===========================================================
}
