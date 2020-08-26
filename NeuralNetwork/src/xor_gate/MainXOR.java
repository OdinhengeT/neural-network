package xor_gate;

import networks.NeuronBasedNeuralNetwork;

public class MainXOR {

	public static void main(String[] args) {

		int[] size = { 2, 3, 1 };
		double[][] input;
		NeuronBasedNeuralNetwork network = new NeuronBasedNeuralNetwork(size);

		System.out.println("==Without Training==");
		System.out.println(XOR.standardDiagnostic(network));
		System.out.println(network);

		for (int i = 0; i < 10000; i++) {
			input = XOR.createInputData(100);
			network.train(input, XOR.createTargetData(input));
		}
		
		System.out.println("==With Training==");
		System.out.println(XOR.standardDiagnostic(network));
		System.out.println(network);

	}

}
