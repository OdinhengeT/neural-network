package applications;

import networks.NeuralNetwork;

/**
 * This abstract class describes an Application or a usecase for a
 * NeuralNetwork, and its subclasses acts as a way to train a NeuralNetwork in a
 * there specified Application or usecase using the AppliedNetwork class.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see AppliedNetwork
 * @see NeuralNetwork
 * @see Mnist
 * @see XorGate
 *
 */
public abstract class Application {
	/**
	 * Keeps track of whereever training and evaluation data is loaded or ready to
	 * use.
	 */
	protected boolean isDataLoaded;

	/**
	 * Loads in the data using the same format that the specified network uses
	 * (float or double).
	 * 
	 * @param network the network the data should be loaded for
	 */
	public void load(NeuralNetwork network) {
		if (isDataLoaded)
			return;
		if (network.getBasicCalculationUnit() == 'f') {
			this.loadF();
			return;
		} else if (network.getBasicCalculationUnit() == 'd') {
			this.loadD();
			return;
		} else {
			throw new IllegalArgumentException("The NeuralNetwork uses no known basic calculation unit.");
		}
	}

	/**
	 * Runs a diagnostic to check how well the specified network handles the current
	 * application or usecase.
	 * 
	 * @param network network to run diagnostic on
	 * @return String containing the results of the diagnostic
	 */
	public String runDiagnostic(NeuralNetwork network) {
		if (network.getBasicCalculationUnit() == 'f') {
			return this.runDiagnosticF(network);
		} else if (network.getBasicCalculationUnit() == 'd') {
			return this.runDiagnosticD(network);
		} else {
			throw new IllegalArgumentException("The NeuralNetwork uses no known basic calculation unit.");
		}
	}

	/**
	 * Runs a quick diagnostic to check how well the specified network handles the
	 * current application or usecase. Might be more usefull for large Applications
	 * where a regular diagnostic may be slow.
	 * 
	 * @param network network to run diagnostic on
	 * @return String containing the results of the diagnostic
	 */
	public String runQuickDiagnostic(NeuralNetwork network) {
		if (network.getBasicCalculationUnit() == 'f') {
			return this.runQuickDiagnosticF(network);
		} else if (network.getBasicCalculationUnit() == 'd') {
			return this.runQuickDiagnosticD(network);
		} else {
			throw new IllegalArgumentException("The NeuralNetwork uses no known basic calculation unit.");
		}
	}

	/**
	 * Load as float
	 */
	public abstract void loadF();

	/**
	 * Load as double
	 */
	public abstract void loadD();

	/**
	 * Returns nbrInputs sets of trainingdata (float) first index is 0 for input and
	 * 1 for target
	 */
	public abstract float[][][] getTrainingDataF(int nbrInputs);

	/**
	 * Returns nbrInputs sets of trainingdata (double) first index is 0 for input
	 * and 1 for target
	 */
	public abstract double[][][] getTrainingDataD(int nbrInputs);

	/**
	 * Returns the entire set of evaluationdata (float) first index is 0 for input
	 * and 1 for target
	 */
	public abstract float[][][] getEvaluationDataF();

	/**
	 * Returns the entire set of evaluationdata (double) first index is 0 for input
	 * and 1 for target
	 */
	public abstract double[][][] getEvaluationDataD();

	/**
	 * Runs a diagnostic (float) to check how well the specified network handles the
	 * current application or usecase.
	 * 
	 * @param network network to run diagnostic on
	 * @return String containing the results of the diagnostic
	 */
	public abstract String runDiagnosticF(NeuralNetwork network);

	/**
	 * Runs a diagnostic (double) to check how well the specified network handles
	 * the current application or usecase.
	 * 
	 * @param network network to run diagnostic on
	 * @return String containing the results of the diagnostic
	 */
	public abstract String runDiagnosticD(NeuralNetwork network);

	/**
	 * Runs a quick diagnostic (float) to check how well the specified network
	 * handles the current application or usecase. Might be more usefull for large
	 * Applications where a regular diagnostic may be slow.
	 * 
	 * @param network network to run diagnostic on
	 * @return String containing the results of the diagnostic
	 */
	public abstract String runQuickDiagnosticF(NeuralNetwork network);

	/**
	 * Runs a quick diagnostic (double) to check how well the specified network
	 * handles the current application or usecase. Might be more usefull for large
	 * Applications where a regular diagnostic may be slow.
	 * 
	 * @param network network to run diagnostic on
	 * @return String containing the results of the diagnostic
	 */
	public abstract String runQuickDiagnosticD(NeuralNetwork network);

}
