package com.gx.code.utils.bio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderIOUtils {
    public static boolean removeFile(File root) {
        if (root == null || !root.exists()) {
            return true;
        }

        boolean result = false;
        if (root.isFile()) {
            result = root.delete();
        } else {
            File[] files = root.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                result = removeFile(file);
            }
            if (result) {
                result = root.delete();
            }
        }
        return result;
    }

    public static void removeGarbageFiles(File rootDir, String name) {
        if (rootDir == null || rootDir.isFile() || name == null || "".equals(name.trim())) {
            System.out.println("illegal arguments: rootDir=" + rootDir + ", name=" + name);
        }

        List<File> results = FolderIOUtils.findFileByName(rootDir, name);
        if (results == null) {
            System.out.println("no result found!");
        } else {
            for (File file : results) {
                boolean deleted = file.delete();
                System.out.println(file.getAbsolutePath() + ": " + (deleted ? "deleted" : "failed"));
            }
        }
    }

    public static void copyFileToDir(File source, File targetDir) {
        if (source == null || !source.isFile() || targetDir == null
                || !targetDir.isDirectory()) {
            return;
        }

        File targetFile = new File(targetDir.getAbsolutePath() + File.separator
                + source.getName());
        FileIOUtils.copyFile(source, targetFile);
    }

    public static List<File> findFileByName(File rootDir, String name) {
        List<File> results = new ArrayList<File>();

        if (!rootDir.isDirectory() || name == null) {
            return results;
        }

        File[] subs = rootDir.listFiles();
        for (int i = 0; i < subs.length; i++) {
            File sub = subs[i];
            if (sub.isDirectory()) {
                results.addAll(FolderIOUtils.findFileByName(sub, name));
            } else {
                String fileName = sub.getName();
                // System.out.println("fileName = " + fileName);
                if (fileName.contains(name)) {
                    results.add(sub);
                }
            }
        }
        return results;
    }

    public static List<File> findFolderByName(File rootDir, String name) {
        List<File> results = new ArrayList<File>();

        if (!rootDir.isDirectory() || name == null) {
            return results;
        }

        if (rootDir.getName().contains(name)) {
            results.add(rootDir);
        }

        File[] subs = rootDir.listFiles();
        for (int i = 0; i < subs.length; i++) {
            File sub = subs[i];
            if (sub.isFile()) {
                continue;
            }

            results.addAll(FolderIOUtils.findFileByName(sub, name));
        }
        return results;
    }

    public static List<File> findFileByType(File rootDir, String ext) {
        List<File> results = new ArrayList<File>();

        if (!rootDir.isDirectory() || ext == null) {
            return results;
        }

        File[] subs = rootDir.listFiles();
        for (int i = 0; i < subs.length; i++) {
            File sub = subs[i];
            if (sub.isDirectory()) {
                results.addAll(FolderIOUtils.findFileByType(sub, ext));
            } else {
                String absolutePath = sub.getAbsolutePath();
                if (absolutePath.endsWith(ext)
                        && !absolutePath.contains("-javadoc")
                        && !absolutePath.contains("-sources")
                        && !absolutePath.contains("-tests")
                        && !absolutePath.contains("-test-sources")) {
                    results.add(sub);
                }
            }
        }
        return results;
    }

    public void findSpecifiedFiles() throws Exception {
        File root = new File("D:\\CSARScan\\CRE1.2_SRC");
        List<File> matched = new ArrayList<File>();

        findSubList(root, matched);

        for (int i = 0; i < matched.size(); i++) {
            File file = matched.get(i);
            String path = file.getAbsolutePath();
            path = "E" + path.substring(1);
            File targetFile = new File(path);
            File targetFolder = new File(path.substring(0,
                    path.lastIndexOf("\\")));
            if (!targetFolder.exists()) {
                targetFolder.mkdirs();
            }

            FileIOUtils.copyFile(file, targetFile);
        }
    }

    private void findSubList(File root, List<File> matched) {
        File[] subList = root.listFiles();
        for (int i = 0; i < subList.length; i++) {
            File file = subList[i];
            if (file.getName().equalsIgnoreCase("pom.xml") && file.isFile()) {
                matched.add(file);
            } else if (file.isDirectory()) {
                this.findSubList(file, matched);
            }
        }
    }
}
