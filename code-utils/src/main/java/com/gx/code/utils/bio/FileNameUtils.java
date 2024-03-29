package com.gx.code.utils.bio;

import java.io.File;

public class FileNameUtils {
    public static void main(String args[]) {
        removeNamePrefix("/Volumes/小冰的移动硬盘/Adult Video/明里紬（明里䌷、明里つむぎ）");
    }

    public static void removeNamePrefix(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            throw new IllegalArgumentException("folder does not exist");
        }

        File[] fileList = folder.listFiles();
        String filePrefix = "[BT-btt.com]";
        for (File file : fileList) {
            System.out.printf("!!! FileNameUtils#renameFiles file=%s\n", file.getName());
            if (file.getName().startsWith(filePrefix)) {
                file.renameTo(new File(folderPath + "/" + file.getName().substring(filePrefix.length())));
            }
        }
    }
}
