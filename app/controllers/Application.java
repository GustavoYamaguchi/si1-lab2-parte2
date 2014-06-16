package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import views.html.*;

import java.util.*;

import models.*;
public class Application extends Controller {
	
	static Form<Semana> semanaForm = Form.form(Semana.class);
	private static List<Semana> semanas = null;
    
    private static void reseta() { if (semanas == null) semanas = new ArrayList<Semana>(); }
    
    public static Result tasks() {
    	return ok(views.html.semana.render(Semana.all(), semanaForm));
    }
    
    public static Result registrar() {    	
    	Semana semana = semanaForm.bindFromRequest().get();
    	
    	Semana.save(semana);
    	
    	return redirect(routes.Application.semana());
    }

    public static Result delete(Long id) {
    	Semana.del(id);
    	
    	return redirect(routes.Application.semana());
    }
    
    public static Result complete(Long id) {
    	Semana.complete(id);
    	return redirect(routes.Application.semana());
    }

}
