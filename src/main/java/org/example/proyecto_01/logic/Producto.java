package org.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private String codigo;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "precio")
    private double precio;
    @Basic
    @Column(name = "cantidad")
    private int cantidad;
    @OneToMany(mappedBy = "productoByProductoCodD")
    private Collection<Detalle> detallesByCodigo;
    @ManyToOne
    @JoinColumn(name = "proveedor_idP", referencedColumnName = "identificacion", nullable = false)
    private Proveedor proveedorByProveedorIdP;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Double.compare(precio, producto.precio) == 0 && cantidad == producto.cantidad && Objects.equals(codigo, producto.codigo) && Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, precio, cantidad);
    }

    public Collection<Detalle> getDetallesByCodigo() {
        return detallesByCodigo;
    }

    public void setDetallesByCodigo(Collection<Detalle> detallesByCodigo) {
        this.detallesByCodigo = detallesByCodigo;
    }

    public Proveedor getProveedorByProveedorIdP() {
        return proveedorByProveedorIdP;
    }

    public void setProveedorByProveedorIdP(Proveedor proveedorByProveedorIdP) {
        this.proveedorByProveedorIdP = proveedorByProveedorIdP;
    }
}
