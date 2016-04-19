/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.model.view;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import vspr.java3.shop.model.Artikal;

/**
 *
 * @author Box
 */
public class ArtikalView extends Artikal {

    private String cijenaPoKomaduView;
    private short kolicina;
    private BigDecimal ukupnaCijena;
    private String ukupnaCijenaView;

    private static String currencyFormat(BigDecimal n) {
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        return defaultFormat.format(n);
    }

    /**
     * @return the CijenaPoKomaduView
     */
    public String getCijenaPoKomadu() {
        return currencyFormat(this.getCijenaBezPDV());
    }

    /**
     * @return the UkupnaCijena
     */
    public BigDecimal getUkupnaCijena() {
        return ukupnaCijena;
    }

    /**
     * @return the UkupnaCijenaView
     */
    public String getUkupnaCijenaView() {
        return currencyFormat(this.ukupnaCijena);
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
     * @param UkupnaCijena the UkupnaCijena to set
     */
    public void setUkupnaCijena(BigDecimal UkupnaCijena) {
        this.ukupnaCijena = UkupnaCijena;
    }

    /**
     * @return the cijenaPoKomaduView
     */
    public String getCijenaPoKomaduView() {
        return currencyFormat(this.getCijenaBezPDV());
    }

    /**
     * @param cijenaPoKomaduView the cijenaPoKomaduView to set
     */
}
