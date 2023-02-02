package br.edmtool.data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ManagedBean
@RequestScoped
public class ModeloBean {
	private Logger logger = LogManager.getRootLogger();
	
    private int id;
    /**
    * Creates a new instance of ModeloBean
    */
    public ModeloBean() {
        try {
            this.id = Integer.parseInt((String)FacesContext
               .getCurrentInstance().getExternalContext()
               .getRequestParameterMap().get("id"));
            //by getting the id you can get more data
            
            logger.trace("Id Modelo " + this.id);
        }
        catch (Exception e) {
            this.id = 0;
            logger.error(e);
        }
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    
}
