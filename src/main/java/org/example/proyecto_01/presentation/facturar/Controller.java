package org.example.proyecto_01.presentation.facturar;


import org.example.proyecto_01.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.service.annotation.GetExchange;

@org.springframework.stereotype.Controller("facturar")
@SessionAttributes({"proveedor", "cliente", "factura", "detalle"})
public class Controller {

    @Autowired
    private Service service;

    @ModelAttribute("proveedor")
    public Proveedor proveedor() {
        return new Proveedor();
    }

    @ModelAttribute("cliente")
    public Cliente cliente() {
        return new Cliente();
    }

    @ModelAttribute("factura")
    public Factura factura() {
        return new Factura();
    }

    @ModelAttribute("detalle")
    public Detalle detalle() {
        return new Detalle();
    }

    @GetMapping("/presentation/facturar/show")
    public String show() {
        return "/presentation/facturar/factura";
    }


    @PostMapping("/presentation/clientes/search")
    public String searchCliente(@ModelAttribute("cliente") Cliente clienteSearch, @ModelAttribute(name = "factura") Factura factura,
                                Model model) {
        try {
            model.addAttribute("cliente", service.clienteById(clienteSearch.getIdentificacion()));
            return "/presentation/facturar/factura";
        } catch (Exception e) {
            model.addAttribute("error", "El cliente no fue encontrado");
            return "/presentation/facturar/factura";
        }
    }

    @GetMapping("/presentation/facturas/show")
    public String show3() {
        return "/presentation/facturas/Facturas";
    }



}
