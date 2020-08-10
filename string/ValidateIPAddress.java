package string;

/**
 * @author Aayush Srivastava
 */
public class ValidateIPAddress {

    public static void main(String[] args) {
        System.out.println(validateIP("192.168.0.1"));
    }

    static boolean validateIP(String ip) {
        // your code goes here
        if (ip == null)
            return false;

        int n = ip.length();

        if (n == 0)
            return false;

        int i = 0;
        int count = 0;
        StringBuilder sb = new StringBuilder();

        while (i <= n) {
            if (i == n || ip.charAt(i) == '.') {
                if (!isValidNumber(sb.toString())) {
                    return false;
                } else {
                    sb = new StringBuilder();
                    count++;
                }
            } else {
                sb.append(ip.charAt(i));
            }

            i++;
        }

        return (count == 4) && (i == n + 1);
    }

    private static boolean isValidNumber(String num) {
        System.out.println("num::"+num);
        if ("".equals(num))
            return false;
        if (num.length() > 3)
            return false;
        if (num.length() == 3) {
            return (num.charAt(0) == '1' || num.charAt(0) == '2') && (isNumber(num));
        } else if (num.length() == 2 && num.charAt(0) == '0') {
            return false;
        } else {
            return isNumber(num);
        }
    }

    private static boolean isNumber(String num) {
        try {
            Integer in = Integer.parseInt(num);
            if (in != null && in >= 0 && in <= 255)
                return true;

            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
