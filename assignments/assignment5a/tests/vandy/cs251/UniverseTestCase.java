package vandy.cs251;

import static org.junit.Assert.*;
import org.junit.*;

import vandy.cs251.*;
import java.util.Iterator;
import mikera.vectorz.Vector2;

public class UniverseTestCase {

	@Test
	public final void testInstance() {
		//fail("Not yet implemented"); // TODO
		Universe obj = Universe.instance ();
		assertNotNull (obj);
		Universe obj2 = Universe.instance ();
		assertSame (obj, obj2);

		Universe.release ();
	}

	@Test
	public final void testRelease () {
		Universe obj = Universe.instance ();
		assertNotNull (obj);

		Universe.release ();

		Universe obj2 = Universe.instance ();
		assertNotNull (obj);
		Universe.release ();
		assertNotSame (obj, obj2);
	}

	@Test
	public final void testAddEntity() {
		//fail("Not yet implemented"); // TODO
		Universe.release ();
		assertNotNull (Universe.instance ());
		assertFalse (Universe.instance ().iterator ().hasNext ());
		Universe.instance ().addEntity (new Entity ("Sun",
							    1.0,
							    new Vector2 (1, 1),
							    new Vector2 (2, 2)
							    ));
		assertTrue (Universe.instance ().iterator ().hasNext ());

		Entity ent = Universe.instance ().iterator ().next ();

		assertTrue(ent.getName ().equals("Sun"));
		assertTrue(ent.getMass () == 1.0);
		assertTrue(ent.getPosition ().equals(new Vector2(1, 1)));
		assertTrue(ent.getVelocity ().equals(new Vector2(2, 2)));

		Universe.instance ().addEntity (new Entity ("Earth",
							    2.0,
							    new Vector2 (3, 3),
							    new Vector2 (4, 4)
							    ));

		Iterator<Entity> it = Universe.instance ().iterator ();

		assertTrue(it.hasNext ());
		ent = it.next ();

		assertTrue(ent.getName ().equals("Sun"));
		assertTrue(ent.getMass () == 1.0);
		assertTrue(ent.getPosition ().equals(new Vector2(1, 1)));
		assertTrue(ent.getVelocity ().equals(new Vector2(2, 2)));

		assertTrue(it.hasNext ());
		ent = it.next ();

		assertTrue(ent.getName ().equals("Earth"));
		assertTrue(ent.getMass () == 2.0);
		assertTrue(ent.getPosition ().equals(new Vector2(3, 3)));
		assertTrue(ent.getVelocity ().equals(new Vector2(4, 4)));
	}

	@Test
	public final void testRemoveEntity() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetEntity() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSpliterator() {
		//fail("Not yet implemented"); // TODO
	}

	// Compare two vectors, tolerating a certain amount of uncertainty.
	private final boolean compVect (Vector2 a, Vector2 b, double tolerance) {
		Vector2 diff = a.clone ();
		diff.sub (b);
		/*System.out.println ("[" + Double.toString(a.get(0)) + "," + Double.toString(a.get(1)) + "] vs [" +
				    Double.toString(b.get(0)) + "," + Double.toString(b.get(1)) + "]: " + "[" +
				    Double.toString(diff.get(0)) + "," + Double.toString(diff.get(1)) + "] " +
				    Double.toString (diff.magnitude ()));*/
		return diff.magnitude () < tolerance;
	}

	@Test
	public final void testStepSimulationIntertia() {
		Universe.release ();

		Universe.instance ().addEntity (new Entity("Sun",
							   0,
							   Vector2.of (0, 0),
							   Vector2.of (0, 0)));

		Vector2 velocity = Vector2.of (100, 0);
		Vector2 correctPos = Vector2.of (0, 0);
		Entity inertialObject = new Entity("Object1",
						   100,
						   correctPos.clone (),
						   velocity.clone ());

		Universe.instance ().addEntity (inertialObject);

		for (int i = 0; i < 50; ++i) {
			assertTrue (compVect(correctPos, inertialObject.getPosition (), 0.00001));
			Universe.instance ().stepSimulation(1);
			correctPos.add (velocity);
		}
	}

	private final double yearSeconds = 31554195.932106005998594489072144;
	/*
	@Test
	public final void testStepSimulationIntertia() {
		Universe.release ();

		Universe.instance ().addEntity (new Entity("Sun",
							   1.98892e30,
							   Vector2.of (0, 0),
							   Vector2.of (0, 0)));


		Universe.instance ().addEntity (new Entity("Earth",
							   5.9742e24,
							   Vector2.of (149597870700, 0),
							   Vector2.of (0, 29788.4676)));


		for (int i = 0; i < yearSeconds; ++i) {

		}
	}
	*/
	@Test
	public final void testGetForce() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCalcVelocityDelta() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCalcPositionDelta() {
		//fail("Not yet implemented"); // TODO
	}

}
