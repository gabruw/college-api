package com.uniacademia.college.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.college.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

	@Transactional
	void deleteById(Long id);

	@Transactional(readOnly = true)
	Optional<Turma> findById(Long id);
}
