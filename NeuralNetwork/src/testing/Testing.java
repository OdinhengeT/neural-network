package testing;

import networks.*;
import applications.mnist.*;

public class Testing {

	public static void main(String[] args) {

		int[] layers = new int[] { 784, 16, 16, 10 };

		AppliedNetwork network = new AppliedNetwork(new NeuralNetworkD(layers, 0.8), new Mnist());

		network.train(10000, 50);

		System.out.println(network.runQuickDiagnostic());

	}

}
