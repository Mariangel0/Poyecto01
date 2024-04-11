package org.example.proyecto_01.data;

import org.example.proyecto_01.logic.Detalle;
import org.example.proyecto_01.logic.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepository extends CrudRepository<Detalle, String> {

    Iterable<Detalle> findAllByFacturaCodD(int cod);
}
