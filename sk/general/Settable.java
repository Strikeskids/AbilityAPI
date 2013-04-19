package sk.general;

public class Settable<T> implements Getter<T> {

	private T val;
	
	public Settable(T val) {
		set(val);
	}
	
	public T set(T v) {
		return this.val = v;
	}
	
	@Override
	public T get() {
		return val;
	}

}
