package org.example.proyecto_01.logic;

import com.itextpdf.layout.element.Paragraph;
import org.example.proyecto_01.data.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;

@org.springframework.stereotype.Service("service")
public class Service {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private DetalleRepository detalleRepository;


    //proveedores
    public void cambiarProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    public Cliente cambiarCliente(Cliente cliente, Proveedor proveedor) {
        cliente.setProveedorIdC(proveedor.getIdentificacion());
        return clienteRepository.save(cliente);
    }

    public Factura crearFactura(Factura factura, Proveedor proveedor, Cliente cliente) {
        factura.setProveedorIdF(proveedor.getIdentificacion());
        LocalDate fecha = LocalDate.now();
        factura.setFecha(Date.valueOf(fecha));
        factura.setClienteByClienteNum(cliente);
        factura.setClienteNum(cliente.getNumCliente());

        return  facturaRepository.save(factura);
    }


    public Producto cambiarProducto(Producto producto, Proveedor proveedor) {
        producto.setProveedorIdP(proveedor.getIdentificacion());
        return productoRepository.save(producto);
    }

    public void eliminarProovedor(String id) {
        proveedorRepository.deleteById(id);
        usuarioRepository.deleteById(id);

    }

    public Iterable<Producto> productosFindAll(Proveedor proveedor) {
        return productoRepository.findByProveedorIdP(proveedor.getIdentificacion());
    }
    public Iterable<Factura> facturasFindAll(Proveedor proveedor) {
        return facturaRepository.findAllByProveedorIdF(proveedor.getIdentificacion());
    }

    public Iterable<Cliente> clientesFindAll(Proveedor proveedor) {
        return clienteRepository.findByProveedorIdC(proveedor.getIdentificacion());
    }

    public Cliente clienteRead(String idP, String idC) {

        return clienteRepository.findByProveedorIdCAndIdentificacion(idP, idC);

    }

    public Iterable<Proveedor> proveedorFindAll() {
        return proveedorRepository.findAll();
    }

    public Iterable<Cliente> busquedaClientes(String nom, String id) {
        return clienteRepository.findByNombreContainingAndProveedorIdC(nom, id);
    }

    public Iterable<Producto> busquedaProductos(String nom, String id) {
        return productoRepository.findByNombreContainingAndProveedorIdP(nom, id);
    }

    public Usuario usuarioRead(String id) {

        return usuarioRepository.findById(id).orElse(null);
    }

    public Producto productoRead(String cod, String id) {

        return productoRepository.findByCodigoAndProveedorIdP(cod, id);
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


    public Cliente clienteById(String identificacion, Proveedor proveedor) {
        return clienteRepository.findByProveedorIdCAndIdentificacion(proveedor.getIdentificacion(), identificacion);
    }

    public Cliente clienteByNum(int num) {
        return clienteRepository.findByNumcliente(num);
    }

    // DETALLE

    public Detalle crearDetalle(Detalle detalle, String codigoProd, String idProveedor) {
        detalle.setProductoCodD(codigoProd);
        detalle.setProductoByProductoCodD(productoRepository.findByCodigoAndProveedorIdP(codigoProd, idProveedor));
        detalle.setMonto(productoRepository.findByCodigoAndProveedorIdP(codigoProd, idProveedor).getPrecio() * detalle.getCantidad());
        return detalle;

    }



    public Factura facturaRead(int cod){
        return facturaRepository.findByCodigo(cod);
    }

    public Iterable<Detalle> detalles(int codFactura){
        return detalleRepository.findAllByFacturaCodD(codFactura);
    }

    public void guardarDetalle(Detalle detalle, int factura) {
        detalle.setFacturaCodD(factura);
        detalle.setFacturaByFacturaCodD(facturaRepository.findByCodigo(factura));
        detalleRepository.save(detalle);
    }

}
