package com.example.springintellij;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class SpringIntelliJApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntelliJApplication.class, args);
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setUser("sa");
        dataSource.setPassword("123");
        dataSource.setServerName("DESKTOP-OHVB6QL");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("SQL_center");

        try (Connection conn = dataSource.getConnection()) {
        }catch (SQLServerException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
