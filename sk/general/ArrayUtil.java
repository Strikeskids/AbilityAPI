package sk.general;

public class ArrayUtil {
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

	public static <T> void shuffleInPlace(T[] arr) {
		for (int i = arr.length - 1; i >= 1; i--) {
			int j = (int) (Math.random() * (i + 1));
			T temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
}
