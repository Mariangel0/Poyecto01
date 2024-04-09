package org.example.proyecto_01.presentation.facturar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@org.springframework.stereotype.Controller("facturar")
@SessionAttributes({"proveedor"})
        public class Controller {

    @GetMapping("/presentation/facturar/show")
    public String show() {
        return "/presentation/facturar/factura";
    }

    @GetMapping("/presentation/facturas/show")
    public String show3() {
        return "/presentation/facturas/Facturas";
    }


}
