package org.example.proyecto_01.presentation.proveedores;

import jakarta.servlet.http.HttpSession;
import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@org.springframework.stereotype.Controller("proveedores")
@SessionAttributes({"proveedoresAceptados","proveedoresEspera", "proveedorEdit"})
public class Controller {

    @Autowired
    private Service service;

    @ModelAttribute("proveedoresAceptados") public Iterable<Proveedor> proveedoresAceptados(){return new ArrayList<Proveedor>();}
    @ModelAttribute("proveedoresEnEspera") public Iterable<Proveedor> proveedoresEnEspera(){return new ArrayList<Proveedor>();}
    @ModelAttribute("proveedorEdit") public Proveedor clienteEdit(){return new Proveedor();}
    @GetMapping("/presentation/proveedores/show")
    public String show(Model model){
        model.addAttribute("proveedoresAceptados", service.proveedoressAceptados());
        model.addAttribute("proveedoresEspera", service.proveedoresEspera());
        return "/presentation/proveedores/Vista";
    }

    @GetMapping("/presentation/proveedores/aprobar/{identificacion}")
    public String aprobarProveedores(@PathVariable("identificacion") String identificacion,
                                     Model model) {
        Proveedor proveedorE = service.proveedorRead(identificacion);
        proveedorE.setEstado((byte) 1);
        service.cambiarProveedor(proveedorE);
        return "redirect:/presentation/proveedores/show";
    }
    @GetMapping("/presentation/proveedores/delete/{identificacion}")
    public String delete(@PathVariable("identificacion") String identificacion){
        service.eliminarProovedor(identificacion);
        return "/presentation/proveedores/Vista";
    }


}
