package com.uniacademia.college.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.uniacademia.college.enumerator.EnsinoEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "turma")
@Entity(name = "turma")
public class Turma implements Serializable {
	private static final long serialVersionUID = 6522775500177338866L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	@Size(min = 1, max = 10, message = "O campo 'Nome' deve conter entre 1 e 10 caracteres.")
	private String nome;

	@Column(name = "ano", nullable = false)
	private int ano;

	@Enumerated(EnumType.STRING)
	@Column(name = "ensino", nullable = false)
	private EnsinoEnum ensino;

	@OneToMany(mappedBy = "turma", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Aluno> alunos;

}
