package utez.edu.mx.manejoErrores.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import utez.edu.mx.manejoErrores.entity.Role;
import utez.edu.mx.manejoErrores.entity.UserApp;
import utez.edu.mx.manejoErrores.errors.UserDataEmptyException;
import utez.edu.mx.manejoErrores.errors.UserExistException;
import utez.edu.mx.manejoErrores.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public String findAllUser(Model model) {
        model.addAttribute("listUser", userService.findAllUser());
        return "";
    }

    @GetMapping("/specific/{id}")
    public String findUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "";

    }

    @GetMapping("/register")
    public String registerUser(Principal principal, RedirectAttributes flash) {
        if (principal != null) {
            flash.addFlashAttribute("title", "Sesión iniciada");
            flash.addFlashAttribute("info", "Ya cuenta con una sesión activa");
            flash.addFlashAttribute("typeAlert", "info");
            return "redirect:/index";
        }

        return "user/createAccount";
    }

    @PostMapping("/createUser")
    public String saveUser(@RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("surName") String surName,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            RedirectAttributes flash) {

        UserApp user = new UserApp();
        
        if (name.isEmpty() || lastName.isEmpty() || surName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            throw new UserDataEmptyException("");
        }

        user.setName(name);
        user.setLastName(lastName);
        user.setSurName(surName);
        user.setUsername(username);
        user.setPassword(password);

        List<Role> roles = new ArrayList<>();

        Role rol = new Role();
        rol.setAuthority("ROLE_CLIENT");

        roles.add(rol);
        
        user.setRoles(roles);
        user.setEnabled(true);
        user.normalizeInfo();

        UserApp validateNotExistUser = userService.findByUsername(user.getUsername());

        if (validateNotExistUser != null) {
            throw new UserExistException(user.getUsername());
        }

        logger.info("Data to user sended " + user);

        UserApp userInserted = userService.saveUser(user);

        if (userInserted != null) {
            flash.addFlashAttribute("title", "Usuario registrado con existo");
            flash.addFlashAttribute("info",
                    "El usuario ".concat(userInserted.getUsername().concat(" se ha registrado con éxito")));
            flash.addFlashAttribute("typeAlert", "success");
            return "redirect:/index";
        }

        return "redirect:/user/register";
    }
}
