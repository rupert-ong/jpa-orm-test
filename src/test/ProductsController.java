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
      findProduct(req, res);
    } else if(req.getParameter("all") != null) {
      sendResponse(req, res, qString);
    } else {
      sendResponse(req, res, "Should write to database");
    }
  }

  /**
   * Search product table (via Product Entity class) by id and send page response
   * @param req HttpServletRequest
   * @param res HttpServletResponse
   */
  private void findProduct(HttpServletRequest req, HttpServletResponse res) {
    EntityManager em = getEM(req);

    Product prod = null;
    String msg = "No product found";

    try{
      long id = Integer.parseInt(req.getParameter("id").trim());
      prod = em.find(Product.class, id);
      if(prod != null) {
        msg = "Product " + id + " is named " + prod.getProductName() + ".";
      }
    } 
    catch(NumberFormatException e){}
    catch(IllegalArgumentException e){}

    sendResponse(req, res, msg);
  }

  /**
   * Get session stored Entity Manager (or create if null)
   * @param  req HttpServlet
   * @return     EntityManager instance (stored in a session)
   */
  private EntityManager getEM(HttpServletRequest req) {
    HttpSession session = req.getSession();
    EntityManager em = (EntityManager) session.getAttribute(emKey);

    if(em == null) {
      em = new PersistenceManager().getEntityManager();
      session.setAttribute(emKey, em);
    }

    return em;
  }

  /**
   * Redirect to a confirmation page with a string to output
   * @param req HttpServletRequest
   * @param res HttpServletResponse
   * @param msg String to output
   */
  private void sendResponse(HttpServletRequest req, HttpServletResponse res, String msg) {
    HttpSession session = req.getSession();
    session.setAttribute("msg", msg);
    try{
      res.sendRedirect("confirm.jsp");
    } catch(Exception e){}
  }

}