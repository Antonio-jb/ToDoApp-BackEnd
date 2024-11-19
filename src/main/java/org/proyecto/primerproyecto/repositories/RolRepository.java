package org.proyecto.primerproyecto.repositories;

import org.proyecto.primerproyecto.models.Rol;
import org.proyecto.primerproyecto.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository <Rol, Long> {

    Rol findByRol(String username);

}
