package com.xian.open.stream;

import com.xian.open.train.StreamTest;

import java.io.*;

public class TxtFileGenerator {


    public static void main(String[] args) {
        answerCardMode("答题卡模板.txt");
    }


    public static void answerCardMode(String fileName) {

        StreamTest streamTest = new StreamTest();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 75; i++) {

            builder.append(i + "、\n");
        }

        File file = new File("D:\\" + fileName);
        boolean exists = file.exists();
        try {
            if (!exists) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
