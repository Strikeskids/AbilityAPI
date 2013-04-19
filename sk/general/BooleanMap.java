package sk.general;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BooleanMap<V> extends HashMap<Boolean, V> {

	private static final long serialVersionUID = -8747744981672794843L;

	public BooleanMap() {
		super(2, 1f);
	}
	
	@SuppressWarnings("unchecked")
	public void copyAll(Map<? extends Boolean, ? extends V> m) {
		for (Entry<? extends Boolean, ? extends V> e : m.entrySet()) {
			if (e.getValue() instanceof Object[]) {
				Object[] arr = (Object[]) e.getValue();
				put(e.getKey(), (V) Arrays.copyOf(arr, arr.length));
			}
		}
	}

	// private Object[] arr = new Object[2];
	//
	// @SuppressWarnings("unchecked")
	// private V get(int loc) {
	// return (V) arr[loc];
	// }
	//
	// @Override
	// public int size() {
	// return (containsKey(true) ? 1 : 0) + (containsKey(false) ? 1 : 0);
	// }
	//
	// @Override
	// public boolean isEmpty() {
	// return size() == 0;
	// }
	//
	// @Override
	// public boolean containsKey(Object key) {
	// return get(key) != null;
	// }
	//
	// @Override
	// public boolean containsValue(Object value) {
	// for (Object o : arr) {
	// if (o == null ? value == null : o.equals(value))
	// return true;
	// }
	// return false;
	// }
	//
	// @Override
	// public V get(Object key) {
	// if (Boolean.TRUE.equals(key))
	// return get(1);
	// else if (Boolean.FALSE.equals(key))
	// return get(0);
	// return null;
	// }
	//
	// @Override
	// public V put(Boolean key, V value) {
	// if (key == null)
	// throw new NullPointerException();
	// int index = (key ? 1 : 0);
	// V ret = get(index);
	// arr[index] = value;
	// return ret;
	// }
	//
	// @Override
	// public V remove(Object key) {
	// int index = -1;
	// if (Boolean.TRUE.equals(key))
	// index = 1;
	// else if (Boolean.FALSE.equals(key))
	// index = 0;
	// if (index >= 0) {
	// V ret = get(index);
	// arr[index] = null;
	// return ret;
	// }
	// return null;
	// }
	//
	// @Override
	// public void putAll(Map<? extends Boolean, ? extends V> m) {
	// for (Entry<? extends Boolean, ? extends V> e : m.entrySet()) {
	// put(e.getKey(), e.getValue());
	// }
	// }
	//

	//
	// @Override
	// public void clear() {
	// Arrays.fill(arr, null);
	// }
	//
	// @Override
	// public Set<Boolean> keySet() {
	// Set<Boolean> ret = new HashSet<>();
	// for (int i = 0; i < 2; i++) {
	// if (get(i) != null)
	// ret.add(i != 0);
	// }
	// return ret;
	// }
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// public Collection<V> values() {
	// Collection<V> ret = new ArrayList<V>(2);
	// for (Object o : arr) {
	// if (o == null)
	// continue;
	// ret.add((V) o);
	// }
	// return ret;
	// }
	//
	// @Override
	// public Set<Entry<Boolean, V>> entrySet() {
	// Set<Entry<Boolean, V>> ret = new HashSet<>();
	// for (int i = 0; i < 2; i++) {
	// if (get(i) != null)
	// ret.add(new BMapEntry(i));
	// }
	// return ret;
	// }
	//
	// private class BMapEntry implements Entry<Boolean, V> {
	//
	// private int key;
	//
	// private BMapEntry(int ind) {
	// this.key = ind;
	// }
	//
	// @Override
	// public Boolean getKey() {
	// return key != 0;
	// }
	//
	// @Override
	// public V getValue() {
	// return get(key);
	// }
	//
	// @Override
	// public V setValue(V value) {
	// V ret = get(key);
	// arr[key] = value;
	// return ret;
	// }
	//
	// }

}
