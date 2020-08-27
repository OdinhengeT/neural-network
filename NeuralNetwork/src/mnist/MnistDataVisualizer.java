package mnist;

import java.awt.*;

import javax.swing.*;

/**
 * This is a simple class that displays data downloaded from the Mnist website
 * in a JFrame.
 * 
 * @author OdinhengeT
 * @see Mnist
 * @see MnistFileReader
 * @see MainMnist
 * @see NeuralNetwork
 */
@SuppressWarnings("serial")
public class MnistDataVisualizer extends JFrame {
	/**
	 * The Mnist dataset contain a number of 28x28 pixel images.
	 */
	private static final short NUMBER_OF_COLUMNS = 28;
	
	private JPanel UiElements = new JPanel();
	private JPanel valueButtonPanel = new JPanel();
	private Canvas canvas;
	private JButton next = new JButton("  Next  ");;
	private JButton prev = new JButton("Previous");;
	private JTextField nowViewing = new JTextField(5);

	/**
	 * Creates a MnistDataVisualizer displaying an image along with its label.
	 * 
	 * @param images Image data downloaded from Mnist (as read by MnistFileReader)
	 * @param labels Label data downloaded from Mnist (as read by MnistFileReader)
	 */
	public MnistDataVisualizer(double[][] images, double[][] labels) {
		super("MnistDataVisualizer");
		canvas = new Canvas(images);
		valueButtonPanel = new JPanel(new GridLayout(1, 11));

		JRadioButton[] valueButtons = new JRadioButton[10];
		for (int i = 0; i < 10; i++) {
			valueButtons[i] = new JRadioButton("" + i);
			valueButtons[i].setSelected(labels[0][i] == 1);
			valueButtons[i].setEnabled(false);
			valueButtonPanel.add(valueButtons[i]);
		}

		next.addActionListener((a) -> {
			int viewingIndex = canvas.getIndex() + 1;
			if (viewingIndex < labels.length) {
				nowViewing.setText("" + viewingIndex);
				canvas.updateIndex(1);
				canvas.paintCanvas();
				for (int i = 0; i < 10; i++) {
					valueButtons[i].setSelected(labels[viewingIndex][i] == 1);
				}
			}
		});

		prev.addActionListener((a) -> {
			int viewingIndex = canvas.getIndex() - 1;
			if (viewingIndex >= 0) {
				nowViewing.setText("" + viewingIndex);
				canvas.updateIndex(-1);
				canvas.paintCanvas();
				for (int i = 0; i < 10; i++) {
					valueButtons[i].setSelected(labels[viewingIndex][i] == 1);
				}
			}
		});
		setUpWindow();
	}

	/**
	 * Creates a MnistDataVisualizer displaying an image along with its label, and
	 * also shows a prediction for a side by side comparison.
	 * 
	 * @param images      Image data downloaded from Mnist (as read by
	 *                    MnistFileReader)
	 * @param labels      Label data downloaded from Mnist (as read by
	 *                    MnistFileReader)
	 * @param predictions predictions made in some way, e.g. by a NeuralNetwork
	 */
	public MnistDataVisualizer(double[][] images, double[][] labels, double[][] predictions) {
		super("MnistDataVisualizer");
		canvas = new Canvas(images);
		valueButtonPanel = new JPanel(new GridLayout(2, 11));

		JRadioButton[] valueButtons = new JRadioButton[10];
		valueButtonPanel.add(new JTextField("Value:     "));
		for (int i = 0; i < 10; i++) {
			valueButtons[i] = new JRadioButton("" + i);
			valueButtons[i].setSelected(labels[0][i] == 1);
			valueButtons[i].setEnabled(false);
			valueButtonPanel.add(valueButtons[i]);
		}

		JTextField[] predicted = new JTextField[10];
		valueButtonPanel.add(new JTextField("Prediction: "));
		for (int i = 0; i < 10; i++) {
			predicted[i] = new JTextField("" + predictions[i]);
			predicted[i].setEditable(false);
			valueButtonPanel.add(predicted[i]);
		}

		next.addActionListener((a) -> {
			int viewingIndex = canvas.getIndex() + 1;
			if (viewingIndex < labels.length) {
				nowViewing.setText("" + viewingIndex);
				canvas.updateIndex(1);
				canvas.paintCanvas();
				for (int i = 0; i < 10; i++) {
					valueButtons[i].setSelected(labels[viewingIndex][i] == 1);
					predicted[i].setText("" + predictions[viewingIndex][i]);
				}
			}
		});

		prev.addActionListener((a) -> {
			int viewingIndex = canvas.getIndex() - 1;
			if (viewingIndex >= 0) {
				nowViewing.setText("" + viewingIndex);
				canvas.updateIndex(-1);
				canvas.paintCanvas();
				for (int i = 0; i < 10; i++) {
					valueButtons[i].setSelected(labels[viewingIndex][i] == 1);
					predicted[i].setText("" + predictions[viewingIndex][i]);
				}
			}
		});

		setUpWindow();
	}

	/**
	 * Private method that contains the basic JFrame configurations shared among
	 * constructors.
	 */
	private void setUpWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(14 * NUMBER_OF_COLUMNS, 14 * NUMBER_OF_COLUMNS));
		setSize(20 * NUMBER_OF_COLUMNS, 20 * NUMBER_OF_COLUMNS);
		setResizable(true);

		nowViewing.setEditable(false);
		nowViewing.setText("0");

		UiElements.add(prev);
		UiElements.add(nowViewing);
		UiElements.add(next);
		add(UiElements, BorderLayout.NORTH);
		add(valueButtonPanel, BorderLayout.SOUTH);
		add(canvas);
		canvas.paintCanvas();

		setVisible(true);
	}

	/**
	 * Basic nested class used for painting the Images from the Mnist dataset into
	 * the JFrame.
	 * 
	 * @author Odinshenge
	 *
	 */
	private class Canvas extends JPanel {
		/**
		 * An array containing multiple images from Mnist, as read by MnistFileReader.
		 */
		private double[][] data;

		/**
		 * Index of the current image beeing displayed.
		 */
		private int index = 0;

		/**
		 * Creates a canvas object.
		 * 
		 * @param data an array containing multiple images from Mnist
		 */
		public Canvas(double[][] data) {
			this.data = data;
		}

		/**
		 * Returns the index stored in this canvas object.
		 * 
		 * @return this.index
		 */
		public int getIndex() {
			return this.index;
		}

		/**
		 * Changes the value of this.index by change amount.
		 * 
		 * @param change the value to add to this.index
		 */
		public void updateIndex(int change) {
			index += change;
		}

		/**
		 * Method to call to repaint the canvas object.
		 */
		public void paintCanvas() {
			repaint();
		}

		/**
		 * Overrides paintComponent(Graphics g) in Component, calls that method, and
		 * then draws the image stored at this.data[this.index].
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int rectWidth = this.getWidth() / NUMBER_OF_COLUMNS;
			int rectHeight = this.getHeight() / NUMBER_OF_COLUMNS;
			int shiftWidth = this.getWidth() % NUMBER_OF_COLUMNS / 2;
			int shiftHeight = this.getHeight() % NUMBER_OF_COLUMNS / 2;
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			for (int i = 0; i < data[index].length; i++) {
				g.setColor(new Color((int) data[index][i], (int) data[index][i], (int) data[index][i]));
				g.fillRect(shiftWidth + rectWidth * (i % NUMBER_OF_COLUMNS),
						shiftHeight + rectHeight * (i / NUMBER_OF_COLUMNS), rectWidth, rectHeight);
			}

		}

	}

}
