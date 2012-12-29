/**
 * 
 */
package programming.contract;

/**
 * @author Alan
 *
 */
public class PreconditionError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8749262457471557935L;

	/**
	 * 
	 */
	public PreconditionError() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public PreconditionError(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public PreconditionError(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public PreconditionError(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param expression If this is true, then the exception is thrown, otherwise
	 * nothing will happen.
	 * @param message Is the message to add to the exception if it is thrown.
	 * @throws PreconditionError
	 */
	static public void Test(boolean expression, String message) throws PreconditionError {
		if (!expression)
			return;
		
		throw new PreconditionError(message);
	}
}
