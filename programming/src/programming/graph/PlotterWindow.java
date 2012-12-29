package programming.graph;

import java.awt.Insets;
import javax.swing.JFrame;
import programming.contract.PreconditionError;

public class PlotterWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5511926046794128388L;
	PlotterPanel panel_ = new PlotterPanel();
	
	public PlotterWindow(int width, int height, 
			int xDivisionCount, int yDivisionCount,
			float xMax, float yMax) throws PreconditionError {
		PreconditionError.Test(width < 2, 
				"The width must be greater than or equal to two.");

		PreconditionError.Test(height < 2, 
				"The height must be greater than or equal to two.");
		
		this.add(panel_);
		this.setTitle("Plotter");
		
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		Insets insets = this.getInsets();
		int windowWidth = width + insets.left + insets.right;
		int windowHeight = height + insets.top + insets.bottom;
		this.setSize(windowWidth, windowHeight);
		this.setResizable(false);
		panel_.xDivisionCount(xDivisionCount);
		panel_.yDivisionCount(yDivisionCount);
		panel_.xMax(xMax);
		panel_.yMax(yMax);
	}
	
	public void addLine(ColoredPath path) {
		panel_.addLine(path);
	}

}
