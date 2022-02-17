package utez.edu.mx.manejoErrores.errors;

public class UserExistException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public UserExistException(String message) {
    super("El usuario ".concat(message).concat(" no se encuentra disponible"));
  }

}
