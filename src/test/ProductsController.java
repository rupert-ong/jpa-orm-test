package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductsController extends HttpServlet{
  private static int n = 1;
  private static final String emKey = "_entityManager";

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) {
    String qString = req.getQueryString();
    System.err.println(qString);

    if(req.getParameter("id") != null) {
      sendResponse(req, res, qString);
    } else if(req.getParameter("all") != null) {
      sendResponse(req, res, qString);
    } else {
      sendResponse(req, res, "Should write to database");
    }
  }

  private void sendResponse(HttpServletRequest req, HttpServletResponse res, String msg) {
    HttpSession session = req.getSession();
    session.setAttribute("msg", msg);
    try{
      res.sendRedirect("confirm.jsp");
    } catch(Exception e){}
  }

}
