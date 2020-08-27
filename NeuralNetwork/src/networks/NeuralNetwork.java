package networks;

import java.util.function.Function;

/**
 * This is an abstract class containing the basic functions and abstract methods
 * needed in a NeuralNetwork.
 * 
 * @author OdinhengeT
 * @see NeuronBasedNeuralNetwork
 * @see MatrixBasedNeuralNetwork
 */
public abstract class NeuralNetwork {

	/**
	 * Non-Linear function (Sigmoid function) applied on the inputs of neurons.
	 */
	protected static Function<Double, Double> sigFun = a -> 1.0 / (1.0 + Math.exp(-1 * a));

	/**
	 * Derivative of the non-Linear function (Sigmoid function)..
	 */
	protected static Function<Double, Double> DsigFun = a -> sigFun.apply(a) * (1 - sigFun.apply(a));

	/**
	 * Number of Layers in this Network
	 */
	protected int nbrLayers;

	/**
	 * Creates a NeuralNetwork (abstract, only used as super constructor).
	 * 
	 * @param nbrLayers number of Layers in the NeuralNetwork
	 */
	public NeuralNetwork(int nbrLayers) {
		this.nbrLayers = nbrLayers;
	}

	/**
	 * The NeuralNetwork is given an input vector and by passing its values through
	 * the NeuralNetwork, calculates an output vector.
	 * 
	 * @param input a double[] of input values (one for each InputNeuron)
	 * @returns a double[] containing the output of each OutputNeuron
	 * @throws IllegalArgumentException if nbr inputs != nbr InputNeurons
	 */
	public abstract double[] run(double[] input);

	/**
	 * Trains the network with multiple inputs (each being a vector) and their
	 * corresponding targets (also one vector each).
	 * 
	 * @param input  multiple input vectors put together in an array
	 * @param target multiple target vectors corresponding to those in input
	 * @throws IllegalArgumentException if input and target are of different length
	 */
	public abstract void train(double[][] input, double[][] target);

	/**
	 * Returns a String representation of the NeuralNetwork.
	 * 
	 * @returns a String describing the NeuralNetwork
	 */
	public abstract String toString();

}
