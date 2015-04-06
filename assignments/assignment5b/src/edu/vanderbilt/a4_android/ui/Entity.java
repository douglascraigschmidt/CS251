package edu.vanderbilt.a4_android.ui;

import mikera.vectorz.Vector2;

public interface Entity {
	public Vector2 getVelocity ();
	
	public Vector2 getPosition ();

	public String getName ();
	
	public double getMass ();

	public interface Memento {
		public Memento setPosition(Vector2 pos);

		public Memento setVelocity(Vector2 vel);

		public void apply();
	}

	/** Construct a new memento object. */
	public Memento update();

	/** Accept the provided visitor */
	public void accept(EntityVisitor visitor);
}
