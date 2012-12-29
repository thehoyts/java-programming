/**
 * 
 */
package programming.contract;

/**
 * @author Alan
 *
 */
public class InvariantError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6989835052480079609L;

	/**
	 * 
	 */
	public InvariantError() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InvariantError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InvariantError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvariantError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvariantError(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	static public void Test(boolean expression, String message) throws InvariantError {
		if (!expression)
			return;
		
		throw new InvariantError(message);
	}

}
