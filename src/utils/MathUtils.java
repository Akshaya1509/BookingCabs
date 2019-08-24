package utils;

import java.util.List;

public class MathUtils {
	public static double getAvg(List<Integer> list) {
		double sum = 0;
		if (list.isEmpty()) {
			return 0;
		}
		for(Integer i : list) {
			sum += i;
		}
		return sum/list.size();
	}

}
