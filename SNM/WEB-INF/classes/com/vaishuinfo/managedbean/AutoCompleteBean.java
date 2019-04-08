package com.vaishuinfo.managedbean;


import java.io.Serializable;
import java.util.ArrayList;  
import java.util.List;  
  
import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;  
  
import org.primefaces.event.UnselectEvent;  
import org.slf4j.Logger;

import com.vaishuinfo.dto.CustomerDTO;
import com.vaishuinfo.setting.DBResourseLoader;
 
@ManagedBean
@ViewScoped
public class AutoCompleteBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<String> customers;
	private List<String> ITTypeList;
	private List<String> AssessmentYearList;
	private List<String> DescriptionListDebit;
	private List<String> DescriptionListCredit;
	private List<String> DescriptionListPayment;
	private List<String> DescriptionListInvoice;
	private List<String> NarrationList;
	private List<String> UnitList;
	private List<String> TDSList;
	private List<String> PayTypeList = new ArrayList<String>();
	private List<String> BankNameList;
	private List<String> OrderNumberList;
	

	private List<String> selectedCustomers;  
	
	private Logger logger = DBResourseLoader.getInstance().getLogger(getClass());
      
    
    public AutoCompleteBean() { 
    	CustomerBean cbean = new CustomerBean();

    	customers = cbean.getCustomers();
    	ITTypeList = cbean.getITType();
    	AssessmentYearList = cbean.getAssessmentYearList();
    	PayTypeList.add("CASH");
    	PayTypeList.add("CHEQUE");
    	PayTypeList.add("Sales/Job work");
    }  
  
    public List<String> complete(String query) {  
        List<String> results = new ArrayList<String>();  
          
        for(int i = 0; i < 10; i++) {  
            results.add(query + i);  
        }  
          
        return results;  
    }  
      
    public List<String> completeCustomer(String query) {  
    	logger.info("completecustomer");
        List<String> suggestions = new ArrayList<String>();  
        for(String p : customers) {  
            if (p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    }
    
    public List<String> completeITTypeList(String query) {  
    	logger.info("completeITType");
        List<String> suggestions = new ArrayList<String>();  
        for(String p : ITTypeList) {  
            if (p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    }
    
    public List<String> completeAssessmentTypeList(String query) {  
    	logger.info("completeAssessmentType");
        List<String> suggestions = new ArrayList<String>();  
        for(String p : AssessmentYearList) {  
            if (p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    }
    
    public List<String> completeDescriptionDebit(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : DescriptionListDebit) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    }  
   
    public List<String> completePayType(String query) {  
        List<String> suggestions = new ArrayList<String>();
        for(String pt : PayTypeList) {  
            if(pt.toLowerCase().startsWith(query.toLowerCase()))  
                suggestions.add(pt);  
        }  
        return suggestions;          
    }
    
    public List<String> completeTDS(String query) {  
        List<String> suggestions = new ArrayList<String>();
        for(String p : TDSList) {  
           suggestions.add(String.valueOf(p));  
        }  
        return suggestions;          
    }

    public List<String> completeBank(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : BankNameList) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))  
                suggestions.add(p);  
        }  
        return suggestions;          
    }
    
    public List<String> completeDescriptionCredit(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : DescriptionListCredit) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    }   

    public List<String> completeNarration(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : NarrationList) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    }   

    public List<String> completeUnit(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : UnitList) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    }   

    public List<String> completeOrderNumber(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : OrderNumberList) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    } 
    
    public List<String> completeDescriptionPayment(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : DescriptionListPayment) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    } 
    
    public List<String> completeDescriptionInvoice(String query) {  
        List<String> suggestions = new ArrayList<String>();  
        for(String p : DescriptionListInvoice) {  
            if(p.toLowerCase().startsWith(query.toLowerCase()))
                suggestions.add(p);  
        }  
        return suggestions;          
    } 
    
    public List<String> getCustomers() {  
        return customers;  
    }  
  
    public List<String> getDescriptionListDebit() {  
        return DescriptionListDebit;  
    }

    public List<String> getDescriptionListCredit() {  
        return DescriptionListCredit;  
    }

    public List<String> getDescriptionListPayment() {  
        return DescriptionListPayment;  
    }
    
    public List<String> getDescriptionListInvoice() {  
        return DescriptionListInvoice;  
    }
    public List<String> getNarrationList() {  
        return NarrationList;  
    }    
    
    public List<String> getUnitList() {  
        return UnitList;  
    }
    
    public List<String> getPayTypeList() {  
        return PayTypeList;  
    }

    public List<String> getSelectedCustomers() {
		return selectedCustomers;
	}

	public void setSelectedCustomers(List<String> selectedCustomers) {
		this.selectedCustomers = selectedCustomers;
	}

	public void handleUnselect(UnselectEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected:" + event.getObject().toString(), null);  
          
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
}
