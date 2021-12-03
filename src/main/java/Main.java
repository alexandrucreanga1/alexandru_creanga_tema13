import accommodation.CreateAccommodationTable;
import accommodation.DataInputAccommodationTable;
import accommodationFareRelation.CreateAccommodationFareRelationTable;
import accommodationFareRelation.DataInputAccommodationFareRelationTable;
import roomFare.CreateRoomFareTable;
import roomFare.DataInputRoomFareTable;
import useful.ConnectToDb;

import java.sql.*;

public class Main {

    static String queryDatabase =
            "SELECT " +
                    "accommodation.type," +
                    "room_fare.value," +
                    "room_fare.season " +
                    "FROM accommodation_fare_relations " +
                    "JOIN accommodation ON accommodation.id = accommodation_fare_relations.id_accommodation " +
                    "JOIN room_fare ON room_fare.id = accommodation_fare_relations.id_room_fare"
            ;

    public static void main(String[] args) {
        CreateAccommodationTable.createAccommodationTable();
        DataInputAccommodationTable.insertDataIntoAccommodationTable();

        CreateRoomFareTable.createRoomFareTable();
        DataInputRoomFareTable.inputDataRoomFareTable();

        CreateAccommodationFareRelationTable.createAccommodationFareRelationTable();
        DataInputAccommodationFareRelationTable.inputDataAccommodationFareRelationTable();

        try (Connection connection = DriverManager.getConnection(ConnectToDb.DB_URL, ConnectToDb.USER, ConnectToDb.PASS);
             Statement statement = connection.createStatement();
        ){
            ResultSet resultSet = statement.executeQuery(queryDatabase);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("type"));
                System.out.println(resultSet.getDouble("value"));
                System.out.println(resultSet.getString("season"));
                System.out.println("-------------------------");
                System.out.println("-------------------------");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
