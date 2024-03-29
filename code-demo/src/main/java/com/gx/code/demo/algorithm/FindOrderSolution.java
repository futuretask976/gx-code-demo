package com.gx.code.demo.algorithm;

import java.util.*;

public class FindOrderSolution {
    public static void main(String args[]) {
        System.out.println(findOrder(2, new int[][]{{1,0}}));
    }

    private static Map<Integer, CourseNode> courseNodeMap = new HashMap<>();

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        courseNodeMap.clear();

        if (prerequisites.length == 0) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];

            CourseNode from = courseNodeMap.get(prerequisite[1]);
            if (from == null) {
                from = new CourseNode();
                from.courseNum = prerequisite[1];
                courseNodeMap.put(from.courseNum, from);
            }

            CourseNode to = courseNodeMap.get(prerequisite[0]);
            if (to == null) {
                to = new CourseNode();
                to.courseNum = prerequisite[0];
                courseNodeMap.put(to.courseNum, to);
            }
            to.isRoot = false;

            from.nextSet.add(to);
        }

        List<CourseNode> totalPathResult = new ArrayList<>();
        List<CourseNode> totalValidList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            CourseNode courseNode = courseNodeMap.get(i);
            if (courseNode == null) {
                courseNode = new CourseNode();
                courseNode.courseNum = i;
                totalValidList.add(courseNode);
            } else {
                if (courseNode.hasCyclic) {
                    continue;
                }
                if (courseNode.isSafe) {
                    totalValidList.add(courseNode);
                } else {
                    Deque<CourseNode> path = new ArrayDeque<>();
                    traverse(courseNode, path, totalPathResult);
                    if (courseNode.hasCyclic) {
                        continue;
                    }
                    totalValidList.add(courseNode);
                }
            }
        }

        if (totalValidList.size() < numCourses) {
            return new int[0];
        } else {
            for (CourseNode node : totalValidList) {
                if (!totalPathResult.contains(node)) {
                    totalPathResult.add(node);
                }
            }
            int[] result = new int[totalPathResult.size()];
            for (int i = 0; i < totalPathResult.size(); i++) {
                result[i] = totalPathResult.get(i).courseNum;
            }
            return result;
        }
    }

    private static void traverse(CourseNode source, Deque<CourseNode> path, List<CourseNode> totalPathResult) {
        if (source.hasCyclic || source.isSafe) {
            return;
        }
        if (path.contains(source)) {
            source.hasCyclic = true;
            return;
        }

        path.addLast(source);
        for (CourseNode node : source.nextSet) {
            traverse(node, path, totalPathResult);
        }
        if (!source.hasCyclic) {
            source.isSafe = true;
            for (CourseNode node : path) {
                if (!totalPathResult.contains(node)) {
                    totalPathResult.add(node);
                }
            }
        }
        path.removeLast();
    }

    static class CourseNode {
        public int courseNum;

        public Set<CourseNode> nextSet = new HashSet<>();

        public boolean hasCyclic = false;

        public boolean isSafe = false;

        public boolean isRoot = true;
    }
}
