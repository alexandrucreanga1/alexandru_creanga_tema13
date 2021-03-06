package roomFare;

import useful.ConnectToDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static useful.Color.ANSI_GREEN;

public class CreateRoomFareTable {

    public static void createRoomFareTable() {
        try (
                Connection connection = DriverManager.getConnection(ConnectToDb.DB_URL, ConnectToDb.USER, ConnectToDb.PASS);
                Statement statement = connection.createStatement();
        ) {
            String sql = "CREATE TABLE room_fare\n" +
                    "                    (\n" +
                    "                    id integer NOT NULL,\n" +
                    "                    value double precision,\n" +
                    "                    season character varying(32) ,\n" +
                    "                    CONSTRAINT room_fare_pkey PRIMARY KEY (id)\n" +
                    "                    );";

            statement.executeUpdate(sql);
            System.out.println(ANSI_GREEN + "The table was created !");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



}
