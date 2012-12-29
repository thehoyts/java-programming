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
	private static final long serialVersionUID = -1271304296700152807L;


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
	 * 
	 * @param expression If this is true, then the exception is thrown, otherwise
	 * nothing will happen.
	 * @param message Is the message to add to the exception if it is thrown.
	 * @throws PreconditionError
	 */
	static public void Test(boolean expression, String message) throws PostconditionError {
		if (!expression)
			return;
		
		throw new PostconditionError(message);
	}

}
