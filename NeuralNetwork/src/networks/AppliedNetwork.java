package networks;

import networks.NeuralNetwork;
import applications.Application;

/**
 * This class is used to simplify the usage of a NeuralNetwork by combineing it
 * with an Application describing how the NeuralNetwork should be trained ect.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetwork
 * @see Application
 */
public class AppliedNetwork {

	/**
	 * The NeuralNetwork to use in this application/usecase.
	 */
	private NeuralNetwork network;

	/**
	 * The Application or usecase in which to use the NeuralNetwork.
	 */
	private Application application;

	/**
	 * Constructs an AppliedNetwork.
	 * 
	 * @param network     the network to use in this application/usecase
	 * @param application the application/usecase in which to use the NeuralNetwork
	 */
	public AppliedNetwork(NeuralNetwork network, Application application) {
		// Fixa så nn alltid har rätt mängd input och output neurons för application
		this.network = network;
		this.application = application;
		application.load(network);
	}

	/**
	 * Returns the NeuralNetwork.
	 * 
	 * @return this.network
	 */
	public NeuralNetwork getNeuralNetwork() {
		return this.network;
	}

	/**
	 * Returns the Application.
	 * 
	 * @return this.application
	 */
	public Application getApplication() {
		return this.application;
	}

	/**
	 * Trains the network on the problem defined by the application (usecase), and
	 * does so nbrRounds time with nbrSetsPerRound sets of training data each round.
	 * 
	 * @param nbrTrainingSets number of sets of trainig data to go through before
	 *                        updating the weights & biases
	 */
	public void train(int nbrRounds, int nbrSetsPerRound) {
		if (network.getBasicCalculationUnit() == 'f') {
			for (int round = 0; round < nbrRounds; round++) {
				float[][][] trainingData = application.getTrainingDataF(nbrSetsPerRound);
				network.train(trainingData[0], trainingData[1]);
			}
		} else if (network.getBasicCalculationUnit() == 'd') {
			for (int round = 0; round < nbrRounds; round++) {
				double[][][] trainingData = application.getTrainingDataD(nbrSetsPerRound);
				network.train(trainingData[0], trainingData[1]);
			}
		} else {
			throw new IllegalArgumentException("The NeuralNetwork uses no known basic calculation unit.");
		}
	}

	/**
	 * Runs a diagnostic on the network to check on its progress, defined by the
	 * Application.
	 * 
	 * @return String containing information on how the NeuralNetwork is doing
	 */
	public String runDiagnostic() {
		return application.runDiagnostic(network);
	}

	/**
	 * Runs a quick diagnostic on the network to check on its progress, defined by
	 * the Application. Might be more usefull for large Applications where a regular
	 * diagnostic may be slow.
	 * 
	 * @return String containing information on how the NeuralNetwork is doing
	 */
	public String runQuickDiagnostic() {
		return application.runQuickDiagnostic(network);
	}

}
