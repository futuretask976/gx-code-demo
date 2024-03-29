package com.gx.code.demo.algorithm.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PostfixSolution {
    public static void main(String args[]) {
        System.out.println(postfix("2-5*3"));
    }

    static Map<String, Integer> opePri = new HashMap<String, Integer>(){{
        put("+", 0);
        put("-", 0);
        put("*", 1);
        put("/", 1);
    }};

    public static int postfix(String exp) {
        char[] expArr = exp.toCharArray();
        Stack<String> opeStack = new Stack<>();
        Stack<String> expStack = new Stack<>();

        for (int i = 0; i < expArr.length; i++) {
            String ele = String.valueOf(expArr[i]);
            if (!isOpe(ele)) {
                expStack.push(ele);
            } else {
                if (opeStack.isEmpty()) {
                    opeStack.push(ele);
                } else {
                    while (opeStack.size() != 0) {
                        String topOpe = opeStack.peek();
                        int compareRtn = compareOpe(ele, topOpe);
                        if (compareRtn >= 0) {
                            opeStack.push(ele);
                            break;
                        } else {
                            expStack.push(topOpe);
                            opeStack.pop();
                        }
                    }
                    if (opeStack.isEmpty()) {
                        expStack.push(ele);
                    }
                }
            }
        }

        while (!opeStack.isEmpty()) {
            expStack.push(opeStack.pop());
        }

        System.out.println(opeStack);
        System.out.println(expStack);

        return 0;
    }

    private static boolean isOpe(String ope) {
        if (opePri.containsKey(ope)) {
            return true;
        } else {
            return false;
        }
    }

    private static int compareOpe(String ope1, String ope2) {
        int ope1Pri = opePri.get(ope1);
        int ope2Pri = opePri.get(ope2);
        if (ope1Pri > ope2Pri) {
            return 1;
        } else if (ope1Pri == ope2Pri) {
            return 0;
        } else {
            return -1;
        }
    }
}
