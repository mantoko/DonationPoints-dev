package com.mistphizzle.donationpoints.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MySQLConnection extends Database {
    
    private String host = "localhost";
    private int port = 3306;
    private String user;
    private String pass = "";
    private String database;
    
    public MySQLConnection(Logger log, String prefix, String host, int port, String user, String pass, String database) {
        super(log, prefix, "[MySQL] ");
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.database = database;
    }
    
    public MySQLConnection(Logger log, String prefix, String user, String pass, String database) {
        super(log, prefix, "[MySQL] ");
        this.user = user;
        this.pass = pass;
        this.database = database;
    }

    @Override
    public Connection open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;
           
            this.connection = DriverManager.getConnection(url, this.user, this.pass);
            this.printInfo("Connection established!");
            
            return this.connection;
        } catch(ClassNotFoundException e) {
            this.printErr("JDBC driver not found!", true);
            return null;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}