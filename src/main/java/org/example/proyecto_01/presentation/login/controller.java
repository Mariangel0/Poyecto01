package org.example.proyecto_01.presentation.login;

import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @Autowired
    private Service service;

    @GetMapping("/")
    public String showHomePage() {
        return "presentation/login/Vista";
    }




}