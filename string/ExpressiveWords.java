//package string;
//
///**
// * @author Aayush Srivastava
// */
//public class ExpressiveWords {
//
//    public static void main(String[] args) {
//        System.out.println("No of stretchy words : "+ new ExpressiveWords().expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
//        System.out.println("No of stretchy words : "+ new ExpressiveWords().expressiveWords("zzzzzyyyyy", new String[]{"zzyy","zy","zyy"}));
//    }
//
//    public int expressiveWords(String S, String[] words) {
//        String compressedS = compressWord(S);
//
//        int count = 0;
//
//        for (String s : words) {
//            if (compressedS.equals(compressWord(s)))
//                count++;
//        }
//
//        return count;
//    }
//
//    private String compressWord(String s) {
//        int n = s.length();
//        int i = 0;
//        char[] arr = s.toCharArray();
//        StringBuilder sb = new StringBuilder();
//        while (i < n - 1) {
//            if (arr[i] != arr[i + 1]) {
//                sb.append(arr[i++]);
//            } else {
//                int j = 1;
//                StringBuilder temp = new StringBuilder();
//                char c = arr[i];
//                temp.append(c);
//                i++;
//                while (i <= n - 1 && arr[i] == c) {
//                    temp.append(arr[i]);
//                    i++;
//                    j++;
//                }
//                if (j >= 3) {
//                    sb.append(arr[i - 1]);
//                    //i++;
//                } else {
//                    sb.append(temp);
//                }
//            }
//
//        }
//
//        if (i == n - 1)
//            sb.append(arr[n - 1]);
//
//        return sb.toString();
//    }
//}
