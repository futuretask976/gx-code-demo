package com.gx.code.demo.algorithm.leetcode;

import java.util.*;

public class CanFinishSolution {
    public static void main(String args[]) {
//        System.out.println(canFinish(2, new int[][]{{1,0}}));
//        System.out.println(canFinish(2, new int[][]{{0,1}}));
//        System.out.println(canFinish(2, new int[][]{{1,0}, {0,1}}));
        System.out.println(canFinish(5, new int[][]{{1,4},{2,4},{3,1},{3,2}}));
//        System.out.println(canFinish(3, new int[][]{{1,0},{1,2},{0,1}}));
    }

    private static Map<Integer, CourseNode> courseNodeMap = new HashMap<>();

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        courseNodeMap.clear();

        if (prerequisites.length == 0) {
            return true;
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

            from.nextSet.add(to);
        }

        Set<CourseNode> totalValidSet = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            CourseNode courseNode = courseNodeMap.get(i);
            if (courseNode == null) {
                courseNode = new CourseNode();
                courseNode.courseNum = i;
                totalValidSet.add(courseNode);
            } else {
                if (courseNode.hasCyclic) {
                    continue;
                }
                if (courseNode.isSafe) {
                    totalValidSet.add(courseNode);
                } else {
                    traverse(courseNode, new ArrayDeque<>());
                    if (courseNode.hasCyclic) {
                        continue;
                    }
                    totalValidSet.add(courseNode);
                }
            }
        }

        return totalValidSet.size() >= numCourses ? true : false;
    }

    private static void traverse(CourseNode source, Deque<CourseNode> path) {
        if (source.hasCyclic || source.isSafe) {
            return;
        }
        if (path.contains(source)) {
            source.hasCyclic = true;
            return;
        }

        path.addLast(source);
        for (CourseNode node : source.nextSet) {
            traverse(node, path);
        }
        if (!source.hasCyclic) {
            source.isSafe = true;
        }
        path.removeLast();
    }

    static class CourseNode {
        public int courseNum;

        public Set<CourseNode> nextSet = new HashSet<>();

        public boolean hasCyclic = false;

        public boolean isSafe = false;
    }
}
