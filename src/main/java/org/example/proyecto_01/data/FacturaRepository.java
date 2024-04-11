package org.example.proyecto_01.data;

import org.example.proyecto_01.logic.Factura;
import org.springframework.data.repository.CrudRepository;

public interface FacturaRepository extends CrudRepository<Factura, String> {

    Factura findByCodigo(int codigo);

    Iterable<Factura> findAllByProveedorIdF(String id);

}