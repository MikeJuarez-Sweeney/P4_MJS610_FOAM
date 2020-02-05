package edu.metrostate.ics425.p4.mjs610.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.metrostate.ics425.p4.mjs610.db.Roster;
import edu.metrostate.ics425.foam.data.RosterException;

/**
 * Servlet implementation class DeleteAthlete
 */
@WebServlet("/deleteAthlete")
public class DeleteAthleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAthleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nationalID = request.getParameter("nationalID");
		String confirmation = " ";

		try {
			if (Roster.getInstance().isOnRoster(nationalID)) {
				try {
					String currFirst = Roster.getInstance().find(nationalID).getFirstName();
					String currLast = Roster.getInstance().find(nationalID).getLastName();
					Roster.getInstance().find(nationalID);
					confirmation = currFirst + " " + currLast + " has been deleted.";
					Roster.getInstance().delete(nationalID);

				} catch (RosterException e) {

					e.printStackTrace();
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		request.setAttribute("confirmation", confirmation);
		request.getRequestDispatcher("/viewRoster").forward(request, response);
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
