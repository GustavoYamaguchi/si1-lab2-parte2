package controllers;

import java.util.*;

import com.avaje.ebean.Ebean;

import models.Meta;
import play.*;
import play.mvc.*;
import play.data.Form;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	List<Meta> metas = Ebean.createQuery(Meta.class).findList();
        return ok(index.render(metas));
    }
    
    public static Result mainMeta(){
    	Form<Meta> form = Form.form(Meta.class);
    	return ok(mainMeta.render(form));    	
    }

    public static Result novaMeta(){
    	Form<Meta> form = Form.form(Meta.class).bindFromRequest();
    	if(form.hasErrors()){
    		return badRequest(mainMeta.render(form));
    	}
    	Meta novaMeta = form.get();
    	novaMeta.save();
    	return redirect(routes.Application.index());
    }
}
