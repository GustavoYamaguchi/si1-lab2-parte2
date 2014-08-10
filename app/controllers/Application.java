package controllers;

import java.util.*;

import dao.GenericDAOImpl;
import models.*;
import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	

	private static GenericDAOImpl dao = new GenericDAOImpl();
	
	public static int contaMetasNaSemana(int semana){
		int metasPorSemana = 0;
		for(Meta meta: dao.all()){
			if(meta.getSemana()==semana)
				metasPorSemana++;
		}
		return metasPorSemana;
	}
	
	public static List<Integer> totalDeMetasNaSemana(){
		List<Integer> listaDeMetasConcluidas = new ArrayList<>();
		for(int i=1; i<=6;i++){
			listaDeMetasConcluidas.add(contaMetasNaSemana(i));
		}
		return listaDeMetasConcluidas;
	}
	
	public static int contaMetasPendentesNaSemana(int semana){
		int metasPendentes = 0;
		for(Meta meta: dao.all()){
			if(meta.getSemana()==semana && meta.getSituacao().equals(meta.getPENDENTE())){
				metasPendentes++;
			}
		}
		return metasPendentes;
	}
	
	public static List<Integer> totalDeMetasPendentes(){
		List<Integer> listaDeMetasPendentes = new ArrayList<>();
		for(int i=1; i<=6;i++){
			listaDeMetasPendentes.add(contaMetasPendentesNaSemana(i));
		}
		return listaDeMetasPendentes;
	}
	
	public static int contaMetasConcluidasPorSemana(int semana){
		int metasConcluidas = 0;
		for(Meta meta: dao.all()){
			if(meta.getSemana()==semana && meta.getSituacao().equals(meta.getCONCLUIDA())){
				metasConcluidas++;
			}
		}
		return metasConcluidas;
	}
	
	public static List<Integer> totalDeMetasConcluidas(){
		List<Integer> listaDeMetasConcluidas = new ArrayList<>();
		for(int i=1; i<=6;i++){
			listaDeMetasConcluidas.add(contaMetasConcluidasPorSemana(i));
		}
		return listaDeMetasConcluidas;
	}
	
    /*public static Result index() {
        return ok(index.render("Your new application is ready."));
    }*/
	
	@Transactional
	public static void iniciaBD(){
		Meta meta;
    	String[] nome = {"Estudar SI", "Fazer o Lab", "Estudar OAC", "Aprender Play", "Acordar cedo", 
    			"Assistir o jogo do Brasil", "Adicionar uma meta", "Contar as metas", "Fazer o projeto", "Estudar pro Miniteste"};
    	int[] prioridades = {5, 3, 3, 2, 1, 5, 2, 1, 4, 1};
    	int[] semanas = {3, 2, 1, 3, 3, 2, 1, 1, 1, 2};
    	for (int i = 0; i <= 9; i++) {
    		meta = new Meta();
    		meta.setDescricao(nome[i]);
    		meta.setPrioridade(prioridades[i]);
    		meta.setSemana(semanas[i]);
    		System.out.println(i);
    		System.out.println(!dao.all().contains(meta));
    		if(!dao.all().contains(meta)){
        		dao.persist(meta);
        		dao.flush();
    		}
		}
	}
	
	//inicia BD
	@Transactional
	public static Result index(){
		iniciaBD();
		return redirect(routes.Application.metas());
	}
	
	//nao inicia BD
	@Transactional
	public static Result metas(){
		Form<Meta> metaForm = Form.form(Meta.class);
		return ok(meta.render(dao.all(),metaForm, totalDeMetasConcluidas(),
				totalDeMetasPendentes(),
				totalDeMetasNaSemana()));
	}
	
	@Transactional
    public static Result addMeta(){
		Form<Meta> form = Form.form(Meta.class).bindFromRequest();
    	if(form.hasErrors()){
    		return badRequest(meta.render(dao.all(),form, totalDeMetasConcluidas(),
    				totalDeMetasPendentes(),
    				totalDeMetasNaSemana()));
    	}
    	Meta novaMeta = form.get();
    	dao.persist(novaMeta);
    	dao.flush();
    	return redirect(routes.Application.metas());
    }
    
	@Transactional
    public static Result removeMeta(Long id){
		 dao.removeById(id);
		 dao.flush();
		 return redirect(routes.Application.metas());	
    }
	
	@Transactional
    public static Result doneMeta(Long id){
		 dao.doneById(id);
		 dao.flush();
		 return redirect(routes.Application.metas());		
    }
    
}
