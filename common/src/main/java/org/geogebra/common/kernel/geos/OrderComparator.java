package org.geogebra.common.kernel.geos;

import java.util.Comparator;

/**
 * Compare geos by their drawing order.
 */
public class OrderComparator implements Comparator<GeoElement> {

	@Override
	public int compare(GeoElement o1, GeoElement o2) {
		return Integer.compare(o1.getOrdering(), o2.getOrdering());
	}
}
