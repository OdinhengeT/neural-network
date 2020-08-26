package xor_gate;

import networks.NeuronBasedNeuralNetwork;

/*
 * Contains all specific XOR-Gate related methods
 */
public class XOR {

	private static final double[][] STANDARD_INPUT = { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };
	private static final double[][] STANDARD_TARGET = createTargetData(STANDARD_INPUT);

	/**
	 * Returns one of each input permutation in an array.
	 * 
	 * @returns STANDARD_INPUT
	 */
	public static double[][] getStandardInput() {
		return STANDARD_INPUT;
	}

	/**
	 * Returns an array with the corresponding targets for the inputs in
	 * STANDARD_INPUT.
	 * 
	 * @returns STANDARD_TARGET
	 */
	public static double[][] getStandardTarget() {
		return STANDARD_TARGET;
	}

	/**
	 * Returns a String informing how well the Network handels the Standard testing
	 * set.
	 * 
	 * @param network the network to be tested
	 * @returns String with results of diagnostic
	 */
	public static String standardDiagnostic(NeuronBasedNeuralNetwork network) {
		StringBuilder sb = new StringBuilder();
		double error = 0;
		for (int i = 0; i < STANDARD_INPUT.length; i++) {
			double result = network.run(STANDARD_INPUT[i])[0];
			error += (result - STANDARD_TARGET[i][0]) * (result - STANDARD_TARGET[i][0]) / 2;
			sb.append("Input: " + STANDARD_INPUT[i][0] + " & " + STANDARD_INPUT[i][1] + ": " + result
					+ System.lineSeparator());
		}
		sb.append("Mean Error: " + error / 4);
		return sb.toString();
	}

	/**
	 * Returns an array of randomly created inputs of size length.
	 * 
	 * @param length the desired amount of inputs in the array
	 * 
	 * @returns an array with length different inputs (randomly created)
	 */
	public static double[][] createInputData(int length) {
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
	 * Returns an array of target values correspongding to the array of inputs given
	 * as argument.
	 * 
	 * @param input, a double[][] with inputs
	 * 
	 * @returns a double[][] with targets corresponding to the give input
	 */
	public static double[][] createTargetData(double[][] input) {
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
