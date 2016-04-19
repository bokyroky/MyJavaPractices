/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vspr.java3.shop.data;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import vspr.java3.shop.model.Artikal;
import vspr.java3.shop.model.Kategorija;
import vspr.java3.shop.model.Korisnik;
import vspr.java3.shop.model.PotKategorija;
import vspr.java3.shop.model.Racun;
import vspr.java3.shop.model.SelectListItem;
import vspr.java3.shop.model.Stavka;
import vspr.java3.shop.model.UserLog;
import vspr.java3.shop.model.view.ArtikalView;

/**
 *
 * @author Box
 */
public class DBAccessor {

    final DataSource dataSource;

    public DBAccessor(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Kategorija> getCategories_db() {
        List<Kategorija> categories = new LinkedList<>();

        final String callQuery = "{CALL GetCategories}";
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Kategorija k = new Kategorija();
                k.setIdKategorija(rs.getInt("IDKategorija"));
                k.setNaziv(rs.getString("Naziv"));
                categories.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;

    }

    public List<PotKategorija> getSubCategories_db(int idCategory) {
        List<PotKategorija> subCategories = new LinkedList<>();
        String callQuery = "{CALL GetSubCategories(?)}";
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idCategory);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                PotKategorija pk = new PotKategorija();
                pk.setIdPotKategorija(rs.getInt("IDPotKategorija"));
                pk.setNaziv(rs.getString("Naziv"));
                pk.setKategorijaID(rs.getInt("KategorijaID"));
                subCategories.add(pk);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subCategories;
    }

    public List<Artikal> getArticles_db(int idSubCategory) {
        List<Artikal> articles = new LinkedList<>();
        String callQuery = "{CALL GetArticles(?)}";
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idSubCategory);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Artikal a = new Artikal();
                a.setIdArtikal(rs.getInt("IDProizvod"));
                a.setNaziv(rs.getString("Naziv"));
                a.setPotKategorijaID(rs.getInt("PotKategorijaID"));
                a.setSifraArtikla(rs.getString("Sifra"));
                a.setCijenaBezPDV(rs.getBigDecimal("CijenaBezPDV"));
                a.setOpis(rs.getString("Opis"));
                a.setSlika(rs.getString("Slika"));
                articles.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articles;
    }

    public Artikal getArticle_db(int idArticle) {
        Artikal a = new Artikal();
        String callQuery = "{CALL GetArticleDetails(?)}";
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idArticle);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {

                a.setIdArtikal(rs.getInt("IDProizvod"));
                a.setNaziv(rs.getString("Naziv"));
                a.setPotKategorijaID(rs.getInt("PotKategorijaID"));
                a.setSifraArtikla(rs.getString("Sifra"));
                a.setCijenaBezPDV(rs.getBigDecimal("CijenaBezPDV"));
                a.setOpis(rs.getString("Opis"));
                a.setSlika(rs.getString("Slika"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public int insertUser_db(Korisnik k) {
        int rowsInserted = 0;
        String callQuery = "{CALL InsertUser(?,?)}";
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setString(1, k.getUsername());
            cs.setString(2, k.getPassword());
            rowsInserted = cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowsInserted;
    }

    public int validateUserCredentials_db(Korisnik k, boolean isAdmin) {
        int brojKorisnika = 0;
        String callQuery = "";
        if (!isAdmin) {
            callQuery = "{CALL CheckIfCustomerExists(?,?)}";
        } else {
            callQuery = "{CALL CheckIfAdminExists(?,?)}";
        }

        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setString(1, k.getUsername());
            cs.setString(2, k.getPassword());
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                brojKorisnika = rs.getInt("BrojKorisnika");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brojKorisnika;
    }

    public void insertKosarica_db(List<ArtikalView> kosarica, int idKorisnik,BigDecimal ukupnaVrijednost,String nacinPlacanja) {
        String callQuery = "{CALL InsertRacun(?,?,?,?)}";

        

        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setBigDecimal(1, ukupnaVrijednost);
            cs.setInt(2, idKorisnik);
            cs.setString(3, nacinPlacanja);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.executeUpdate();
            int idRacun = cs.getInt(4);
            for (ArtikalView av : kosarica) {
                insertStavka_db(av, idRacun);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getKorisnikID_db(String username, String password) {
        int idKorisnik = 0;
        String callQuery = "{CALL GetKorisnikID(?,?)}";
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setString(1, username);
            cs.setString(2, password);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                idKorisnik = rs.getInt("IDKorisnik");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idKorisnik;
    }

    private void insertStavka_db(ArtikalView stavka, int idRacun) {
        String callQuery = "{CALL InsertStavka(?,?,?,?,?)}";

        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idRacun);
            cs.setShort(2, stavka.getKolicina());
            cs.setInt(3, stavka.getIdArtikal());
            cs.setBigDecimal(4, stavka.getCijenaBezPDV());
            cs.setBigDecimal(5, stavka.getUkupnaCijena());

            cs.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Racun> getRacuniForKupac_db(int idKupac) {
        String callQuery = "{CALL GetRacuniForKupac(?)}";
        List<Racun> racuni = new LinkedList<>();
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idKupac);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Racun r = new Racun();
                r.setIdRacun(rs.getInt("IDRacun"));
                r.setBrojRacuna(rs.getString("BrojRacuna"));
                racuni.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return racuni;
    }

    public Racun getRacunDetalji_db(int idRacun) {
        String callQuery = "{CALL GetRacunDetalji(?)}";
        Racun r = new Racun();
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idRacun);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {

                r.setIdRacun(rs.getInt("IDRacun"));
                r.setBrojRacuna(rs.getString("BrojRacuna"));
                r.setDatumIzdavanja(rs.getDate("DatumIzdavanja"));
                r.setNacinPlacanja(rs.getString("NacinPlacanja"));
                r.setUkupnaCijena(rs.getBigDecimal("UkupnaCijena"));
            }
            return r;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Stavka> getStavkeForRacun_db(int idRacun) {
        String callQuery = "{CALL GetStavkeForRacun(?)}";
        List<Stavka> stavke = new LinkedList<>();
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idRacun);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Stavka s = new Stavka();
                s.setNazivArtikla(rs.getString("NazivProizvoda"));
                s.setCijenaPoKomadu(rs.getBigDecimal("CijenaPoKomadu"));
                s.setUkupnaCijena(rs.getBigDecimal("UkupnaCijena"));

                s.setKolicina(rs.getShort("Kolicina"));
                stavke.add(s);
            }
            return stavke;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addNewLog_db(UserLog log) {
        String callQuery = "{CALL InsertLog(?,?)}";

        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setString(1, log.getUsername());
            cs.setString(2, log.getUserIPAdress());
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<UserLog> getLogList_db(String orderColumn, String orderDirection) {
        String callQuery = "{CALL GetLogList(?,?)}";
        List<UserLog> logList = new LinkedList<>();
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setString(1, orderColumn);
            cs.setString(2, orderDirection);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                UserLog l = new UserLog();
                l.setUsername(rs.getString("KorisnickoIme"));
                l.setUserIPAdress(rs.getString("IPAdresa"));
                l.setLogDateTime(rs.getTimestamp("DatumVrijemeLogiranja"));
                logList.add(l);
            }
            return logList;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<SelectListItem> getKupci_db() {
        String callQuery = "{CALL GetKupci()}";
        List<SelectListItem> kupci = new LinkedList<>();
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                SelectListItem sli = new SelectListItem();
                sli.setValue(String.valueOf(rs.getInt("IDKorisnik")));
                sli.setText(rs.getString("KorisnickoIme"));
                kupci.add(sli);
            }
            return kupci;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Racun> getRacuniForKupacWithFilter_db(int idKupac, java.sql.Date startDate, java.sql.Date endDate) {
        String callQuery = "{CALL GetRacuniWithFilter(?,?,?)}";
        List<Racun> racuni = new LinkedList<>();
        try (Connection conn = dataSource.getConnection();
                CallableStatement cs = conn.prepareCall(callQuery)) {
            cs.setInt(1, idKupac);
            cs.setDate(2, startDate);
            cs.setDate(3, endDate);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Racun r = new Racun();
                r.setIdRacun(rs.getInt("IDRacun"));
                r.setBrojRacuna(rs.getString("BrojRacuna"));
                r.setDatumIzdavanja(rs.getDate("DatumIzdavanja"));
                r.setNacinPlacanja("Gotovina");
                r.setUkupnaCijena(rs.getBigDecimal("UkupnaCijena"));
                r.setKupacID(rs.getInt("KorisnikID"));
                racuni.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return racuni;
    }

}
