package amazon.online_assessment;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aayush Srivastava
 */
public class UtilizationChecks {

    /**
     * util = instances
     *
     * instances < 25 -----------> d = d / 2;
     *
     * 25 <= instances <= 60 --------> No Action
     *
     * instances > 60  ----> if (d * 2) > 2 *  10^ 28 -> No Action else d = d * 2
     *
     * @param instances
     * @param averageUtil
     * @return
     */
    public static int finalInstances(int instances, List<Integer> averageUtil) {
        if (instances <= 0 || averageUtil == null || averageUtil.isEmpty())
            return 0;

        int n = averageUtil.size();
        int i = 0;
        long threshold = 200000000;

        while (i < n) {
            if (averageUtil.get(i) < 25) {
                if (instances > 1) {
                    instances = (int) Math.ceil((double) instances / 2);
                    i += 10;
                }
            }
            else if (averageUtil.get(i) > 60) {
                long k = instances * 2;
                if (k < threshold) {
                    instances = (int) k;
                    i += 10;
                }
            }
            i++;
        }

        return instances;
    }

    public static void main(String[] args) {
        System.out.println(finalInstances(2, Arrays.asList(25, 23, 1, 2, 3, 4, 5,6,7,8,9,10,76,80)));
        System.out.println(finalInstances(1, Arrays.asList(5, 10, 80)));
        System.out.println(finalInstances(5, Arrays.asList(30, 5, 4, 8, 19, 89)));
    }
}
