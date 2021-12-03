import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class MainTest {
    Connection conn;

    @Before
    public void setUp() throws Exception {
        //create h2 DB
        //vezi exemplu in lectie video!
        // daca nu merge testul sterge fisierele "test.mv.db" si "test.trace.db" din C:Users\User
        //


        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE", "sa", "");
            Statement statement1 = conn.createStatement();
            Statement statement2 = conn.createStatement();
            Statement statement3 = conn.createStatement();


            statement1.execute("CREATE TABLE accommodation\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    type character varying(32) NOT NULL,\n" +
                    "    bed_type character varying(32) NOT NULL,\n" +
                    "    max_guests integer NOT NULL,\n" +
                    "    description character varying(512) NOT NULL,\n" +
                    "    CONSTRAINT accommodation_pkey PRIMARY KEY (id)\n" +
                    ");");


            statement2.execute("CREATE TABLE room_fare\n" +
                    "(\n" +
                    "    id integer NOT NULL,\n" +
                    "    value double precision,\n" +
                    "    season character varying(32) ,\n" +
                    "    CONSTRAINT room_fare_pkey PRIMARY KEY (id)\n" +
                    ");");


            statement3.executeUpdate("CREATE TABLE accommodation_fare_relations (\n" +
                    "  id integer,\n" +
                    "  id_accommodation integer NOT NULL,\n" +
                    "  id_room_fare integer NOT NULL,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  FOREIGN KEY (id_accommodation) REFERENCES accommodation(id)\n" +
                    "                        ON DELETE CASCADE,\n" +
                    "  FOREIGN KEY (id_room_fare) REFERENCES room_fare(id)\n" +
                    "                        ON DELETE CASCADE\n" +
                    ");");
//-----------------------------------------------------------------------------------------------------
// versiune anterioara --- poate trebuie de revizuit ?!

//            statement3.executeUpdate("CREATE TABLE accommodation_fare_relations (\n" +
//                    "  id_accommodation integer NOT NULL,\n" +
//                    "  id_room_fare integer NOT NULL,\n" +
//                    "  FOREIGN KEY (id_accommodation) REFERENCES accommodation(id)\n" +
//                    "                        ON DELETE CASCADE,\n" +
//                    "  FOREIGN KEY (id_room_fare) REFERENCES room_fare(id)\n" +
//                    "                        ON DELETE CASCADE\n" +
//                    ");");
//
// ---------------------------------------------------------------------------------------------------


            inputDataAccommodation(conn);
            inputDataRoomFare(conn);
            inputDataInAccommodationFareRelation(conn);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void inputDataAccommodation(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO accommodation VALUES (?, ?, ?, ?, ?)");

        accommodationRowCreation(ps, 1, "single", "standard", 1, "perfect for lonely guys or girls!");
        accommodationRowCreation(ps, 2, "double", "double", 2, "perfect for lovers!");
        accommodationRowCreation(ps, 3, "triple", "queen", 3, "perfect for ....family +1 small kid!");
        accommodationRowCreation(ps, 4, "quad", "king", 4, "perfect for ....family +2 small kids!");
        accommodationRowCreation(ps, 5, "king", "king", 2, "perfect for big boys and big girls");
        accommodationRowCreation(ps, 6, "queen", "queen", 2, "perfect for big girls and big boys");
        accommodationRowCreation(ps, 7, "budgetDouble", "standard", 2, "perfect for economClass");

    }

    private void accommodationRowCreation(
            PreparedStatement ps,
            int id,
            String type,
            String bed_type,
            int max_guests,
            String description) throws SQLException {

        ps.setInt(1, id);
        ps.setString(2, type);
        ps.setString(3, bed_type);
        ps.setInt(4, max_guests);
        ps.setString(5, description);

        ps.executeUpdate();

    }

    private void inputDataRoomFare(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO room_fare VALUES (?, ?, ?)");

        createRowRoomFare(ps, 1, 100, "winter");
        createRowRoomFare(ps, 2, 150, "winter");
        createRowRoomFare(ps, 3, 165, "winter");
        createRowRoomFare(ps, 4, 200, "winter");

        createRowRoomFare(ps, 5, 25, "spring");
        createRowRoomFare(ps, 6, 50, "spring");
        createRowRoomFare(ps, 7, 75, "spring");
        createRowRoomFare(ps, 8, 100, "spring");

        createRowRoomFare(ps, 9, 30, "summer");
        createRowRoomFare(ps, 10, 60, "summer");
        createRowRoomFare(ps, 11, 90, "summer");
        createRowRoomFare(ps, 12, 100, "summer");

        createRowRoomFare(ps, 13, 25, "autumn");
        createRowRoomFare(ps, 14, 40, "autumn");
        createRowRoomFare(ps, 15, 55, "autumn");
        createRowRoomFare(ps, 16, 85, "autumn");

    }

//    private void inputDataIntoRoomFare(Connection conn) throws SQLException {
//        PreparedStatement ps = conn.prepareStatement("INSERT INTO room_fare VALUES (?, ?, ?)");
//        insertRowIntoRoomFare(ps, 1, 100, "winter");
//        insertRowIntoRoomFare(ps, 2, 150, "winter");
//        insertRowIntoRoomFare(ps, 3, 165, "winter");
//        insertRowIntoRoomFare(ps, 4, 200, "winter");
//        insertRowIntoRoomFare(ps, 5, 25, "spring");
//        insertRowIntoRoomFare(ps, 6, 50, "spring");
//        insertRowIntoRoomFare(ps, 7, 75, "spring");
//        insertRowIntoRoomFare(ps, 8, 100, "spring");
//        insertRowIntoRoomFare(ps, 9, 30, "summer");
//        insertRowIntoRoomFare(ps, 10, 60, "summer");
//        insertRowIntoRoomFare(ps, 11, 90, "summer");
//        insertRowIntoRoomFare(ps, 12, 100, "summer");
//        insertRowIntoRoomFare(ps, 13, 25, "autumn");
//        insertRowIntoRoomFare(ps, 14, 40, "autumn");
//        insertRowIntoRoomFare(ps, 15, 55, "autumn");
//        insertRowIntoRoomFare(ps, 16, 85, "autumn");
//    }




    private void createRowRoomFare(PreparedStatement ps, int id, double value, String season) throws SQLException {
        ps.setInt(1, id);
        ps.setDouble(2, value);
        ps.setString(3, season);
        ps.executeUpdate();
    }

    private void inputDataInAccommodationFareRelation(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO accommodation_fare_relations VALUES (?,?,?)");

        createRowInAccommodationFareRelation(ps, 1, 1, 1);
        createRowInAccommodationFareRelation(ps, 2, 1, 5);
        createRowInAccommodationFareRelation(ps, 3, 1, 9);
        createRowInAccommodationFareRelation(ps, 4, 1, 13);

        createRowInAccommodationFareRelation(ps, 5, 2, 2);
        createRowInAccommodationFareRelation(ps, 6, 2, 6);
        createRowInAccommodationFareRelation(ps, 7, 2, 10);
        createRowInAccommodationFareRelation(ps, 8, 2, 14);

        createRowInAccommodationFareRelation(ps, 9, 3, 3);
        createRowInAccommodationFareRelation(ps, 10, 3, 7);
        createRowInAccommodationFareRelation(ps, 11, 3, 11);
        createRowInAccommodationFareRelation(ps, 12, 3, 15);

        createRowInAccommodationFareRelation(ps, 13, 4, 4);
        createRowInAccommodationFareRelation(ps, 14, 4, 8);
        createRowInAccommodationFareRelation(ps, 15, 4, 12);
        createRowInAccommodationFareRelation(ps, 16, 4, 16);

        createRowInAccommodationFareRelation(ps, 17, 5, 2);
        createRowInAccommodationFareRelation(ps, 18, 5, 6);
        createRowInAccommodationFareRelation(ps, 19, 5, 10);
        createRowInAccommodationFareRelation(ps, 20, 5, 14);

        createRowInAccommodationFareRelation(ps, 21, 6, 2);
        createRowInAccommodationFareRelation(ps, 22, 6, 6);
        createRowInAccommodationFareRelation(ps, 23, 6, 10);
        createRowInAccommodationFareRelation(ps, 24, 6, 14);

        createRowInAccommodationFareRelation(ps, 25, 7, 1);
        createRowInAccommodationFareRelation(ps, 26, 7, 2);
        createRowInAccommodationFareRelation(ps, 27, 7, 3);
        createRowInAccommodationFareRelation(ps, 28, 7, 1);


    }

    private void createRowInAccommodationFareRelation(PreparedStatement ps, int id, int id_accommodation, int id_room_fare) throws SQLException {
        ps.setInt(1, id);
        ps.setInt(2, id_accommodation);
        ps.setInt(3, id_room_fare);
        ps.executeUpdate();
    }


    @Test
    public void testOfferView() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT " +
                "accommodation.type," +
                "room_fare.value," +
                "room_fare.season " +
                "FROM accommodation_fare_relations " +
                "JOIN accommodation ON accommodation.id = accommodation_fare_relations.id_accommodation " +
                "JOIN room_fare ON room_fare.id = accommodation_fare_relations.id_room_fare");
        ResultSet resultSet =  ps.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString( "type"));
            System.out.println(resultSet.getDouble( "value"));
            System.out.println(resultSet.getString( "season"));
            System.out.println("------------------------- \n " +
                                "------------------------");
        }
    }




}