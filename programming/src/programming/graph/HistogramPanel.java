package programming.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import programming.contract.PreconditionError;

public class HistogramPanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 653402512264703253L;
	
	Color backgroundColor_ = new Color(255, 255, 255);
	Color foregroundColor_ = new Color(0, 0, 255);
	Color outlineColor_ = new Color(0,0,0);
	
	
	int [] data_ = null;
	
	private int findMin(int [] a) throws PreconditionError {
		PreconditionError.Test(a == null, "The array parameter must not be null.");
		int min = 0x7FFFFFFF;
		for(int i = 0; i < a.length; ++i) {
			if (a[i] < min) {
				min = a[i];
			}
		}
		
		return min;
	}
	
	private int findMax(int [] a) throws PreconditionError {
		PreconditionError.Test(a == null, "The array parameter must not be null.");
		int max = -0x7FFFFFFF;
		for(int i = 0; i < a.length; ++i) {
			if (a[i] > max) {
				max = a[i];
			}
		}
		
		return max;
	}
	
	public void data(int [] a) throws PreconditionError {
		PreconditionError.Test(a == null, "The array parameter must not be null.");
		PreconditionError.Test(a.length > this.getSize().width, 
				"Can't rener a histogram with a window width that is " +
				"smaller than the number of elements in the array.");
		data_ = a;
		this.repaint();
	}
	
	private void clear_(Graphics g) {
		
		Graphics2D copy = (Graphics2D)g.create();
		
		try {
			Dimension size = this.getSize();
			copy.translate(1.0, size.height);
			copy.scale(1.0, -1.0);
			copy.setColor(backgroundColor_);
			copy.fillRect(0, 0, size.width, size.height);
		} finally {
			copy.dispose();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		clear_(g);

		if (null == data_)
			return;
		
		int minVal, maxVal;

		Graphics2D g2D = (Graphics2D)g.create();

		try {
			Dimension size = this.getSize();
			minVal = this.findMin(data_);
			maxVal = this.findMax(data_);
			int delta = (maxVal - minVal) + 1;
			
			int pixelsPerVal = size.height / delta;
			int pixelsPerElement = size.width / data_.length;
			g2D.setColor(this.foregroundColor_);
			
			Rectangle2D.Float rect = new Rectangle2D.Float();
			
			g2D.translate(0, size.height);
			g2D.scale(1.0, -1.0);
			
			
			for(int i = 0; i < data_.length; ++i) {
				rect.setRect(i * pixelsPerElement, 0, pixelsPerElement, data_[i] * pixelsPerVal);

				g2D.setColor(this.foregroundColor_);
				g2D.fill(rect);
				
				g2D.setColor(this.outlineColor_);
				g2D.draw(rect);
			}
		} catch (PreconditionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != g2D)
				g2D.dispose();
		}
	}
	
}
