package utez.edu.mx.manejoErrores.controller;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import utez.edu.mx.manejoErrores.entity.Product;
import utez.edu.mx.manejoErrores.service.ProductService;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("")
    public String findAllProduct(Model model) {
        model.addAttribute("listProducts", productService.findAllProduct());
        return "product/listProducts";
    }

    @Secured({ "ROLE_CLIENT" })
    @GetMapping("/specific/{id}")
    public String findProductById(@PathVariable("id") long id, Model model) throws NoSuchElementException {

        Product product = productService.findProductById(id);

        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        model.addAttribute("description", product.getDescription());
        model.addAttribute("folio", product.getFolio());
        model.addAttribute("isActive", product.isActive());

        return "product/productDetail";

    }

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("/register")
    public String registerProduct() {
        return "product/createProduct";
    }

    @Secured({ "ROLE_ADMIN" })
    @PostMapping("/createProduct")
    public String saveProduct(@RequestParam("name") String name,
            @RequestParam("price") float price,
            @RequestParam("description") String description,
            @RequestParam("isActive") boolean isActive) {

        Product product = new Product();

        UUID uuid = UUID.randomUUID();

        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setActive(isActive);
        product.setFolio(uuid.toString());
        product.setDate(new Date());

        boolean flag = productService.saveProduct(product);

        if (flag) {
            logger.info("product inserted c:");
            return "redirect:/product";
        }

        return "redirect:/product/register";
    }

}
