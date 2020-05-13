package com.bsmlima.salesdataanalysis.parser;

import com.bsmlima.salesdataanalysis.model.Sale;
import com.bsmlima.salesdataanalysis.model.SaleItem;
import com.bsmlima.salesdataanalysis.model.SalesData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SaleDataParser implements DataParser {

    private static final int SALE_ID_INDEX = 1;
    private static final int SALE_ITEMS_INDEX = 2;
    private static final int SALE_SALESMAN_NAME_INDEX = 3;

    private static final int ITEM_ID_INDEX = 0;
    private static final int ITEM_QUANTITY_INDEX = 1;
    private static final int ITEM_PRICE_INDEX = 2;

    private static final String ITEM_SEPARATOR = ",";
    private static final String ITEM_FIELD_SEPARATOR = "-";


    @Override
    public void parse(List<String> data, SalesData salesData) {
        Sale sale = new Sale();

        sale.setId(Integer.parseInt(data.get(SALE_ID_INDEX)));
        sale.setItems(parseItems(data.get(SALE_ITEMS_INDEX)));
        sale.setSalesmanName(data.get(SALE_SALESMAN_NAME_INDEX));

        salesData.addSale(sale);
    }

    private List<SaleItem> parseItems(String items) {
        items = items.substring(1, items.length()-1); // remove brackets

        return Arrays.stream(items.split(ITEM_SEPARATOR)).map(item -> {
            List<String> itemData = Arrays.asList(item.split(ITEM_FIELD_SEPARATOR));

            return new SaleItem( Integer.parseInt(itemData.get(ITEM_ID_INDEX)),
                                 Integer.parseInt(itemData.get(ITEM_QUANTITY_INDEX)),
                                 Double.parseDouble(itemData.get(ITEM_PRICE_INDEX)));
        }).collect(Collectors.toList());
    }
}
