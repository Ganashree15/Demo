package demo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Invocation;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Phone;
import model.User;

public class DemoExceptionTest {

	protected Mockery context = new JUnit4Mockery() {

		{
			// setThreadingPolicy(new Synchroniser());
			// setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	private Service service;

	@Before
	public void setUp() throws Exception {
		service = context.mock(Service.class);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDemoException() {

		context.checking(new Expectations() {

			{

				exactly(1).of(service).execute(with(any(String.class)));
				will(new CustomAction("my custom action") {

					@Override
					public Object invoke(Invocation invocation) throws Throwable {
						String name = (String) invocation.getParameter(0);
						return "hello, " + name;
					}

				});

			}

		});
		new DemoException(service);
	}

	@Test(expected = NullPointerException.class)
	public void testDemoExceptionWithNullPointerException() {
		new DemoException(null);
	}

	@Test
	public void testNewUser() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
		EntityManager entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();
		User user = null;
		Phone phone1 = null;
		Phone phone2 = null;
		try {
			user = new User();
			phone1 = new Phone();
			phone2 = new Phone();

			user.setName(Long.toString(new Date().getTime()));
			phone1.setNumber("0000000000");
			phone2.setNumber("1111111111");
			entityManager.persist(user);

			phone1.setUser(user);
			phone2.setUser(user);
			entityManager.persist(phone1);
			entityManager.persist(phone2);

			// user.getPhones().add(phone1);
			// user.getPhones().add(phone2);

			entityManager.getTransaction().commit();
			entityManager.refresh(user);

			System.err.println("phone size:" + user.getPhones().size());

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}
		User foundUser = entityManager.find(User.class, 1L);
		entityManager.close();
		emf.close();

		// see that the ID of the user was set by Hibernate
		System.err.println("user=" + user + ", user.id=" + user.getId());

		// note that foundUser is the same instance as user and is a concrete
		// class (not a proxy)
		System.err.println("foundUser=" + foundUser);

		for (Phone phone : foundUser.getPhones()) {
			System.err.println("phone number=" + phone.getNumber());
		}

		assertEquals(user.getName(), foundUser.getName());

	}
}
