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
public class Artikal {
    private int idArtikal;
    private String naziv;
    private String sifraArtikla;
    private BigDecimal cijenaBezPDV;
    private int potKategorijaID;
    private String opis;
    private String slika;

    /**
     * @return the idArtikal
     */
    public int getIdArtikal() {
        return idArtikal;
    }

    /**
     * @param idArtikal the idArtikal to set
     */
    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
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
     * @return the sifraArtikla
     */
    public String getSifraArtikla() {
        return sifraArtikla;
    }

    /**
     * @param sifraArtikla the sifraArtikla to set
     */
    public void setSifraArtikla(String sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    /**
     * @return the cijenaBezPDV
     */
    public BigDecimal getCijenaBezPDV() {
        return cijenaBezPDV;
    }

    /**
     * @param cijenaBezPDV the cijenaBezPDV to set
     */
    public void setCijenaBezPDV(BigDecimal cijenaBezPDV) {
        this.cijenaBezPDV = cijenaBezPDV;
    }

    /**
     * @return the potKategorijaID
     */
    public int getPotKategorijaID() {
        return potKategorijaID;
    }

    /**
     * @param potKategorijaID the potKategorijaID to set
     */
    public void setPotKategorijaID(int potKategorijaID) {
        this.potKategorijaID = potKategorijaID;
    }

    /**
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * @param opis the opis to set
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * @return the slika
     */
    public String getSlika() {
        return slika;
    }

    /**
     * @param slika the slika to set
     */
    public void setSlika(String slika) {
        this.slika = slika;
    }
}
