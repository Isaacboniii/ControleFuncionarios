package com.ibm.sisapp.repository;

import com.ibm.sisapp.model.Funcionario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNomeContainingIgnoreCase(String nome);
}
