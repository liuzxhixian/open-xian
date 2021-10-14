package com.xian.open.stream;

import com.xian.open.train.StreamTest;

import java.io.*;
import java.util.Arrays;

public class TxtFileGenerator {


    public static void main(String[] args) {
        answerCardMode("答题卡模板.txt");
    }


    public static void answerCardMode(String fileName) {

        StreamTest streamTest = new StreamTest();
        StringBuilder builder = new StringBuilder();
        // 题目数量
        for (int i = 1; i <= 75; i++) {
            builder.append(i + "、\n");
        }

        File file = new File("D:\\" + fileName);
        boolean exists = file.exists();
        // 删除原来的文件，创建新文件
        try {
            if (exists) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将内容写入到文件中
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));) {
            writer.write(builder.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] arr = new int[10];
        Arrays.sort(arr);
        int max = arr[arr.length - 1];
    }


}
