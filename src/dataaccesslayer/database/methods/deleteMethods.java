package dataaccesslayer.database.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteMethods {

    public void deleteTourMethod(String url, String user, String password, String name, String description){

        //delete in database
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM touritems WHERE tour_name = ? AND tour_description = ?;")
        ){
            statement.setString(1, name);
            statement.setString(2,description);

            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteLogMethod(String url, String user, String password, String date, String report){

        //delete in database
        try(Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM logitems WHERE log_date = ? AND log_report = ?;")
        ){
            statement.setString(1, date);
            statement.setString(2,report);

            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    };
}
