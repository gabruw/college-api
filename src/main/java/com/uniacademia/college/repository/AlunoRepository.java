package com.uniacademia.college.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.college.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	@Transactional
	void deleteById(Long id);
	
	@Transactional(readOnly = true)
	Optional<Aluno> findById(Long id);
}
