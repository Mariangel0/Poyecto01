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
@SessionAttributes({"productos", "proveedor", "productoEditar",  "productoBusqueda", "productoSeleccionado"})
public class controller {
    @Autowired
    private Service service;
    @ModelAttribute("proveedor") public Proveedor proveedor(){return new Proveedor();}
    @ModelAttribute("productos") public Iterable<Producto> clientes(){return new ArrayList<Producto>();}
    @ModelAttribute("productoEditar") public Producto clienteEditar(){return new Producto();}
    @ModelAttribute("productoBusqueda") public Cliente productoBusqueda(){return new Cliente();}

    @GetMapping("/presentation/productos/show")
    public String show(Model model,@ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor) {
        model.addAttribute("productos", service.productosFindAll(proveedor));
        model.addAttribute("productoEditar", new Producto());
        model.addAttribute("productoBusqueda", new Producto());
        return "/presentation/productos/Vista";
    }

    @GetMapping("/presentation/productos/editar/{codigo}")
    public String editar(@PathVariable("codigo") String codigo, Model model,
                         @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor){
        model.addAttribute("productoEditar", service.productoRead(codigo, proveedor.getIdentificacion()));
        return "/presentation/productos/Vista";
    }
    @PostMapping("/presentation/productos/crear")
    public String guardar(@ModelAttribute("productoEditar")Producto producto,
                          @ModelAttribute(name="proveedor",binding = false)Proveedor proveedor,
                          Model model){
        model.addAttribute("productoEditar", service.cambiarProducto(producto,proveedor));
        model.addAttribute("productos", service.productosFindAll(proveedor));
        return "/presentation/productos/Vista";
    }

    @PostMapping("/presentation/productos/buscar")
    public String buscar(@ModelAttribute("productoBusqueda") Producto productoBusqueda, Model model,
                         @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor){
        model.addAttribute("productos", service.busquedaProductos(productoBusqueda.getNombre(), proveedor.getIdentificacion()));
        return "/presentation/productos/Vista";
    }


}
