package org.example.proyecto_01.presentation.facturar;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller("facturar")
public class Controller {

    @GetMapping("/presentation/facturar/show")
    public String show() {
        return "/presentation/facturar/factura";
    }
}
