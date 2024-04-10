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
@SessionAttributes({"proveedor", "cliente", "factura", "detalle", "detalles"})
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

    @PostMapping("/presentation/productos/agregar")
    public String searchProducto(String codigo, @ModelAttribute("cliente") Cliente cliente,
                                 @ModelAttribute(name = "factura") Factura factura,
                                 @ModelAttribute(name = "detalle") Detalle detalle,
                                 @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                                 Model model) {
        System.out.println(codigo + "perro");
        try {
            factura.setClienteByClienteNum(service.clienteByNum(cliente.getNumCliente()));
            model.addAttribute("detalle", service.crearDetalle(detalle, codigo, factura.getCodigo(), proveedor.getIdentificacion()));
            factura.getDetallesByCodigo().add(detalle);
            model.addAttribute("detalles", factura.getDetallesByCodigo());
            model.addAttribute("detalle", new Detalle());
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

    @GetMapping("/presentation/facturas/eliminar")
    public String eliminar() {
        return "/presentation/facturas/Facturas";
    }

    @GetMapping("/presentation/facturas/add")
    public String add() {
        return "/presentation/facturas/Facturas";
    }


}
