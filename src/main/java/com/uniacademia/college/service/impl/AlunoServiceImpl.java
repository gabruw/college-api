package com.uniacademia.college.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.college.entity.Aluno;
import com.uniacademia.college.repository.AlunoRepository;
import com.uniacademia.college.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {

	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImpl.class);

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public List<Aluno> getAll() {
		log.info("Listando todos os alunos");
		return this.alunoRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo Aluno por Id {}", id);
		alunoRepository.deleteById(id);
	}

	@Override
	public Aluno persistir(Aluno aluno) {
		log.info("Persistindo aluno: {}", aluno);
		return this.alunoRepository.save(aluno);
	}

	@Override
	public Optional<Aluno> getById(Long id) {
		log.info("Procurando o aluno por Id {}", id);
		return this.alunoRepository.findById(id);
	}
}
