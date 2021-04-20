package dataaccesslayer.database.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class updateMethods {

    public void UpdateTourItem(String url, String user, String password,
                               String chosenTourName, String description, int distance, String start, String end){
        System.out.println("update this pls");
        String choseName = chosenTourName.toLowerCase(Locale.ROOT);

        try(Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE touritems SET tour_description = ?, tour_distance = ?, tour_start = ?, tour_end = ? WHERE lower(tour_name) LIKE ?;");
        ){
            statement.setString(1,description);
            statement.setInt(2, distance);
            statement.setString(3, start);
            statement.setString(4,end);

            statement.setString(5,choseName);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
