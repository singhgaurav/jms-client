/**
 * 
 */
package com.sapient.main;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sapient.service.cli.CliService;
import com.sapient.service.jms.JMSClientService;

/**
 * @author gsing5
 * 
 */
public class Main {

	public static void main(String[] args) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		
		CliService cliService = (CliService) appContext
				.getBean("basicCliService");
		
		JMSClientService jmsService = (JMSClientService)appContext
				.getBean("basicJmsService");
		
		try {
			
			// sample data for running
			
			String[] my_args = new String[] { "--report", "DailyPositionReport", "--firm",
					"CreditSwiss", "--portfolio", "CSLAB1", "--periodStartDate",
					"20130808000000", "--periodEndDate", "20130808235959" };
			
			Map<String, String> inputMap = cliService.parse(my_args);
			
			jmsService.sendToQueue(inputMap);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
