package com.bsmlima.salesdataanalysis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SalesData {

    private List<Salesman> salesmen = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    private HashMap<String, Double> salesmenSalesRevenue = new HashMap<>();

    public void addSalesman(Salesman salesman) {
        this.salesmen.add(salesman);
        this.salesmenSalesRevenue.putIfAbsent(salesman.getName(), 0.0);
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addSale(Sale sale) {
        this.sales.add(sale);
        this.salesmenSalesRevenue.putIfAbsent(sale.getSalesmanName(), 0.0);

        Double newValue = salesmenSalesRevenue.get(sale.getSalesmanName()) + sale.getItems().stream()
                .map(item -> item.getQuantity() * item.getPrice())
                .reduce(0.0, Double::sum);

        this.salesmenSalesRevenue.put(sale.getSalesmanName(), newValue);
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public HashMap<String, Double> getSalesmenSalesRevenue() {
        return salesmenSalesRevenue;
    }
}
