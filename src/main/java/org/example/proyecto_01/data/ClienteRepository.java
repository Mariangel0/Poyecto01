package org.example.proyecto_01.data;

import org.example.proyecto_01.logic.Cliente;
import org.example.proyecto_01.logic.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {
    Cliente findByidentificacion(String identificacion);
    Iterable<Cliente> findByProveedorIdC(String identificacion);
}
