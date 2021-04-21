package dataaccesslayer.database.methods;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class updateMethods {

    private static final Logger logger = LogManager.getLogger(updateMethods.class);

    public void UpdateTourItem(String url, String user, String password,
                               String chosenTourName, String description, int distance, String start, String end){
        System.out.println("update this pls");
        String choseName = chosenTourName.toLowerCase(Locale.ROOT);

        try(Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE touritems SET tour_description = ?, tour_distance = ?, " +
                    "tour_start = ?, tour_end = ? WHERE lower(tour_name) LIKE ?;");
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
        logger.info("Tour has been updated in Database");

    }

    public void UpdateLogItem(String url, String user, String password, String indexString, String date, String report, String distance,
                              String totalTime, int rating, String averageSpeed, String weather, int breaks, String start, String end){
        System.out.println("Updating Log");

        String[] arrayString = indexString.split("\\|", 2);
        String logName = arrayString[0].toLowerCase(Locale.ROOT);
        String logDate = arrayString[1];
        //System.out.println(logName);
        //System.out.println(logDate);

        try(Connection connection = DriverManager.getConnection(url,user, password);
            PreparedStatement statement = connection.prepareStatement("UPDATE logitems SET log_date = ?, log_report = ?, log_distance = ?," +
                    "log_total_time = ?, log_rating = ?, log_average_speed = ?, log_weather = ?, log_breaks = ?," +
                    "log_start = ?, log_end = ? WHERE (lower(log_name) LIKE ?) AND  (log_date LIKE ?);");
        ){
            statement.setString(1,date);
            statement.setString(2,report);
            statement.setString(3,distance);
            statement.setString(4,totalTime);
            statement.setInt(5, rating);
            statement.setString(6, averageSpeed);
            statement.setString(7, weather);
            statement.setInt(8, breaks);
            statement.setString(9, start);
            statement.setString(10,end);

            statement.setString(11,logName);
            statement.setString(12,logDate);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        logger.info("Log has been updated in Database");

    }
}
