package com.challenge.Eldar.controller;

import com.challenge.Eldar.models.UsuarioTarjeta;
import com.challenge.Eldar.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioTarjeta> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioTarjeta guardarUsuario(@RequestBody UsuarioTarjeta usuario) throws ParseException {
        this.usuarioService.tasaOperacion(usuario);
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping( path = "/{id}")
    public Optional<UsuarioTarjeta> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if(ok){
            return "Se elimino el usuario con id " + id;
        }else{
            return "No se pudo eliminar el usuario con id " + id;
        }
    }

}
