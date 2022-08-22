package com.challenge.Eldar.services;

import com.challenge.Eldar.models.UsuarioTarjeta;
import com.challenge.Eldar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioTarjeta> obtenerUsuarios(){
        return (ArrayList<UsuarioTarjeta>) usuarioRepository.findAll();
    }

    public UsuarioTarjeta guardarUsuario(UsuarioTarjeta usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioTarjeta> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public void tasaOperacion(UsuarioTarjeta usuario) throws ParseException {

        LocalDate fecha = LocalDate.now();
        int anio = fecha.getYear() % 100;
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        double tarif;
        usuario.setValid();

        switch (usuario.getCardBrand()) {
            case "VISA":
                tarif = (anio / mes);
                break;
            case "AMEX":
                tarif = (mes * 0.1);
                break;
            case "NARA":
                tarif = (dia * 0.5);
                break;
            default:
                tarif = 1;
                break;
        }

        if (usuario.getValid()){
            usuario.setTarifa(tarif*(usuario.getMonto()));
        }
    }
}
