package com.vaishuinfo.managedbean;

import com.vaishuinfo.dao.MastersDAO;
import com.vaishuinfo.dto.LoginDTO;
import com.vaishuinfo.setting.DBResourseLoader;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;


@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private LoginDTO logDTO;

    private Logger logger = DBResourseLoader.getInstance().getLogger(getClass());
    private MastersDAO dao = new MastersDAO();

    public LoginBean() {
    	logDTO = new LoginDTO();
    }

    
    public LoginDTO getLogDTO() {
		return logDTO;
	}


	public void setLogDTO(LoginDTO logDTO) {
		this.logDTO = logDTO;
	}


	// Re-direct to index page after invalidating the session
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //logger.info("Logged out Successfully");
        return "index.jsp?faces-redirect=true";
    }

    public String login() {
        String url = "login";
        
        if (logDTO.getUserId() == null || logDTO.getUserId().trim().length() == 0 || logDTO.getPassword() == null || logDTO.getPassword().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("frmLogin:btnLogin", new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "UserId/Password cannot be empty"));
        } else {
        	LoginDTO lDTO = new LoginDTO();
            lDTO = dao.checkLogin(logDTO.getUserId().trim(), logDTO.getPassword().trim());
            
            logDTO.setUserName(lDTO.getUserName());
            if (logDTO.getUserName() == null || logDTO.getUserName().trim().length() == 0) {
                FacesContext.getCurrentInstance().addMessage("frmLogin:btnLogin", new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "UserId/Password is not correct"));
            } else {
                logDTO.setIsLoggedIn(true);
                logDTO.setAdmin(lDTO.isAdmin());
                if (lDTO.isReadOnlyUser()){
                	logDTO.setReadOnlyUser(false);
                }else{
                	logDTO.setReadOnlyUser(true);
                }
                url = "home";
            }
        }
        return url;
    }
}
