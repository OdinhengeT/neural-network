package mnist;

public class MainMnist {

	public static void main(String[] args) {

		Mnist.load();
		
		new MnistDataVisualizer(Mnist.getTestImages(), Mnist.getTestLabels());
		
	}

}
