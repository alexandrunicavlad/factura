package com.alexandrunica.factura;

import java.io.Serializable;

/**
 * Created by Nica on 1/23/2018.
 */

public class FacturaModel implements Serializable {

    private String furnizor;
    private String startDate;
    private String endDate;
    private String pret;

    public FacturaModel () {
    }

    public FacturaModel (String furnizor, String startDate, String endDate, String pret) {
        this.furnizor = furnizor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pret = pret;
    }

    public String getFurnizor () {
        return furnizor;
    }

    public void setFurnizor (String furnizor) {
        this.furnizor = furnizor;
    }

    public String getStartDate () {
        return startDate;
    }

    public void setStartDate (String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate () {
        return endDate;
    }

    public void setEndDate (String endDate) {
        this.endDate = endDate;
    }

    public String getPret () {
        return pret;
    }

    public void setPret (String pret) {
        this.pret = pret;
    }
}
