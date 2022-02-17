package utez.edu.mx.manejoErrores.errors;

public class UserDataEmptyException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UserDataEmptyException(String message) {
    super("Los datos de entrada para el registro del usuario están vacíos");
  }

}
