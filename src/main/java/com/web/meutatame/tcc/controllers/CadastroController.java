package com.web.meutatame.tcc.controllers;


import com.web.meutatame.tcc.domain.Administrador;
import com.web.meutatame.tcc.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("administrador", new Administrador());
        return "cadastro/index"; // O template do formulário de cadastro
    }

    @PostMapping("/cadastro")
    public String cadastrarAdministrador(@Validated @ModelAttribute("administrador") Administrador administrador,
                                         BindingResult result, Model model) {

        // Verificar se o e-mail já está cadastrado
        if (administradorRepository.existsByEmail(administrador.getEmail())) {
            result.rejectValue("email", "error.administrador", "E-mail já cadastrado.");
        }

        // Verificar se as senhas coincidem
        if (administrador.getSenha() == null || !administrador.getSenha().equals(administrador.getConfirmacaoSenha())) {
            result.rejectValue("senha", "error.administrador", "As senhas não conferem.");
        }

        // Se houver erros, volta para a tela de cadastro
        if (result.hasErrors()) {
            return "cadastro/index"; // A tela de cadastro com erros
        }

        // Salva o novo administrador se não houver erros
        administradorRepository.save(administrador);
        return "redirect:/login?cadastro=sucesso"; // Redireciona para login após cadastro com sucesso
    }
}
