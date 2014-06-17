package models;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class Meta extends Model implements Comparable<Meta>{
	
	@Id
	private Long id;
	
	private final int PENDENTE = 0;
	private final int ALCANCADA = 1;
	
	@OneToOne(mappedBy = "Meta")
	@JoinColumn
	private String descricao;
	private int situacao;
	private int prioridade;
	
	public Meta(String descricao, int situacao, int prioridade){
		this.descricao = descricao;
		if(situacao == PENDENTE | situacao ==ALCANCADA)
			this.situacao = situacao;
		setPrioridade(prioridade);
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		if(prioridade >= 0 & prioridade <= 5)
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
