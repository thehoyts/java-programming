/**
 * 
 */
package programming.tools;

import programming.contract.*;

/**
 * @author Alan Hoyt
 *
 */
public class Timer {
	public enum Units {
		unknown,
		nanoseconds,
		microseconds,
		milliseconds,
		seconds
	}

	final private long INVALID_TIME = -1;
	
	private long start_ = INVALID_TIME;
	private long end_ = INVALID_TIME;
	private Units units_ = Units.unknown;

	public Timer(Units units) throws PreconditionError {
		PreconditionError.Test(units == Units.unknown, 
				"The units were not specified for the timer.");
		
		units_ = units;
	}
	
	public Units units() {
		return units_;
	}
	
	public void start() throws InvariantError {
		InvariantError.Test(start_ != INVALID_TIME, "The timer is already started.");
		
		switch(this.units()) {
		case nanoseconds:
			start_ = System.nanoTime();
			break;
			
		case microseconds:
			start_ = System.nanoTime() / 1000;
			break;
			
		case milliseconds:
			start_ = System.nanoTime() / (1000 * 1000);
			break;
			
		case seconds:
			start_ = System.nanoTime() / (1000 * 1000 * 1000);
			break;
			
		default:
			InvariantError.Test(true, "The units should not be unknown.");
		};
	}
	
	public void stop() throws InvariantError {
		InvariantError.Test(start_ == INVALID_TIME, "start() must be called before end().");
		InvariantError.Test(end_ != INVALID_TIME, "The timer has already been stopped.");
		switch(this.units()) {
		case nanoseconds:
			end_ = System.nanoTime();
			break;
			
		case microseconds:
			end_ = System.nanoTime() / 1000;
			break;
			
		case milliseconds:
			end_ = System.nanoTime() / (1000 * 1000);
			break;
			
		case seconds:
			end_ = System.nanoTime() / (1000 * 1000 * 1000);
			break;
			
		default:
			InvariantError.Test(true, "The units should not be unknown.");
		};
		
	}
	
	public long elapsed() throws InvariantError {
		InvariantError.Test(start_ == INVALID_TIME || end_ == INVALID_TIME, 
				"start and stop must be called before elapsed can return a value.");
		
		return end_ - start_;
	}
}
