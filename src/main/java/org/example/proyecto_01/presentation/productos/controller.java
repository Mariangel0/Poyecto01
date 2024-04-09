package org.example.proyecto_01.presentation.productos;

import jakarta.servlet.http.HttpSession;
import org.example.proyecto_01.logic.Producto;
import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

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
}
