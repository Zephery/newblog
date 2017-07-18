package com.myblog.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	private static final String defaultConfigFile = "/load.properties";

	private static Properties props;

	// TODO
	static {
		init();
	}

	private Config() {
		// should not call
	}

	private static void init() {
		props = loadConfig();
	}

	private static Properties loadConfig() {
		final Properties p = new Properties();
		loadDefaultConfig(p);
		return p;
	}

	private static void loadDefaultConfig(final Properties props) {
		final InputStream input = Config.class
				.getResourceAsStream(defaultConfigFile);
		if (input == null) {
			System.err.println("default Config File not found: "
					+ defaultConfigFile);
			System.exit(1);
		}
		try {
			props.load(input);
		} catch (final IOException e) {
			e.printStackTrace();
			System.err.println("load default Config File error: "
					+ e.getMessage());
			System.exit(1);
		} finally {
			try {
				input.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(final String key, final String defaultValue) {
		if (props == null)
			init();
		final String value = props.getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return value.trim();
	}

	public static String getProperty(final String key) {
		return getProperty(key, "");
	}

	public static int getIntProperty(String key) {
		final String value = getProperty(key, "0");
		if (value.length() <= 0) {
			return 0;
		}
		return Integer.parseInt(value);
	}
	
	public static long getLongProperty(String key) {
		final String value = getProperty(key, "0");
		if (value.length() <= 0) {
			return 0;
		}
		return Long.parseLong(value);
	}
	
    public static boolean getBooleanProperty(final String key) {
        final String value = getProperty(key, "false");
        if (value.length() <= 0) {
            return false;
        }
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("on");
    }
}
