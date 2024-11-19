package org.proyecto.primerproyecto.repositories;

import org.proyecto.primerproyecto.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

}
