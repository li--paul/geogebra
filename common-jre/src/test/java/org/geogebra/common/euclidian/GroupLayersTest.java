package org.geogebra.common.euclidian;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.geogebra.common.factories.AwtFactoryCommon;
import org.geogebra.common.jre.headless.AppCommon;
import org.geogebra.common.jre.headless.LocalizationCommon;
import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoPolygon;
import org.geogebra.common.kernel.geos.groups.Group;
import org.junit.Before;
import org.junit.Test;

public class GroupLayersTest {

	private LayerManager layerManager;
	private GeoElement[] geos;
	private Map<String, GeoElement> geoMap = new HashMap<>();
	private Construction construction;
	private Group group;
	private ArrayList<GeoElement> members;

	@Before
	public void setup() {
		AwtFactoryCommon factoryCommon = new AwtFactoryCommon();
		AppCommon app = new AppCommon(new LocalizationCommon(2), factoryCommon);
		construction = app.getKernel().getConstruction();
		layerManager = new LayerManager();
		geos = new GeoElement[5];
		for (int i = 0; i < 5; i++) {
			geos[i] = createDummyGeo(i);
			layerManager.addGeo(geos[i]);
		}
		members = new ArrayList<GeoElement>(
						Arrays.asList(
								geos[1], geos[2], geos[3])
		);
		group = new Group(members);
		construction.addGroupToGroupList(group);
	}

	private GeoElement createDummyGeo(int i) {
		GeoElement geo = new GeoPolygon(construction);
		geo.setLabel(i +"");
		return geo;
	}

	private GeoElement geoByLabel(String label) {
		return geoMap.get(label);
	}

	@Test
	public void testMoveToFront() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[1]);
		layerManager.moveToFront(selection);
		assertOrdering(0, 2, 3, 1, 4);
	}

	private void assertOrdering(int... newOrder) {
		LayerManagerTest.assertOrdering(geos, newOrder);
	}

	@Test
	public void testMoveFirstToFront() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[3]);
		layerManager.moveToFront(selection);
		assertOrderingUnchanged();
	}

	private void assertOrderingUnchanged() {
		assertOrdering(0, 1, 2, 3, 4);
	}

	@Test
	public void testMoveToBack() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[3]);
		layerManager.moveToBack(selection);
		assertOrdering(0, 3, 1, 2, 4);
	}

	@Test
	public void testMoveLastToBack() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[1]);
		layerManager.moveToBack(selection);
		assertOrderingUnchanged();
	}

	@Test
	public void testMoveForward() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[1]);
		layerManager.moveForward(selection);
		assertOrdering(0, 2, 1, 3, 4);
	}

	@Test
	public void testMoveForwardLastInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[3]);
		layerManager.moveForward(selection);
		assertOrderingUnchanged();
	}

	@Test
	public void testMoveBackwardInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[3]);
		layerManager.moveBackward(selection);
		assertOrdering(0, 1, 3, 2, 4);
	}

	@Test
	public void testMoveBackwardLastInGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[1]);
		layerManager.moveBackward(selection);
		assertOrderingUnchanged();
	}

	private void assertOrderingInGroup(Integer... orders) {
		fail();
	}


	@Test
	public void testMoveToFrontAndBack() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[1]);
		layerManager.moveToFront(selection);
		layerManager.moveToBack(selection);
		assertOrderingUnchanged();
	}

	@Test
	public void testMoveForwardAndBack() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[1]);
		layerManager.moveForward(selection);
		layerManager.moveForward(selection);
		layerManager.moveBackward(selection);
		assertOrdering(0, 2, 1, 3, 4);
	}

	@Test
	public void testMoveBackwardAndReset() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[3]);
		layerManager.moveBackward(selection);
		layerManager.moveBackward(selection);
		layerManager.moveForward(selection);
		layerManager.moveForward(selection);
		assertOrderingUnchanged();
	}

	@Test
	public void testMoveForwardThroughGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[0]);
		layerManager.moveForward(selection);
		assertOrdering(1, 2, 3, 0, 4);
	}

	@Test
	public void testMoveBackwardThroughGroup() {
		ArrayList<GeoElement> selection = new ArrayList<>();
		selection.add(geos[4]);
		layerManager.moveBackward(selection);
		assertOrdering(0, 4, 1, 2, 3);
	}
}
