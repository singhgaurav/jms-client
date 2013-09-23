/**
 * 
 */
package com.sapient.service.jms;

import java.util.Map;

/**
 * @author gsing5
 *
 */
public interface JMSClientService {
	public void sendToQueue(String string);
	public void sendToQueue(Map<String, String> map);
}
