/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Box
 */
public class Racun {
    private int idRacun;
    private String brojRacuna;
    private Date datumIzdavanja;
    private String nacinPlacanja;
    private BigDecimal ukupnaCijena;
    private Timestamp vrijemeIzdavanja;
    private int kupacID;

    /**
     * @return the idRacun
     */
    public int getIdRacun() {
        return idRacun;
    }

    /**
     * @param idRacun the idRacun to set
     */
    public void setIdRacun(int idRacun) {
        this.idRacun = idRacun;
    }

    /**
     * @return the brojRacuna
     */
    public String getBrojRacuna() {
        return brojRacuna;
    }

    /**
     * @param brojRacuna the brojRacuna to set
     */
    public void setBrojRacuna(String brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    /**
     * @return the datumIzdavanja
     */
    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    /**
     * @param datumIzdavanja the datumIzdavanja to set
     */
    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    /**
     * @return the nacinPlacanja
     */
    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    /**
     * @param nacinPlacanja the nacinPlacanja to set
     */
    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    /**
     * @return the ukupnaCijena
     */
    public BigDecimal getUkupnaCijena() {
        return ukupnaCijena;
    }

    /**
     * @param ukupnaCijena the ukupnaCijena to set
     */
    public void setUkupnaCijena(BigDecimal ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    /**
     * @return the vrijemeIzdavanja
     */
    public Timestamp getVrijemeIzdavanja() {
        return vrijemeIzdavanja;
    }

    /**
     * @param vrijemeIzdavanja the vrijemeIzdavanja to set
     */
    public void setVrijemeIzdavanja(Timestamp vrijemeIzdavanja) {
        this.vrijemeIzdavanja = vrijemeIzdavanja;
    }

    /**
     * @return the kupacID
     */
    public int getKupacID() {
        return kupacID;
    }

    /**
     * @param kupacID the kupacID to set
     */
    public void setKupacID(int kupacID) {
        this.kupacID = kupacID;
    }
    
}
