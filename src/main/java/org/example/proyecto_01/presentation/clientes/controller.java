package org.example.proyecto_01.presentation.clientes;

import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller("clientes")
public class controller {
    @Autowired
    private Service service;

    @GetMapping("/presentation/clientes/show")
    public String show(Model model){
        model.addAttribute("clientes", service.clienteFindAll());
        return "Vista";
    }
}
