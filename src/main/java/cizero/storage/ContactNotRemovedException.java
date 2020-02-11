package cizero.storage;

public class ContactNotRemovedException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	/**
     *
     */
	
	private String msg;
	
	

	ContactNotRemovedException(String arg){
        System.out.println(arg);
    }
	
	@Override
	public String getMessage() {
		return this.msg;
	}

}
