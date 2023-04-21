package ajc.formation.spring.bibliotheque.exceptions;

public class EmpruntException extends RuntimeException{
	
	public EmpruntException() {}
	
	public EmpruntException(String message) {
		super(message);
	}

}
