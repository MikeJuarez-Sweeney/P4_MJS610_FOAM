package edu.metrostate.ics425.p4.mjs610.model;

import java.io.Serializable;
import java.time.LocalDate;

import edu.metrostate.ics425.foam.model.Athlete;

/**
 * Java Bean to hold Athlete info.
 * 
 * @author mikej
 *
 */
public class AthleteBean implements Serializable, Athlete {
	/**
	 * 
	 */
	private static final long serialVersionUID = 20190917001L;
	private String nationalID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;

	/**
	 * No argument constructor.
	 */
	public AthleteBean() {
		nationalID = "";
		firstName = "";
		lastName = "";
		dateOfBirth = null;
	}

	/**
	 * Getter for the parameter National ID.
	 * 
	 * @return National ID
	 */

	public String getNationalID() {
		return this.nationalID;
	}

	/**
	 * Setter for the National ID.
	 * 
	 * @param nationalID
	 */
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	/**
	 * Getter for the athlete first name.
	 * 
	 * @return First Name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Setter for the parameter first name.
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the athlete last name.
	 * 
	 * @return
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Setter for the parameter last name.
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the athlete date of birth.
	 * 
	 * @return
	 */
	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * Setter for the parameter date of birth.
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * A method that generates the Age of the athlete based on date of birth.
	 * 
	 * @return age
	 */
	public int getAge() {
		return dateOfBirth == null ? UNSET_DATE
				: dateOfBirth.isAfter(OLYMPIC_DATE) ? INVALID_DATE : dateOfBirth.until(OLYMPIC_DATE).getYears();
	}

	/**
	 * Boolean that returns whether or not the athlete is old enough to compete.
	 * 
	 * @return Boolean
	 */
	public boolean isEligible() {
		return getAge() >= ELIGIBLE_AGE;
	}

	/**
	 * ToString method to display athlete information.
	 * 
	 * @return String
	 */

	@Override
	public String toString() {
		return "National ID: " + nationalID + "\n" + "First Name: " + firstName + "\n" + "Last Name: " + lastName
				+ "\n" + "Date of Birth: " + dateOfBirth;
	}

}
