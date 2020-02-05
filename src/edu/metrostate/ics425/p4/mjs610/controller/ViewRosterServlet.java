package edu.metrostate.ics425.p4.mjs610.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.metrostate.ics425.p4.mjs610.db.Roster;

/**
 * Servlet implementation class ViewRosterServlet
 */
@WebServlet("/viewRoster")
public class ViewRosterServlet extends HttpServlet {
	private static final long serialVersionUID = 20191004L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRosterServlet() {
        super();
        
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		var athletes = Roster.getInstance().findAll();
		request.setAttribute("athletes", athletes);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
