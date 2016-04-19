/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.model;

import java.math.BigDecimal;

/**
 *
 * @author Box
 */
public class Stavka {
    private int idStavka;
    private String nazivArtikla;
    private short kolicina;
    private BigDecimal cijenaPoKomadu;
    private BigDecimal ukupnaCijena;

    /**
     * @return the idStavka
     */
    public int getIdStavka() {
        return idStavka;
    }

    /**
     * @param idStavka the idStavka to set
     */
    public void setIdStavka(int idStavka) {
        this.idStavka = idStavka;
    }

    

    

    /**
     * @return the kolicina
     */
    public short getKolicina() {
        return kolicina;
    }

    /**
     * @param kolicina the kolicina to set
     */
    public void setKolicina(short kolicina) {
        this.kolicina = kolicina;
    }

    /**
     * @return the cijenaPoKomadu
     */
    

    /**
     * @return the ukupnaCijenaStavke
     */
    

    /**
     * @return the nazivArtikla
     */
    public String getNazivArtikla() {
        return nazivArtikla;
    }

    /**
     * @param nazivArtikla the nazivArtikla to set
     */
    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    /**
     * @return the cijenaPoKomadu
     */
    public BigDecimal getCijenaPoKomadu() {
        return cijenaPoKomadu;
    }

    /**
     * @param cijenaPoKomadu the cijenaPoKomadu to set
     */
    public void setCijenaPoKomadu(BigDecimal cijenaPoKomadu) {
        this.cijenaPoKomadu = cijenaPoKomadu;
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
}
