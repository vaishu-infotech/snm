package com.vaishuinfo.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import com.vaishuinfo.dto.CustomerDTO;

public class CustomerDataModel extends ListDataModel<CustomerDTO> implements SelectableDataModel<CustomerDTO>, Serializable {
	private static final long serialVersionUID = 1L;
    public CustomerDataModel() {
    }

    public CustomerDataModel(List<CustomerDTO> data) {
        super(data);
    }

    
    public CustomerDTO getRowData(String rowKey) {
        List<CustomerDTO> customers = (List<CustomerDTO>) getWrappedData();
        if (rowKey != null && rowKey.trim().length() != 0 && !rowKey.equalsIgnoreCase("null")) {
            for (CustomerDTO customer : customers) {
            	int irowkey = Integer.parseInt(rowKey);
                if (irowkey == customer.getCustomerSerialNumber()) {
                    return customer;
                }
            }
        }
        return null;
    }

    public Object getRowKey(CustomerDTO customer) {
        return customer.getCustomerSerialNumber();
    }

}
