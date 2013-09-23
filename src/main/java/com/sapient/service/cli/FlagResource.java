/**
 * 
 */
package com.sapient.service.cli;

/**
 * @author gsing5
 * 
 * Wrapper bean for flag json file. It can then be autowired.
 *
 */
public class FlagResource {
	
	private String file;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	

}
