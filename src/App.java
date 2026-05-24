import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.java.model.Student;
import com.java.service.StudentService;

public class App {
    private Connection connection;
    private static StudentService studentService;

    public App() {
        connection = gConnection();
        studentService = new StudentService(connection);
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        studentService.deleteStudent(4);
    }
    
    private Connection gConnection(){
        String url = "jdbc:postgresql://localhost:5432/schoolManagement";
        String user = "admin";
        String passwd = "braw$tdb95";
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void insert(String name, String gender, int grade){
        Student student = new Student(name, gender, grade);
        studentService.insertStudent(student);
    }
}
