package org.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numcliente")
    private int numcliente;
    @Basic
    @Column(name = "identificacion")
    private String identificacion;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "correo")
    private String correo;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @Basic
    @Column(name = "proveedor_idC")
    private String proveedorIdC;
    @ManyToOne
    @JoinColumn(name = "proveedor_idC", referencedColumnName = "identificacion", nullable = false,  insertable=false, updatable=false)
    private Proveedor proveedorByProveedorIdC;
    @OneToMany(mappedBy = "clienteByClienteNum")
    private Collection<Factura> facturasByNumCliente;


    public int getNumCliente() {
        return numcliente;
    }

    public void setNumCliente(int numCliente) {
        this.numcliente = numCliente;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return numcliente == cliente.numcliente && Objects.equals(identificacion, cliente.identificacion) && Objects.equals(nombre, cliente.nombre) && Objects.equals(correo, cliente.correo) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numcliente, identificacion, nombre, correo, telefono);
    }

    public Proveedor getProveedorByProveedorIdC() {
        return proveedorByProveedorIdC;
    }

    public void setProveedorByProveedorIdC(Proveedor proveedorByProveedorIdC) {
        this.proveedorByProveedorIdC = proveedorByProveedorIdC;
    }

    public Collection<Factura> getFacturasByNumCliente() {
        return facturasByNumCliente;
    }

    public void setFacturasByNumCliente(Collection<Factura> facturasByNumCliente) {
        this.facturasByNumCliente = facturasByNumCliente;
    }

    public String getProveedorIdC() {
        return proveedorIdC;
    }

    public void setProveedorIdC(String proveedorIdC) {
        this.proveedorIdC = proveedorIdC;
    }
}
