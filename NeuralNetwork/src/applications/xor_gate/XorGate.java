package applications.xor_gate;

import applications.Application;
import networks.NeuralNetwork;

public class XorGate extends Application {

	private static final float[][] INPUT_QD_F = { { 0.0f, 0.0f }, { 1.0f, 0.0f }, { 0.0f, 1.0f }, { 1.0f, 1.0f } };
	private static final double[][] INPUT_QD_D = { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };

	public XorGate() {
		super.isDataLoaded = true;
	}

	public void loadF() {
		System.out.println("The XOR application doesn't require loading.");
	}

	public void loadD() {
		System.out.println("The XOR application doesn't require loading.");
	}

	public float[][][] getTrainingDataF(int nbrInputs) {
		float[][][] TrainingData = new float[2][1][1];
		TrainingData[0] = XorGateDataGenerator.generateInputF(nbrInputs);
		TrainingData[1] = XorGateDataGenerator.generateTargetF(TrainingData[0]);
		return TrainingData;
	}

	public double[][][] getTrainingDataD(int nbrInputs) {
		double[][][] TrainingData = new double[2][1][1];
		TrainingData[0] = XorGateDataGenerator.generateInputD(nbrInputs);
		TrainingData[1] = XorGateDataGenerator.generateTargetD(TrainingData[0]);
		return TrainingData;
	}
	
	public float[][][] getEvaluationDataF() {
		float[][][] EvaluationData = new float[2][1][1];
		EvaluationData[0] = INPUT_QD_F;
		EvaluationData[1] = XorGateDataGenerator.generateTargetF(INPUT_QD_F);
		return EvaluationData;
	}
	
	public double[][][] getEvaluationDataD() {
		double[][][] EvaluationData = new double[2][1][1];
		EvaluationData[0] = INPUT_QD_D;
		EvaluationData[1] = XorGateDataGenerator.generateTargetD(INPUT_QD_D);
		return EvaluationData;
	}

	public String runDiagnosticF(NeuralNetwork network) {
		StringBuilder sb = new StringBuilder("==Diagnostic==" + System.lineSeparator());
		for (int i = 0; i < INPUT_QD_F.length; i++) {
			float result = network.run(INPUT_QD_F[i])[0];
			float[][] target = XorGateDataGenerator.generateTargetF(INPUT_QD_F);
			float error = (result - target[i][0]) * (result - target[i][0]) / 2;
			sb.append("  Input: " + INPUT_QD_F[i][0] + " & " + INPUT_QD_F[i][1] + " -> " + result + "  (Error: " + error
					+ ")" + System.lineSeparator());
		}
		return sb.toString();
	}

	public String runDiagnosticD(NeuralNetwork network) {
		StringBuilder sb = new StringBuilder("==Diagnostic==" + System.lineSeparator());
		for (int i = 0; i < INPUT_QD_D.length; i++) {
			double result = network.run(INPUT_QD_D[i])[0];
			double[][] target = XorGateDataGenerator.generateTargetD(INPUT_QD_D);
			double error = (result - target[i][0]) * (result - target[i][0]) / 2;
			sb.append("  Input: " + INPUT_QD_D[i][0] + " & " + INPUT_QD_D[i][1] + " -> " + result + "  (Error: " + error
					+ ")" + System.lineSeparator());
		}
		return sb.toString();
	}

	public String runQuickDiagnosticF(NeuralNetwork network) {
		return this.runDiagnosticF(network);
	}

	public String runQuickDiagnosticD(NeuralNetwork network) {
		return this.runDiagnosticD(network);
	}

}
