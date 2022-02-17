package utez.edu.mx.manejoErrores.errors;

public class ProductNotExistException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ProductNotExistException(String message) {
    super("El producto con id " + message + " no existe");
  }

}
