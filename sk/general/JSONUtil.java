package sk.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

public class JSONUtil {

	@SuppressWarnings("unchecked")
	public static Iterator<Entry<String, Object>> iterateObject(Object jobj) {
		if (jobj instanceof Map) {
			return ((Map<String, Object>) jobj).entrySet().iterator();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static ListIterator<Object> iterateArray(Object jarr) {
		if (jarr instanceof List) {
			return ((List<Object>) jarr).listIterator();
		} else {
			return null;
		}
	}

	// Getters
	public static Boolean getBoolean(Object boo) {
		if (boo instanceof Boolean) {
			return (Boolean) boo;
		} else {
			String str = getString(boo);
			try {
				if (str.toLowerCase().equals("true"))
					return true;
				else if (str.toLowerCase().equals("false"))
					return false;
			} catch (Exception ignored) {
			}
		}
		return null;
	}

	public static Object getFromObject(Object jobj, String key) {
		if (jobj instanceof Map) {
			return ((Map<?, ?>) jobj).get(key);
		} else {
			return null;
		}
	}

	public static Object getFromArray(Object jarr, int index) {
		if (jarr instanceof List) {
			return ((List<?>) jarr).get(index);
		} else {
			return null;
		}
	}

	public static Double getDouble(Object in) {
		if (in instanceof Double)
			return (Double) in;
		else {
			String str = getString(in);
			try {
				return Double.parseDouble(str.replaceAll(",", ""));
			} catch (Exception ignored) {
			}
		}
		return null;
	}

	public static Integer getInteger(Object in) {
		Double d = getDouble(in);
		if (d == null)
			return null;
		return (int) (double) d;
	}

	public static String getString(Object str) {
		if (str instanceof String)
			return (String) str;
		else
			return null;
	}

	// Parser
	public static Object parse(String json) {
		ArrayList<Character> chars = new ArrayList<Character>(json.length());
		for (char c : json.toCharArray()) {
			chars.add(c);
		}
		return parse(chars.listIterator());
	}

	private static Object parse(ListIterator<Character> chars) {
		while (chars.hasNext()) {
			char cur = chars.next();
			if (cur == '{')
				return parseObject(chars);
			else if (cur == '[')
				return parseArray(chars);
			else if (cur == '"')
				return parseString(chars);
			else if (cur == '-' || Character.isDigit(cur)) {
				chars.previous();
				return parseNumber(chars);
			} else if (cur == 't' || cur == 'f') {
				chars.previous();
				return parseBoolean(chars);
			} else if (cur == 'n') {
				chars.previous();
				return parseNull(chars);
			} else if (Character.isWhitespace(cur))
				continue;
			else
				return null;
		}
		return null;
	}

	private static Boolean parseBoolean(ListIterator<Character> chars) {
		String t = "true", f = "fals";
		StringBuilder b = new StringBuilder();
		while (chars.hasNext() && b.length() < 4) {
			b.append(Character.toLowerCase(chars.next()));
		}
		if (b.toString().equals(t))
			return true;
		else if (b.toString().equals(f)) {
			if (chars.hasNext()) {
				chars.next();
				return false;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	private static Null parseNull(ListIterator<Character> chars) {
		String n = "null";
		StringBuilder b = new StringBuilder();
		while (chars.hasNext() && b.length() < 4) {
			b.append(Character.toLowerCase(chars.next()));
		}
		if (b.toString().equals(n)) {
			return new Null();
		} else {
			return null;
		}
	}

	private static HashMap<String, Object> parseObject(ListIterator<Character> chars) {
		boolean sep = true;
		boolean col = false;
		HashMap<String, Object> ret = new HashMap<>();
		String curKey = null;
		while (chars.hasNext()) {
			if (sep) {
				char cur = chars.next();
				if (Character.isWhitespace(cur))
					continue;
				else if (cur == '"') {
					String s = parseString(chars);
					if (s == null)
						return null;
					curKey = s;
					sep = false;
					continue;
				} else {
					return null;
				}
			} else if (col) {
				Object obj = parse(chars);
				if (obj == null)
					return null;
				ret.put(curKey, obj);
				col = false;
				curKey = null;
				continue;
			}
			char cur = chars.next();
			if (Character.isWhitespace(cur))
				continue;
			else if (cur == ',')
				sep = true;
			else if (cur == ':')
				col = true;
			else if (cur == '}')
				return ret;
			else
				return null;
		}
		return null;
	}

	private static ArrayList<Object> parseArray(ListIterator<Character> chars) {
		ArrayList<Object> ret = new ArrayList<>();
		boolean sep = true;
		while (chars.hasNext()) {
			if (sep) {
				Object obj = parse(chars);
				if (obj == null)
					return null;
				ret.add(obj);
				sep = false;
				continue;
			}
			char cur = chars.next();
			if (cur == ',')
				sep = true;
			else if (cur == ']')
				return ret;
			else if (!Character.isWhitespace(cur))
				return null;
		}
		return null;
	}

	private static Double parseNumber(ListIterator<Character> chars) {
		StringBuilder curString = new StringBuilder();
		Double ret = null;
		while (chars.hasNext()) {
			char cur = chars.next();
			curString.append(cur);
			try {
				ret = Double.valueOf(curString.toString());
			} catch (NumberFormatException ex) {
				if (ret != null) {
					chars.previous();
					return ret;
				} else {
					continue;
				}
			}
		}
		return ret;
	}

	private static String controls = "\"\"\\\\//b\bf\fn\nr\rt\t";

	private static String parseString(ListIterator<Character> chars) {
		StringBuilder ret = new StringBuilder();
		boolean isControlled = false;
		int hexValue = 0;
		int hexChar = -1;
		while (chars.hasNext()) {
			char cur = chars.next();
			if (hexChar >= 0) {
				try {
					hexValue <<= 4;
					hexValue |= Integer.parseInt(cur + "", 16);
				} catch (NumberFormatException ex) {
					return null;
				}
				if (++hexChar >= 4) {
					ret.append((char) hexValue);
					hexChar = -1;
					hexValue = 0;
				}
			} else if (isControlled) {
				isControlled = false;
				int ind;
				if (cur == 'u') {
					hexChar = 0;
				} else if ((ind = controls.indexOf(cur)) >= 0) {
					ret.append(controls.charAt(ind + 1));
				} else {
					return null;
				}
			} else {
				if (cur == '"')
					return ret.toString();
				else if (cur == '\\')
					isControlled = true;
				else
					ret.append(cur);
			}
		}
		return null;
	}

	private static class Null {

	}
}
