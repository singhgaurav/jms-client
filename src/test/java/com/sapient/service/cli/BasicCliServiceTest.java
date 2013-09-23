/**
 * 
 */
package com.sapient.service.cli;

import static org.junit.Assert.fail;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @author gsing5
 * 
 */
public class BasicCliServiceTest {

	private FlagResource flag;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		flag = new FlagResource();
		flag.setFile("flags.json");
	}

	/**
	 * Test method for
	 * {@link com.sapient.service.cli.BasicCliService#parse(java.lang.String[])}
	 * .
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testParse() throws Exception {
		BasicCliService service = new BasicCliService();
		service.setResourceFile(flag);
		service.afterPropertiesSet();
		Map<String, String> result = service.parse(new String[] { "--report", "DailyPositionReport", "--firm",
				"CreditSwiss", "--portfolio", "CSLAB1", "--periodStartDate",
				"20130808000000", "--periodEndDate", "20130808235959" });
		
		System.out.println(result);
	}

	/**
	 * Test method for
	 * {@link com.sapient.service.cli.BasicCliService#afterPropertiesSet()}.
	 */
	@Test
	public final void testAfterPropertiesSet() {
		fail("Not yet implemented"); // TODO
	}

}
