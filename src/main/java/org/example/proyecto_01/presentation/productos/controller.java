package org.example.proyecto_01.presentation.productos;

import jakarta.servlet.http.HttpSession;
import org.example.proyecto_01.logic.Cliente;
import org.example.proyecto_01.logic.Producto;
import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@org.springframework.stereotype.Controller("productos")
@SessionAttributes({"productos", "productoBusqueda", "productoEditar", "proveedor"})
public class controller {
    @Autowired
    private Service service;

    @ModelAttribute("productos") public Iterable<Producto> productos(){return new ArrayList<Producto>();}
    @ModelAttribute("productoBusqueda") public Producto productoBusqueda(){return new Producto();}
    @ModelAttribute("productoEditar") public Producto productoEditar(){return new Producto();}
    @ModelAttribute("proveedor") public Proveedor proveedor(){return new Proveedor();}

    @GetMapping("/presentation/productos/show")
    public String show(Model model, @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor){
        model.addAttribute("productos", service.productosFindAll(proveedor));
        return "/presentation/productos/Vista";
    }

    @GetMapping("/presentation/productos/edit/{identificacion}")
    public String editar(@PathVariable("identificacion") String identificacion, Model model,
                         @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor){
        model.addAttribute("clienteEditar", service.clienteRead(proveedor.getIdentificacion(), identificacion));
        return "redirect:/presentation/clientes/show";
    }
    @PostMapping("/presentation/productos/create")
    public String guardar(@ModelAttribute("productoEditar") Cliente clienteEdit,
                          @ModelAttribute(name="proveedor",binding = false)Proveedor proveedor,
                          Model model){
        model.addAttribute("productoEditar", service.cambiarCliente(clienteEdit,proveedor));
        model.addAttribute("productos", service.clientesFindAll(proveedor));
        model.addAttribute("productoEditar", new Cliente());
        return "redirect:/presentation/clientes/show";
    }
}
