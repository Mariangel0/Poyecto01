package org.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Detalle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private int codigo;
    @Basic
    @Column(name = "cantidad")
    private int cantidad;
    @Basic
    @Column(name = "monto")
    private double monto;
    @ManyToOne
    @JoinColumn(name = "producto_codD", referencedColumnName = "codigo", nullable = false)
    private Producto productoByProductoCodD;
    @ManyToOne
    @JoinColumn(name = "factura_codD", referencedColumnName = "codigo", nullable = false)
    private Factura facturaByFacturaCodD;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detalle detalle = (Detalle) o;
        return codigo == detalle.codigo && cantidad == detalle.cantidad && Double.compare(monto, detalle.monto) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, cantidad, monto);
    }

    public Producto getProductoByProductoCodD() {
        return productoByProductoCodD;
    }

    public void setProductoByProductoCodD(Producto productoByProductoCodD) {
        this.productoByProductoCodD = productoByProductoCodD;
    }

    public Factura getFacturaByFacturaCodD() {
        return facturaByFacturaCodD;
    }

    public void setFacturaByFacturaCodD(Factura facturaByFacturaCodD) {
        this.facturaByFacturaCodD = facturaByFacturaCodD;
    }
}
