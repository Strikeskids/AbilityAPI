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
	public static <T> T[] shuffle(T[] arr) {
		if (arr.length <= 1)
			return arr;
		@SuppressWarnings("unchecked")
		T[] ret = ((T[]) new Object[arr.length]);
		ret[0] = arr[0];
		for (int i = arr.length - 1; i >= 1; i--) {
			int j = (int) (Math.random() * (i + 1));
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
	public static <T> void shuffleInPlace(T[] arr) {
		for (int i = arr.length - 1; i >= 1; i--) {
			int j = (int) (Math.random() * (i + 1));
			T temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
}
