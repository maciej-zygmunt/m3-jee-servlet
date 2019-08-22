package pl.coderslab;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
//    public static final String URL_MYSQL ="jdbc:mysql://localhost:3306/coderslab?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
//    public static final String USER="root";
//    public static final String PASSWORD="coderslab";
    public static Connection getConnection(ServletContext context) throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String urlMysql=context.getInitParameter("url_mysql");
        String user=context.getInitParameter("user");
        String password=context.getInitParameter("password");
        return DriverManager.getConnection(urlMysql,user,password);
    }
}
