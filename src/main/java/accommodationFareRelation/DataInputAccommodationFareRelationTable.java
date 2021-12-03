package accommodationFareRelation;

import useful.ConnectToDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInputAccommodationFareRelationTable {
    public static void inputDataAccommodationFareRelationTable() {
        try (Connection connection = DriverManager.getConnection(ConnectToDb.DB_URL, ConnectToDb.USER, ConnectToDb.PASS)){
            inputDataAccommodationFareRelation(connection);
//            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void inputDataAccommodationFareRelation(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO accommodation_fare_relations VALUES (?,?,?)");

        createRowAccommodationFareRelation(ps, 1, 1, 1);
        createRowAccommodationFareRelation(ps, 2, 1, 5);
        createRowAccommodationFareRelation(ps, 3, 1, 9);
        createRowAccommodationFareRelation(ps, 4, 1, 13);

        createRowAccommodationFareRelation(ps, 5, 2, 2);
        createRowAccommodationFareRelation(ps, 6, 2, 6);
        createRowAccommodationFareRelation(ps, 7, 2, 10);
        createRowAccommodationFareRelation(ps, 8, 2, 14);

        createRowAccommodationFareRelation(ps, 9, 3, 3);
        createRowAccommodationFareRelation(ps, 10, 3, 7);
        createRowAccommodationFareRelation(ps, 11, 3, 11);
        createRowAccommodationFareRelation(ps, 12, 3, 15);

        createRowAccommodationFareRelation(ps, 13, 4, 4);
        createRowAccommodationFareRelation(ps, 14, 4, 8);
        createRowAccommodationFareRelation(ps, 15, 4, 12);
        createRowAccommodationFareRelation(ps, 16, 4, 16);

        createRowAccommodationFareRelation(ps, 17, 5, 2);
        createRowAccommodationFareRelation(ps, 18, 5, 6);
        createRowAccommodationFareRelation(ps, 19, 5, 10);
        createRowAccommodationFareRelation(ps, 20, 5, 14);

        createRowAccommodationFareRelation(ps, 21, 6, 2);
        createRowAccommodationFareRelation(ps, 22, 6, 6);
        createRowAccommodationFareRelation(ps, 23, 6, 10);
        createRowAccommodationFareRelation(ps, 24, 6, 14);

        createRowAccommodationFareRelation(ps, 25, 7, 2);
        createRowAccommodationFareRelation(ps, 26, 7, 6);
        createRowAccommodationFareRelation(ps, 27, 7, 10);
        createRowAccommodationFareRelation(ps, 28, 7, 14);

    }

    private static void createRowAccommodationFareRelation(PreparedStatement ps, int id, int id_accommodation,
                                                           int id_room_fare) throws SQLException {

        ps.setInt(1, id);
        ps.setInt(2, id_accommodation);
        ps.setInt(3, id_room_fare);
        ps.executeUpdate();
    }



}
