package org.example.proyecto_01.logic;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Proveedor {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "identificacion")
    private String identificacion;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "correo")
    private String correo;
    @Basic
    @Column(name = "estado")
    private Byte estado;
    @OneToMany(mappedBy = "proveedorByProveedorIdC")
    private Collection<Cliente> clientesByIdentificacion;
    @OneToMany(mappedBy = "proveedorByProveedorIdF")
    private Collection<Factura> facturasByIdentificacion;
    @OneToMany(mappedBy = "proveedorByProveedorIdP")
    private Collection<Producto> productosByIdentificacion;
    @OneToOne
    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion", nullable = false)
    private Usuario usuarioByIdentificacion;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return Objects.equals(identificacion, proveedor.identificacion) && Objects.equals(nombre, proveedor.nombre) && Objects.equals(correo, proveedor.correo) && Objects.equals(estado, proveedor.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacion, nombre, correo, estado);
    }

    public Collection<Cliente> getClientesByIdentificacion() {
        return clientesByIdentificacion;
    }

    public void setClientesByIdentificacion(Collection<Cliente> clientesByIdentificacion) {
        this.clientesByIdentificacion = clientesByIdentificacion;
    }

    public Collection<Factura> getFacturasByIdentificacion() {
        return facturasByIdentificacion;
    }

    public void setFacturasByIdentificacion(Collection<Factura> facturasByIdentificacion) {
        this.facturasByIdentificacion = facturasByIdentificacion;
    }

    public Collection<Producto> getProductosByIdentificacion() {
        return productosByIdentificacion;
    }

    public void setProductosByIdentificacion(Collection<Producto> productosByIdentificacion) {
        this.productosByIdentificacion = productosByIdentificacion;
    }

    public Usuario getUsuarioByIdentificacion() {
        return usuarioByIdentificacion;
    }

    public void setUsuarioByIdentificacion(Usuario usuarioByIdentificacion) {
        this.usuarioByIdentificacion = usuarioByIdentificacion;
    }
}
