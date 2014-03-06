package com.alexgilleran.goget.model;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import com.alexgilleran.goget.dao.csv.CSVField;

public class CaltexFuel {
    // CardNumber,TranDateTime,TranTime__hhmmss_,ProcessedDateTime,LocnNo,LocationName,LocnState,Registration,Product,Quantity_Litres,PumpPrice,TotalIncGST
    // 7.07E+15,10/1/2013,131300,10/2/2013,44414,REYNELLA CALTEX
    // WOOLWORTHS,SA,XYV116,ULP,36.1599999999999,1.359,48.06
    @CSVField(value = "trand", dateFormat = "d/MM/yyyy")
    private DateTime trandate;
    @CSVField(value = "TranTime__hhmmss_", dateFormat = "HHmmss")
    private DateTime tranTime;
    @CSVField("Quantity_Litres")
    private int quantityLitres;
    @CSVField("TotalIncGST")
    private BigDecimal total;
}
