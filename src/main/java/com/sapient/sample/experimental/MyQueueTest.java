/**
 * 
 */
package com.sapient.sample.experimental;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author gsing5
 *
 */
@RunWith(value=Parameterized.class)
public class MyQueueTest {
	
	private MyQueue<Integer> queueObject;
	private Integer capacity;
	
	
	@Parameters
	public static Collection<Integer[]> getTestParameters() {
		return Arrays.asList(new Integer[][] {
				{20},
				{25},
				{26},
				{21},
				{30}
		});
		
	}
	
	public MyQueueTest(Integer capacity) {
		this.capacity = capacity;
		queueObject = new MyQueue<Integer>(capacity);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public final void testFullAndSizeAfterPuts() throws InterruptedException {
		for(int i = 0; i < capacity; i++) {
			queueObject.put(new Integer(i));
		}
		Assert.assertTrue("Is Full : ", queueObject.isFull());
		Assert.assertEquals("Size of Queue", queueObject.size(), capacity.intValue() );
	}
	
	@Test
	public final void testEmptyWhenConstructed() {
		Assert.assertTrue("Should be empty ", queueObject.isEmpty());
		Assert.assertFalse("Should not be Full", queueObject.isFull());
	}

	@Test
	public final void testGet() throws InterruptedException {
		queueObject.put(5);
		queueObject.put(15);
		queueObject.get();
	}

}
