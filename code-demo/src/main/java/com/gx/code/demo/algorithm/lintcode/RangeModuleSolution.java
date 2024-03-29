package com.gx.code.demo.algorithm.lintcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class RangeModuleSolution {
    public static void main(String args[]) {
        RangeModuleSolution r = new RangeModuleSolution();

        addRange(5,8);
        // System.out.println(queryRange(3,4));
        removeRange(5,6);
        removeRange(3,6);
        addRange(1,3);
        // System.out.println(queryRange(2,3));
        addRange(4,8);
        // System.out.println(queryRange(2,3));
        removeRange(4,9);
    }


    static TreeSet<Interval> ranges = new TreeSet<>();

    public RangeModuleSolution() {
        //do some initialization if necessary
    }

    public static void addRange(int left, int right) {
        Iterator<Interval> itr = ranges.tailSet(new Interval(0, left - 1)).iterator();
        while (itr.hasNext()) {
            Interval iv = itr.next();
            if (right < iv.left) break;
            left = Math.min(left, iv.left);
            right = Math.max(right, iv.right);
            itr.remove();
        }
        ranges.add(new Interval(left, right));
    }

    public static boolean queryRange(int left, int right) {
        Interval iv = ranges.higher(new Interval(0, left));
        return (iv != null && iv.left <= left && right <= iv.right);
    }

    public static void removeRange(int left, int right) {
        Iterator<Interval> itr = ranges.tailSet(new Interval(0, left)).iterator();
        ArrayList<Interval> todo = new ArrayList();
        while (itr.hasNext()) {
            Interval iv = itr.next();
            if (right < iv.left) break;
            if (iv.left < left) todo.add(new Interval(iv.left, left));
            if (right < iv.right) todo.add(new Interval(right, iv.right));
            itr.remove();
        }
        for (Interval iv: todo) ranges.add(iv);
    }

    static class Interval implements Comparable<Interval>{
        int left;
        int right;

        public Interval(int left, int right){
            this.left = left;
            this.right = right;
        }

        public int compareTo(Interval that){
            if (this.right == that.right) return this.left - that.left;
            return this.right - that.right;
        }
    }
}


