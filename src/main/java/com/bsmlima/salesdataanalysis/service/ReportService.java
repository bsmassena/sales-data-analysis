package com.bsmlima.salesdataanalysis.service;

import com.bsmlima.salesdataanalysis.dao.ReportDao;
import com.bsmlima.salesdataanalysis.model.Sale;
import com.bsmlima.salesdataanalysis.model.SalesData;
import com.bsmlima.salesdataanalysis.parser.FileParser;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class ReportService {

    private FileParser fileParser;
    private ReportDao reportDao;
    private final static String OUTPUT_TEMPLATE =
            "Customers amount: %s\n" +
            "Salesmen amount: %s\n" +
            "Most expensive sale ID: %s\n" +
            "Worst salesman: %s\n";

    public ReportService(FileParser fileParser, ReportDao reportDao) {
        this.fileParser = fileParser;
        this.reportDao = reportDao;
    }

    public void processFile(String filename) throws IOException {
        SalesData salesData = fileParser.parse(reportDao.read(filename));

        reportDao.save(filename, String.format(OUTPUT_TEMPLATE,
                getCustomersAmount(salesData),
                getSalesmenAmount(salesData),
                getMostExpensiveSaleId(salesData),
                getWorstSalesman(salesData)));
    }

    public int getCustomersAmount(SalesData salesData) {
        return salesData.getCustomers().size();
    }

    public int getSalesmenAmount(SalesData salesData) {
        return salesData.getSalesmen().size();
    }

    public int getMostExpensiveSaleId(SalesData salesData) {
        Comparator<Sale> saleComparator = Comparator.comparingDouble(sale ->
            sale.getItems().stream().map(item -> item.getQuantity() * item.getPrice()).reduce(0.0, Double::sum));

        return salesData.getSales().stream()
                .max(saleComparator)
                .get()
                .getId();
    }

    public String getWorstSalesman(SalesData salesData) {
        return Collections.min(salesData.getSalesmenSalesRevenue().entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }
}
