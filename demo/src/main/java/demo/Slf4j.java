package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4j {

	private static final Logger logger = LoggerFactory.getLogger(Slf4j.class);

	public static void main(String args[]) {

		logger.debug("hello:{}", new Object[] { logger.getName() });

	}

}
