import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    private Connection connection;

    public App() {
        connection = gConnection();
    }
    
    public static void main(String[] args) throws Exception {
        App app = new App();
    }
    
    private Connection gConnection(){
        String url = "jdbc:postgresql://localhost:5432/schoolManagement";
        String user = "admin";
        String passwd = "braw$tdb95";
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connection established successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
