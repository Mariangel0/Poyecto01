package org.example.proyecto_01.presentation.login;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.proyecto_01.logic.Proveedor;
import org.example.proyecto_01.logic.Service;
import org.example.proyecto_01.logic.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@org.springframework.stereotype.Controller("usuario")
@SessionAttributes({"proveedor"})
public class controller {

    @Autowired
    private Service service;

    @ModelAttribute("usuario")
    public Usuario UsuarioSearch() {
        return new Usuario();
    }
    @ModelAttribute("proveedor")
    public Proveedor proveedor() {
        return new Proveedor();
    }

    @GetMapping("/")
    public String showHomePage() {
        return "presentation/login/Vista";
    }

    @GetMapping("/presentation/login/show")
    public String show() {
        return "/presentation/login/Vista";
    }


    @PostMapping("/presentation/login/login")
    public String login(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
                        HttpSession httpSession, Model model) {
        try {
            Usuario usuarioDB = service.autenticarUsuario(usuario.getIdentificacion(), usuario.getClave());
            if (usuarioDB != null) {
                if (Objects.equals(usuarioDB.getRol(), "ADM") || service.proveedorRead(usuarioDB.getIdentificacion()).getEstado().equals((byte) 1)) {
                    httpSession.setAttribute("usuario", usuarioDB);
                    httpSession.setAttribute("proveedor", service.proveedorRead(usuarioDB.getIdentificacion()));
                    model.addAttribute("proveedor", service.proveedorRead(usuarioDB.getIdentificacion()));
                    switch (usuarioDB.getRol()) {
                        case "PRO":
                            return "redirect:/presentation/facturar/show";
                        case "ADM":
                            return "redirect:/presentation/proveedores/show";
                    }
                }
            }
            model.addAttribute("error", "Credenciales incorrectas. Por favor, intente de nuevo.");
            return "/presentation/login/Vista";

        } catch (Exception e) {
            model.addAttribute("error", "Credenciales incorrectas. Por favor, intente de nuevo.");
            return "/presentation/login/Vista";
        }

    }

    @GetMapping("/presentation/login/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "/presentation/login/Vista";
    }

    @GetMapping("/presentation/login/perfil")
    public String configurarPerfil(@ModelAttribute(name = "proveedor", binding = false) Proveedor proveedor,
                                   Model model) {
        model.addAttribute("proveedor", service.proveedorRead(proveedor.getIdentificacion()));
        return "/presentation/login/Perfil";
    }
    @PostMapping("/presentation/login/modificar")
    public String guardar(@ModelAttribute(name = "proveedor") Proveedor proveedor,
                          Model model) {
        try {
            model.addAttribute("proveedor", service.editarProveedor(proveedor));
            model.addAttribute("exito", "Los cambios han sido guardados correctamente");
            return "/presentation/login/Perfil";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo realizar el cambio. Por favor, intente de nuevo.");
            return "/presentation/login/Perfil";
        }
    }

    @PostMapping("/presentation/login/registro")
    public String registrar(String identificacion, String clave, String nombre, String correo, HttpSession httpSession, Model model) {
        try {

            service.crearUsuario(identificacion, clave, nombre, correo);
            model.addAttribute("exito", "El usuario ha sido registrado");
            httpSession.setAttribute("proveedor", service.proveedorRead(identificacion));
            return "/presentation/login/registrar";
        } catch (Exception e) {
            model.addAttribute("error", "El usuario no pudo ser registrado");
        }
        return "/presentation/login/registrar";
    }

}