package org.example.proyecto_01.presentation.clientes;

import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@org.springframework.stereotype.Controller("clientes")
public class controller {
    @Autowired
    private Service service;

    @GetMapping("/presentation/clientes/show")
    public String show(Model model,@ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor) {
        model.addAttribute("clientes", service.clientesFindAll(proveedor));
        return "/presentation/clientes/Vista";
    }
}
