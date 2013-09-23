/**
 * 
 */
package com.sapient.service.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sapient.service.jms.JMSClientService;

/**
 * @author gsing5
 * 
 *         Cli Services initialize itself with a json flag file. Service method
 *         create a key-value pair of the input (String array) passed.
 * 
 */

@Service("basicCliService")
@Scope(value = "singleton")
public class BasicCliService implements InitializingBean, CliService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BasicCliService.class);

	@Autowired
	private FlagResource resourceFile;

	private final Options options = new Options();

	
	@Override
	public Map<String, String> parse(String[] args) throws Exception {
		logger.info("Starting input argument parse.");
		CommandLineParser parser = new BasicParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args);
			logger.info("Parsing successful");
			return createKeyValuePair(cmd);
		} catch (ParseException e) {
			logger.error("Parsing exception" + e.getMessage());
			throw new Exception("Could not parse", e);
		}
	}

	/**
	 * Return Key-Value pair for input array arguments.
	 * @param cmd
	 * @return
	 */
	private Map<String, String> createKeyValuePair(CommandLine cmd) {
		Map<String, String> map = new HashMap<String, String>();
		for (Object o_option : options.getOptions()) {
			String optionName = ((Option) o_option).getOpt();
			if (cmd.hasOption(optionName)) {
				map.put(optionName, cmd.getOptionValue(optionName));
			}
		}
		return map;
	}

	/**
	 * 
	 * Spring executed post construct method will load the flag file and
	 * generate CLI Options
	 * 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		if (resourceFile == null)
			throw new Exception("Resource bean not injected");
		if (resourceFile.getFile() == null)
			throw new Exception("Resource file not set propertly");

		Resource flagFile = new ClassPathResource(resourceFile.getFile());

		logger.info("Using flag file : " + flagFile.getURL());
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				flagFile.getInputStream()))) {
			Gson gson = new Gson();
			Flag[] flagArray = gson.fromJson(br, Flag[].class);

			if (flagArray != null) {
				List<Flag> flagList = Arrays.asList(flagArray);
				createCliOptions(flagList);
			}
		} catch (IOException e) {
			throw new Exception("Error in parsing flag file.", e);
		}
	}

	private void createCliOptions(List<Flag> flagList) {
		for (Flag flag : flagList) {
			this.options.addOption(createCliOption(flag));
		}
	}

	private Option createCliOption(Flag flag) {

		if (flag.getName() != null) {
			Option option = new Option(flag.getName(), true, flag.getDescription());
			if (flag.getHasArg() != null
					&& "Y".compareToIgnoreCase(flag.getHasArg()) == 0) {
				option.setArgs(1);
			}
			return option;
		}
		throw new RuntimeException("No name defined for flag");
	}

	public FlagResource getResourceFile() {
		return resourceFile;
	}

	public void setResourceFile(FlagResource resourceFile) {
		this.resourceFile = resourceFile;

	}

	private static class Flag {
		private String name;
		private String type;
		private String description;
		private String hasArg;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getHasArg() {
			return hasArg;
		}

		public void setHasArg(String hasArg) {
			this.hasArg = hasArg;
		}

	}
}
