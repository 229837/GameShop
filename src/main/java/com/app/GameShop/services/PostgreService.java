package com.app.GameShop.services;

import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class PostgreService {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    private String DatabaseServer = "jdbc:postgresql://localhost:5432/";
    private String user = "postgres";
    private String password = "root";

    public String read() {

            String database = "Test";

            try {
                con = DriverManager.getConnection(DatabaseServer+database, user, password);
                st = con.createStatement();

                rs = st.executeQuery("SELECT VERSION()");
                if (rs.next()) {
                    return (rs.getString(1));
                }

                con.close();

            } catch (SQLException ex) {
                return ("Error connection : " + ex.getMessage());
            }

            return "...";
        }

}
