import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Fish {
    private SimpleStringProperty IMIE;
    private SimpleStringProperty NAZWISKO;
    private SimpleStringProperty GATUNEK_RYBY;
    private SimpleFloatProperty ROZMIAR_CM;
    private SimpleBooleanProperty ZWERYFIKOWANA;
    private Date DATA_ZLOWIENIA;

    Fish() {
        this.IMIE = new SimpleStringProperty();
        this.NAZWISKO = new SimpleStringProperty();
        this.GATUNEK_RYBY = new SimpleStringProperty();
        this.ROZMIAR_CM = new SimpleFloatProperty();
        this.ZWERYFIKOWANA = new SimpleBooleanProperty();
        this.DATA_ZLOWIENIA = new Date(0);
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

    public String getGATUNEK_RYBY() {
        return GATUNEK_RYBY.get();
    }

    public SimpleStringProperty GATUNEK_RYBYProperty() {
        return GATUNEK_RYBY;
    }

    public void setGATUNEK_RYBY(String GATUNEK_RYBY) {
        this.GATUNEK_RYBY.set(GATUNEK_RYBY);
    }

    public float getROZMIAR_CM() {
        return ROZMIAR_CM.get();
    }

    public SimpleFloatProperty ROZMIAR_CMProperty() {
        return ROZMIAR_CM;
    }

    public void setROZMIAR_CM(float ROZMIAR_CM) {
        this.ROZMIAR_CM.set(ROZMIAR_CM);
    }

    public boolean isZWERYFIKOWANA() {
        return ZWERYFIKOWANA.get();
    }

    public SimpleBooleanProperty ZWERYFIKOWANAProperty() {
        return ZWERYFIKOWANA;
    }

    public void setZWERYFIKOWANA(boolean ZWERYFIKOWANA) {
        this.ZWERYFIKOWANA.set(ZWERYFIKOWANA);
    }

    public Date getDATA_ZLOWIENIA() {
        return DATA_ZLOWIENIA;
    }

    public void setDATA_ZLOWIENIA(Date DATA_ZLOWIENIA) {
        this.DATA_ZLOWIENIA = DATA_ZLOWIENIA;
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
}
