package cizero.storage;

public class ContactNotAddedException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     */
	
	private String msg;
	
	

	public ContactNotAddedException(String arg){
        System.out.println(arg);
    }
	
	@Override
	public String getMessage() {
		return this.msg;
	}

}
