package utez.edu.mx.manejoErrores.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import utez.edu.mx.manejoErrores.service.ProductService;

@Controller
public class MainController {

  private Logger logger = LoggerFactory.getLogger(MainController.class);

  @Autowired
  private ProductService productService;

  @GetMapping("/")
  public String goToIndex() {
    return "forward:/index";
  }

  @GetMapping("/index")
  public String index(Authentication authentication, Model model) {
    
    if (authentication != null) {
      boolean flag = false;

      logger.info("username with session : " + authentication.getName());

      for (GrantedAuthority item : authentication.getAuthorities()) {
        if (item.getAuthority().toString().contains("ADMIN")) {
          flag = true;
          break;
        }
      }

      if (flag) {
        return "index";
      }
    }

    logger.info("read list of products");
    model.addAttribute("listProducts", productService.findAllProduct());

    return "index";
  }

}
