package org.example.proyecto_01.data;

import org.example.proyecto_01.logic.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, String> {

    Cliente findByIdentificacion(String identificacion);

    Cliente findByNumcliente(int numcliente);

    Iterable<Cliente> findByProveedorIdC(String identificacion);

    Cliente findByProveedorIdCAndIdentificacion(String idP, String idC);

}
