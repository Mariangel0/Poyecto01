package org.example.proyecto_01.presentation.facturar;


import jakarta.servlet.http.HttpSession;
import org.example.proyecto_01.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller("facturar")
@SessionAttributes({"proveedor", "cliente", "factura", "detalle", "detalles", "producto"})
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

    @ModelAttribute("producto")
    public Producto producto() {
        return new Producto();
    }

    @ModelAttribute("detalle")
    public Detalle detalle() {
        return new Detalle();
    }

    @GetMapping("/presentation/facturar/show")
    public String show() {
        return "/presentation/facturar/Vista";
    }

    @PostMapping("/presentation/clientes/search")
    public String searchCliente(@ModelAttribute("cliente") Cliente clienteSearch, @ModelAttribute(name = "factura") Factura factura, @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                                Model model) {
        try {
            model.addAttribute("cliente", service.clienteById(clienteSearch.getIdentificacion(), proveedor));
            return "/presentation/facturar/Vista";
        } catch (Exception e) {
            model.addAttribute("error", "El cliente no fue encontrado");
            return "/presentation/facturar/Vista";
        }
    }

    @PostMapping("/presentation/productos/agregar")
    public String searchProducto(String producto, @ModelAttribute("cliente") Cliente cliente,
                                 @ModelAttribute(name = "factura") Factura factura,
                                 @ModelAttribute(name = "detalle") Detalle detalle,
                                 @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                                 Model model) {
        try {
            factura.setClienteByClienteNum(service.clienteByNum(cliente.getNumCliente()));
            model.addAttribute("detalle", service.crearDetalle(detalle, producto, factura.getCodigo(), proveedor.getIdentificacion()));
            factura.getDetallesByCodigo().add(detalle);
            model.addAttribute("detalles", factura.getDetallesByCodigo());
            model.addAttribute("detalle", new Detalle());
            return "/presentation/facturar/Vista";
        } catch (Exception e) {
            model.addAttribute("error", "El cliente no fue encontrado");
            return "/presentation/facturar/Vista";
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
