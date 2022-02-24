package manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Nota;
import model.User;

public class Hibernate {
	private Session session;
	private Transaction tx;

	public void init() {
		initSession();

		// CRUD
		int id = insertExample();
		User user = getExample(id);
		updateExample(user);
		deleteExample(user);

		// Manual Queries
		ArrayList<User> users = getAllExample();
		oneToOneExample(user); // check User "notaMedia" attribute
		oneToManyExample(user); // chec User "notas" ArrayList

		endSession();
	}

	private void endSession() {
		session.close();
	}

	private void initSession() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();

		// Get the session object.
		session = sessionFactory.openSession();
	}

	private int insertExample() {
		// We create a user to save in the Database
		User user = new User("Maria", "Fernandez");

		try {
			// Start transaction (consecutive queries that need to be executed
			// together)
			tx = session.beginTransaction();

			// Insert a new student record in the database. Returns the id
			// generated
			int id = (Integer) session.save(user);

			tx.commit();
			System.out.println("Inserted Successfully.");

			return id;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}

		return 0;
	}

	private User getExample(int id) {
		try {
			tx = session.beginTransaction();
			
			// We get the user whith an specific id from database
			User user = session.get(User.class, id);

			// we print the information
			System.out.println(user.toString());

			tx.commit();
			System.out.println("Saved Successfully.");

			return user;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}

		return null;
	}

	// Update a User value and save it to the database
	private void updateExample(User user) {
		// We change some info from the user
		user.setSureName("Guitierrez");

		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			System.out.println("Updated Successfully.");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}
	}

	// deletes a user from the Database
	private void deleteExample(User user) {
		try {
			tx = session.beginTransaction();
			session.remove(user);
			tx.commit();
			System.out.println("Deleted Successfully.");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}
	}

	private ArrayList<User> getAllExample() {
		ArrayList<User> users = new ArrayList<>();

		try {
			tx = session.beginTransaction();

			// We create a manual query. Remember that "*" does not exist
			Query q = session.createQuery("select u from User u");

			// We get a List of Users
			List<User> usersList = q.list();

			// we add this users to our ArrayList
			users.addAll(usersList);

			for (User u : users) {
				System.out.println(u);
			}

			tx.commit();
			System.out.println("Get All Successfully.");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}

		return users;
	}

	private void oneToOneExample(User user) {
		// we generate a new Nota element to asign to the User notaMedia field.
		Nota notaMedia = new Nota(3, "I");

		// we add this element to the user
		user.setNotaMedia(notaMedia);

		try {
			tx = session.beginTransaction();
			
			//we save the Nota object on database
			session.save(notaMedia);

			// we update this information in the Database
			session.save(user);
			tx.commit();
			System.out.println("Added and Updated Successfully.");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}
	}

	private void oneToManyExample(User user) {
		List<Nota> notas = user.getNotas();
		int[] qualificaciones = {4, 7, 9};
		String[] abreviaturas = {"I","B","E"};

		try {
			tx = session.beginTransaction();

			//we generate and save all new Nota elements
			for (int i = 0; i < 3; i ++) {
				Nota nota = new Nota(qualificaciones[i], abreviaturas[i]);
				session.save(nota);
				
				//we add te Nota element to the arrayList of the user
				notas.add(nota);
			}
			
			// we update this information in the Database
			session.save(user);
			tx.commit();
			System.out.println("Entries added and Updated Successfully.");

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			e.printStackTrace();
		}
	}

}
