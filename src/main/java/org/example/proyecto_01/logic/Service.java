package org.example.proyecto_01.logic;

import org.example.proyecto_01.data.ClienteRepository;
import org.example.proyecto_01.data.ProveedorRepository;
import org.example.proyecto_01.data.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    private HaciendaStub hacienda;

    public Iterable<Cliente> clienteFindAll() {
        return clienteRepository.findAll();
    }

    public Iterable<Proveedor> proveedorFindAll() {
        return proveedorRepository.findAll();
    }

    public Usuario usuarioRead(String id) {

        return usuarioRepository.findById(id).orElse(null);
    }

    public Proveedor proveedorRead(String id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Usuario autenticarUsuario(String identificacion, String clave) {
        return usuarioRepository.findByIdentificacionAndClave(identificacion, clave);
    }

    public Iterable<Proveedor> proveedoressAceptados() {
        return proveedorRepository.findAllByestado((byte) 1);
    }

    public Iterable<Proveedor> proveedoresEspera() {
        return proveedorRepository.findAllByestado((byte) 0);
    }

    public void crearUsuario(String identificacion, String clave, String nombre, String correo) {
        validateIdentificacionClave(identificacion, clave);

        // Verificar si el proveedor ya existe
        Proveedor proveedorExistente = proveedorRepository.findByidentificacion(identificacion);
        if (proveedorExistente != null) {
            throw new IllegalArgumentException("El proveedor con identificación " + identificacion + " ya está registrado.");
        }

        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setIdentificacion(identificacion);
        usuario.setClave(clave);
        usuario.setRol("PRO");
        usuarioRepository.save(usuario);

        // Crear un nuevo proveedor
        Proveedor proveedor = new Proveedor();
        proveedor.setIdentificacion(identificacion);
        proveedor.setNombre(nombre);
        proveedor.setCorreo(correo);
        proveedor.setEstado((byte) 0);
        proveedorRepository.save(proveedor);
    }

    private void validateIdentificacionClave(String identificacion, String clave) {
        if (identificacion == null || identificacion.isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser vacía");
        }
        if (clave == null || clave.isEmpty()) {
            throw new IllegalArgumentException("La clave no puede ser vacía");
        }
    }

}
