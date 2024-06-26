package com.lenigir.tomtickets;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

// This servlet is used to handle the event management
public class GestioneEventi extends HttpServlet {

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

    // Loading the driver
    try {

      Class.forName("org.apache.derby.jdbc.ClientDriver");
    }
    catch (ClassNotFoundException e) {

      throw new ServletException("Driver not found");
    }
  }

  public void doGet (HttpServletRequest req,
                     HttpServletResponse res)
    throws ServletException, IOException
  {

      // Get connection from session,
      // Assuming that the connection is already established
      HttpSession session = req.getSession(false);
      SessionConnection scon = (SessionConnection) session.getAttribute("sessionconnection");

      try {

        if (scon == null) {
          throw new Exception();
        }


	    // Forwarding the request to the Profile.jsp
        req.getRequestDispatcher("/WEB-INF/jsp/GestioneEventi.jsp").forward(req, res);

      }
      catch (Exception e) {

          req.setAttribute("error", "(Gestione Eventi) Errore di connessione al database, " + e.getMessage());
          req.getRequestDispatcher("/error").forward(req, res);
      }
  }

  public void doPost (HttpServletRequest req,
                      HttpServletResponse res)
    throws ServletException, IOException
  {
    doGet(req, res);
  }
}
