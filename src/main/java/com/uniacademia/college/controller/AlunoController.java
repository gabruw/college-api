package com.uniacademia.college.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.college.entity.Aluno;
import com.uniacademia.college.response.Response;
import com.uniacademia.college.service.AlunoService;

@RestController
@RequestMapping("/aluno")
@CrossOrigin(origins = "*")
public class AlunoController {
	private static final Logger log = LoggerFactory.getLogger(AlunoController.class);

	@Autowired
	private AlunoService alunoService;

	public AlunoController() {

	}

	@GetMapping("/all")
	public ResponseEntity<Response<List<Aluno>>> all() throws NoSuchAlgorithmException {
		Response<List<Aluno>> response = new Response<List<Aluno>>();

		List<Aluno> alunos = alunoService.getAll();
		response.setData(alunos);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<Optional<Aluno>>> getById(@PathVariable("id") Long id)
			throws NoSuchAlgorithmException {
		Response<Optional<Aluno>> response = new Response<Optional<Aluno>>();

		Optional<Aluno> aluno = alunoService.getById(id);
		if (!aluno.isPresent()) {
			log.error("A pesquisa não retornou resultados para o Aluno: {}", id);
			response.getErrors().add("Aluno não encontrado");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(aluno);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/include")
	public ResponseEntity<Response<Aluno>> include(@Valid @RequestBody Aluno aluno, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Aluno: {}", aluno.toString());
		Response<Aluno> response = new Response<Aluno>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do Aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.alunoService.persistir(aluno);
		response.setData(aluno);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/edit")
	public ResponseEntity<Response<Aluno>> edit(@Valid @RequestBody Aluno aluno, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Editar Aluno: {}", aluno.toString());
		Response<Aluno> response = new Response<Aluno>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição do Aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.alunoService.persistir(aluno);
		response.setData(aluno);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Long>> remove(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		log.info("Removendo Aluno: {}", id);

		Response<Long> response = new Response<Long>();
		this.alunoService.deleteById(id);

		response.setData(id);
		return ResponseEntity.ok(response);
	}
}
