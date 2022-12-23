/**
 *  Tests Planet's update() method
 */
public class TestPlanet{

    /**
     *  Tests update.
     */
    public static void main(String[] args) {
        checkPlanet();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */
    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     *  prints out the pairwise force between the two bodies
     */
    private static void checkPlanet() {
        System.out.println("Printing out the pairwise force between Samh and Aegir...");

        Planet Samh = new Planet(1.0, 0.0, 0, 0, 10," ");
        Planet Aegir = new Planet(3.0, 3.0, 0, 0, 5," ");

        System.out.println("The pairwise force from Aegir to Samh is " + Samh.calcForceExertedBy(Aegir) + ", and the force form Samh to Aegir is " + Aegir.calcForceExertedBy(Samh));
    }
} 