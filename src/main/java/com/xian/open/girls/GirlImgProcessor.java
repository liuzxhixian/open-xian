package com.xian.open.girls;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xian
 * @description
 * @createTime 2021/9/9 8:41
 */
public class GirlImgProcessor {
    private String url;
    private GirlImgPipeline pipeline;
    private List<JSONObject> dataList;
    private List<String> urlList;
    private String word;

    public GirlImgProcessor(String url,String word) {
        this.url = url;
        this.word = word;
        this.pipeline = new GirlImgPipeline();
        this.dataList = new ArrayList<JSONObject>();
        this.urlList = new ArrayList<String>();
    }

    public GirlImgProcessor(String url,String word,String savePath) {
        this.url = url;
        this.word = word;
        this.pipeline = new GirlImgPipeline(savePath);
        this.dataList = new ArrayList<JSONObject>();
        this.urlList = new ArrayList<String>();
    }

    public void process(int idx, int size) {
        String s = HttpUtil.get(String.format(this.url, idx, size, word));
        System.out.println("获取数据。。。");
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);
        List<JSONObject> items = (List<JSONObject>)((JSONObject) jsonObject.get("data")).get("items");
        for (JSONObject item : items) {
            this.urlList.add(item.getString("picUrl"));
        }
        this.dataList.addAll(items);
    }

    public void pipelineData() {
        pipeline.processSync(this.urlList,this.word);
    }

    public static void main(String[] args) {
        String url = "https://pic.sogou.com/napi/pc/searchList?mode=1&start=%s&xml_len=%s&query=%s";
//        int start = 0, size = 2, limit = 2;
        String word = "小苏菲";
//        if (args != null) {
//            if (args.length == 1 ) {
//                word = args[0];
//            }
//            if (args.length == 2 ) {
//                word = args[0];
//                start = Integer.valueOf(args[1]);
//            }
//            if (args.length == 3 ) {
//                start = Integer.valueOf(args[1]);
//                size = Integer.valueOf(args[2]);
//                word = args[0];
//            }
//
//        }
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入保存路径：");
        String path = sc.nextLine();
        System.out.println("请输入关键字：");
        String keyword = sc.nextLine();
        System.out.println("请输入爬取索引：");
        String index = sc.nextLine();
        System.out.println("请输入每次爬取数量：");
        String size2 = sc.nextLine();


        GirlImgProcessor processor = new GirlImgProcessor(url,keyword,path);
        for (int i = 0; i < Integer.valueOf(index) + Integer.valueOf(size2); i++) {
            processor.process(i,Integer.valueOf(size2));
        }
        processor.pipelineData();
    }



}
