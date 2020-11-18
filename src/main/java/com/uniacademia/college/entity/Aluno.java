package com.uniacademia.college.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "aluno")
@Entity(name = "aluno")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 6549776800155330566L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false)
	@Size(min = 1, max = 10, message = "O campo 'Nome' deve conter entre 1 e 255 caracteres.")
	private String nome;

	@Column(name = "ano", nullable = false)
	private int ano;

	@Column(name = "matricula", nullable = false)
	private int matricula;

	@ManyToOne(fetch = FetchType.EAGER)
	private Turma turma;

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", ano=" + ano + ", matricula=" + matricula + "]";
	}
}
