package com.sk.methods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.powerbot.script.methods.Keyboard;
import org.powerbot.script.methods.MethodContext;

public class SkKeyboard extends Keyboard {

	public SkKeyboard(MethodContext ctx) {
		super(ctx);
	}

	public boolean release(String key) {
		return key(key, 2);
	}

	public boolean press(String key) {
		return key(key, 1);
	}

	public boolean key(String key, int type) {
		if (key == null)
			return false;
		String act;
		switch (type) {
		case 0:
			if (!key(key, 1))
				return false;
			sleep(100, 150);
			return key(key, 2);
		case 1:
			act = "down";
			break;
		case 2:
			act = "up";
			break;
		default:
			throw new IllegalArgumentException("Type of interaction must be 0 (type) 1 (press) or 2 (release)");
		}
		return send("{VK_" + convert(key) + " " + act + "}");
	}

	public String convert(String key) {
		key = key.toUpperCase();
		if (valid.contains(key))
			return key;
		if (keyNames.containsKey(key))
			return keyNames.get(key);
		throw new IllegalArgumentException();
	}

	private static final Set<String> valid = new HashSet<>();
	private static final Map<String, String> keyNames = new HashMap<>();

	static {
		for (char c = 'A'; c <= 'Z'; ++c)
			valid.add(Character.toString(c));
		for (char c = '0'; c <= '9'; ++c)
			valid.add(Character.toString(c));
		for (int i = 1; i <= 24; ++i)
			valid.add("F" + i);
		valid.addAll(Arrays.asList("LEFT", "RIGHT", "UP", "DOWN"));
		keyNames.put("-", "MINUS");
		keyNames.put("=", "EQUALS");
		keyNames.put("\n", "ENTER");
		keyNames.put(".", "PERIOD");
		keyNames.put(",", "COMMA");
		keyNames.put(";", "SEMICOLON");
		keyNames.put("'", "QUOTE");
		keyNames.put("[", "OPEN_BRACKET");
		keyNames.put("]", "CLOSE_BRACKET");
		keyNames.put("\\", "BACKSLASH");
		keyNames.put("`", "BACK_QUOTE");
		keyNames.put("/", "SLASH");
	}
}
