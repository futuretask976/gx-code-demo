package com.gx.code.demo.algorithm.lintcode;

import lombok.Data;

import java.util.*;

public class TopKFrequentWordsSolution {
    public static void main(String args[]) {
        String[] words = new String[]{"yes","lint","code","yes","code","baby","you","baby","chrome","safari","lint","code","body","lint","code"};
        int k = 3;
        topKFrequentWords(words, k);
    }

    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    public static String[] topKFrequentWords(String[] words, int k) {
        // write your code here
        Map<String, Integer> treeMap = new HashMap<String, Integer>();

        for (String word : words) {
            if (treeMap.containsKey(word)) {
                int count = treeMap.get(word);
                treeMap.put(word, ++count);
            } else {
                treeMap.put(word, 1);
            }
        }

        TreeSet<Pair> treeSet = new TreeSet<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }
                if (o1.count > o2.count) {
                    return -1;
                } else if (o1.count == o2.count) {
                    return String.CASE_INSENSITIVE_ORDER.compare(o1.word, o2.word);
                } else {
                    return 1;
                }
            }
        });
        for (Map.Entry<String, Integer> entry: treeMap.entrySet()) {
            Pair pair = new Pair(entry.getKey(), entry.getValue());
            treeSet.add(pair);
        }

        String[] result = new String[k];
        int i = 0;
        for (Pair pair: treeSet) {
            if (i < k) {
                result[i] = pair.word;
                i++;
            } else {
                break;
            }
        }
        return result;
    }

    static class Pair {
        public String word;
        public int count;

        public Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair p = (Pair) o;
            if (this.word.equals(p.word) && this.count == p.count) {
                return true;
            } else {
                return false;
            }
        }
    }

}
