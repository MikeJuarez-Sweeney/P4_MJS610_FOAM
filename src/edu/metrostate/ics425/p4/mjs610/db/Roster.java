package edu.metrostate.ics425.p4.mjs610.db;
/**
 * This roster class allows one to get an instance of the roster and performs functions on the roster as needed.
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.metrostate.ics425.foam.model.Athlete;
import edu.metrostate.ics425.p4.mjs610.model.AthleteBean;

public class Roster {
	private DataSource ds;
	private static Roster instance = null;

	private Roster() {
		try {
			ds = getResource();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	private static DataSource getResource() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/FoamDB");
		return ds;
	}

	/**
	 * returns the instance of the Roster
	 * 
	 * @return instance of Roster
	 */
	public static Roster getInstance() {
		if (instance == null) {
			instance = new Roster();
		}
		return instance;
	}
	
	private static PreparedStatement myPreparedStatment(PreparedStatement pStmt, String newID) throws SQLException {
		pStmt.setString(1, newID);
		return pStmt;
	}

	/**
	 * Returns a linked list of all athletes in roster
	 * 
	 * @return athlete linked list list
	 */
	public List<Athlete> findAll() {
		LinkedList<Athlete> athletes = new LinkedList<>();
		String query = "SELECT NationalID, FirstName, LastName, DateOfBirth from foam.Athletes";

		try (Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {

			while (rs.next()) {

				String nationalID = rs.getString("NationalID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				LocalDate dob = rs.getDate("DateOfBirth").toLocalDate();

				AthleteBean newAthlete = new AthleteBean();

				newAthlete.setNationalID(nationalID);
				newAthlete.setFirstName(firstName);
				newAthlete.setLastName(lastName);
				newAthlete.setDateOfBirth(dob);

				athletes.add(newAthlete);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return athletes;
	}

	/**
	 * Returns an instance of the roster
	 * 
	 * @return Roster instance
	 */
	public static Roster getRoster() {
		return instance;
	}

	/**
	 * Finds an athlete on roster with a given National ID
	 * 
	 * @param newID
	 * @return Athlete
	 * @throws Exception
	 */
	public Athlete find(String newID) throws Exception {
		String query = "SELECT NationalID, FirstName, LastName, DateOfBirth from foam.Athletes WHERE NationalID = ?";
		Connection conn = ds.getConnection();
		PreparedStatement pStmt = myPreparedStatment(conn.prepareStatement(query), newID);
		ResultSet rs = pStmt.executeQuery();
		AthleteBean newAthlete = new AthleteBean();

		if (rs.next()) {
			newAthlete.setNationalID(rs.getString("NationalID"));
			newAthlete.setFirstName(rs.getString("FirstName"));
			newAthlete.setLastName(rs.getString("LastName"));
			newAthlete.setDateOfBirth(rs.getDate("DateOFBirth").toLocalDate());
		}
		return newAthlete;
	}

	/**
	 * Checks roster to see of given athlete ID is on the roster
	 * 
	 * @param newID
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean isOnRoster(String newID) throws SQLException {
		String query = "SELECT NationalID, FirstName, LastName, DateOfBirth from foam.Athletes WHERE NationalID = ?";
		Connection conn = ds.getConnection();
		PreparedStatement pStmt = myPreparedStatment(conn.prepareStatement(query), newID);
		ResultSet rs = pStmt.executeQuery();

		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A method that deletes a athlete record based on a passed NationalID
	 * 
	 * @param newID
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(String newID) throws SQLException {
		String query = "DELETE from Athletes where NationalID = ?";
		try (Connection conn = ds.getConnection();
				PreparedStatement pStmt = myPreparedStatment(conn.prepareStatement(query), newID);) {

			if (newID != null) {
				pStmt.setString(1, newID);
				pStmt.executeUpdate();
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * A method that adds a new athlete to the roster
	 * 
	 * @param athlete
	 * @return boolean
	 */
	public boolean add(Athlete athlete) {
		String natID = athlete.getNationalID();
		String fName = athlete.getFirstName();
		String lName = athlete.getLastName();
		LocalDate dob = athlete.getDateOfBirth();
		Date date = Date.valueOf(dob);
		String query = "INSERT INTO Athletes(NationalID, FirstName, LastName, DateOfBirth) VALUES(?, ?, ?, ?)";

		try (Connection conn = ds.getConnection();
				PreparedStatement pStmt = myPreparedStatment(conn.prepareStatement(query), natID);) {

			if (athlete != null) {
				pStmt.setString(1, natID);
				pStmt.setString(2, fName);
				pStmt.setString(3, lName);
				pStmt.setDate(4, date);


				pStmt.executeUpdate();
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * A method to replace an existing athlete record with an updated one.
	 * 
	 * @param athlete
	 * @return boolean
	 */
	public boolean update(AthleteBean athlete) {
		String natID = athlete.getNationalID();
		String fName = athlete.getFirstName();
		String lName = athlete.getLastName();
		LocalDate dob = athlete.getDateOfBirth();
		Date date = Date.valueOf(dob);

		String query = "UPDATE Athletes SET NationalID = ?, FirstName = ?, LastName = ?, DateOfBirth = ? WHERE NationalID = ?";

		try (Connection conn = ds.getConnection();
				PreparedStatement pStmt = myPreparedStatment(conn.prepareStatement(query), natID);) {

			if (athlete != null) {
				pStmt.setString(1, natID);
				pStmt.setString(2, fName);
				pStmt.setString(3, lName);
				pStmt.setDate(4, date);
				pStmt.setString(5, natID);

				pStmt.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
