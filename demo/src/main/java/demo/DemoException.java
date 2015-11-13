package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoException {

	private static final Logger logger = LoggerFactory.getLogger(DemoException.class);

	public DemoException(Service arg) {
		logger.info("before execution");
		String test = arg.execute("gana");

		logger.info("afterExecution:{}", test);
	}
}
