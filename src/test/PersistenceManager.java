package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PersistenceManager implements ServletContextListener {
  private static EntityManagerFactory emf;
  private EntityManager em;

  public PersistenceManager(){}

  @Override
  public void contextInitialized(ServletContextEvent event){
    // ProductPU persistence unit defined in persistence.xml
    emf = Persistence.createEntityManagerFactory("ProductPU");
  }

  @Override
  public void contextDestroyed(ServletContextEvent event){
    emf.close();
  }

  public EntityManager getEntityManager(){
    if(emf == null){
      emf = Persistence.createEntityManagerFactory("ProductPU");
    }
    return emf.createEntityManager();
  }
}