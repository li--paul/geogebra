package org.geogebra.common.euclidian;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.groups.Group;

public class LayersForGroup {
	public static final Comparator<GeoElement> orderComparator = new Comparator<GeoElement>() {
		@Override
		public int compare(GeoElement geo1, GeoElement geo2) {
			Integer ordering1 = geo1.getOrdering();
			return ordering1.compareTo(geo2.getOrdering());
		}
	};

	private final List<GeoElement> drawingOrder;

	public LayersForGroup(List<GeoElement> drawingOrder) {
		this.drawingOrder = drawingOrder;
	}

	/**
	 * Moves geo to the top of drawables
	 * within its group.
	 *
	 * @param geo to move front.
	 */
	void moveToFront(GeoElement geo) {
		moveTo(geo, lastIndexOf(geo.getParentGroup()));
	}

	private void moveTo(GeoElement geo, int index) {
		int srcIdx = indexOf(geo);
		if (srcIdx != index) {
			drawingOrder.remove(geo);
			drawingOrder.add(index, geo);
		}
	}

	private int indexOf(GeoElement geo) {
		return drawingOrder.indexOf(geo);
	}

	/**
	 * Moves geo to the bottom of drawables
	 * within its group.
	 *
	 * @param geo to move back.
	 */
	void moveToBack(GeoElement geo) {
		moveTo(geo, firstIndexOf(geo.getParentGroup()));
	}

	private int firstIndexOf(Group group) {
		sortOrder();
		return drawingOrder.indexOf(group.getMinByOrder());
	}

	/**
	 * Moves geo one step forward in the drawables
	 * within its group.
	 *
	 * @param geo to move forward.
	 */
	void moveForward(GeoElement geo) {
		int index = indexOf(geo);
		if (index < lastIndexOf(geo.getParentGroup())) {
			Collections.swap(drawingOrder, index, index + 1);
		}
	}

	private int lastIndexOf(Group group) {
		sortOrder();
		return drawingOrder.indexOf(group.getMaxByOrder());
	}

	private void sortOrder() {
		Collections.sort(drawingOrder, orderComparator);
	}

	/**
	 * Moves geo one step backward in the drawables
	 * within its group.
	 *
	 * @param geo to move backward.
	 */
	void moveBackward(GeoElement geo) {
		int index = indexOf(geo);
		if (index > firstIndexOf(geo.getParentGroup())) {
			Collections.swap(drawingOrder, index, index - 1);
		}
	}
}
