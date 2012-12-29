package programming.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.ListIterator;


import javax.swing.JPanel;

import programming.contract.InvariantError;
import programming.contract.PreconditionError;

public class PlotterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7975541904086901527L;
	Color backgroundColor_ = new Color(255, 255, 255);
	Color foregroundColor_ = new Color(0, 0, 255);
	Color outlineColor_ = new Color(0,0,0);
	int leftGutter_ = 50;
	int bottomGutter_ = 50;
	int rightGutter_ = 25;
	int topGutter_ = 25;
	int xDivisionCount_ = 0;
	int yDivisionCount_ = 0;
	float xMax_ = 0.0f;
	float yMax_ = 0.0f;
	
	int divisionLineSize_ = 5;
	
	LinkedList<ColoredPath> lineList_ = new LinkedList<ColoredPath>();
	
	private void clear_(Graphics2D g) {
		Dimension size = this.getSize();
		g.setColor(backgroundColor_);
		g.fillRect(0, 0, size.width, size.height);
	}
	
	private void drawXaxisLabels_(Graphics2D g) throws PreconditionError, InvariantError {
		
		Graphics2D textG = (Graphics2D)g.create();
		
		try {
			textG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

			textG.setColor(foregroundColor_);
			FontMetrics metrics = textG.getFontMetrics();
			
			int fontHeight = metrics.getHeight();
			float delta = xMax_ / xDivisionCount_;
			String label;
			for (int i = 0; i < xDivisionCount_ + 1; ++i) {
				
				label = Float.toString(delta * i);
				int textWidth = metrics.stringWidth(label);
				textG.drawString(label, toPixelX_(i * delta) - textWidth / 2, 
						toPixelY_(0) + fontHeight + divisionLineSize_ + 3);
				
			}
			
		} finally {
			if(textG != null) {
				textG.dispose();
			}
		}
	}

	private void drawYaxisLabels_(Graphics2D g) throws PreconditionError, InvariantError {
		
		Graphics2D textG = (Graphics2D)g.create();
		
		try {
			textG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

			textG.setColor(foregroundColor_);
			FontMetrics metrics = textG.getFontMetrics();
			
			int fontHeight = metrics.getHeight();
			float delta = yMax_ / yDivisionCount_;
			String label;
			for (int i = 0; i < yDivisionCount_ + 1; ++i) {
				
				label = Float.toString(delta * i);
				int textWidth = metrics.stringWidth(label);
				textG.drawString(label, toPixelX_(0) - (textWidth + divisionLineSize_ + 3), 
						toPixelY_(i * delta) + fontHeight/3);
				
			}
			
		} finally {
			if(textG != null) {
				textG.dispose();
			}
		}
		
		
	}
	
	private void drawLines_(Graphics2D g) throws PreconditionError, InvariantError {
		
		if (this.lineList_.isEmpty())
			return;
		
		Graphics2D lineG = (Graphics2D)g.create();
		
		try {
			lineG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
			lineG.translate(leftGutter_, topGutter_ + axisHeightPixels_());
			lineG.scale(axisWidthPixels_() / xMax_, -axisHeightPixels_() / yMax_);

			ListIterator<ColoredPath> listIter = this.lineList_.listIterator();
			
			while(listIter.hasNext()) {
				ColoredPath path = listIter.next();
				lineG.setColor(path.color());
				lineG.draw(path);
			}

		} finally {
			if (lineG != null) {
				lineG.dispose();
			}
		}
	}
	
	private void drawAxes_(Graphics2D g) throws PreconditionError, InvariantError {

		g.setColor(foregroundColor_);
		
		int x_axisPixelCount = axisWidthPixels_();
		int y_axisPixelCount = axisHeightPixels_();
		// x-axis
		g.drawLine(toPixelX_(0), toPixelY_(0), 
				toPixelX_(xMax_), 
				toPixelY_(0));
		
		// y-axis
		g.drawLine(toPixelX_(0), toPixelY_(0),
				toPixelX_(0), toPixelY_(yMax_));
		
		
		if (xDivisionCount_ > 0 && xDivisionCount_ <= x_axisPixelCount / 3) {
			// Draw divisions on the x-axis
			float delta = xMax_ / xDivisionCount_;
			
			float currentXpos = 0.0f;
			
			for(int i = 0; i < xDivisionCount_ + 1; ++i) {
				g.drawLine(
						toPixelX_(currentXpos), toPixelY_(0), 
						toPixelX_(currentXpos), 
						toPixelY_(0) + divisionLineSize_);
				
				currentXpos += delta;
			}
			
			drawXaxisLabels_(g);
		}
		
		if (yDivisionCount_ > 0 && yDivisionCount_ <= y_axisPixelCount / 3) {
			// Draw divisions on the y-axis
			float delta = yMax_ / yDivisionCount_;
			
			float currentYpos = 0.0f;
			
			for(int i = 0; i < yDivisionCount_ + 1; ++i) {
				g.drawLine(toPixelX_(0), toPixelY_(currentYpos), 
						toPixelX_(0) - divisionLineSize_, 
						toPixelY_(currentYpos));
				
				currentYpos += delta;
			}
			
			drawYaxisLabels_(g);
		}
	}
	
	private int axisWidthPixels_() {
		Dimension size = this.getSize();
		return size.width - (rightGutter_ + leftGutter_);
	}
	
	private int axisHeightPixels_() {
		Dimension size = this.getSize();
		return size.height - (topGutter_ + bottomGutter_);
		
	}
	
	private int toPixelX_(float x) throws PreconditionError, InvariantError {
		PreconditionError.Test(x < 0.0f, 
				"The x coordiante must be greater than or equal to 0.");
		
		InvariantError.Test(xMax_ == 0.0f, "The maximum x value must be set.");
		
		float pixelDelta = axisWidthPixels_() / xMax_;
		
		return Math.round(leftGutter_ + x * pixelDelta);
	}
	
	private int toPixelY_(float y) throws PreconditionError, InvariantError {
		PreconditionError.Test(y < 0.0f, 
				"The y coordiante must be greater than or equal to 0.");
		
		InvariantError.Test(yMax_ == 0.0f, "The maximum y value must be set.");
		
		float pixelDelta = axisHeightPixels_() / yMax_;
		
		int pixelCountY = this.getSize().height - 1;
		return Math.round(pixelCountY - (bottomGutter_ + y * pixelDelta));
		
	}
	
	public void xDivisionCount(int count) throws PreconditionError {
		PreconditionError.Test(count < 1, 
				"count must be greater than 0");
		xDivisionCount_ = count;
	}
	
	public void yDivisionCount(int count) throws PreconditionError {
		PreconditionError.Test(count < 1, 
				"count must be greater than 0");
		yDivisionCount_ = count;
	}
	
	public void addLine(ColoredPath line) {
		lineList_.add(line);
		this.repaint();
	}
	
	public void yMax(float max) throws PreconditionError {
		PreconditionError.Test(max <= 0.0f, 
				"max must be greater than 0");
		yMax_ = max;
	}
	
	public void xMax(float max) throws PreconditionError {
		PreconditionError.Test(max <= 0.0f, 
				"max must be greater than 0");
		xMax_ = max;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D)g.create();
		try {
			
			clear_(g2D);
			drawAxes_(g2D);
			drawLines_(g2D);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(g2D != null)
				g2D.dispose();
		}
	}
}
