package org.example.proyecto_01.data;

import org.example.proyecto_01.logic.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends CrudRepository<Proveedor, String> {

    Iterable<Proveedor> findAllByestado(byte b);
    Proveedor findByidentificacion(String identificacion);

}
