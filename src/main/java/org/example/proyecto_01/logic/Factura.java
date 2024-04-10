package org.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Factura {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private int codigo;
    @Basic
    @Column(name = "total")
    private double total;
    @OneToMany(mappedBy = "facturaByFacturaCodD")
    private Collection<Detalle> detallesByCodigo;
    @ManyToOne
    @JoinColumn(name = "cliente_num", referencedColumnName = "numCliente", nullable = false)
    private Cliente clienteByClienteNum;
    @ManyToOne
    @JoinColumn(name = "proveedor_idF", referencedColumnName = "identificacion", nullable = false)
    private Proveedor proveedorByProveedorIdF;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return codigo == factura.codigo && Double.compare(total, factura.total) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, total);
    }

    public Collection<Detalle> getDetallesByCodigo() {
        return detallesByCodigo;
    }

    public void setDetallesByCodigo(Collection<Detalle> detallesByCodigo) {
        this.detallesByCodigo = detallesByCodigo;
    }

    public Cliente getClienteByClienteNum() {
        return clienteByClienteNum;
    }

    public void setClienteByClienteNum(Cliente clienteByClienteNum) {
        this.clienteByClienteNum = clienteByClienteNum;
    }

    public Proveedor getProveedorByProveedorIdF() {
        return proveedorByProveedorIdF;
    }

    public void setProveedorByProveedorIdF(Proveedor proveedorByProveedorIdF) {
        this.proveedorByProveedorIdF = proveedorByProveedorIdF;
    }
}
