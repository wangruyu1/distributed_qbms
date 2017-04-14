package cn.qtech.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author wangruyu
 * @since 2017/4/4-21:48
 */
public class RandomNumberUtil {
    private static Random random = new Random();

    public static int[] getDifferentNumbers(int need, int max) {
        Map<Integer, Boolean> flagMap = new HashMap<>();
        int[] result = new int[need];
        int count = 0;
        while (count < need) {
            int num = random.nextInt(max);
            if (flagMap.get(num) != null) {
                continue;
            }
            flagMap.put(num, true);
            result[count++] = num;
        }
        return result;
    }
}
