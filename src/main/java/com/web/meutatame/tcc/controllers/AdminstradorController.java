package com.web.meutatame.tcc.controllers;

import com.web.meutatame.tcc.domain.Administrador;
import com.web.meutatame.tcc.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminstradorController {

    @Autowired
    private AdministradorRepository admRepository;


    @PostMapping("/administradores/criar")
    public String criar(Administrador administrador){
        admRepository.save(administrador);
        return "redirect:/administradores";
    }

}
