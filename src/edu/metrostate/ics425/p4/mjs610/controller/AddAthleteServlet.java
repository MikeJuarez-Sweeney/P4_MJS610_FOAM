package edu.metrostate.ics425.p4.mjs610.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.metrostate.ics425.p4.mjs610.db.Roster;
import edu.metrostate.ics425.p4.mjs610.model.AthleteBean;
import edu.metrostate.ics425.foam.data.RosterException;

/**
 * Servlet implementation class AthleteBeanServlet
 */
@WebServlet("/addAthlete")
public class AddAthleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 20191004L;

	public AddAthleteServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/viewRoster";

		var athleteData = getAthleteData(request);
		List<String> errors = null;
		try {
			errors = validateAthleteData(athleteData);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (errors.isEmpty()) {
			try {
				addAthleteToRoster(athleteData);

			} catch (RosterException e) {
				errors.add("Unable to add athlete to roster.");
				e.printStackTrace();
			}
		} else {
			request.setAttribute("errors", errors);
			url = "/AddAthlete.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

	private void addAthleteToRoster(String[] athleteData) throws RosterException {
		AthleteBean newAthlete = new AthleteBean();

		newAthlete.setNationalID((athleteData[0]));
		newAthlete.setFirstName(athleteData[1]);
		newAthlete.setLastName(athleteData[2]);
		newAthlete.setDateOfBirth(
				(athleteData[3] == null || athleteData[3].isBlank()) ? null : LocalDate.parse(athleteData[3]));
		Roster.getInstance().add(newAthlete);
	}

	private List<String> validateAthleteData(String[] athleteData) throws SQLException {
		List<String> errors = new LinkedList<>();
		
		if (Roster.getInstance().isOnRoster(athleteData[0])) {
			errors.add("National ID already exists");
		}
		if (athleteData[0] == null || athleteData[0].isBlank()) {
			errors.add("National ID is missing");
		}
		if (athleteData[1] == null || athleteData[1].isBlank()) {
			errors.add("First name missing");
		}
		if (athleteData[2] == null || athleteData[2].isBlank()) {
			errors.add("Last name missing");
		}

		try {
		LocalDate.parse(athleteData[3]);
					
		} catch(Exception e) {
			errors.add("Date of birth invalid");
		}
	
		return errors;

		
	}

	private String[] getAthleteData(HttpServletRequest request) {
		String[] athleteData = new String[4];

		athleteData[0] = request.getParameter("nationalID");
		athleteData[1] = request.getParameter("firstName");
		athleteData[2] = request.getParameter("lastName");
		athleteData[3] = request.getParameter("dateOfBirth");

		return athleteData;
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
