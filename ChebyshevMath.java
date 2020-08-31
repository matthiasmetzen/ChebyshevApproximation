public class ChebyshevMath {

    private static final float BF_SIN_TO_COS;

    static {
        BF_SIN_TO_COS = (float)(Math.PI * 0.5f);
    }

    public static float sin(float radians) {
        // http://mooooo.ooo/chebyshev-sine-approximation/
        float x2 = radians*radians;

        float p11 = .13291342E-9f;
        float p9  = p11*x2 - .23317787E-7f;
        float p7  = p9*x2  + .25222919E-5f;
        float p5  = p7*x2  - .17350505E-3f;
        float p3  = p5*x2  + 0.0066208798f;
        float p1  = p3*x2  - 0.10132118f;

        return (x2 - 9.8696052419868325661544629284f) * p1 * radians;
        //return (x2 - 9.869605241f) * p1 * radians;
		//return (x2 - 9.86960440109f) * p1 * radians;
		//return (x2 - 9.8696041434f) * p1 * radians;
    }

    public static float cos(float radians) {
        return sin(radians + BF_SIN_TO_COS);
    }

	public static void main(String[] args) {
		int rounds = 10000;
		double sum = 0;
		float max = 0;

		for(int i = 0; i<rounds; i++) {
			double rnd = Math.random()*1000;
			float radians = (float)Math.toRadians(rnd % 360);
			
			float difference = Math.abs(sin(radians) - (float)Math.sin(radians));
			sum += difference;
			//System.out.println("Difference: " + difference);

			if(difference > max)
				max = difference;
		}

		System.out.println("Avg. Difference: " + (sum/rounds) + ", Max: " + max);
	}
}
