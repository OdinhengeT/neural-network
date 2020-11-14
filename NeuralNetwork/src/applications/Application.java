package applications;

import networks.NeuralNetwork;

public abstract class Application {

	protected boolean isDataLoaded;

	public void load(NeuralNetwork network) {
		if (isDataLoaded) return;
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

	public String runDiagnostic(NeuralNetwork network) {
		if (network.getBasicCalculationUnit() == 'f') {
			return this.runDiagnosticF(network);
		} else if (network.getBasicCalculationUnit() == 'd') {
			return this.runDiagnosticD(network);
		} else {
			throw new IllegalArgumentException("The NeuralNetwork uses no known basic calculation unit.");
		}
	}

	public String runQuickDiagnostic(NeuralNetwork network) {
		if (network.getBasicCalculationUnit() == 'f') {
			return this.runQuickDiagnosticF(network);
		} else if (network.getBasicCalculationUnit() == 'd') {
			return this.runQuickDiagnosticD(network);
		} else {
			throw new IllegalArgumentException("The NeuralNetwork uses no known basic calculation unit.");
		}
	}

	public abstract void loadF();
	public abstract void loadD();

	public abstract float[][][] getTrainingDataF(int nbrInputs);
	public abstract double[][][] getTrainingDataD(int nbrInputs);  
	
	public abstract float[][][] getEvaluationDataF();
	public abstract double[][][] getEvaluationDataD();  
	
	public abstract String runDiagnosticF(NeuralNetwork network);
	public abstract String runDiagnosticD(NeuralNetwork network);

	public abstract String runQuickDiagnosticF(NeuralNetwork network);
	public abstract String runQuickDiagnosticD(NeuralNetwork network);

}
