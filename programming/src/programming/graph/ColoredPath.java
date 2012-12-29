package programming.graph;

import java.awt.Color;
import java.awt.geom.Path2D;

public class ColoredPath extends Path2D.Float {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8167527710885528293L;
	Color color_ = new Color(0,0,0);
	
	public Color color() {
		return color_;
	}
	
	public void color(Color newColor) {
		
		if (null == newColor) {
			color_ = new Color(0,0,0);
			return;
		}
		color_ = new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue());
	}
}
