package com.bsmlima.salesdataanalysis.parser;

import com.bsmlima.salesdataanalysis.model.Customer;
import com.bsmlima.salesdataanalysis.model.SalesData;

import java.util.List;

public class CustomerDataParser implements DataParser {

    private static final int CNPJ_INDEX = 1;
    private static final int NAME_INDEX = 2;
    private static final int AREA_INDEX = 3;

    @Override
    public void parse(List<String> data, SalesData salesData) {
        Customer customer = new Customer();

        customer.setCnpj(data.get(CNPJ_INDEX));
        customer.setName(data.get(NAME_INDEX));
        customer.setArea(data.get(AREA_INDEX));

        salesData.addCustomer(customer);
    }
}
