/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.tags;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import vspr.java3.shop.model.view.ArtikalView;

/**
 *
 * @author Box
 */
public class KosaricaHandler extends SimpleTagSupport {

    private List<ArtikalView> kosarica;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            out.println(currencyFormat(izracunajUkupnuCijenuKosarice(kosarica)));

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in KosaricaHandler tag", ex);
        }
    }

    public void setKosarica(List<ArtikalView> kosarica) {
        this.kosarica = kosarica;
    }

    private static String currencyFormat(BigDecimal n) {
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        return defaultFormat.format(n);

    }

    public BigDecimal izracunajUkupnuCijenuKosarice(List<ArtikalView> kosarica) {
        BigDecimal ukupnaCijenaKosarice = new BigDecimal(0);
        for (ArtikalView av : kosarica) {
            ukupnaCijenaKosarice = ukupnaCijenaKosarice.add(av.getUkupnaCijena());
        }
        return ukupnaCijenaKosarice;
    }

}
