package org.example.proyecto_01.data;

import org.example.proyecto_01.logic.Cliente;
import org.example.proyecto_01.logic.Producto;
import org.example.proyecto_01.logic.Proveedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, String> {
    Iterable<Producto> findByProveedorIdP(String proveedor);

    Producto findByCodigoAndProveedorIdP(String codigo, String proveedor);

    Iterable<Producto> findByNombreContainingAndProveedorIdP(String nom, String id);


}
