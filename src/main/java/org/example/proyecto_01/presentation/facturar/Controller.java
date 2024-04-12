package org.example.proyecto_01.presentation.facturar;


import org.example.proyecto_01.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Controller("facturar")
@SessionAttributes({"proveedor", "cliente", "factura", "detalle", "detalles", "producto"})
public class Controller {

    @Autowired
    private Service service;

    @ModelAttribute("proveedor")
    public Proveedor proveedor() {
        return new Proveedor();
    }

    @ModelAttribute("detalles")
    public List<Detalle> detalles() {
        return new ArrayList<>();
    }

    @ModelAttribute("cliente")
    public Cliente cliente() {
        return new Cliente();
    }

    @ModelAttribute("factura")
    public Factura factura() {
        return new Factura();
    }

    @ModelAttribute("total")
    public Double total() {
        return 0.0;
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
    public String show(Model model, @ModelAttribute("detalles") List<Detalle> detalles) {
        double total = updateTotal(detalles); // Calcular el total antes de mostrar la vista
        model.addAttribute("total", total); // Agregar el total al modelo
        return "/presentation/facturar/Vista";
    }

    @PostMapping("/presentation/clientes/search")
    public String searchCliente(Cliente cliente,
                                @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                                Model model) {
        model.addAttribute("cliente", service.clienteRead(proveedor.getIdentificacion(), cliente.getIdentificacion()));
        return "/presentation/facturar/Vista";
    }

    @GetMapping("/presentation/facturar/selectC")
    public String selectCliente(@RequestParam("cliID") String id, Model model,
                                @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor) {
        Cliente cliente = service.clienteRead(proveedor.getIdentificacion(), id);
        model.addAttribute("cliente", cliente);
        return "redirect:/presentation/facturar/show";
    }

    @GetMapping("/presentation/facturar/selectP")
    public String selectProducto(@RequestParam("proCod") String cod, Model model,
                                 @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                                 @ModelAttribute(name = "factura") Factura factura,
                                 @ModelAttribute("detalles") Iterable<Detalle> detalles,
                                 @ModelAttribute(name = "total") Double total) {
        Detalle nuevoDetalle = new Detalle();
        for (Detalle d : detalles) {
            if (Objects.equals(d.getProductoCodD(), cod)) {
                d.setCantidad(d.getCantidad() + 1);
                d.setMonto(d.getProductoByProductoCodD().getPrecio() * d.getCantidad());
                model.addAttribute("detalles", detalles);
                model.addAttribute("total", updateTotal(detalles));
                return "/presentation/facturar/Vista";
            }
        }

        nuevoDetalle = service.crearDetalle(nuevoDetalle, cod, proveedor.getIdentificacion());
        factura.getDetallesByCodigo().add(nuevoDetalle);
        model.addAttribute("detalles", factura.getDetallesByCodigo());
        model.addAttribute("total", updateTotal(detalles));
        model.addAttribute("detalle", new Detalle());

        return "redirect:/presentation/facturar/show";
    }


    @PostMapping("/presentation/facturar/agregar")
    public String searchProducto(String producto, @ModelAttribute("cliente") Cliente cliente,
                                 @ModelAttribute(name = "factura") Factura factura,
                                 @ModelAttribute(name = "detalle") Detalle detalle,
                                 @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                                 @ModelAttribute("detalles") Iterable<Detalle> detalles,
                                 Model model) {
        try {

            for (Detalle d : detalles) {
                if (Objects.equals(d.getProductoCodD(), producto)) {
                    d.setCantidad(d.getCantidad() + 1);
                    d.setMonto(d.getProductoByProductoCodD().getPrecio() * d.getCantidad());
                    model.addAttribute("detalles", detalles);
                    model.addAttribute("total", updateTotal(detalles));
                    return "/presentation/facturar/Vista";
                }
            }
            Detalle detalle1 = service.crearDetalle(detalle, producto, proveedor.getIdentificacion());
            factura.getDetallesByCodigo().add(detalle1);
            model.addAttribute("detalles", factura.getDetallesByCodigo());
            model.addAttribute("total", updateTotal(detalles));

            model.addAttribute("detalle", new Detalle());
            return "/presentation/facturar/Vista";
        } catch (Exception e) {
            model.addAttribute("error", "El cliente no fue encontrado");
            return "/presentation/facturar/Vista";
        }
    }


    @PostMapping("/presentation/facturar/crear")
    public String add(@ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                      @ModelAttribute(name = "factura") Factura factura, Model model,
                      @ModelAttribute(name = "cliente") Cliente cliente,
                      @ModelAttribute("detalles") Iterable<Detalle> detalles,
                      @ModelAttribute(name = "total") Double total) {
        try {
            if( cliente == null) {
                model.addAttribute("error", "El cliente no pudo ser encontrado");
            }
            if (factura.getDetallesByCodigo().isEmpty()) {
                model.addAttribute("error", "La factura no pudo ser creada");
            } else {
                Double total1 =  updateTotal(factura.getDetallesByCodigo());
                model.addAttribute("factura", service.crearFactura(factura, proveedor, cliente, total1));
                for (Detalle d : detalles) {
                    service.guardarDetalle(d, factura.getCodigo());
                }
            }
            model.addAttribute("detalles", new ArrayList<>());
            model.addAttribute("detalle", new Detalle());
            model.addAttribute("cliente", new Cliente());
            model.addAttribute("factura", new Factura());
            return "/presentation/facturar/Vista";
        } catch (Exception e) {
            model.addAttribute("error", "La factura no pudo ser creada");
            return "/presentation/facturar/Vista";
        }
    }

    @GetMapping("/presentation/facturar/sumar/{codigo}")
    public String sumarCantidad(@PathVariable("codigo") String codigo, Model model,
                                @ModelAttribute("detalles") Iterable<Detalle> detalles,
                                @ModelAttribute(name = "total") Double total) {
        for (Detalle detalle : detalles) {
            if (Objects.equals(detalle.getProductoCodD(), codigo)) {
                detalle.setCantidad(detalle.getCantidad() + 1);
                detalle.setMonto(detalle.getProductoByProductoCodD().getPrecio() * detalle.getCantidad());
            }
        }
        model.addAttribute("detalles", detalles);
        model.addAttribute("total", updateTotal(detalles));
        return "/presentation/facturar/Vista";
    }

    @GetMapping("/presentation/facturar/eliminar/{cod}")
    public String eliminar(Model model,
                           @ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                           @ModelAttribute(name = "factura") Factura factura,
                           @ModelAttribute("detalles") List<Detalle> detalles,
                           @PathVariable("cod") String cod) {
        Detalle detalleAEliminar = null;
        for (Detalle detalle : detalles) {
            if (Objects.equals(detalle.getProductoCodD(), cod)) {
                detalleAEliminar = detalle;
                break;
            }
        }

        if (detalleAEliminar != null) {
            if (detalleAEliminar.getCantidad() == 1) {
                factura.getDetallesByCodigo().remove(detalleAEliminar);
            } else {
                detalleAEliminar.setCantidad(detalleAEliminar.getCantidad() - 1);
                detalleAEliminar.setMonto(detalleAEliminar.getProductoByProductoCodD().getPrecio() * detalleAEliminar.getCantidad());
            }
        }

        model.addAttribute("detalles", factura.getDetallesByCodigo());
        model.addAttribute("total", updateTotal(factura.getDetallesByCodigo()));
        return "redirect:/presentation/facturar/show";
    }

    private double updateTotal(Iterable<Detalle> detalles) {
        double total = 0.0;
        for (Detalle detalle : detalles) {
            total += detalle.getMonto();
        }
        return total;
    }

}
