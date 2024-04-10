package org.example.proyecto_01.presentation.clientes;

import org.example.proyecto_01.logic.Cliente;
import org.example.proyecto_01.logic.Producto;
import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@org.springframework.stereotype.Controller("clientes")
@SessionAttributes({"clientes", "proveedor", "clienteEditar"})
public class controller {
    @Autowired
    private Service service;
    @ModelAttribute("clientes") public Iterable<Cliente> clientes(){return new ArrayList<Cliente>();}
    @ModelAttribute("clienteEditar") public Cliente clienteEditar(){return new Cliente();}

    @GetMapping("/presentation/clientes/show")
    public String show(Model model,@ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor) {
        model.addAttribute("clientes", service.clientesFindAll(proveedor));
        model.addAttribute("clienteEditar", new Cliente());
        return "/presentation/clientes/Vista";
    }

    @GetMapping("/presentation/clientes/edit/{identificacion}")
    public String editar(@PathVariable("identificacion") String identificacion, Model model,
                         @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor){
        model.addAttribute("clienteEditar", service.clienteRead(proveedor.getIdentificacion(), identificacion));
        return "/presentation/clientes/Vista";
    }
    @PostMapping("/presentation/clientes/create")
    public String guardar(@ModelAttribute("clienteEditar")Cliente clienteEdit,
                       @ModelAttribute(name="proveedor",binding = false)Proveedor proveedor,
                       Model model){
        model.addAttribute("clienteEditar", service.cambiarCliente(clienteEdit,proveedor));
        model.addAttribute("clientes", service.clientesFindAll(proveedor));
        return "/presentation/clientes/Vista";
    }
}
