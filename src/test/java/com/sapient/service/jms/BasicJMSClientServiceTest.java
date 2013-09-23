/**
 * 
 */
package com.sapient.service.jms;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author gsing5
 *
 */
public class BasicJMSClientServiceTest {
	
	@Mock
	JmsTemplate mockTemplate;
	
	private BasicJMSClientService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new BasicJMSClientService();
		service.setTemplate(mockTemplate);
	}
	
	@Test
	public final void testTemplateDotSendisCalled() {
		service.sendToQueue("Some message");
		verify(mockTemplate, times(1)).send(any(MessageCreator.class));
		
		service.sendToQueue(new HashMap<String, String>());
		verify(mockTemplate, times(2)).send(any(MessageCreator.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testNullArugmentPassingString() {
		service.sendToQueue((String)null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testNullArugmentPassingMap() {
		service.sendToQueue((Map)null);
	}

	
	@Test
	public final void testSendToQueueMapOfStringString() {
		
	}

}
