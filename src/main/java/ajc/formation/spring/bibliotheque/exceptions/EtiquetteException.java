package ajc.formation.spring.bibliotheque.exceptions;

public class EtiquetteException extends RuntimeException {
	public EtiquetteException() {
		
	}

	public EtiquetteException(String message) {
		super(message);
	}
	
	public static class IdIntrouvable extends EtiquetteException {
		public IdIntrouvable() {
			
		}
		
		public IdIntrouvable(String message) {
			super(message);
		}
	}
}
