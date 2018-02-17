package com.ef;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Parser {
	public static void main(String args[])throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/imcs", "root", "12345");
		Statement statement = con.createStatement();
        Statement statement1 = con.createStatement();
		
        statement.executeUpdate( "LOAD DATA LOCAL INFILE 'E:/nikith/access.log' INTO TABLE  logger FIELDS TERMINATED BY '|' LINES TERMINATED BY '\\n'");

        statement1.execute("select count(ip) as count ,ip as ip from logger \r\n" + 
        		"where time>='2017-01-01.00:00:00' and time<='2017-01-01.23:59:59' group by ip HAVING count > 500");
        ResultSet rs = statement1.getResultSet();
        while(rs.next()) {
        	System.out.println(rs.getString("ip"));
        }
        rs.close();
        statement.close();
        con.close();
		
	}
	
}
