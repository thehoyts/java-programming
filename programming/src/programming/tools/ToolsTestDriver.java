package programming.tools;

import java.awt.Color;

import programming.contract.InvariantError;
import programming.contract.PreconditionError;
import programming.graph.ColoredPath;
import programming.graph.PlotterWindow;

public class ToolsTestDriver {

	/**
	 * @param args
	 * @throws PreconditionError 
	 * @throws InvariantError 
	 */
	public static void main(String[] args){
		try {
			Timer secondTimer = new Timer(Timer.Units.seconds);
			Timer millisecondTimer = new Timer(Timer.Units.milliseconds);
			Timer microsecondTimer = new Timer(Timer.Units.microseconds);
			Timer nanosecondTimer = new Timer(Timer.Units.nanoseconds);
			
			secondTimer.start();
			millisecondTimer.start();
			microsecondTimer.start();
			nanosecondTimer.start();
			
			Thread.sleep(500);
			
			secondTimer.stop();
			millisecondTimer.stop();
			microsecondTimer.stop();
			nanosecondTimer.stop();
			
			System.out.println("Seconds: " + secondTimer.elapsed());
			System.out.println("Milliseconds: " + millisecondTimer.elapsed());
			System.out.println("Microseconds: " + microsecondTimer.elapsed());
			System.out.println("Nanoseconds: " + nanosecondTimer.elapsed());
			
			PlotterWindow plotWindow = new PlotterWindow(512, 512, 10, 10, 200, 1000);
			
			ColoredPath testLine = new ColoredPath();
			testLine.color(Color.RED);
			testLine.moveTo(20.0f,20.0f);
			testLine.lineTo(40.0f,40.0f);
			testLine.lineTo(60.0f,60.0f);
			testLine.lineTo(80.0f,80.0f);
			testLine.lineTo(100.0f,100.0f);
			testLine.lineTo(120.0f,120.0f);
			testLine.lineTo(140.0f,140.0f);
			testLine.lineTo(160.0f,160.0f);

			plotWindow.addLine(testLine);
			
			testLine = new ColoredPath();
			testLine.color(Color.GREEN);
			testLine.moveTo(20.0f, Math.pow(20.0f, 2));
			testLine.lineTo(40.0f, Math.pow(40.0f, 2));
			testLine.lineTo(60.0f, Math.pow(60.0f, 2));
			testLine.lineTo(80.0f, Math.pow(80.0f, 2));
			testLine.lineTo(100.0f, Math.pow(100.0f, 2));
			testLine.lineTo(120.0f, Math.pow(120.0f, 2));
			testLine.lineTo(140.0f, Math.pow(140.0f, 2));
			testLine.lineTo(160.0f, Math.pow(160.0f, 2));
			
			plotWindow.addLine(testLine);
			
			
			while(plotWindow.isShowing()) {}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvariantError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PreconditionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
