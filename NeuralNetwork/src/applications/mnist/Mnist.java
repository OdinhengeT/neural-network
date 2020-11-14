package applications.mnist;

import java.io.IOException;
import applications.Application;
import networks.NeuralNetwork;

/**
 * Contains basic methods needed to use the Mnist dataset for training a
 * NeuralNetwork.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see MnistFileReader
 * @see MnistDataVisualizer
 */
public class Mnist extends Application {

	private int offset;

	/**
	 * When loaded contain the test images downloaded from the Mnist website.
	 */
	private float[][] evaluationImagesF;
	/**
	 * When loaded contain the test images downloaded from the Mnist website.
	 */
	private double[][] evaluationImagesD;

	/**
	 * When loaded contain the test labels downloaded from the Mnist website. Edited
	 * so that each label is an array of size 10 and the the value at the index of
	 * the correct number is 1.0 with the others being 0.0.
	 */
	private float[][] evaluationLabelsF;
	/**
	 * When loaded contain the test labels downloaded from the Mnist website. Edited
	 * so that each label is an array of size 10 and the the value at the index of
	 * the correct number is 1.0 with the others being 0.0.
	 */
	private double[][] evaluationLabelsD;

	/**
	 * When loaded contain the training images downloaded from the Mnist website.
	 */
	private float[][] trainingImagesF;
	/**
	 * When loaded contain the training images downloaded from the Mnist website.
	 */
	private double[][] trainingImagesD;

	/**
	 * When loaded contain the training labels downloaded from the Mnist website.
	 * Edited so that each label is an array of size 10 and the the value at the
	 * index of the correct number is 1.0 with the others being 0.0.
	 */
	private float[][] trainingLabelsF;
	/**
	 * When loaded contain the training labels downloaded from the Mnist website.
	 * Edited so that each label is an array of size 10 and the the value at the
	 * index of the correct number is 1.0 with the others being 0.0.
	 */
	private double[][] trainingLabelsD;

	public Mnist() {
		super.isDataLoaded = false;
		offset = 0;
	}

	/**
	 * Loads the data from the files downloaded from the Mnist website into the
	 * float[][] above, and changes isDataLoaded to true.
	 */
	public void loadF() {
		try {
			MnistFileReader mnistFileReader = new MnistFileReader("src/mnist/TestImages.idx3-ubyte");
			evaluationImagesF = mnistFileReader.readF();

			mnistFileReader = new MnistFileReader("src/mnist/TestLabels.idx1-ubyte");
			evaluationLabelsF = mnistFileReader.readF();

			mnistFileReader = new MnistFileReader("src/mnist/TrainingImages.idx3-ubyte");
			trainingImagesF = mnistFileReader.readF();

			mnistFileReader = new MnistFileReader("src/mnist/TrainingLabels.idx1-ubyte");
			trainingLabelsF = mnistFileReader.readF();

			mnistFileReader = null;
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		isDataLoaded = true;
	}

	/**
	 * Loads the data from the files downloaded from the Mnist website into the
	 * double[][] above, and changes isDataLoaded to true.
	 */
	public void loadD() {
		try {
			MnistFileReader mnistFileReader = new MnistFileReader("src/applications/mnist/TestImages.idx3-ubyte");
			evaluationImagesD = mnistFileReader.readD();

			mnistFileReader = new MnistFileReader("src/applications/mnist/TestLabels.idx1-ubyte");
			evaluationLabelsD = mnistFileReader.readD();

			mnistFileReader = new MnistFileReader("src/applications/mnist/TrainingImages.idx3-ubyte");
			trainingImagesD = mnistFileReader.readD();

			mnistFileReader = new MnistFileReader("src/applications/mnist/TrainingLabels.idx1-ubyte");
			trainingLabelsD = mnistFileReader.readD();

			mnistFileReader = null;
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		isDataLoaded = true;
	}

	/**
	 * Returns the collection of trainig images and labels (downloaded from the
	 * Mnist website) as a float[][][], first index: 0 for images 1 for labels;
	 * second index corresponds to which set of trainingData; the images and labels
	 * have been vectorized, so the thrid index corresponds to a specific entry of
	 * the labe/image.
	 * 
	 * @return The collection of training images & labels from Mnist (as read by
	 *         MnistFileReader)
	 * @throws IllegalCallerException if the data hasn't already been loaded
	 */
	public float[][][] getTrainingDataF(int nbrInputs) {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}
		int temp = 0;
		float[][][] trainingData = new float[2][nbrInputs][1];
		for (int i = 0; i < nbrInputs; i++) {
			if (offset + i < trainingLabelsF.length) {
				trainingData[0][i] = trainingImagesF[offset + i];
				trainingData[1][i] = trainingLabelsF[offset + i];
			} else {
				offset = 0;
				temp = i;
			}
		}
		offset = offset + nbrInputs - temp;
		return trainingData;
	}

	/**
	 * Returns the collection of trainig images and labels (downloaded from the
	 * Mnist website) as a double[][][], first index: 0 for images 1 for labels;
	 * second index corresponds to which set of trainingData; the images and labels
	 * have been vectorized, so the thrid index corresponds to a specific entry of
	 * the labe/image.
	 * 
	 * @return The collection of training images & labels from Mnist (as read by
	 *         MnistFileReader)
	 * @throws IllegalCallerException if the data hasn't already been loaded
	 */
	public double[][][] getTrainingDataD(int nbrInputs) {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}
		int temp = 0;
		double[][][] trainingData = new double[2][nbrInputs][1];
		for (int i = 0; i < nbrInputs; i++) {
			if (offset + i < trainingLabelsF.length) {
				trainingData[0][i] = trainingImagesD[offset + i];
				trainingData[1][i] = trainingLabelsD[offset + i];
			} else {
				offset = 0;
				temp = i;
			}
		}
		offset = offset + nbrInputs - temp;
		return trainingData;
	}

	public float[][][] getEvaluationDataF() {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}
		float[][][] evaluationData = new float[2][1][1];
		evaluationData[0] = evaluationImagesF;
		evaluationData[1] = evaluationLabelsF;
		return evaluationData;
	}

	public double[][][] getEvaluationDataD() {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}
		double[][][] evaluationData = new double[2][1][1];
		evaluationData[0] = evaluationImagesD;
		evaluationData[1] = evaluationLabelsD;
		return evaluationData;
	}

	/**
	 * Tests a NeuralNetwork using the Evaluation data and returns a String
	 * containing the results of the diagnostic.
	 * 
	 * @param network the NeuralNetwork to run the diagnostic on
	 * @return a String containing the results of the diagnostic
	 * @throws IllegalCallerException if the data hasn't already been loaded
	 */
	public String runDiagnosticF(NeuralNetwork network) {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}
		StringBuilder sb = new StringBuilder();
		float[] result;
		float meanError = 0;
		for (int i = 0; i < evaluationImagesF.length; i++) {
			result = network.run(evaluationImagesF[i]);
			for (int j = 0; j < result.length; j++) {
				meanError += Math.abs(result[j] - evaluationLabelsF[i][j]);
			}

		}
		meanError /= evaluationLabelsF.length;
		sb.append("Mean Error: " + meanError + System.lineSeparator());
		sb.append("Index:   Target:   Output:" + System.lineSeparator());
		int randomIndex = (int) (Math.random() * evaluationImagesF.length);
		result = network.run(evaluationImagesF[randomIndex]);
		for (int i = 0; i < 10; i++) {
			sb.append("  " + i + "        " + evaluationLabelsF[randomIndex][i] + "       " + result[i]
					+ System.lineSeparator());
		}

		float[][] randomTestImage = new float[1][1];
		float[][] randomTestLabel = new float[1][1];
		randomTestImage[0] = evaluationImagesF[randomIndex];
		randomTestLabel[0] = evaluationLabelsF[randomIndex];
		// new MnistDataVisualizer(randomTestImage, randomTestLabel);

		return sb.toString();
	}

	/**
	 * Tests a NeuralNetwork using the Evaluation data and returns a String
	 * containing the results of the diagnostic.
	 * 
	 * @param network the NeuralNetwork to run the diagnostic on
	 * @return a String containing the results of the diagnostic
	 * @throws IllegalCallerException if the data hasn't already been loaded
	 */
	public String runDiagnosticD(NeuralNetwork network) {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}

		StringBuilder sb = new StringBuilder();
		double[] result;
		double meanError = 0.0;
		for (int i = 0; i < evaluationImagesD.length; i++) {
			result = network.run(evaluationImagesD[i]);
			for (int j = 0; j < result.length; j++) {
				meanError += Math.abs(result[j] - evaluationLabelsD[i][j]);
			}

		}
		meanError /= evaluationLabelsF.length;
		sb.append("Mean Error: " + meanError + System.lineSeparator());
		sb.append("Index:   Target:   Output:" + System.lineSeparator());
		int randomIndex = (int) (Math.random() * evaluationImagesF.length);
		result = network.run(evaluationImagesD[randomIndex]);
		for (int i = 0; i < 10; i++) {
			sb.append("  " + i + "        " + evaluationLabelsD[randomIndex][i] + "       " + result[i]
					+ System.lineSeparator());
		}

		double[][] randomTestImage = new double[1][1];
		double[][] randomTestLabel = new double[1][1];
		randomTestImage[0] = evaluationImagesD[randomIndex];
		randomTestLabel[0] = evaluationLabelsD[randomIndex];
		new MnistDataVisualizer(randomTestImage, randomTestLabel);

		return sb.toString();
	}

	public String runQuickDiagnosticF(NeuralNetwork network) {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}

		StringBuilder main = new StringBuilder("==QuickDiagnostic==");
		StringBuilder info = new StringBuilder("  Overview:" + System.lineSeparator());
		StringBuilder evaluatedSets = new StringBuilder("  Evaluated Sets:" + System.lineSeparator());

		float meanError = 0.0f;
		float[][] results = new float[10][1];
		float[][] displayImages = new float[10][1];
		float[][] displayLabels = new float[10][1];
		
		for (int set = 0; set < 10; set++) {
			results[set] = network.run(evaluationImagesF[set]);
			evaluatedSets.append("    Set " + set + ": Index:  Target:  Output:" + System.lineSeparator());
			for (int i = 0; i < results[set].length; i++) {
				meanError += Math.abs(results[set][i] - evaluationLabelsF[set][i]) / 10;

				evaluatedSets.append("           " + i + "       " + evaluationLabelsF[set][i] + "      " + results[set][i]
						+ System.lineSeparator());
			}
			displayImages[set] = evaluationImagesF[set];
			displayLabels[set] = evaluationLabelsF[set];
		}
		
		//new MnistDataVisualizer(displayImages, displayLabels, results);
		
		info.append("  Info:" + System.lineSeparator());
		info.append("    Mean Error: " + meanError + System.lineSeparator());
		
		main.append(info);
		main.append(evaluatedSets);
		return main.toString();
	}

	public String runQuickDiagnosticD(NeuralNetwork network) {
		if (!isDataLoaded) {
			throw new IllegalCallerException(
					"Data has not yet been Loaded, make sure the data is loaded before proceeding");
		}

		StringBuilder main = new StringBuilder("==QuickDiagnostic==" + System.lineSeparator());
		StringBuilder info = new StringBuilder("Overview:" + System.lineSeparator());
		StringBuilder evaluatedSets = new StringBuilder("Evaluated Sets:" + System.lineSeparator());

		double meanError = 0.0;
		double[][] results = new double[10][1];
		double[][] displayImages = new double[10][1];
		double[][] displayLabels = new double[10][1];
		
		for (int set = 0; set < 10; set++) {
			results[set] = network.run(evaluationImagesD[set]);
			evaluatedSets.append("  Set " + set + ": Index:  Target:  Output:" + System.lineSeparator());
			for (int i = 0; i < results[set].length; i++) {
				meanError += Math.abs(results[set][i] - evaluationLabelsD[set][i]) / 10;

				evaluatedSets.append("         " + i + "       " + evaluationLabelsD[set][i] + "      " + results[set][i]
						+ System.lineSeparator());
			}
			displayImages[set] = evaluationImagesD[set];
			displayLabels[set] = evaluationLabelsD[set];
		}
		
		new MnistDataVisualizer(displayImages, displayLabels, results);
		
		info.append("Info:" + System.lineSeparator());
		info.append("  Mean Error: " + meanError + System.lineSeparator());
		
		main.append(info);
		main.append(evaluatedSets);
		return main.toString();
	}

}
