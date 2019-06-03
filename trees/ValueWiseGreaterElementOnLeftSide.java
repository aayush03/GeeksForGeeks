package trees;

import java.util.Set;
import java.util.TreeSet;

/**
 * check code
 */
public class ValueWiseGreaterElementOnLeftSide {

    public static void main(String[] args) {

        int[] arr = {10,5,19,20,15,18};

        greaterElementOnLeftSide(arr);
    }

    private static void greaterElementOnLeftSide(int[] arr){
        Set<Integer> set = new TreeSet<>();
        for (int i : arr){
            Integer n = ((TreeSet<Integer>) set).lower(i)==null?-1:((TreeSet<Integer>) set).higher(i);
            System.out.println(n);
            set.add(i);
        }
    }

}
