import java.util.*;

public class ParseLispExpression {

    public int evaluate(String expression) {
        return eval(expression, new HashMap<>());
    }

    /**
     * Recursive evaluation function
     */
    private int eval(String expr, Map<String, Integer> scope) {

        // Case 1: If it's not an expression
        if (expr.charAt(0) != '(') {
            // If number → return directly
            if (Character.isDigit(expr.charAt(0)) || expr.charAt(0) == '-') {
                return Integer.parseInt(expr);
            }
            // Otherwise it's a variable
            return scope.get(expr);
        }

        // Remove outer parentheses
        String inner = expr.substring(1, expr.length() - 1);

        // Split tokens
        List<String> tokens = parse(inner);

        String op = tokens.get(0);

        // Case 2: add
        if (op.equals("add")) {
            return eval(tokens.get(1), new HashMap<>(scope)) +
                   eval(tokens.get(2), new HashMap<>(scope));
        }

        // Case 3: mult
        if (op.equals("mult")) {
            return eval(tokens.get(1), new HashMap<>(scope)) *
                   eval(tokens.get(2), new HashMap<>(scope));
        }

        // Case 4: let
        Map<String, Integer> newScope = new HashMap<>(scope);

        for (int i = 1; i < tokens.size() - 1; i += 2) {
            String var = tokens.get(i);
            int val = eval(tokens.get(i + 1), newScope);
            newScope.put(var, val);
        }

        // Last token is expression result
        return eval(tokens.get(tokens.size() - 1), newScope);
    }


    private List<String> parse(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }

            // If it's an expression
            if (s.charAt(i) == '(') {
                int start = i;
                int balance = 0;

                while (i < s.length()) {
                    if (s.charAt(i) == '(') balance++;
                    if (s.charAt(i) == ')') balance--;
                    i++;

                    if (balance == 0) break;
                }

                result.add(s.substring(start, i));
            } else {
                int start = i;

                while (i < s.length() && s.charAt(i) != ' ') {
                    i++;
                }

                result.add(s.substring(start, i));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ParseLispExpression obj = new ParseLispExpression();

        String expr = "(mult 3 (add 2 3))";

        System.out.println(obj.evaluate(expr)); 
    }
}
