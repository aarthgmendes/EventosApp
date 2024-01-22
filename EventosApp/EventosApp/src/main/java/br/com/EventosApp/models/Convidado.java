package br.com.EventosApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Convidado {
	
	@Id
	@NotEmpty //evita com que os campos fiquem vazios ao adicionar convidado
	private String rg;
	
	@NotEmpty
	private String nomeConvidado;
	
	//RELACIONAMENTO DE ENTIDADES
	@ManyToOne
	private Evento evento;
	
	
	// getters and setters
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomeConvidado() {
		return nomeConvidado;
	}
	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
}
