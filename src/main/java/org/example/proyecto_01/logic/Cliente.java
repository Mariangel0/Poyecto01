package org.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numCliente")
    private int numCliente;
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
    @ManyToOne
    @JoinColumn(name = "proveedor_idC", referencedColumnName = "identificacion", nullable = false)
    private Proveedor proveedorByProveedorIdC;
    @OneToMany(mappedBy = "clienteByClienteNum")
    private Collection<Factura> facturasByNumCliente;

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
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
        return numCliente == cliente.numCliente && Objects.equals(identificacion, cliente.identificacion) && Objects.equals(nombre, cliente.nombre) && Objects.equals(correo, cliente.correo) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCliente, identificacion, nombre, correo, telefono);
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
}
