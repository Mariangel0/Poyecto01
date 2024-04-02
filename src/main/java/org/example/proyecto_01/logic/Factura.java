package org.example.proyecto_01.logic;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Factura {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigoFactura")
    private int codigoFactura;
    @Basic
    @Column(name = "total")
    private Double total;

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return codigoFactura == factura.codigoFactura && Objects.equals(total, factura.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoFactura, total);
    }
}
