//import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class AppController {
    @FXML
    TableView<Fisherman> tableViewFisherman;
    @FXML
    TableView<Fish> tableViewFish;
    @FXML
    TableColumn<Fisherman, String> tableColumnFishermanName;
    @FXML
    TableColumn<Fisherman, String> tableColumnFishermanSurname;
    @FXML
    TableColumn<Fisherman, SimpleDateFormat> tableColumnFishermanLicence;
    @FXML
    TableColumn<Fisherman, Boolean> tableColumnFishermanCommunity;
    @FXML
    TableColumn<Fish, String> tableColumnFishName;
    @FXML
    TableColumn<Fish, String> tableColumnFishSurname;
    @FXML
    TableColumn<Fish, String> tableColumnFishType;
    @FXML
    TableColumn<Fish, Float> tableColumnFishSize;
    @FXML
    TableColumn<Fish, Boolean> tableColumnFishVerified;
    @FXML
    TableColumn<Fish, SimpleDateFormat> tableColumnFishCatchDate;
    @FXML
    Button addFisherman;
    @FXML
    Button addFish;
    @FXML
    ObservableList<Fisherman> fishermen;
    @FXML
    ObservableList<Fish> fishes;
    @FXML
    Connection c;
    @FXML private int numberOfFishermen = 10;

    @FXML
    public void initialize() throws SQLException {
        prepareData();
        buildTables();
        updateFishermen();
        updateFishes();
    }

    @FXML
    public void addFisherman() throws SQLException {
        String[] names = {"Donald", "Jackie", "Merida", "Bugs", "Sknerus", "Karol", "Elton", "Anna", "Jojak", "Leman", "Harold", "Astra", "Paulina", "Marcin", "Owen", "Karolina", "Koralina", "Maria", "Bernadeta", "Will"};
        String[] surnames = {"Pi", "Kownacky", "Beer", "Warner", "Popowicz", "Welman", "Pepero", "Owenson", "Kokoran", "Najman", "Park", "Ufo", "Serman", "Koper", "Inson", "Wakanda", "Tampio", "Austin", "Hatter", "Stanovsky"};
        Random rand = new Random();
        PreparedStatement prep = c.prepareStatement("INSERT INTO FANATYCY_WEDKARSTWA (IMIE, NAZWISKO, OD_KIEDY_LICENCJA, ZWIAZEK) VALUES (?, ?, ?, ?)");
        prep.setString(1, names[rand.nextInt(names.length)]);
        prep.setString(2, surnames[rand.nextInt(surnames.length)]);
        prep.setDate(3, java.sql.Date.valueOf(randomDay()));
        prep.setBoolean(4, rand.nextBoolean());
        prep.executeUpdate();
        numberOfFishermen++;
        buildTables();
        updateFishermen();
    }

    @FXML
    public void addFish() throws SQLException {
        String[] typeOfFish = {"Szczupak", "Losos", "Plotka", "Rekin", "Karas", "Karp", "Sum"};
        PreparedStatement prepFish = c.prepareStatement("INSERT INTO ZLOWIONE_RYBY (IMIE, NAZWISKO, GATUNEK_RYBY, ROZMIAR_CM, ZWERYFIKOWANA, DATA_ZLOWIENIA, OWNER) SELECT FANATYCY_WEDKARSTWA.IMIE, FANATYCY_WEDKARSTWA.NAZWISKO, ?, ?, ?, ?, FANATYCY_WEDKARSTWA.ID FROM FANATYCY_WEDKARSTWA WHERE ID = ?");
        Random rand = new Random();
        prepFish.setString(1, typeOfFish[rand.nextInt(typeOfFish.length)]);
        prepFish.setFloat(2, rand.nextFloat() * 40 + 10);
        prepFish.setBoolean(3, rand.nextBoolean());
        prepFish.setDate(4, java.sql.Date.valueOf(randomDay()));
        prepFish.setObject(5, rand.nextInt(numberOfFishermen));
        prepFish.executeUpdate();
        buildTables();
        updateFishes();
    }

    public void prepareData() throws SQLException {
        try {
            c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
            createAndPopulateTable();
        } catch (SQLException exc) {
            throw new SQLException(exc);
        }
    }

    void createAndPopulateTable() throws SQLException {
        Statement s = c.createStatement();
        s.execute("DROP TABLE IF EXISTS FANATYCY_WEDKARSTWA");
        s.execute("CREATE TABLE FANATYCY_WEDKARSTWA (ID INT IDENTITY PRIMARY KEY, IMIE VARCHAR(255), NAZWISKO VARCHAR(255), OD_KIEDY_LICENCJA DATETIME, ZWIAZEK BOOLEAN);");
        s.execute("DROP TABLE IF EXISTS ZLOWIONE_RYBY");
        s.execute("CREATE TABLE ZLOWIONE_RYBY (ID INT IDENTITY PRIMARY KEY, IMIE VARCHAR(255), NAZWISKO VARCHAR(255), GATUNEK_RYBY VARCHAR(255), ROZMIAR_CM DECIMAL, ZWERYFIKOWANA BOOLEAN, DATA_ZLOWIENIA DATETIME, OWNER INT, FOREIGN KEY(OWNER) REFERENCES FANATYCY_WEDKARSTWA(ID));");
        String[] names = {"Donald", "Jackie", "Merida", "Bugs", "Sknerus", "Karol", "Elton", "Anna", "Jojak", "Leman", "Harold", "Astra", "Paulina", "Marcin", "Owen", "Karolina", "Koralina", "Maria", "Bernadeta", "Will"};
        String[] surnames = {"Pi", "Kownacky", "Beer", "Warner", "Popowicz", "Welman", "Pepero", "Owenson", "Kokoran", "Najman", "Park", "Ufo", "Serman", "Koper", "Inson", "Wakanda", "Tampio", "Austin", "Hatter", "Stanovsky"};
        Random rand = new Random();
        PreparedStatement prep = c.prepareStatement("INSERT INTO FANATYCY_WEDKARSTWA (IMIE, NAZWISKO, OD_KIEDY_LICENCJA, ZWIAZEK) VALUES (?, ?, ?, ?)");
        for (int i = 0; i < 10; i++) {
            prep.setString(1, names[rand.nextInt(names.length)]);
            prep.setString(2, surnames[rand.nextInt(surnames.length)]);
            prep.setDate(3, java.sql.Date.valueOf(randomDay()));
            prep.setBoolean(4, rand.nextBoolean());
            prep.executeUpdate();
        }
        String[] typeOfFish = {"Szczupak", "Losos", "Plotka", "Rekin", "Karas", "Karp", "Sum"};
        PreparedStatement prepFish = c.prepareStatement("INSERT INTO ZLOWIONE_RYBY (IMIE, NAZWISKO, GATUNEK_RYBY, ROZMIAR_CM, ZWERYFIKOWANA, DATA_ZLOWIENIA, OWNER) SELECT FANATYCY_WEDKARSTWA.IMIE, FANATYCY_WEDKARSTWA.NAZWISKO, ?, ?, ?, ?, FANATYCY_WEDKARSTWA.ID FROM FANATYCY_WEDKARSTWA WHERE ID = ?");
        for (int i = 0; i < 20; i++) {
            prepFish.setString(1, typeOfFish[rand.nextInt(typeOfFish.length)]);
            prepFish.setFloat(2, rand.nextFloat() * 40 + 10);
            prepFish.setBoolean(3, rand.nextBoolean());
            prepFish.setDate(4, java.sql.Date.valueOf(randomDay()));
            prepFish.setObject(5, rand.nextInt(10));
            prepFish.executeUpdate();
        }
    }

    public void buildTables() throws SQLException {
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM FANATYCY_WEDKARSTWA");
        ObservableList<Fisherman> items = FXCollections.observableArrayList();
        while (rs.next()) {
            Fisherman fisherman = new Fisherman();
            fisherman.setIMIE(rs.getString("IMIE"));
            fisherman.setNAZWISKO(rs.getString("NAZWISKO"));
            fisherman.setOD_KIEDY_LICENCJA(rs.getDate("OD_KIEDY_LICENCJA"));
            fisherman.setZWIAZEK(rs.getBoolean("ZWIAZEK"));
            items.add(fisherman);
        }
        fishermen = items;

        ResultSet rsf = s.executeQuery("SELECT * FROM ZLOWIONE_RYBY");
        ObservableList<Fish> itemsf = FXCollections.observableArrayList();
        while (rsf.next()) {
            Fish fish = new Fish();
            fish.setIMIE(rsf.getString("IMIE"));
            fish.setNAZWISKO(rsf.getString("NAZWISKO"));
            fish.setGATUNEK_RYBY(rsf.getString("GATUNEK_RYBY"));
            fish.setROZMIAR_CM(rsf.getFloat("ROZMIAR_CM"));
            fish.setZWERYFIKOWANA(rsf.getBoolean("ZWERYFIKOWANA"));
            fish.setDATA_ZLOWIENIA(rsf.getDate("DATA_ZLOWIENIA"));
            itemsf.add(fish);
        }
        fishes = itemsf;
    }

    void updateFishermen() throws SQLException {
        tableColumnFishermanName.setCellValueFactory(new PropertyValueFactory<>("IMIE"));
        tableColumnFishermanSurname.setCellValueFactory(new PropertyValueFactory<>("NAZWISKO"));
        tableColumnFishermanLicence.setCellValueFactory(new PropertyValueFactory<>("OD_KIEDY_LICENCJA"));
        tableColumnFishermanCommunity.setCellValueFactory(new PropertyValueFactory<>("ZWIAZEK"));
        tableViewFisherman.setItems(fishermen);
    }

    void updateFishes() throws SQLException {
        tableColumnFishName.setCellValueFactory(new PropertyValueFactory<>("IMIE"));
        tableColumnFishSurname.setCellValueFactory(new PropertyValueFactory<>("NAZWISKO"));
        tableColumnFishType.setCellValueFactory(new PropertyValueFactory<>("GATUNEK_RYBY"));
        tableColumnFishSize.setCellValueFactory(new PropertyValueFactory<>("ROZMIAR_CM"));
        tableColumnFishVerified.setCellValueFactory(new PropertyValueFactory<>("ZWERYFIKOWANA"));
        tableColumnFishCatchDate.setCellValueFactory(new PropertyValueFactory<>("DATA_ZLOWIENIA"));
        tableViewFish.setItems(fishes);
    }

    public static String randomDay() {
        int startYear = 2010;
        int endYear = 2021;
        long start = Timestamp.valueOf(startYear + 1 + "-1-1 0:0:0").getTime();
        long end = Timestamp.valueOf(endYear + "-1-1 0:0:0").getTime();
        long ms = (long) ((end - start) * Math.random() + start);
        Date day = new Date(ms);
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        String strDate = formatter.format(day);
        return strDate;
    }
}
