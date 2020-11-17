package testing;

import networks.*;
import applications.xor_gate.*;

public class TestTwo {

	public static void main(String[] args) {
		
		int[] layers =  { 2, 3, 1};
		
		AppliedNetwork network = new AppliedNetwork(new NeuralNetworkF(layers, 0.8f), new XorGate());
		
		System.out.println(network.runDiagnostic());
		
		network.train(10000, 10);
		
		System.out.println(network.runDiagnostic());
		
		
	}

}
