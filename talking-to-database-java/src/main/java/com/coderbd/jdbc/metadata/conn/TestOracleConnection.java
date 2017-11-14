package com.coderbd.jdbc.metadata.conn;

import java.sql.Connection;

public class TestOracleConnection {
    public static void main(String[] args) {
         Connection conn=OracleDBConnection.getConnection("xe", "hr", "hr");
    }
  
}
