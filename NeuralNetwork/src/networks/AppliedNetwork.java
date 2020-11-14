package networks;

import networks.NeuralNetwork;
import applications.Application;

public class AppliedNetwork {

	private NeuralNetwork network;
	private Application application;

	public AppliedNetwork(NeuralNetwork network, Application application) {
		// Fixa så nn alltid har rätt mängd input och output neurons för application
		this.network = network;
		this.application = application;
		application.load(network);
	}

	public NeuralNetwork getNeuralNetwork() {
		return this.network;
	}

	public Application getApplication() {
		return this.application;
	}

	public void train(int nbrTrainingSets) {
		if (network.getBasicCalculationUnit() == 'f') {
			float[][][] trainingData = application.getTrainingDataF(nbrTrainingSets);
			network.train(trainingData[0], trainingData[1]);
		} else if (network.getBasicCalculationUnit() == 'd') {
			double[][][] trainingData = application.getTrainingDataD(nbrTrainingSets);
			network.train(trainingData[0], trainingData[1]);
		} else {
			throw new IllegalArgumentException("The NeuralNetwork uses no known basic calculation unit.");
		}
	}

	public String runDiagnostic() {
		return application.runDiagnostic(network);
	}

	public String runQuickDiagnostic() {
		return application.runQuickDiagnostic(network);
	}

}
