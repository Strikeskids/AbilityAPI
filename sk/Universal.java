package sk;

import java.io.OutputStream;
import java.io.PrintStream;

public class Universal {
	public static boolean testing = false;
	
	public static void debug(String s) {
		debug(System.out, s);
	}

	public static void debug(OutputStream stream, String s) {
		if (stream == null) {
			debug(System.out, s);
			return;
		}
		if (s == null)
			s = "null";
		StackTraceElement prev = null;
		try {
			throw new NullPointerException();
		} catch (Exception ex) {
			StackTraceElement[] trace = ex.getStackTrace();
			if (trace.length < 2) {
				return;
			}
			prev = trace[1];
		}
		StringBuilder out = new StringBuilder(s.length() + 50);
		out.append(s);
		out.append(" in ");
		out.append(prev.getClassName());
		out.append(".");
		out.append(prev.getMethodName());
		out.append(" at ");
		out.append(prev.getLineNumber() < 0 ? "Unknown" : prev.getLineNumber());
		new PrintStream(stream).println(out);
	}
}
