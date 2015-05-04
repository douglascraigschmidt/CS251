package mikera.vectorz;

import java.nio.DoubleBuffer;
import java.util.Arrays;

/**
 * Specialised 2D vector
 * 
 * @author Mike
 */
public final class Vector2  {
  private static final long serialVersionUID = -7815583836324137277L;

  public double x;
  public double y;
	
  public Vector2() {
    super();
  }
	
  public Vector2(double x, double y) {
    this.x=x;
    this.y=y;
  }
	
  public static Vector2 of(double x, double y) {
    return new Vector2(x,y);
  }
	
  public static Vector2 of(double... values) {
    if (values.length!=2) throw new IllegalArgumentException("Can't create Vector2 vector from: "+Arrays.toString(values));
    return new Vector2(values[0],values[1]);
  }
	
	
  public void add(Vector2 v) {
    x+=v.x;
    y+=v.y;
  }
	
  public void sub(Vector2 v) {
    x-=v.x;
    y-=v.y;
  }
	
  public void addMultiple(Vector2 v, double factor) {
    x+=v.x*factor;
    y+=v.y*factor;
  }
	
  public void addProduct(Vector2 a, Vector2 b) {
    x+=a.x*b.x;
    y+=a.y*b.y;
  }
	
  public void addProduct(Vector2 a, Vector2 b, double factor) {
    x+=a.x*b.x*factor;
    y+=a.y*b.y*factor;
  }
		


  public double dotProduct(double[] data, int offset) {
    return x*data[offset+0]+y*data[offset+1];
  }
	
  public double dotProduct(Vector2 a) {
    return x*a.x+y*a.y;
  }
	

  public void scaleAdd(double factor, double constant) {
    x=(x*factor)+constant;
    y=(y*factor)+constant;
  }
	

	
  public void scaleAdd(double factor, Vector2 constant) {
    x=(x*factor)+constant.x;
    y=(y*factor)+constant.y;
  }
	
  /**
   * Complex multiplication by another Vector2, treating an (x,y) vector as the complex value x+iy
   * @param a
   */
  public void complexMultiply(Vector2 a) {
    double nx=x*a.x-y*a.y;
    double ny=x*a.y+y*a.x;
    this.x=nx;
    this.y=ny;
  }
	
  public Vector2 complexConjugate() {
    return new Vector2(x,-y);
  }
	
  public Vector2 complexReciprocal() {
    double d=x*x+y*y;
    return new Vector2(x/d,-y/d);
  }
	
  public Vector2 complexNegation() {
    return new Vector2(-x,-y);
  }
	

  public void negate() {
    x=-x;
    y=-y;
  }


  public void add(double constant) {
    x=x+constant;
    y=y+constant;
  }
	
  public void add(double dx, double dy) {
    x=x+dx;
    y=y+dy;
  }
	

  public int length() {
    return 2;
  }
	

  public double elementSum() {
    return x+y;
  }
	

  public double elementProduct() {
    return x*y;
  }
	

  public double elementMax(){
    return Math.max(x, y);
  }
	

  public double elementMin(){
    return Math.min(x, y);
  }
	

  public double magnitudeSquared() {
    return (x*x)+(y*y);
  }
	

  public double magnitude() {
    return Math.sqrt(magnitudeSquared());
  }


  public double get(int i) {
    switch (i) {
    case 0: return x;
    case 1: return y;
    default: throw new IndexOutOfBoundsException();
    }
  }
	

  public double unsafeGet(int i) {
    return (i==0)?x:y;
  }
	

  public void getElements(double[] data, int offset) {
    data[offset]=x;
    data[offset+1]=y;
  }
	

  public void toDoubleBuffer(DoubleBuffer dest) {
    dest.put(x);
    dest.put(y);
  }
	

  public double[] toDoubleArray() {
    return new double[] {x,y};
  }
	

  public Vector2 toNormal() {
    double d=this.magnitude();
    return (d==0)?new Vector2():new Vector2(x/d,y/d);
  }


  public void set(int i, double value) {
    switch (i) {
    case 0: x=value; return;
    case 1: y=value; return;
    default: throw new IndexOutOfBoundsException();
    }
  }
	

  public void unsafeSet(int i, double value) {
    switch (i) {
    case 0: x=value; return;
    default: y=value; return;
    }
  }
	

  public void fill(double v) {
    x=v;
    y=v;
  }
	

  public void addAt(int i, double value) {
    switch (i) {
    case 0: x+=value; return;
    default: y+=value; return;
    }
  }
	
  /**
   * Rotates a 2D vector around the origin by a given angle
   * @param angle
   */
  public void rotateInPlace(int angle) {
    double ca=Math.cos(angle);
    double sa=Math.sin(angle);
    double nx=(x*ca)-(y*sa);
    double ny=(x*sa)+(y*ca);
    x=nx;
    y=ny;
  }
	
  public void setValues(double x, double y) {
    this.x=x;
    this.y=y;
  }
	

  public Vector2 clone() {
    return new Vector2(x,y);
  }


  public double getX() {
    return x;
  }
	

  public double getY() {
    return y;
  }
	
  public Vector2 exactClone() {
    return clone();
  }
	
  public boolean equals(Vector2 v) {
    return (x==v.x)&&(y==v.y);
  }

  public Vector2 scaleCopy(double d) {
    return multiplyCopy(d);
  }
  
  public void multiply(double d) {
    x *= d;
    y *= d;
  }

  public Vector2 multiplyCopy(double d) {
    Vector2 r= clone();
    r.multiply(d);
    return r;
  }
}
