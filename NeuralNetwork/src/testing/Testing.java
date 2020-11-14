package testing;

import networks.*;
import applications.xor_gate.*;
import applications.mnist.*;

public class Testing {

	public static void main(String[] args) {

		int[] layers = new int[] { 784, 16, 16, 10 };

		AppliedNetwork network = new AppliedNetwork(new NeuralNetworkD(layers, 0.8), new Mnist());

		for (int i = 0; i < 10000; i++) {
			network.train(50);
		}
		System.out.println(network.runQuickDiagnostic());

	}

}
