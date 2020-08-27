package mnist;

import java.io.IOException;

import networks.NeuralNetwork;

/**
 * Contains basic methods needed to use the Mnist dataset for training a
 * NeuralNetwork, used in a static way.
 * 
 * @author OdinhengeT
 * @see NeuralNetwork
 * @see MnistFileReader
 * @see MnistDataVisualizer
 */
public class Mnist {

	/**
	 * Boolean used in order to recognize when the data has been loaded.
	 */
	private static boolean isDataLoaded = false;

	/**
	 * When loaded contain the test images downloaded from the Mnist website.
	 */
	private static double[][] testImages = new double[1][1];

	/**
	 * When loaded contain the test labels downloaded from the Mnist website.
	 * Eddited so that each label is an array of size 10 and the the value at the
	 * index of the correct number is 1.0 with the others beeing 0.0.
	 */
	private static double[][] testLabels = new double[1][1];

	/**
	 * When loaded contain the training images downloaded from the Mnist website.
	 */
	private static double[][] trainingImages = new double[1][1];

	/**
	 * When loaded contain the training labels downloaded from the Mnist website.
	 * Eddited so that each label is an array of size 10 and the the value at the
	 * index of the correct number is 1.0 with the others beeing 0.0.
	 */
	private static double[][] trainingLabels = new double[1][1];

	/**
	 * Loads the data from the files downloaded from the Mnist website into the
	 * double[][] above, and changes isDataLoaded to true.
	 */
	public static void load() {
		try {
			MnistFileReader mnistFileReader = new MnistFileReader("src/mnist/TestImages.idx3-ubyte");
			testImages = mnistFileReader.read();

			mnistFileReader = new MnistFileReader("src/mnist/TestLabels.idx1-ubyte");
			testLabels = mnistFileReader.read();

			mnistFileReader = new MnistFileReader("src/mnist/TrainingImages.idx3-ubyte");
			trainingImages = mnistFileReader.read();

			mnistFileReader = new MnistFileReader("src/mnist/TrainingLabels.idx1-ubyte");
			trainingLabels = mnistFileReader.read();

			mnistFileReader = null;
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		isDataLoaded = true;
	}

	/**
	 * Tests a NeuralNetwork using the Test data and returns a String containing the
	 * results of the diagnostic.
	 * 
	 * @param network the NeuralNetwork to run the standard diagnostic on
	 * @return a String containing the results of the standardDiagnostic
	 * @throws IllegalCallerException if void load() hasn't already been called
	 */
	public static String standardDiagnostic(NeuralNetwork network) {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		StringBuilder sb = new StringBuilder();
		double[] result = new double[1];
		double meanError = 0;
		for (int i = 0; i < testImages.length; i++) {
			result = network.run(testImages[i]);
			for (int j = 0; j < result.length; j++) {
				meanError += Math.abs(result[j] - testLabels[i][j]);
			}

		}
		meanError /= testLabels.length;
		sb.append("Mean Error: " + meanError + System.lineSeparator());
		sb.append("Index:   Target:   Output:" + System.lineSeparator());
		int randomIndex = (int) (Math.random() * testImages.length);
		result = network.run(testImages[randomIndex]);
		for (int i = 0; i < 10; i++) {
			sb.append("  " + i + "        " + testLabels[randomIndex][i] + "       " + result[i]
					+ System.lineSeparator());
		}

		double[][] randomTestImage = new double[1][1];
		double[][] randomTestLabel = new double[1][1];
		randomTestImage[0] = testImages[randomIndex];
		randomTestLabel[0] = testLabels[randomIndex];
		new MnistDataVisualizer(randomTestImage, randomTestLabel);

		return sb.toString();
	}

	/**
	 * Returns the collection of test images (downloaded from the Mnist website) as
	 * a double[][].
	 * 
	 * @return The collection of test images from Mnist (as read by MnistFileReader)
	 * @throws IllegalCallerException if void load() hasn't already been called
	 */
	public static double[][] getTestImages() {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return testImages;
	}

	/**
	 * Returns the test image with index index (downloaded from the Mnist website)
	 * as a double[].
	 * 
	 * @param index index of image to fetch in testImages
	 * @return The test image at index index
	 * @throws IllegalCallerException         if void load() hasn't already been
	 *                                        called
	 * @throws ArrayIndexOutOfBoundsException if int index out of bounds for
	 *                                        testImages[index]
	 */
	public static double[] getTestImageAt(int index) {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return testImages[index];
	}

	/**
	 * Returns the collection of test labels (downloaded from the Mnist website) as
	 * a double[][].
	 * 
	 * @return The collection of test labels from Mnist (as read by MnistFileReader)
	 * @throws IllegalCallerException if void load() hasn't already been called
	 */
	public static double[][] getTestLabels() {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return testLabels;
	}

	/**
	 * Returns the test label with index index (downloaded from the Mnist website)
	 * as a double[].
	 * 
	 * @param index index of image to fetch in testLabels
	 * @return The test label at index index
	 * @throws IllegalCallerException         if void load() hasn't already been
	 *                                        called
	 * @throws ArrayIndexOutOfBoundsException if int index out of bounds for
	 *                                        testLabels[index]
	 */
	public static double[] getTestLabelAt(int index) {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return testLabels[index];
	}

	/**
	 * Returns the collection of training images (downloaded from the Mnist website)
	 * as a double[][].
	 * 
	 * @return The collection of training images from Mnist (as read by
	 *         MnistFileReader)
	 * @throws IllegalCallerException if void load() hasn't already been called
	 */
	public static double[][] getTrainingImages() {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return trainingImages;
	}

	/**
	 * Returns the training image with index index (downloaded from the Mnist
	 * website) as a double[].
	 * 
	 * @param index index of image to fetch in trainingImages
	 * @return The training image at index index
	 * @throws IllegalCallerException         if void load() hasn't already been
	 *                                        called
	 * @throws ArrayIndexOutOfBoundsException if int index out of bounds for
	 *                                        trainingImages[index]
	 */
	public static double[] getTrainingImageAt(int index) {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return trainingImages[index];
	}

	/**
	 * Returns the collection of training labels (downloaded from the Mnist website)
	 * as a double[][].
	 * 
	 * @return The collection of training labels from Mnist (as read by
	 *         MnistFileReader)
	 * @throws IllegalCallerException if void load() hasn't already been called
	 */
	public static double[][] getTrainingLabels() {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return trainingLabels;
	}

	/**
	 * Returns the training label with index index (downloaded from the Mnist
	 * website) as a double[].
	 * 
	 * @param index index of image to fetch in trainingLabels
	 * @return The training labels downloaded from the Mnist website.
	 * @throws IllegalCallerException         if void load() hasn't already been
	 *                                        called
	 * @throws ArrayIndexOutOfBoundsException if index out of bounds for
	 *                                        trainingLabels[index]
	 */
	public static double[] getTrainingLabelAt(int index) {
		if (!isDataLoaded) {
			throw new IllegalCallerException("Data has not yet been Loaded, call void load() before proceeding");
		}
		return trainingLabels[index];
	}

}
