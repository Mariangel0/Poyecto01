package org.example.proyecto_01.logic;

import org.example.proyecto_01.data.ClienteRepository;
import org.example.proyecto_01.data.ProveedorRepository;
import org.example.proyecto_01.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    public Iterable<Cliente> clienteFindAll(){
        return clienteRepository.findAll();
    }

    public Usuario usuarioRead(String id){

        return usuarioRepository.findById(id).orElse(null);
    }

    public Proveedor proveedorRead(String id){
        return proveedorRepository.findById(id).orElse(null);
    }

    public Usuario autenticarUsuario(String identificacion, String clave) {
        return usuarioRepository.findByIdentificacionAndClave(identificacion, clave);
    }


}
