package com.uniacademia.college.service;

import java.util.List;
import java.util.Optional;

import com.uniacademia.college.entity.Turma;

public interface TurmaService {

	List<Turma> getAll();

	void deleteById(Long id);

	Turma persistir(Turma turma);

	Optional<Turma> getById(Long id);
}
