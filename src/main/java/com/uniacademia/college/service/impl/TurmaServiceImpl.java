package com.uniacademia.college.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.college.entity.Turma;
import com.uniacademia.college.repository.TurmaRepository;
import com.uniacademia.college.service.TurmaService;

@Service
public class TurmaServiceImpl implements TurmaService {

	private static final Logger log = LoggerFactory.getLogger(TurmaServiceImpl.class);

	@Autowired
	private TurmaRepository turmaRepository;

	@Override
	public List<Turma> getAll() {
		log.info("Listando todas as turmas");
		return this.turmaRepository.findAll();
	}

	@Override
	public Turma persistir(Turma turma) {
		log.info("Persistindo turma: {}", turma);
		return this.turmaRepository.save(turma);
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo Turma por Id {}", id);
		turmaRepository.deleteById(id);
	}

	@Override
	public Optional<Turma> getById(Long id) {
		log.info("Procurando a turma por Id {}", id);
		return this.turmaRepository.findById(id);
	}
}
