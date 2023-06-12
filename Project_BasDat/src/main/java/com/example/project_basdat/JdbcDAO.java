package com.example.project_basdat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDAO {
    public static Connection dblink;
    String dbname = "project basdat sem2";
    String dbUrl = "jdbc:mysql://localhost/" + dbname;
    String dbUser = "root";
    String dbPass = "";


}
