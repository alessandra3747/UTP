package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Database {

    private Statement statement;
    private String url;
    private TravelData travelData;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {
        try {
            Connection connection = DriverManager.getConnection(url);
            this.statement = connection.createStatement();

            initializeTableOffer();

            addRecordsToTableOffer(Locale.getDefault());

        } catch (SQLException ex) {

        }
    }

    public void showGui() {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Travel Offers");

            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout());

            JComboBox<String> languageSelector = new JComboBox<>(new String[] {"pl_PL", "en_GB"});
            mainPanel.add(languageSelector, BorderLayout.NORTH);

            JTable table = new JTable();
            JScrollPane scrollPane = new JScrollPane(table);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            loadTableData(table);

            languageSelector.addActionListener(e -> {
                initializeTableOffer();

                String[] parts = languageSelector.getSelectedItem().toString().split("_");
                Locale userLocale = new Locale(parts[0], parts[1].toUpperCase());

                addRecordsToTableOffer(userLocale);

                loadTableData(table);
            });

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }

    private void loadTableData(JTable table) {

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
                "Country", "Date From", "Date To", "Place", "Price", "Currency"
        });

        try {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM offer");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while (resultSet.next()) {
                String country = resultSet.getString("COUNTRY");
                String dateFrom = dateFormat.format(resultSet.getDate("DATE_FROM"));
                String dateTo = dateFormat.format(resultSet.getDate("DATE_TO"));
                String place = resultSet.getString("PLACE");
                String price = resultSet.getString("PRICE");
                String currency = resultSet.getString("CURRENCY");

                model.addRow(new Object[]{country, dateFrom, dateTo, place, price, currency});

            }

            table.setModel(model);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void initializeTableOffer() {
        try {
            statement.executeUpdate("DROP TABLE OFFER");
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("42X05"))
                System.out.println("Table OFFER exist");
            else
                System.out.println(ex.getMessage());
        }

        try {
            statement.executeUpdate("CREATE TABLE OFFER (" +
                    "ID INT PRIMARY KEY," +
                    "COUNTRY VARCHAR(60)," +
                    "DATE_FROM DATE," +
                    "DATE_TO DATE," +
                    "PLACE VARCHAR(60)," +
                    "PRICE VARCHAR(60)," +
                    "CURRENCY VARCHAR(30))");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void addRecordsToTableOffer(Locale userLocale) {
        int idCounter = 1;
        try {
            for(Offer offer : travelData.getOffers()) {
                String sql = "INSERT INTO OFFER VALUES (" +
                        idCounter++ + ", '" +
                        offer.translateCountry(userLocale) + "', '" +
                        offer.translateDateFrom(userLocale, "yyyy-MM-dd") + "', '" +
                        offer.translateDateTo(userLocale, "yyyy-MM-dd") + "', '" +
                        offer.translatePlace(userLocale) + "', '" +
                        offer.translatePrice(userLocale) + "', '" +
                        offer.translateCurrency() + "')";

                statement.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
