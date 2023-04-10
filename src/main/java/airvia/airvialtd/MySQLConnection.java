package airvia.airvialtd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLConnection {

    public static Connection ConnectDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk/in2018g22", "in2018g22_a", "dM8Sf9EB");
            System.out.println("connection");
            return conn;
        } catch (Exception e) {
            System.out.println("connection error");
        }

        return null;
    }

    public static ObservableList<Tickets> getDataTickets() {

        Connection conn = ConnectDB();
        ObservableList<Tickets> list = FXCollections.observableArrayList();
        System.out.println("try to display");
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Ticket");
            System.out.println("prepared statement");
            ResultSet rs = ps.executeQuery();
            System.out.println("result set");

            /*while (rs.next()) {
                list.add(new Tickets(Integer.parseInt(rs.getString("ticket_ID")),
                        rs.getString("validity_status"),
                        rs.getDate("purchase_date"),
                        rs.getString("payment_type"),
                        Float.parseFloat(rs.getString("payment_total")),
                        rs.getString("refund_status")));


            }*/

            for (int i=0; i<rs.getMetaData().getColumnCount(); i++) {

            }
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
