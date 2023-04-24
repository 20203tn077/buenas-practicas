package mx.edu.utez.extraordinario.controller;

import jakarta.validation.Valid;
import mx.edu.utez.extraordinario.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("clientes")
    public String clientList() {
        return "views/client_list";
    }

    @GetMapping("pedidos")
    public String orderList() {
        return "views/order_list";
    }

    @GetMapping("productos")
    public String productList() {
        return "views/product_list";
    }

    @GetMapping("capturistas")
    public String transcriberList() {
        return "views/transcriber_list";
    }

    @GetMapping("registrarse")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "views/register";
    }

    @PostMapping("register")
    public String register(@Valid Model model, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("user", new User());
            return "views/register";
        }
        return "redirect:/";
    }
}
