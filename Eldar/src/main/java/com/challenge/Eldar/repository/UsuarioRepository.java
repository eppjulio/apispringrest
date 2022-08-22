package com.challenge.Eldar.repository;

import com.challenge.Eldar.models.UsuarioTarjeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioTarjeta, Long> {

}
