package org.example.proyecto_01.presentation.login;

import jakarta.servlet.http.HttpSession;
import org.example.proyecto_01.logic.Service;
import org.example.proyecto_01.logic.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller {

    @Autowired
    private Service service;

    @GetMapping("/")
    public String showHomePage() {
        return "presentation/login/Vista";
    }

    @GetMapping("/presentation/login/show")
    public String show() {
        return "/presentation/login/Vista";
    }

    @PostMapping("/presentation/login/login")
    public String login(@RequestParam("nombre") String nombre,
                        @RequestParam("clave") String clave,
                        HttpSession httpSession) {
        try {
            Usuario usuarioDB = service.autenticarUsuario(nombre, clave);
            if (usuarioDB != null) {
                httpSession.setAttribute("usuario", usuarioDB);
                httpSession.setAttribute("proveedor", service.proveedorRead(usuarioDB.getIdentificacion()));
                switch (usuarioDB.getRol()) {
                    case "PRO":
                        return "redirect:/presentation/facturar/show";
                    case "ADM":
                        return "redirect:/presentation/facturar/factura";
                    default:
                        return "redirect:/error";
                }
            } else {
                return "redirect:/error";
            }
        } catch (Exception e) {
            return "redirect:/error";
        }
    }

    @GetMapping("/presentation/login/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "/presentation/login/Vista";
    }

    @GetMapping("/presentation/login/registrar")
    public String registrar() {
        return "/presentation/login/registrar";
    }

}