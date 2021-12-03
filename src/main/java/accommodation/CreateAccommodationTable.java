package accommodation;

import useful.ConnectToDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static useful.Color.ANSI_GREEN;

public class CreateAccommodationTable {


    public static void createAccommodationTable() {
        try (Connection connection = DriverManager.getConnection(ConnectToDb.DB_URL, ConnectToDb.USER, ConnectToDb.PASS);
             Statement statement = connection.createStatement();
        ) {
            String sql = "CREATE TABLE accommodation\n" +
                    "                    (\n" +
                    "                    id integer NOT NULL,\n" +
                    "                    type character varying(32) NOT NULL,\n" +
                    "                    bed_type character varying(32) NOT NULL,\n" +
                    "                    max_guests integer NOT NULL,\n" +
                    "                    description character varying(512) NOT NULL,\n" +
                    "                    CONSTRAINT accommodation_pkey PRIMARY KEY (id)\n" +
                    "                    );";

            statement.executeUpdate(sql);
            System.out.println(ANSI_GREEN + "The table was created !");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
