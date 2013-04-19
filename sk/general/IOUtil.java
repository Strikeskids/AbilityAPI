package sk.general;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

public class IOUtil {

	public static String readFromURL(URL url, String userAgent) {
		try {
			StringBuilder ret = new StringBuilder();
			URLConnection conn = url.openConnection();
			conn.addRequestProperty("User-agent", userAgent);
			conn.connect();
			BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			int cur = -1;
			while ((cur = r.read()) != -1) {
				ret.append((char) cur);
			}
			r.close();
			return ret.toString();
		} catch (Exception ex) {
			return null;
		}
	}

	public static String readFromURL(String url, String userAgent) {
		try {
			return readFromURL(new URL(url), userAgent);
		} catch (MalformedURLException ex) {
			return null;
		}
	}

	public static String readFromURL(URL url) {
		try {
			StringBuilder ret = new StringBuilder();
			BufferedReader r = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			int cur = -1;
			while ((cur = r.read()) != -1) {
				ret.append((char) cur);
			}
			r.close();
			return ret.toString();
		} catch (Exception ex) {
			return null;
		}
	}

	public static String readFromURL(String url) {
		try {
			return readFromURL(new URL(url));
		} catch (MalformedURLException ex) {
			return null;
		}
	}

	public static String postToURL(String url, Map<String, String> data) {
		try {
			return postToURL(new URL(url), data);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String postToURL(URL url, Map<String, String> data) {
		try {
			StringBuilder dataOut = new StringBuilder();
			for (String key : data.keySet()) {
				if (dataOut.length() > 0)
					dataOut.append("&");
				dataOut.append(URLEncoder.encode(key, "UTF-8"));
				dataOut.append("=");
				dataOut.append(URLEncoder.encode(data.get(key), "UTF-8"));
			}
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			OutputStreamWriter w = new OutputStreamWriter(con.getOutputStream());
			w.write(dataOut.toString());
			w.flush();
			dataOut.delete(0, dataOut.length());
			BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));
			int cur = -1;
			while ((cur = r.read()) != -1) {
				dataOut.append((char) cur);
			}
			w.close();
			r.close();
			return dataOut.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static Map<String, String> createData(String[] a, String[] b) {
		Map<String, String> ret = new HashMap<String, String>((int) (Math.min(a.length, b.length) * 0.75 + 1));
		for (int i = 0; i < Math.min(a.length, b.length); i++) {
			ret.put(a[i], b[i]);
		}
		return ret;
	}

	public static Map<String, String> createData(Object[][] input) {
		Map<String, String> ret = new HashMap<>();
		for (Object[] s : input) {
			if (s.length == 2)
				ret.put(s[0] + "", s[1] + "");
		}
		return ret;
	}

	public static Map<String, String> buildData(int uid, String uname, String script, Object[][] data) {
		Map<String, String> ret = createData(data);
		ret.put("script", script);
		ret.put("id", uid + "");
		ret.put("name", uname);
		ret.put("user", IOUtil.hashUser(uname, uid));
		return ret;
	}

	public static BufferedImage getImage(String b64) {
		try {
			return ImageIO.read(new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(b64)));
		} catch (IOException e) {
			return null;
		}
	}

	public static String hashUser(String name, int uid) {
		try {
			return bytesToHex(MessageDigest.getInstance("MD5").digest((name + "strikeskids" + uid).getBytes()));
		} catch (NoSuchAlgorithmException ignored) {
		}
		return null;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			ret.append(String.format("%02x", b & 0xFF));
		}
		return ret.toString();
	}


}
