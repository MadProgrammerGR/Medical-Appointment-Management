package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mainpackage.Admin;
import mainpackage.Doctor;
import mainpackage.Patient;
import mainpackage.User;

public class Accounts {
	private static PreparedStatement stm1;
	private static Connection con;
	static{
		try {
			InitialContext context = new InitialContext();
			DataSource src = (DataSource) context.lookup("java:comp/env/jdbc/postgres");
			con = src.getConnection();
			stm1 = con.prepareStatement("SELECT * FROM patient WHERE username = ? AND password = ?");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Patient getPatient(String username, String password) {
		try {
			stm1.setString(1, username);
			stm1.setString(2, password);
			ResultSet rs = stm1.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String amka = rs.getString("amka");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				return new Patient(id, username, password, amka, name, surname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Doctor getDoctor(String username, String password) {
		return null;
	}
	
	public static Admin getAdmin(String username, String password) {
		return null;
	}
	
	public static boolean register(Patient pat) {
		try {
			PreparedStatement  stm2 = con.prepareStatement("INSERT INTO PATIENT(AMKA,username,password,name,surname) VALUES( ?, ?, ?, ?, ?)");
			stm2.setString(1, pat.getAmka());
			stm2.setString(2, pat.getUsername());
			stm2.setString(3, pat.getPassword());
			stm2.setString(4, pat.getName());
			stm2.setString(5, pat.getSurname());
			stm2.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean register(Doctor doc) {
		return false;
	}
	
	public boolean ban(User user) {
		return false;
	}
	
}
