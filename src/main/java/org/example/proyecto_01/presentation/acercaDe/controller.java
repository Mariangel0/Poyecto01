package org.example.proyecto_01.presentation.acercaDe;


import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller("acercaDe")
public class controller {
    @GetMapping("/presentation/acercadeDe/show")
    public String show() {
        return "/presentation/acercaDe/Vista";
    }
}
