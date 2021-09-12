package com.xian.open.girls;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xian
 * @description
 * @createTime 2021/9/8 16:15
 */
public class GirlImgPipeline {

    private String extension = ".jpg";
    private String path;

    private volatile AtomicInteger suc;
    private volatile AtomicInteger fails;

    public GirlImgPipeline() {
        this("D:\\picture_video\\like");
    }

    public GirlImgPipeline(String path) {
        this(path,".jpg");
    }

    public GirlImgPipeline(String path,String extension) {
        this.path = path;
        this.extension = extension;
        suc = new AtomicInteger();
        fails = new AtomicInteger();
    }

    public void setPath(String path) {
        this.path = path;
    }

    private void download(String url,String cate,String name) throws Exception{
        String path = this.path + "/" + cate + "/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!url.endsWith(".jpg") && !url.endsWith(".jpeg") && !url.endsWith(".png")) {
            return;
        }
        String realExt = url.substring(url.lastIndexOf("."));
        String fileName = name + realExt;
        fileName = fileName.replace("-","");
        String filePath = path + fileName;
        File img = new File(filePath);
        if (img.exists()) {
            System.out.printf("文件%s已存在本地目录中",fileName);
            return;
        }
        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        InputStream inputStream = connection.getInputStream();
        byte[] cache = new byte[1024];

        File file1 = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(file1,true);
        int len;
        while ((len = inputStream.read(cache)) != -1) {
            outputStream.write(cache,0,len);
        }
        System.out.println("picUrl:" + url);
        System.out.printf("正在下载第%s张图片",suc.getAndIncrement());
    }

    /**
     * 单线程处理
     * @param data
     * @param word
     */
    public void process(List<String> data, String word) {
        long start = System.currentTimeMillis();
        for (String picUrl : data) {
            if (picUrl == null || "".equals(picUrl)) {
                continue;
            }
            try {
                download(picUrl,word,picUrl);
            } catch (Exception e) {
                System.out.println("下载失败！");
                fails.incrementAndGet();
            }
        }
        System.out.println("下载成功：" + suc.get());
        System.out.println("下载失败：" + fails.get());
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000 + "秒");
    }

    /**
     * 多线程处理
     * @param data
     * @param word
     */
    public void processSync(List<String> data,String word) {
        final long start = System.currentTimeMillis();
        int count = 0;
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 100, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("gill-image-download-thread");
                        return thread;
                    }
                });
        for (int i = 0; i < data.size(); i++) {
            String picUrl = data.get(i);
            if (picUrl == null || "".equals(picUrl)) {
                continue;
            }
            String name = "";
            if (i < 10) {
                name = "000" + i;
            } else if (i < 100) {
                name = "00" + i;
            } else if (i < 1000) {
                name = "0" + i;
            }
            String finalName = name;
            executorService.submit(() -> {
                try {
                    download(picUrl,word,finalName);
                } catch (Exception e) {
                    System.out.println("下载失败！");
                }
            });
            count++;
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(600,TimeUnit.SECONDS)) {
                executorService.shutdown();
            }
            System.out.println("AwaitTermination Finished");
            System.out.println("共有URL：" + data.size());
            System.out.println("下载成功：" + suc);
            System.out.println("下载失败" + fails);

            File dir = new File(this.path + "/" + word + "/");
            int length = Objects.requireNonNull(dir.list()).length;
            System.out.println("当前共有文件：" + length);

            long end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start) / 1000 + "秒");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 多线程分段处理
     * @param data
     * @param word
     * @param threadNum
     */
    public void processSync(List<String> data,final String word,int threadNum) {
        if (data.size() < threadNum) {
            process(data,word);
        } else {
            ExecutorService executorService = Executors.newCachedThreadPool();
            int num = data.size();
            for (int i = 0; i < threadNum; i++) {
                int start = i * num;
                int end = (i + 1) * num;
                if (i == threadNum - 1) {
                    end = data.size();
                }
                List<String> strings = data.subList(start, end);
                executorService.submit(() -> process(strings,word));
            }
            executorService.shutdown();
        }
    }


}
