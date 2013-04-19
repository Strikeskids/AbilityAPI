package sk.general;

public class StringUtil {
	public static String toEnumName(String s) {
		StringBuilder b = new StringBuilder(s);
		while (b.length() > 0 && !Character.isLetter(b.charAt(0)))
			b.deleteCharAt(0);
		while (b.length() > 0 && !Character.isLetter(b.charAt(b.length() - 1)))
			b.deleteCharAt(b.length() - 1);
		for (int i = 0; i < b.length(); i++) {
			if (Character.isLowerCase(b.charAt(i)))
				b.setCharAt(i, Character.toUpperCase(b.charAt(i)));
			else if (Character.isWhitespace(b.charAt(i)))
				b.setCharAt(i, '_');
		}
		return b.toString();
	}
}
