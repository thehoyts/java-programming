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
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public PreconditionError(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	
	static public void Test(boolean expression, String message) throws PreconditionError {
		if (!expression)
			return;
		
		throw new PreconditionError(message);
	}
}
