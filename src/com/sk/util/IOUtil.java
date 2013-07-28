package com.sk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IOUtil {

	public static String read(String url) {
		try {
			return read(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String read(URL url) {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuilder ret = new StringBuilder();
			String line;
			while ((line = r.readLine()) != null)
				ret.append(line);
			return ret.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
