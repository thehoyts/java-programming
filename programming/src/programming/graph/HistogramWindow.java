/**
 * 
 */
package programming.graph;

import java.awt.Insets;

import javax.swing.JFrame;

import programming.contract.PreconditionError;

/**
 * @author Alan
 *
 */
public class HistogramWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8845317840152926961L;
	int width_ = 0;
	int height_ = 0;
	
	HistogramPanel panel_ = new HistogramPanel();
	
	public HistogramWindow(int width, int height) throws PreconditionError {
		PreconditionError.Test(width < 2, 
				"The width must be greater than or equal to two.");

		PreconditionError.Test(height < 2, 
				"The height must be greater than or equal to two.");
		
		this.add(panel_);
		this.setTitle("Histogram");
		
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Insets insets = this.getInsets();
		int windowWidth = width + insets.left + insets.right;
		int windowHeight = height + insets.top + insets.bottom;
		this.setSize(windowWidth, windowHeight);
		this.setResizable(false);
	}
	
	public void data(int [] a) throws PreconditionError {
		panel_.data(a);
	}

}
