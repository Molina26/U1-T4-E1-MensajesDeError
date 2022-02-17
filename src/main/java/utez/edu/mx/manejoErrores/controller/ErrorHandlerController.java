package utez.edu.mx.manejoErrores.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import utez.edu.mx.manejoErrores.errors.ProductNotExistException;
import utez.edu.mx.manejoErrores.errors.UserDataEmptyException;
import utez.edu.mx.manejoErrores.errors.UserExistException;

@ControllerAdvice
public class ErrorHandlerController {

  @ExceptionHandler({ ArithmeticException.class })
  public String arithmeticError(ArithmeticException ex, Model model) {

    model.addAttribute("error", "Error de aritmética");
    model.addAttribute("message", ex.getMessage());
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    model.addAttribute("timestamp", new Date());

    return "error/aritmetica";
  }

  @ExceptionHandler(NumberFormatException.class)
  public String numberFormat(NumberFormatException ex, Model model) {
    model.addAttribute("error", "Error de number format");
    model.addAttribute("message", ex.getMessage());
    model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    model.addAttribute("timestamp", new Date());
    return "error/numberFormat";
  }

  @ExceptionHandler({ MissingServletRequestParameterException.class })
  public String requestParameters(MissingServletRequestParameterException ex, Model model) {
    model.addAttribute("title", "Error por parámetros no enviados");
    model.addAttribute("message", ex.getMessage());
    model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
    model.addAttribute("timestamp", new Date());

    return "error/parameters";
  }

  @ExceptionHandler({ UserExistException.class })
  public String userDuplicated(UserExistException ex, RedirectAttributes flash) {

    flash.addFlashAttribute("title", "Usuario no disponible");
    flash.addFlashAttribute("info", ex.getMessage());
    flash.addFlashAttribute("typeAlert", "error");

    return "redirect:/user/register";
  }

  @ExceptionHandler({ UserDataEmptyException.class })
  public String userDataEmpty(UserDataEmptyException ex, RedirectAttributes flash) {

    flash.addFlashAttribute("title", "Valores vacíos");
    flash.addFlashAttribute("info", ex.getMessage());
    flash.addFlashAttribute("typeAlert", "error");

    return "redirect:/user/register";
  }

  @ExceptionHandler({ ProductNotExistException.class })
  public String productNotExist(ProductNotExistException ex, Model model) {

    model.addAttribute("error", "Error en consulta de producto");
    model.addAttribute("message", ex.getMessage());
    model.addAttribute("status", HttpStatus.NOT_FOUND.value());
    model.addAttribute("timestamp", new Date());

    return "product/errorProductNotExist";
  }
}
