package com.ibm.sisapp.service;

import com.ibm.sisapp.model.Funcionario;
import com.ibm.sisapp.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }
    
    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioRepository.findByNomeContainingIgnoreCase(nome); // Chama o método no repositório
    }
}