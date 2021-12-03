package accommodation;
import useful.ConnectToDb;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;



public class DataInputAccommodationTable {

    static Accommodation accommodation1 = new Accommodation(1, "single", "standard", 1, "perfect for lonely guys or girls!");
    static Accommodation accommodation2 = new Accommodation(2, "double", "double", 2, "perfect for lovers!");
    static Accommodation accommodation3 = new Accommodation(3, "triple", "triple", 3, "perfect for ....family +1 small kid!");
    static Accommodation accommodation4 = new Accommodation(4, "quad", "king", 4, "perfect for ....family +2 small kids!");
    static Accommodation accommodation5 = new Accommodation(5, "king", "king", 2, "perfect for big boys and big girls");
    static Accommodation accommodation6 = new Accommodation(6, "queen", "queen", 2, "perfect for big girls and big boys");
    static Accommodation accommodation7 = new Accommodation(7, "budgetDouble", "standard", 2, "perfect for economClass");

    static List<Accommodation> accommodationList = Arrays.asList(
            accommodation1,
            accommodation2,
            accommodation3,
            accommodation4,
            accommodation5,
            accommodation6,
            accommodation7
    );

    static String sql = "INSERT INTO accommodation VALUES (?, ?, ?, ?, ?)";

    public static void insertDataIntoAccommodationTable() {
        try (
                Connection connection = DriverManager.getConnection(ConnectToDb.DB_URL, ConnectToDb.USER, ConnectToDb.PASS);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            for (Accommodation a : accommodationList) {
                preparedStatement.setInt(1, a.getId());
                preparedStatement.setString(2, a.getType());
                preparedStatement.setString(3, a.getBed_type());
                preparedStatement.setInt(4, a.getMax_guests());
                preparedStatement.setString(5, a.getDescription());

                preparedStatement.addBatch();

                preparedStatement.executeBatch();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}


// versiunea 1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//static Accommodation accommodation1 = new Accommodation(1, "single", "standard", 1, "perfect for lonely guys or girls!");
//    static Accommodation accommodation2 = new Accommodation(2, "double", "double", 2, "perfect for lovers!");
//    static Accommodation accommodation3 = new Accommodation(3, "triple", "triple", 3, "perfect for ....family +1 small kid!");
//
//
//    static List<Accommodation> accommodationList = Arrays.asList(
//            accommodation1,
//            accommodation2,
//            accommodation3,
//            accommodation4,
//            accommodation5,
//            accommodation6,
//            accommodation7
//    );
//
//    static String sql = "INSERT INTO accommodation VALUES (?, ?, ?, ?, ?)";
//
//    public static void insertDataIntoAccommodationTable() {
//        try (
//                Connection connection = DriverManager.getConnection(ConnectToDb.DB_URL, ConnectToDb.USER, ConnectToDb.PASS);
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ) {
//            for (Accommodation a : accommodationList) {
//                preparedStatement.setInt(1, a.getId());
//                preparedStatement.setString(2, a.getType());
//                preparedStatement.setString(3, a.getBed_type());
//                preparedStatement.setInt(4, a.getMax_guests());
//                preparedStatement.setString(5, a.getDescription());
//
//                preparedStatement.addBatch();
//
//                preparedStatement.executeBatch();
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


// versiunea 2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//static Accommodation accommodation1 = new Accommodation(1, "single", "standard", 1, "perfect for lonely guys or girls!");
//    static Accommodation accommodation2 = new Accommodation(2, "double", "double", 2, "perfect for lovers!");
//    static Accommodation accommodation3 = new Accommodation(3, "triple", "triple", 3, "perfect for ....family +1 small kid!");
//
//
//    static List<Accommodation> accommodationList = Arrays.asList(
//            accommodation1,
//            accommodation2,
//
//    );
//
//    static String sql = "INSERT INTO accommodation VALUES (?, ?, ?, ?, ?)";
//
//    public static void insertDataIntoAccommodationTable() {
//        try (
//                Connection connection = DriverManager.getConnection(ConnectToDb.DB_URL, ConnectToDb.USER, ConnectToDb.PASS);
//                PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        ) {
//            for (Accommodation a : accommodationList) {
//                preparedStatement.setInt(1, a.getId());
//                preparedStatement.setString(2, a.getType());
//
//
//                preparedStatement.addBatch();
//
//                preparedStatement.executeBatch();
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }