package com.uniacademia.college.service;

import java.util.List;
import java.util.Optional;

import com.uniacademia.college.entity.Aluno;

public interface AlunoService {

	List<Aluno> getAll();

	void deleteById(Long id);

	Aluno persistir(Aluno aluno);

	Optional<Aluno> getById(Long id);
}
