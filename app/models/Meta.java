package models;

import java.util.*;

import javax.persistence.*;

import play.data.Form;
import play.data.validation.Constraints.*;
import play.db.ebean.*;
import play.mvc.Content;

@Entity
public class Meta extends Model implements Comparable<Meta>{
	
	@Id @GeneratedValue
	private Long id;

	private final int PENDENTE = 0;
	private final int ALCANCADA = 1;

	@OneToOne(mappedBy = "Meta")
	@JoinColumn
	private String descricao;
	private int situacao;
	private int prioridade;
	private int semana;
	
	public static Finder<Long, Meta> find = new Finder<Long, Meta>(Long.class, Meta.class);
	
	public int getSemana() {
		return semana;
	}

	public void setSemana(int semana) {
		if(semana> 0 && semana <7)
			this.semana = semana;
	}

	public Meta(){}

	public Meta(String descricao, int prioridade, int semana){
		situacao = PENDENTE;
		this.descricao = descricao;
		setSemana(semana);
		setPrioridade(prioridade);
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		if(prioridade >= 1 && prioridade <= 5)
			this.prioridade = prioridade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public int getPENDENTE() {
		return PENDENTE;
	}

	public int getALCANCADA() {
		return ALCANCADA;
	}
	
	public static List<Meta> all() {
	  return find.all();
	}

	public static void create(Meta meta) {
	  meta.save();
	}

	public static void delete(Long id) {
	  find.ref(id).delete();
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Meta){
			if(descricao.equals(((Meta) o).getDescricao()))
				return true;
		}
		return false;
	}

	public String getSitucaoString(){
		if(situacao == PENDENTE){
			return "pendente";
		}
		else if(situacao ==ALCANCADA){
			return "alcancada";
		}
		else return "";
	}

	@Override
	public String toString(){
		return "Id: " + getId() + "; Descricao: " + getDescricao() + "; Situcao: " + getSitucaoString();
	}

	@Override
	public int compareTo(Meta outraMeta) {
		if(prioridade > outraMeta.getPrioridade())
			return -1;
		else if(prioridade > outraMeta.getPrioridade())
			return 1;
		return 0;
	}
}
