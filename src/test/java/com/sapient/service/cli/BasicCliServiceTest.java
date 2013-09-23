/**
 * 
 */
package com.sapient.service.cli;

import java.util.Map;

import org.junit.Assert;
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
		flag.setFile("flags.json");
		BasicCliService service = new BasicCliService();
		service.setResourceFile(flag);
		service.afterPropertiesSet();
		Map<String, String> result = service.parse(new String[] { "--report", "DailyPositionReport", "--firm",
				"CreditSwiss", "--portfolio", "CSLAB1", "--periodStartDate",
				"20130808000000", "--periodEndDate", "20130808235959" });
		
		Assert.assertEquals(5, result.keySet().size());
		Assert.assertTrue(result.containsKey("report"));
		Assert.assertTrue(result.containsKey("firm"));
		Assert.assertTrue(result.containsKey("portfolio"));
		Assert.assertTrue(result.containsKey("periodStartDate"));
		Assert.assertTrue(result.containsKey("periodEndDate"));
		Assert.assertTrue(result.containsValue("DailyPositionReport"));
		Assert.assertTrue(result.containsValue("CreditSwiss"));
		Assert.assertTrue(result.containsValue("CSLAB1"));
		Assert.assertTrue(result.containsValue("20130808000000"));
		Assert.assertTrue(result.containsValue("20130808235959"));
		
		
	}

	/**
	 * Test method for
	 * {@link com.sapient.service.cli.BasicCliService#afterPropertiesSet()}.
	 * @throws Exception 
	 */
	@Test(expected=Exception.class )
	public final void testParsingFlagConfigurationFile() throws Exception {
		flag.setFile("flags_error.json");
		BasicCliService service = new BasicCliService();
		service.setResourceFile(flag);
		service.afterPropertiesSet();
	}

}
