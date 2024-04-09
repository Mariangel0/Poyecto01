package org.example.proyecto_01.data;

import org.example.proyecto_01.logic.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, String> {
    Iterable<Producto> findByProveedorIdP(String proveedor);

}
