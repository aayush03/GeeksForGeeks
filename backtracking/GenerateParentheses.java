package backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        new GenerateParentheses().generateParenthesis(3);
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0)
            return res;
        dfs(0, 0, n, new StringBuilder(), res);

        System.out.println(res);
        res = new ArrayList<>();
        dfs("", 0, 0, n, res);
        System.out.println(res);
        return res;
    }

    private void dfs(int left, int right, int n, StringBuilder sb, List<String> res) {
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }

        if (left < right)
            return;

        int len = sb.length();
        if (left < n) {
            sb.append("(");
            dfs(left + 1, right, n, sb, res);
            sb.setLength(len);
        }

        if (right < n) {
            sb.append(")");
            dfs(left, right + 1, n, sb, res);
            sb.setLength(len);
        }
    }

    public void dfs(String curstr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curstr);
            return;
        }
        //unsatisfied condition, cut off
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs(curstr + "(", left + 1, right, n, res);
        }

        if (right < n) {
            dfs(curstr + ")", left, right + 1, n, res);
        }

    }
}
