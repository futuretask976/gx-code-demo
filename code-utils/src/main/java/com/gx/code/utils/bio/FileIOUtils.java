package com.gx.code.utils.bio;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileIOUtils {
    public static void main(String args[]) {
        File file = new File("/Users/Miya/Downloads/PikPak/abc/A-0001-200W.csv");

        CSVFormat csvFormat = CSVFormat.Builder.create()
                .setDelimiter(",") // 自定义数据字段为|+|替换默认的逗号
                .setTrim(true) // 去除数据两边的空格，如 "Abc " 则实际输出为"Abc"，但是数据为"A bc",实际输出还是"A bc"
                .setIgnoreEmptyLines(true) // 忽略空行
                // .setHeader(CSV_HEAD) // CSV 表头
                .setSkipHeaderRecord(false) // 跳过表头(需要设置表头后生效)
                .build();

        try {
            Reader reader = new FileReader(file);
            CSVParser parser = new CSVParser(reader, csvFormat);
            int non33 = 0;
            for (CSVRecord record : parser) {
                if (record.size() != 33) {
                    System.out.println("record=" + record.toString());
                    non33++;
                }
            }
            System.out.println("non33=" + non33);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseCsvFile(File file) {
        CSVFormat csvFormat = CSVFormat.Builder.create()
                .setDelimiter(",") // 自定义数据字段为|+|替换默认的逗号
                .setTrim(true) // 去除数据两边的空格，如 "Abc " 则实际输出为"Abc"，但是数据为"A bc",实际输出还是"A bc"
                .setIgnoreEmptyLines(true) // 忽略空行
                // .setHeader(CSV_HEAD) // CSV 表头
                .setSkipHeaderRecord(false) // 跳过表头(需要设置表头后生效)
                .build();

        try {
            Reader reader = new FileReader(file);
            CSVParser parser = new CSVParser(reader, csvFormat);
            for (CSVRecord record : parser) {
                // 逐行获取数据并进行处理
                String name = record.get(0);
                String id = record.get(32);

                System.out.println("name=" + name + ", id=" + id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream stringToInputStream(String input) {
        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(is.toString());
        return is;
    }

    public static ByteArrayOutputStream InputStreamToOutputStream(InputStream in) {
        ByteArrayOutputStream outStream = null;
        try {
            outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int count = -1;
            while ((count = in.read(data, 0, 1024)) != -1)
                outStream.write(data, 0, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outStream;
    }

    public static boolean isContain(File file, String targetContent) {
        String fileContent = readFileToLine(file);
        return fileContent.contains(targetContent);
    }

    public static String readFileToLine(File inputFile) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    inputFile), "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
        } catch (IOException e) {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static List<String> readFileToList(File inputFile) {
        List<String> contentList = new ArrayList<String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    inputFile), "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                if (null != line && !"".equals(line.trim())) {
                    contentList.add(line);
                }
            }
        } catch (IOException e) {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return contentList;
    }

    public static String inputStreamToString(InputStream in) throws IOException {
        BufferedInputStream buf = new BufferedInputStream(in);
        InputStreamReader isr = new InputStreamReader(buf);
        BufferedReader br = new BufferedReader(isr);
        String readLine = null;
        StringBuffer stringBuffer = new StringBuffer();
        while ((readLine = br.readLine()) != null) {
            stringBuffer.append(readLine).append("\n");
        }
        return stringBuffer.toString();
    }

    public static void copyFile(File source, File target) {
        File in = source;
        File out = target;
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(in);
            fos = new FileOutputStream(out);
            copyBytes(fis, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void copyBytes(InputStream in, OutputStream out) {
        int c;
        byte[] bytes = new byte[1024];
        try {
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static File zipFile(String sourceDir, String toZipFilePath)
            throws IOException {
        File file = new File(sourceDir);
        File toZipFile = new File(toZipFilePath);
        ZipOutputStream zos = null;
        try {
            OutputStream os = new FileOutputStream(toZipFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            zos = new ZipOutputStream(bos);

            String basePath = null;
            if (file.isDirectory()) {
                basePath = file.getPath();
            } else {
                basePath = file.getParent();
            }
            doZipFile(file, basePath, zos);
        } finally {
            if (zos != null) {
                zos.closeEntry();
                zos.close();
            }
        }
        return toZipFile;
    }

    /**
     * @param source
     * @param basePath
     * @param zos
     */
    private static void doZipFile(File source, String basePath,
            ZipOutputStream zos) throws IOException {
        File[] files = null;
        if (source.isDirectory()) {
            files = source.listFiles();
        } else {
            files = new File[1];
            files[0] = source;
        }

        InputStream is = null;
        String pathName;
        byte[] buf = new byte[1024];
        int length = 0;
        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    pathName = file.getPath().substring(basePath.length() + 1)
                            + "/";
                    zos.putNextEntry(new ZipEntry(pathName));
                    doZipFile(file, basePath, zos);
                } else {
                    pathName = file.getPath().substring(basePath.length() + 1);
                    is = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    zos.putNextEntry(new ZipEntry(pathName));
                    while ((length = bis.read(buf)) > 0) {
                        zos.write(buf, 0, length);
                    }
                }
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
