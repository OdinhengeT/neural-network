package networks;

/**
 * This is an interface containing the basic abstract methods needed in order to
 * operate a NeuralNetwork. It supports using either float or double
 * (java primitives) as the basic calculation unit.
 * 
 * @author OdinhengeT
 * @date 14th November 2020
 * @see NeuralNetworkF
 * @see NeuralNetworkD
 */
public interface NeuralNetwork {

	public abstract char getBasicCalculationUnit();

	/**
	 * The NeuralNetwork is given an input vector (double), and calculates an output
	 * vector (double) by passing its values through the NeuralNetwork.
	 * 
	 * @param input a double[] of input values to the NeuralNetwork
	 * @returns a double[] containing the output of the NeuralNetwork
	 * @throws IllegalArgumentException if nbr inputs != nbr InputNeurons
	 * @throws IllegalCallerException   if the NeuralNetwork doesn't accept inputs
	 *                                  of type double
	 */
	public abstract double[] run(double[] input);

	/**
	 * The NeuralNetwork is given an input vector (float), and calculates an output
	 * vector (float) by passing its values through the NeuralNetwork.
	 * 
	 * @param input a float[] of input values to the NeuralNetwork
	 * @returns a float[] containing the output of the NeuralNetwork
	 * @throws IllegalArgumentException if nbr inputs != nbr InputNeurons
	 * @throws IllegalCallerException   if the NeuralNetwork doesn't accept inputs
	 *                                  of type float
	 */
	public abstract float[] run(float[] input);

	/**
	 * Trains the network with multiple vector inputs (double) and their
	 * corresponding target vectors (double).
	 * 
	 * @param input,  multiple vector inputs (double) gathered in an array
	 * @param target, multiple vector targets (double) corresponding to the input
	 *                vectors in input
	 * @throws IllegalArgumentException if input and target are of different length
	 * @throws IllegalCallerException   if the NeuralNetwork doesn't accept inputs
	 *                                  of type double
	 */
	public abstract void train(double[][] input, double[][] target);

	/**
	 * Trains the network with multiple vector inputs (float) and their
	 * corresponding target vectors (float).
	 * 
	 * @param input,  multiple vector inputs (float) gathered in an array
	 * @param target, multiple vector targets (float) corresponding to the input
	 *                vectors in input
	 * @throws IllegalArgumentException if input and target are of different length
	 * @throws IllegalCallerException   if the NeuralNetwork doesn't accept inputs
	 *                                  of type float
	 */
	public abstract void train(float[][] input, float[][] target);

	/**
	 * Returns a String representation of the NeuralNetwork.
	 * 
	 * @returns a String describing the NeuralNetwork
	 */
	public abstract String toString();

}
