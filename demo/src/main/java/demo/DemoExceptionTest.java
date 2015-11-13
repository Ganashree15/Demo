package demo;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.api.Invocation;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

}
