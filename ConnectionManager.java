import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public Connection connect(){
        String url = "jdbc:postgresql://localhost:5432/shop";
        String user = "postgres";
        String password = "123";
        try {
         Connection   conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
     return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



}
