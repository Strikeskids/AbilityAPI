package sk.general;

public class ArrayUtil {
	/**
	 * Shuffles the array into a new array (returns a copy)
	 * 
	 * @param arr
	 *            The array to shuffle
	 * @return the copied and shuffled array
	 * @see ArrayUtil#shuffleInPlace(Object[])
	 */
	public static <T> T[] shuffle(final T[] arr) {
		if (arr.length <= 1) {
			return arr;
		}
		@SuppressWarnings("unchecked")
		final T[] ret = ((T[]) new Object[arr.length]);
		ret[0] = arr[0];
		for (int i = arr.length - 1; i >= 1; i--) {
			final int j = (int) (Math.random() * (i + 1));
			ret[i] = ret[j];
			ret[j] = arr[i];
		}
		return ret;
	}

	/**
	 * Shuffles the array in place (loses the original ordering)
	 * 
	 * @param arr
	 *            the array to shuffle in place
	 * @see ArrayUtil#shuffle(Object[])
	 */
	public static <T> void shuffleInPlace(final T[] arr) {
		for (int i = arr.length - 1; i >= 1; i--) {
			final int j = (int) (Math.random() * (i + 1));
			final T temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	public static int indexOf(final int[] arr, final int search) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == search) {
				return i;
			}
		}
		return -1;
	}

	public static boolean contains(final int[] arr, final int search) {
		return indexOf(arr, search) >= 0;
	}

	public static int indexOf(final Object[] arr, final Object search) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == search || arr[i] != null && search != null && arr[i].equals(search)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean contains(final Object[] arr, final Object search) {
		return indexOf(arr, search) >= 0;
	}

	public static boolean containsSubstring(final String[] arr, String search) {
		if (search == null)
			return false;
		search = search.toLowerCase();
		for (String s : arr) {
			if (s == null) continue;
			s = s.toLowerCase();
			if (s.contains(search) || search.contains(s))
				return true;
		}
		return false;
	}

	public static <T> Getter<T>[] createGetters(final T[] objects) {
		@SuppressWarnings("unchecked")
		Getter<T>[] ret = new Getter[objects.length];

		for (int i = 0; i < objects.length; i++) {
			final T curObj = objects[i];
			ret[i] = new Getter<T>() {
				@Override
				public T get() {
					return curObj;
				}
			};
		}
		return ret;
	}
}
