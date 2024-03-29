package com.gx.code.utils.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOUtils {
    public static void main(String args[]) {
        File source = new File("/Users/Miya/xiaobing.txt");
        System.out.println(readNIO(source));
    }

    /**
     * 最终确认，是因为UTF-8编码的时候，是变长的（可能1-4个字节），所以在
     * ByteBuffer bf = ByteBuffer.allocate(capacity);
     * byte[] bytes = bf.array();
     * 的时候，会出现数字英文和中文边界截断了的问题，所以导致出现测���java 文件操作这样的乱码。
     * 解决方案1：对这类文件不进行截断输出，整个输出
     * 解决方案2：截断输出的时候，判断编码边界。进行减长度操作，避免截断了中文。
     */
    public static String readNIO(File sources) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(sources);
            FileChannel channel = fin.getChannel();

            int capacity = 1024; // 字节
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            System.out.println("限制是：" + bf.limit() + ",容量是：" + bf.capacity() + " ,位置是：" + bf.position());

            StringBuffer result = new StringBuffer();
            int length = -1;
            while ((length = channel.read(bf)) != -1) {
                // 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
                bf.clear();
                byte[] bytes = bf.array();
                // 最后输出就会有乱码符号：测���java 文件操作这样输出也有乱码
                String str = new String(bytes, 0, length,"UTF-8");
                result.append(str);
                System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity() + "位置是：" + bf.position());

            }
            // 最后输出就会有乱码符号：测���java 文件操作
            channel.close();

            return result.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    /**
     * 通道之间的数据传输(直接缓冲区的模式)
     * 耗费时间:68
     */
    private static void copyByChannel(File source, File target) throws IOException {
        long startTime = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get(source.getAbsolutePath()),
                StandardOpenOption.READ);
        FileChannel outChennel = FileChannel.open(Paths.get(target.getAbsolutePath()),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        outChennel.transferFrom(inChannel, 0, inChannel.size());

        long end = System.currentTimeMillis();
        System.out.println("nioCopyTest3耗费时间:"+(end-startTime));
    }

    /**
     * 使用直接缓冲区完成文件的复制(内存映射文件)
     * 耗费时间:142
     */
    private static void copyByBuffer(File source, File target) throws IOException {
        long startTime = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get(source.getAbsolutePath()),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(target.getAbsolutePath()),
                StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        // 内存映射文件(什么模式 从哪开始 到哪结束)
        MappedByteBuffer inMappeBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappeBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接都缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappeBuf.limit()];
        inMappeBuf.get(dst);
        outMappeBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("nioCopyTest2耗费时间:"+(end-startTime));
    }


    /**
     *  非直接缓冲区 文件的复制
     *  耗费时间:1417
     * @throws IOException
     */
    private static void coppy(File source, File target) throws IOException {
        long startTime = System.currentTimeMillis();

        FileInputStream fileInputStream = new FileInputStream(source);
        FileOutputStream fileOutputStream = new FileOutputStream(target);

        // 获取通道
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChanel = fileOutputStream.getChannel();

        // 分配缓冲区的大小
        ByteBuffer buf = ByteBuffer.allocate(1024);

        // 将通道中的数据存入缓冲区
        while (inChannel.read(buf) != -1) {
            buf.flip();  // 切换读取数据的模式
            outChanel.write(buf);
            buf.clear();
        }
        outChanel.close();
        inChannel.close();
        fileInputStream.close();
        fileOutputStream.close();

        long end = System.currentTimeMillis();
        System.out.println("nioCopyTest1耗费时间:" + (end - startTime));
    }

    /**
     *
     * @param address
     *     类似 www.baidu.com
     */
    public void readHTMLCont(String address) {
        SocketChannel channel = null;
        Charset charset = Charset.forName("GBK");
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(address, 80);

            channel = SocketChannel.open(socketAddress);
            channel.write(charset.encode("GET " + "/ HTTP/1.1" + "\r\n\r\n"));

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                System.out.println(charset.decode(buffer));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
