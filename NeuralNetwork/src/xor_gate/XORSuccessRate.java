package xor_gate;

import networks.NeuronBasedNeuralNetwork;

/**
 * Tests the succsess rate of training a neural network to function as a
 * XOR-gate.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see NeuronBasedNeuralNetwork
 */
public class XORSuccessRate {

	public static void main(String[] args) {

		int[] size = { 2, 3, 1 };
		NeuronBasedNeuralNetwork network;
		double[][] input = XOR.getStandardInput();
		double[][] target = XOR.getStandardTarget();
		double successes = 0;

		for (int loop = 0; loop < 1000; loop++) {

			network = new NeuronBasedNeuralNetwork(size);

			for (int i = 0; i < 10000; i++) {
				double[][] inputT = XOR.createInputData(100);
				double[][] targetT = XOR.createTargetData(inputT);
				network.train(inputT, targetT);

				if (i == 2499 || i == 4999 || i == 7499 || i == 9999) {
					double error = 0;
					for (int j = 0; j < input.length; j++) {
						double result = network.run(input[j])[0];
						error += (result - target[j][0]) * (result - target[j][0]) / 8;
					}
					if (error < 0.01) {
						successes++;
						break;
					}
				}
			}

		}
		successes = successes / 10;
		System.out.println(successes + " %");

	}
}
