public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;
	private int xxSign = 1;
	private int yySign = 1;

    //constructor to initialize integer and string	
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

    //constructor to initialize another object
	public Planet(Planet b) {
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}

    public double calcDistance(Planet b) {
    	return Math.sqrt(Math.pow((xxPos-b.xxPos),2)+Math.pow((yyPos-b.yyPos),2));
    }

    public double calcForceExertedBy(Planet b) {
    	return G*mass*b.mass/Math.pow(calcDistance(b),2);
    }

    public double calcForceExertedByX(Planet b) {
    	return calcForceExertedBy(b)*(b.xxPos - xxPos)/calcDistance(b);
    }

    public double calcForceExertedByY(Planet b) {
    	return calcForceExertedBy(b)*(b.yyPos - yyPos)/calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet[] b_array) {
    	double xxTotalForce = 0;
    	for (int i=0; i<b_array.length; i++) {
    		if (!equals(b_array[i])) {
    			xxTotalForce = xxTotalForce + calcForceExertedByX(b_array[i]);
    		}
    	}
    	return xxTotalForce;
    }

    public double calcNetForceExertedByY(Planet[] b_array) {
    	double yyTotalForce = 0;
    	for (int i=0; i<b_array.length; i++) {
    		if (!equals(b_array[i])) {
    			yyTotalForce = yyTotalForce + calcForceExertedByY(b_array[i]);
    		}
    	}
    	return yyTotalForce;
    }

    public void update(double dt, double fX, double fY) {
    	xxVel = xxVel + dt*fX/mass;
    	yyVel = yyVel + dt*fY/mass;
    	xxPos = xxPos + dt*xxVel;
    	yyPos = yyPos + dt*yyVel;
    }

    public void draw() {
    	StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
    }

}

/* 2022/12/11 The difficulties: cannot correctly initialize an object. 
	I tried to do:
    public Planet Planet_origin;
at the beginning of the Planet class, but this won't work because the Planet object 
has not been declared yet. So to initialize an object, i need to define every param
in that new object.
	Reference:https://www.javatpoint.com/java-constructor */

/* 2022/12/17 all good, did not run into major issues. 
just need to think about what's the static and non-static method.
cannot come up with the answer right away. */

/* 2022/12/23 don't know how to push files into remote repo ... */

/*** 2022/12/23 use website to upload to repo for now, there is internet issue
 auto grader message 1: API Checker
 Testing Planet.java
 The following fields should be made private:
  *  public int xxSign
  *  public int yySign
  *  static final double G

Testing NBody.java

One or more API checks failed.

don't know what is API checker
*/


