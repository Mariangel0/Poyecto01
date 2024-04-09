package org.example.proyecto_01.presentation.proveedores;

import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;

@org.springframework.stereotype.Controller("proveedores")
@SessionAttributes({"proveedoresAceptados","proveedoresEspera"})
public class Controller {

    @Autowired
    private Service service;

    @ModelAttribute("proveedoresAceptados") public Iterable<Proveedor> proveedoresAceptados(){return new ArrayList<Proveedor>();}
    @ModelAttribute("proveedoresEnEspera") public Iterable<Proveedor> proveedoresEnEspera(){return new ArrayList<Proveedor>();}

    @GetMapping("/presentation/proveedores/show")
    public String show(Model model){
//        model.addAttribute("proveedoresAceptados", service.proveedoressAceptados());
//        model.addAttribute("proveedoresEspera", service.proveedoresEspera());
        return "/presentation/proveedores/Vista";
    }

}
