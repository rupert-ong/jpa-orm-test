package test;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProductsController extends HttpServlet {
  private static int n = 1;
  private static final String emKey = "_entityManager";

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) {
    String qString = req.getQueryString();
    System.err.println(qString);

    if(req.getParameter("id") != null) {
      findProduct(req, res);
    } else if(req.getParameter("all") != null) {
      listAllProducts(req, res);
    } else {
      saveToDatabase(req, res);
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

  private void listAllProducts(HttpServletRequest req, HttpServletResponse res) {
    EntityManager em = getEM(req);

    TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
    List<Product> results = query.getResultList();

    sendResponse(req, res, results);
  }

  /**
   * Save an iterated Product to the database
   * @param req HttpServletRequest
   * @param res HttpServletResponse
   */
  private void saveToDatabase(HttpServletRequest req, HttpServletResponse res) {
    EntityManager em = getEM(req);

    Product prod = new Product();
    prod.setProductName("Super Ski " + n++);

    em.getTransaction().begin();
    // EntityManager maintains a list of things to persist
    if(!em.contains(prod)) {
      try{
        em.persist(prod); // Add to list
        em.flush();       // Flush the list - saves to database
      } catch(PersistenceException e){
        System.err.println("New product not saved to database");
      }
    }
    em.getTransaction().commit();

    sendResponse(req, res, prod.getProductName() + " has been persisted");
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

  /**
   * Redirect to a confirmation page with a list to output (show all)
   * @param req HttpServletRequest
   * @param res HttpServletResponse
   * @param msg String to output
   */
  private void sendResponse(HttpServletRequest req, HttpServletResponse res, List<Product> products) {
    HttpSession session = req.getSession();
    session.setAttribute("products", products);
    try{
      res.sendRedirect("show-all.jsp");
    } catch(Exception e){}
  }
}