package com.gx.code.demo.algorithm.leetcode;

import java.util.Stack;

class PostfixCalcTestor {
    private static PostfixCalcTestor test = new PostfixCalcTestor();

    public static void main(String args[]) throws Exception {
        String exp = "7*(2/3+8)-6";
        test.convertPostfixExp(exp.split(""));
        // exp = "2*6*5*3+1";
        // test.convertPostfixExp(exp.split(""));
    }

    public void convertPostfixExp(String[] expArr) throws Exception {
        Stack<String> digitOptStack = new Stack<String>();
        Stack<String> optStatck = new Stack<String>();

        for (String str : expArr) {
            // 此处是数字
            char c = str.toCharArray()[0];
            if (c >= '0' && c <= '9') {
                digitOptStack.push(String.valueOf(c));
                continue;
            }
            // 此处一定是操作符
            if (optStatck.isEmpty()) {
                // 栈空，特殊处理
                optStatck.push(str);
            } else if (optStatck.peek().equals("(") && str.equals(")")) {
                optStatck.pop();
            } else {
                while (!optStatck.isEmpty() && compareOperator(optStatck.peek(), str) > 0) {
                    digitOptStack.push(optStatck.pop());
                }
                if (!optStatck.isEmpty() && optStatck.peek().equals("(") && str.equals(")")) {
                    optStatck.pop();
                } else {
                    optStatck.push(str);
                }
            }
        }
        while (!optStatck.isEmpty()) {
            digitOptStack.push(optStatck.pop());
        }

        String result = "";
        Stack<String> temp = (Stack<String>) digitOptStack.clone();
        while (!temp.isEmpty()) {
            result = result + temp.pop();
        }
        System.out.println("rtn1: " + result);

        // 计算
        String val = count(digitOptStack);
        System.out.println("rtn2: " + val);
    }

    public int compareOperator(String inStack, String cur) {
        if (cur.equals(")")) {
            return 1;
        }
        if (cur.equals("(")) {
            return -1;
        }

        if (inStack.equals("(") || inStack.equals(")")) {
            return -1;
        }

        if (inStack.equals("+") || inStack.equals("-")) {
            if (cur.equals("+") || cur.equals("-")) {
                return 0;
            } else {
                return -1;
            }
        }

        if (inStack.equals("*") || inStack.equals("/")) {
            if (cur.equals("*") || cur.equals("/")) {
                return 0;
            } else {
                return 1;
            }
        }

        return 0;
    }

    public static String count(Stack<String> digitOptStack) throws Exception {
        char c = digitOptStack.pop().toCharArray()[0];
        if (c >= '0' && c <= '9') {
            return String.valueOf(c);
        }

        String right = count(digitOptStack);
        String left = count(digitOptStack);
        return calc(right, left, c);
    }

    public static String calc(String right, String left, char opt) throws Exception {
        if(opt == '+') return String.valueOf(Integer.parseInt(left) + Integer.parseInt(right));
        if(opt == '-') return String.valueOf(Integer.parseInt(left) - Integer.parseInt(right));
        if(opt == '*') return String.valueOf(Integer.parseInt(left) * Integer.parseInt(right));
        if(opt == '/') {
            if(Integer.parseInt(right) == 0)
                throw new Exception("分母不能为零");
            return String.valueOf(Integer.parseInt(left) / Integer.parseInt(right));
        }
        return "0";
    }
}