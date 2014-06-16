package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class Semana extends Model{

	@Id
	private Long id;
	
	@OneToMany
	@Column
	private List<Meta> metas;
	
	public Semana(){
		metas = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Meta> getMetas() {
		return metas;
	}

	public void setMetas(List<Meta> metas) {
		this.metas = metas;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Semana){
			if(metas.equals(((Semana) o).metas))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "Id: " + getId() + "; Metas: " + getMetas();
	}
}
