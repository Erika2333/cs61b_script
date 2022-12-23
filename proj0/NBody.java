
/******************************************************************************
 *  Compilation:  javac NBody.java
 *  Execution:    java NBody 0 0 ./data/planets.txt
 *  Dependencies: StdDraw.java
 ******************************************************************************/


public class NBody {

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] bodies = readPlanets(filename);
		double radUniverse = readRadius(filename);

		StdDraw.setScale(-radUniverse, radUniverse);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (int i = 0; i < bodies.length; i++)  {
			bodies[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		for (double curTime = 0; curTime <= T;curTime = curTime + dt) {
			double[] xForce = new double[5];
			double[] yForce = new double[5];

			for (int j = 0; j < bodies.length; j++) {
				xForce[j] = bodies[j].calcNetForceExertedByX(bodies);
				yForce[j] = bodies[j].calcNetForceExertedByY(bodies);
			}

			for (int m = 0; m < bodies.length; m++) {
				bodies[m].update(dt,xForce[m],yForce[m]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (int i = 0; i < bodies.length; i++)  {
				bodies[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(1);
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radUniverse);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
               			bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
               			bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}

 	}

	public static double readRadius(String file_name) {
		In in = new In(file_name);
        double numBody = in.readInt();
        return in.readDouble();
	}

	public static Planet[] readPlanets(String file_name) {

		Planet[] bodies = new Planet[5];

		In in = new In(file_name);
		int numBody = in.readInt();
		double radUniverse = in.readDouble();        

		for (int j = 0; j<5; j++) {

            double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			bodies[j] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}

		return bodies;
	}
}

/* 2022/12/19
 * Only method can be categarized as static or non-static. not class!!! 
 * Still don't remember how to initial a new Planet OBJECT
 * Got this error while working on it:
   NBody.java:25: error: ']' expected
 			Body bodies[j] = new Body(xxPos,yyPos,xxVel,yyVel,mass,image);
 			            ^
   NBody.java:25: error: ';' expected
 			Body bodies[j] = new Body(xxPos,yyPos,xxVel,yyVel,mass,image);
 * for instead, i should do 
            Body[] bodies = new Body[5];
 * and then assign parameters to each element in bodies (just like the correct code above) */