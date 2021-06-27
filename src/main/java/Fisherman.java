import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Fisherman {

    private SimpleStringProperty IMIE;
    private SimpleStringProperty NAZWISKO;
    private Date OD_KIEDY_LICENCJA;
    private SimpleBooleanProperty ZWIAZEK;

    Fisherman() {
        this.IMIE = new SimpleStringProperty();
        this.NAZWISKO = new SimpleStringProperty();
        this.OD_KIEDY_LICENCJA = new Date(0);
        this.ZWIAZEK = new SimpleBooleanProperty();
    }

    public String getIMIE() {
        return IMIE.get();
    }

    public SimpleStringProperty IMIEProperty() {
        return IMIE;
    }

    public void setIMIE(String IMIE) {
        this.IMIE.set(IMIE);
    }

    public String getNAZWISKO() {
        return NAZWISKO.get();
    }

    public SimpleStringProperty NAZWISKOProperty() {
        return NAZWISKO;
    }

    public void setNAZWISKO(String NAZWISKO) {
        this.NAZWISKO.set(NAZWISKO);
    }

    public Date getOD_KIEDY_LICENCJA() {
        return OD_KIEDY_LICENCJA;
    }

    public void setOD_KIEDY_LICENCJA(Date OD_KIEDY_LICENCJA) {
        this.OD_KIEDY_LICENCJA = OD_KIEDY_LICENCJA;
    }

    public boolean isZWIAZEK() {
        return ZWIAZEK.get();
    }

    public SimpleBooleanProperty ZWIAZEKProperty() {
        return ZWIAZEK;
    }

    public void setZWIAZEK(boolean ZWIAZEK) {
        this.ZWIAZEK.set(ZWIAZEK);
    }
}
