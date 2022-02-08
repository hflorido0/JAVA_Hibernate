package manager;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Users;
import utils.HibernateUtil;

public class Hibernate {

	public static void main(String[] args) {
		//Create the student object.
    	Users user = new Users();
 
    	//Setting the object properties.
    	user.setUserName("Maria");
    	user.setPassword("maria123");
    	user.setCreateDate(new Date());
    	user.setCreateUser("Pol");
 
    	Transaction tx = null;
    	//Get the session object.
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	try{
              //Start hibernate session.
    	      tx = session.beginTransaction();
 
              //Insert a new student record in the database.
    	      //returns the id generated
    	      int id = (Integer) session.save(user); 
    	      
    	      //We store the user inserted into an object user2
    	      Users user2 = session.get(Users.class, id);
    	      
    	      //we print the information
    	      System.out.println(user2.toString());
 
              //Commit hibernate transaction if no exception occurs.
    	      tx.commit();
    	      System.out.println("Saved Successfully.");
    	  }catch (HibernateException e) {
    	     if(tx!=null){
                 //Roll back if any exception occurs. 
    	         tx.rollback();
    	     }
    	     e.printStackTrace(); 
    	  }finally {
             //Close hibernate session.
    	     session.close(); 
    	  }

	}

}
