package org.example.proyecto_01.presentation.facturar;


import org.example.proyecto_01.logic.Cliente;
import org.example.proyecto_01.logic.Factura;
import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@org.springframework.stereotype.Controller("facturar")
@SessionAttributes({"proveedor", "cliente", "factura"})
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

    @GetMapping("/presentation/facturar/show")
    public String show() {
        return "/presentation/facturar/factura";
    }



    @PostMapping("/presentation/clientes/search")
    public String searchCliente(@ModelAttribute("cliente") Cliente clienteSearch, @ModelAttribute(name = "factura") Factura factura,
                                Model model) {
        try {
            System.out.println(clienteSearch.getIdentificacion() + "arroz");
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
