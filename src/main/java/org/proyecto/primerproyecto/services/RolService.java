package org.proyecto.primerproyecto.services;

import org.proyecto.primerproyecto.models.Rol;
import org.proyecto.primerproyecto.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return this.rolRepository.findAll();
    }

    public Optional<Rol> getUserById(Long id) {
        return this.rolRepository.findById(id);
    }

    public Rol create(Rol rol) {
        return this.rolRepository.save(rol);
    }

    public void delete(Long id) {
        this.rolRepository.deleteById(id);
    }

}
