package com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;


import com.controller.Controller;

public class Model {

	private static Model instance;
	
	private final Integer[] days;
	private final Integer[] months;
	private Integer[] years;
	private Connection con;
	
	
	
	private Model() {
		this.days = buildArrayOfIntegers(31);
		this.months = buildArrayOfIntegers(12);
		this.years = buildArrayOfIntegers(2020, Calendar.getInstance().get(Calendar.YEAR));
	}
	
	public static Model getInstance() {
		if(instance == null) {
			instance = new Model();
		}
		return instance;
	}
	
	/**
	 * Overload of the buildArrayOfIntegers method that set's the start point 1 default. 
	 * @param end
	 * @return Integer[]
	 */
	public Integer[] buildArrayOfIntegers(int end) {
		return buildArrayOfIntegers(1, end);
	}
	
	/**
	 * Method that uses the streams to create an array of Integers given starting point and ending point.
	 * @param start 
	 * @param end
	 * @return Integer[]
	 */
	public Integer[] buildArrayOfIntegers(int start, int end) {
		return IntStream.rangeClosed(start, end).boxed().toArray(Integer[]::new);
	}

	public String dateBuilder(String dayOrMonth) {
		String result = "";
		if(dayOrMonth.length()==1) {
			result += "0";
		}
		result += dayOrMonth;
		return result;
	}

	public void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aerlingusdb", "root", "root");	
			System.out.println("Database connection succes!");
		} catch(ClassNotFoundException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Connection getCon() {
		return con;
	}
	
	public Integer getMinYear() {

		Statement stmt;
		Calendar calendar = Calendar.getInstance();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MIN(PAYDAY) FROM PROVA;");
			
			if(!rs.next()) {
				System.out.println("table empty!");
			}else {
				Date paydayMin = rs.getDate("MIN(Payday)");
				calendar.setTime(paydayMin);
			    stmt.close();
			    
			    return ((Integer)calendar.get(Calendar.YEAR));
			
			}
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getMaxYear() {

		Statement stmt;
		Calendar calendar = Calendar.getInstance();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(PAYDAY) FROM PROVA;");
			
			if(!rs.next()) {
				System.out.println("table empty!");
			}else {
				Date paydayMax = rs.getDate("MAX(Payday)");
				calendar.setTime(paydayMax);
			    stmt.close();
			    return ((Integer)calendar.get(Calendar.YEAR));
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Integer getMinMonth(Integer year) {

		PreparedStatement stmt;
		Calendar calendar = Calendar.getInstance();
		try {
			stmt = con.prepareStatement("SELECT MIN(PAYDAY) FROM PROVA WHERE YEAR(PAYDAY) = ?;");
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				System.out.println("table empty!");
			}else {
				Date result = rs.getDate("MIN(PAYDAY)");
			    stmt.close();
			    return result.getMonth()+1;
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getMaxMonth(Integer year) {

		PreparedStatement stmt;
		Calendar calendar = Calendar.getInstance();
		try {
			stmt = con.prepareStatement("SELECT MAX(PAYDAY) FROM PROVA WHERE YEAR(PAYDAY) = ?;");
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				System.out.println("table empty!");
			}else {
				Date result = rs.getDate("MAX(PAYDAY)");
			    stmt.close();
			    return result.getMonth()+1;
			
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Double getExpectedSalary(String start, String end) {
		PreparedStatement stmt;
		Double expectedSalary = 0.0;
		try {
			stmt = con.prepareStatement("SELECT EXPECTEDSALARY FROM PROVA WHERE (PAYDAY BETWEEN ? AND ?);");
			stmt.setString(1, start);
			stmt.setString(2, end);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(1);
				expectedSalary+=rs.getDouble("EXPECTEDSALARY");
			}
			stmt.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return expectedSalary;
	}
	
	public Double getActualSalary(String start, String end) {
		PreparedStatement stmt;
		Double expectedSalary = 0.0;
		try {
			stmt = con.prepareStatement("SELECT ACTUALSALARY FROM PROVA WHERE (PAYDAY BETWEEN ? AND ?);");
			stmt.setString(1, start);
			stmt.setString(2, end);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				expectedSalary+=rs.getDouble("ACTUALSALARY");
			}
			stmt.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return expectedSalary;
	}

	public Integer[] getDays() {
		return days;
	}

	public Integer[] getMonths() {
		return months;
	}

	public Integer[] getYears() {
		return years;
	}
	
	

	
}
