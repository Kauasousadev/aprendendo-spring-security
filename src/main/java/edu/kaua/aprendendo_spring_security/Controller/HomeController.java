package edu.kaua.aprendendo_spring_security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // Página pública
    }

    @GetMapping("/storage")
    public String protectedPage() {
        return "storage"; // Página protegida
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Página de login
    }
}
