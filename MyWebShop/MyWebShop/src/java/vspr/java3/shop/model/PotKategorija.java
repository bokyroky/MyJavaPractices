/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.model;

/**
 *
 * @author Box
 */
public class PotKategorija {
    private int idPotKategorija;
    private String naziv;
    private int kategorijaID;

    /**
     * @return the idPotKategorija
     */
    public int getIdPotKategorija() {
        return idPotKategorija;
    }

    /**
     * @param idPotKategorija the idPotKategorija to set
     */
    public void setIdPotKategorija(int idPotKategorija) {
        this.idPotKategorija = idPotKategorija;
    }

    /**
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * @param naziv the naziv to set
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     * @return the kategorijaID
     */
    public int getKategorijaID() {
        return kategorijaID;
    }

    /**
     * @param kategorijaID the kategorijaID to set
     */
    public void setKategorijaID(int kategorijaID) {
        this.kategorijaID = kategorijaID;
    }
}
