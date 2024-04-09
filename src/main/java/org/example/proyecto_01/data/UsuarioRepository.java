package org.example.proyecto_01.data;


import org.example.proyecto_01.logic.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    Usuario findByIdentificacionAndClave(String identificacion, String clave);


}
