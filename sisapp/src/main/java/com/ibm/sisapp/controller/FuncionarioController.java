package com.ibm.sisapp.controller;

import com.ibm.sisapp.model.Funcionario;
import com.ibm.sisapp.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String listarFuncionarios(
        @RequestParam(value = "nome", required = false) String nome,
        @RequestParam(value = "id", required = false) Long id,
        Model model) {
        
        List<Funcionario> funcionarios;

        // Verifica se um ID foi fornecido
        if (id != null) {
            Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
            if (funcionario.isPresent()) {
                funcionarios = List.of(funcionario.get()); // Envolve o funcionário encontrado em uma lista
            } else {
                funcionarios = List.of(); // Se não encontrar o funcionário, retorna lista vazia
            }
        }
        // Se o ID não foi fornecido, verifica se um nome foi informado
        else if (nome != null && !nome.isEmpty()) {
            funcionarios = funcionarioService.buscarPorNome(nome); // Busca por nome
        } else {
            funcionarios = funcionarioService.listarTodos(); // Lista todos se nenhum nome nem ID forem fornecidos
        }

        model.addAttribute("funcionarios", funcionarios);
        return "listagem"; // Página de listagem de funcionários
    }

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario) {
        funcionarioService.salvar(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model) {
        Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
        if (funcionario.isPresent()) {
            model.addAttribute("funcionario", funcionario.get());
            return "editar";
        }
        return "redirect:/funcionarios";
    }

    @PostMapping("/atualizar")
    public String atualizarFuncionario(@ModelAttribute Funcionario funcionario) {
        funcionarioService.salvar(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return "redirect:/funcionarios";
    }
}