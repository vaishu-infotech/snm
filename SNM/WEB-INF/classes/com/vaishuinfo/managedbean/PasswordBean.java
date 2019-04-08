package com.vaishuinfo.managedbean;

import com.vaishuinfo.dao.MastersDAO;
import com.vaishuinfo.dto.LoginDTO;
import com.vaishuinfo.setting.DBResourseLoader;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;

/**
 *
 * @author Raghav
 */
@ManagedBean
@ViewScoped
public class PasswordBean implements Serializable {

    @ManagedProperty(value = "#{loginDTO}")
    private LoginDTO loginDTO;
    private String password;
    private MastersDAO db = new MastersDAO();

    private Logger logger = DBResourseLoader.getInstance().getLogger(getClass());
    
    public PasswordBean() {
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void changePassword() {
        if (password == null || password.trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage("frmPass:saveButton", new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Password cannot be empty"));
        } else {
        	logger.info("change pw:" + password.trim());
        	logger.info("change pw:" + loginDTO.getUserId());
            boolean status = db.changePassword(password.trim(), loginDTO.getUserId());
            if (status) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Status", "Password changed successfully"));
            } else {
                FacesContext.getCurrentInstance().addMessage("frmPass:saveButton", new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Password not changed !!! Contact Administrator"));
            }
        }
    }
}
