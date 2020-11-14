package applications.mnist;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is a class that reads the idx1-ubyte/idx3-ubyte files that can be
 * downloaded at the Mnist website (http://yann.lecun.com/exdb/mnist/) and
 * returns them as float[][] or double[][], where the
 * first index represents the set of training data and the second index
 * represents which part of that data.
 * 
 * @author OdinhengeT
 * @see Mnist
 * @see MnistDataVisualizer
 * @see NeuralNetwork
 * @see AppliedNetwork
 */

public class MnistFileReader {

	private int nbrItems;
	private byte nbrDimensions;
	private DataInputStream dataInputStream;

	/**
	 * Creates a MnistFileReader object.
	 * 
	 * @param fileName name of file wished to have read
	 * @throws FileNotFoundException if the file was not found
	 * @throws SecurityException     if thrown by FileInputStream or dataInputStream
	 * @throws IOException           if thrown by FileInputStream or dataInputStream
	 */
	public MnistFileReader(String fileName) throws SecurityException, IOException {
		dataInputStream = new DataInputStream(new FileInputStream(fileName));
		dataInputStream.skipBytes(3);
		nbrDimensions = dataInputStream.readByte();
		nbrItems = dataInputStream.readInt();
	}

	/**
	 * Returns the data in the file as a float[][] where the first index corresponds
	 * to which "item" it is and the second index corresponds to which instance of
	 * data it is in that item. NOTE: Can only be called once, and then closes the
	 * dataInputStream.
	 * 
	 * @return a float[][] containing the data from the file
	 * @throws IOException              if thrown by dataInputStream
	 * @throws IllegalArgumentException if the file isn't an idx1-ubyte or
	 *                                  idx3-ubyte file.
	 */
	public float[][] readF() throws IOException {
		float[][] result;
		if (nbrDimensions == 1) {
			result = new float[nbrItems][10];
			for (int i = 0; i < nbrItems; i++) {
				result[i][dataInputStream.readByte()] = 1.0f;
			}
		} else if (nbrDimensions == 3) {
			result = new float[nbrItems][dataInputStream.readInt() * dataInputStream.readInt()];
			for (int i = 0; i < nbrItems; i++) {
				for (int j = 0; j < result[0].length; j++) {
					result[i][j] = (float) dataInputStream.readUnsignedByte();
				}
			}
		} else {
			throw new IllegalArgumentException(
					"The Mnist dataset only contain files of type idx1-ubyte or idx3-ubyte, to read idx" + nbrDimensions
							+ "-ubyte files, please use another FileReader.");
		}

		dataInputStream.close();
		return result;
	}

	/**
	 * Returns the data in the file as a double[][] where the first index corresponds
	 * to which "item" it is and the second index corresponds to which instance of
	 * data it is in that item. NOTE: Can only be called once, and then closes the
	 * dataInputStream.
	 * 
	 * @return a double[][] containing the data from the file
	 * @throws IOException              if thrown by dataInputStream
	 * @throws IllegalArgumentException if the file isn't an idx1-ubyte or
	 *                                  idx3-ubyte file.
	 */
	public double[][] readD() throws IOException {
		double[][] result;
		if (nbrDimensions == 1) {
			result = new double[nbrItems][10];
			for (int i = 0; i < nbrItems; i++) {
				result[i][dataInputStream.readByte()] = 1.0;
			}
		} else if (nbrDimensions == 3) {
			result = new double[nbrItems][dataInputStream.readInt() * dataInputStream.readInt()];
			for (int i = 0; i < nbrItems; i++) {
				for (int j = 0; j < result[0].length; j++) {
					result[i][j] = (double) dataInputStream.readUnsignedByte();
				}
			}
		} else {
			throw new IllegalArgumentException(
					"The Mnist dataset only contain files of type idx1-ubyte or idx3-ubyte, to read idx" + nbrDimensions
							+ "-ubyte files, please use another FileReader.");
		}

		dataInputStream.close();
		return result;
	}

}
