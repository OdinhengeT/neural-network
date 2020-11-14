package applications.xor_gate;

public abstract class XorGateDataGenerator {

	/**
	 * Returns an array of randomly created inputs of size length.
	 * 
	 * @param length the desired amount of inputs in the array
	 * 
	 * @returns an array with length different inputs (randomly created)
	 */
	public static float[][] generateInputF(int length) {
		float[][] input = new float[length][2];
		double r;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < 2; j++) {
				r = Math.random();
				if (r < 0.5) {
					input[i][j] = 0.0f;
				} else {
					input[i][j] = 1.0f;
				}
			}
		}
		return input;
	}

	/**
	 * Returns an array of target values corresponding to the array of inputs given
	 * as argument.
	 * 
	 * @param input, a float[][] with inputs
	 * 
	 * @returns a float[][] with targets corresponding to the give input
	 */
	public static float[][] generateTargetF(float[][] input) {
		float[][] target = new float[input.length][1];
		for (int i = 0; i < input.length; i++) {
			if (input[i][0] == input[i][1]) {
				target[i][0] = 0.0f;
			} else {
				target[i][0] = 1.0f;
			}
		}
		return target;
	}
	
	/**
	 * Returns an array of randomly created inputs of size length.
	 * 
	 * @param length the desired amount of inputs in the array
	 * 
	 * @returns an array with length different inputs (randomly created)
	 */
	public static double[][] generateInputD(int length) {
		double[][] input = new double[length][2];
		double r;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < 2; j++) {
				r = Math.random();
				if (r < 0.5) {
					input[i][j] = 0.0;
				} else {
					input[i][j] = 1.0;
				}
			}
		}
		return input;
	}

	/**
	 * Returns an array of target values corresponding to the array of inputs given
	 * as argument.
	 * 
	 * @param input, a double[][] with inputs
	 * 
	 * @returns a double[][] with targets corresponding to the give input
	 */
	public static double[][] generateTargetD(double[][] input) {
		double[][] target = new double[input.length][1];
		for (int i = 0; i < input.length; i++) {
			if (input[i][0] == input[i][1]) {
				target[i][0] = 0.0;
			} else {
				target[i][0] = 1.0;
			}
		}
		return target;
	}
	
}
