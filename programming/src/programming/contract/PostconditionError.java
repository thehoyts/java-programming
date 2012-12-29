/**
 * 
 */
package programming.contract;

/**
 * @author Alan
 *
 */
public class PostconditionError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5031993278336991146L;

	/**
	 * 
	 */
	public PostconditionError() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public PostconditionError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public PostconditionError(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PostconditionError(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PostconditionError(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	static public void Test(boolean expression, String message) throws PostconditionError {
		if (!expression)
			return;
		
		throw new PostconditionError(message);
	}

}
