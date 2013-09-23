/**
 * 
 */
package com.sapient.service.cli;

import java.util.Map;

/**
 * @author gsing5
 * Cli Service interface. Implementation of the interface should get the option configuration 
 * from json flag file.
 */
public interface CliService {
	Map<String, String> parse(String[] args) throws Exception;
}
