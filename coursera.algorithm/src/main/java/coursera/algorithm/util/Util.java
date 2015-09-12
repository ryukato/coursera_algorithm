package coursera.algorithm.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Util {
	public static String collected(int[] array, String delimeter){
		return Arrays.stream(array).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(delimeter));
	}
}
