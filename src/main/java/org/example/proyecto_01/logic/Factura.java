package org.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Factura {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private int codigo;
    @Basic
    @Column(name = "total")
    private double total;
    @OneToMany(mappedBy = "facturaByFacturaCodD")
    private Collection<Detalle> detallesByCodigo;
    @Basic
    @Column(name = "cliente_num")
    private int clienteNum;
    @Basic
    @Column(name = "proveedor_idF")
    private String proveedorIdF;
    @ManyToOne
    @JoinColumn(name = "cliente_num", referencedColumnName = "numCliente", nullable = false, insertable=false, updatable=false)
    private Cliente clienteByClienteNum;
    @ManyToOne
    @JoinColumn(name = "proveedor_idF", referencedColumnName = "identificacion", nullable = false, insertable=false, updatable=false)
    private Proveedor proveedorByProveedorIdF;
    @Basic
    @Column(name = "fecha")
    private Date fecha;

    public Factura() {
        this.detallesByCodigo = new ArrayList<>();
    }

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

    public int getClienteNum() {
        return clienteNum;
    }

    public void setClienteNum(int clienteNum) {
        this.clienteNum = clienteNum;
    }

    public String getProveedorIdF() {
        return proveedorIdF;
    }

    public void setProveedorIdF(String proveedorIdF) {
        this.proveedorIdF = proveedorIdF;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
