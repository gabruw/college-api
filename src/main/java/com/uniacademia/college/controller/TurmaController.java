package com.uniacademia.college.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.uniacademia.college.entity.Turma;
import com.uniacademia.college.response.Response;
import com.uniacademia.college.service.AlunoService;
import com.uniacademia.college.service.TurmaService;

@RestController
@RequestMapping("/turma")
@CrossOrigin(origins = "*")
public class TurmaController {
	private static final Logger log = LoggerFactory.getLogger(TurmaController.class);

	@Autowired
	private TurmaService turmaService;

	@Autowired
	private AlunoService alunoService;

	public TurmaController() {

	}

	@GetMapping("/all")
	public ResponseEntity<Response<List<Turma>>> all() throws NoSuchAlgorithmException {
		Response<List<Turma>> response = new Response<List<Turma>>();

		List<Turma> turmas = turmaService.getAll();
		response.setData(turmas);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<Optional<Turma>>> getById(@PathVariable("id") Long id)
			throws NoSuchAlgorithmException {
		Response<Optional<Turma>> response = new Response<Optional<Turma>>();

		Optional<Turma> turma = turmaService.getById(id);
		if (!turma.isPresent()) {
			log.error("A pesquisa não retornou resultados para a Turma: {}", id);
			response.getErrors().add("Turma não encontrada");

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(turma);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/include")
	public ResponseEntity<Response<Turma>> include(@Valid @RequestBody Turma turma, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Turma: {}", turma.toString());
		Response<Turma> response = new Response<Turma>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro da Turma: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		try {
			List<Aluno> alunos = turma.getAlunos().stream().map(aluno -> alunoService.getById(aluno.getId()).get())
					.collect(Collectors.toList());
			turma.setAlunos(alunos);

			this.turmaService.persistir(turma);
			response.setData(turma);
		} catch (Exception e) {
			log.error("Erro ao validar os alunos da Turma: {}", result.getAllErrors());
			response.getErrors().add("Erro ao validar os alunos da Turma");

			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@PutMapping("/edit")
	public ResponseEntity<Response<Turma>> edit(@Valid @RequestBody Turma turma, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Editar Turma: {}", turma.toString());
		Response<Turma> response = new Response<Turma>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição da Turma: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		try {
			List<Aluno> alunos = turma.getAlunos().stream().map(aluno -> alunoService.getById(aluno.getId()).get())
					.collect(Collectors.toList());
			turma.setAlunos(alunos);

			this.turmaService.persistir(turma);
			response.setData(turma);
		} catch (Exception e) {
			log.error("Erro ao validar os alunos da Turma: {}", result.getAllErrors());
			response.getErrors().add("Erro ao validar os alunos da Turma");

			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Long>> remove(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		log.info("Removendo Turma: {}", id);

		Response<Long> response = new Response<Long>();
		this.turmaService.deleteById(id);

		response.setData(id);
		return ResponseEntity.ok(response);
	}
}
