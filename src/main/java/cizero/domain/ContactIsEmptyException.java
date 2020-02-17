package cizero.domain;

public class ContactIsEmptyException extends Exception{

	private static final long serialVersionUID = 3L;
	private String msg;
	
	public ContactIsEmptyException(String arg){
        System.out.println(arg);
    }
	
	@Override
	public String getMessage() {
		return this.msg;
	}

}
