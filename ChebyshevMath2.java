public class ChebyshevMath2 {

    private static final float BF_SIN_TO_COS;
    private static final float[] coeffs;
    private static final float PI;

    static {
        PI = (float)Math.PI;
        BF_SIN_TO_COS = PI * 0.5f;
        coeffs = new float[]{-1.4495300e-04f, 7.9559227e-03f, -1.6565645e-01f, 9.9926287e-01f};
    }

    public static float sin(float radians) {
        radians = -(radians % (2f*PI) - PI);

        float x2 = radians * radians;

        return radians
                * (coeffs[3] + x2
                * (coeffs[2] + x2
                * (coeffs[1] + x2
                *  coeffs[0])));
    }

    public static float cos(float radians) {
        return sin(radians + BF_SIN_TO_COS);
    }

	public static void main(String[] args) {
		int rounds = 1000000;
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
