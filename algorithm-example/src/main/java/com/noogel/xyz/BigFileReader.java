package com.noogel.xyz;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BigFileReader {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        System.out.println(random.nextBoolean());
        System.out.println(random.nextDouble());
        System.out.println(random.nextInt());
        System.out.println(random.nextLong());
        System.out.println(random.nextFloat());
        System.out.println(214748364 / 1024 / 1024 / 104);
//        System.out.println(codeString("/home/xyz/Desktop/dpcq.txt"));
        extractLines("/home/xyz/Desktop/dpcq.txt", 3);
    }

    public static List<String> extractLines(String filePath, int line) throws IOException {
        File file = new File(filePath);

        Random random = new Random();
        List<String> resp = new ArrayList<>(line);
        try{
            FileChannel channel = new RandomAccessFile(file, "r").getChannel();
            long length = channel.size();
            long partSize = length / line;
            for (int i = 0; i < line; i++) {
                long point = (long) (random.nextDouble() * partSize * (i + 1));
                long start = point - (1 << 10);
                start = start > 0 ? start : 0;
                long end = point + (1 << 10);
                end = end < length ? end : length;
                MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, start, end);
                int partPoint = (int) (point - start);
                int partLength = (int) (end - start);
                ArrayList<Byte> curLine = new ArrayList<>();
                boolean record = false;
                for (int j = partPoint; j < partLength; j++) {
                    if ((char)buffer.get(j) == '\n') {
                        record = !record;
                    }else if (record){
                        curLine.add(buffer.get(j));
                    }else if (!record && curLine.size() > 0){
                        break;
                    }
                    System.out.println("@ " + j + " " + (char) buffer.get(j) + " " + record + " " + ((char)buffer.get(j) == '\n'));
                }

//                System.out.println(new String(list2Array(curLine), "GBK"));
            }

        }finally {

        }
        return resp;
    }


    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        bin.close();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

    public static byte[] list2Array(List<Byte> blist){
        byte[] barr = new byte[blist.size()];
        for(int i=0;i<blist.size();i++){
            barr[i] = blist.get(i);
        }
        return barr;
    }
}
