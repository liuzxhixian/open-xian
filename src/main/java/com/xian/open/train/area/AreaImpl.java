package com.xian.open.train.area;

import java.util.Scanner;

/**
 * @author xian
 * @description
 * @createTime 2021/10/12 14:59
 */
public class AreaImpl implements AreaInterface{

    static final Scanner sc = new Scanner(System.in);
    /**
     * 计算三角形面积
     */
    @Override
    public void calTriangleArea() {
        System.out.println("请输入三角形是三条边：");
        System.out.println("第一条边：");
        String a = sc.next();
        System.out.println("第二条边：");
        String b = sc.next();
        System.out.println("第三条边：");
        String c = sc.next();
        double aa = Double.valueOf(a);
        double bb = Double.valueOf(b);
        double cc = Double.valueOf(c);
        // 判断三条边是否符合规范
        if ((aa + bb) > cc
            && (aa + cc) > bb
            && (bb + cc) > aa) {
            // 计算三角形面积
            double p = (aa + bb + cc) / 2;
            double s = Math.sqrt(p*(p-aa)*(p-bb)*(p-cc));
            System.out.println("该三角形面积为：" + s);
        } else {
            try {
                throw new CalAreaIException("任意两边之和不能小于第三边！");
            } catch (CalAreaIException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算圆面积
     */
    @Override
    public void calCircleArea() {
        System.out.println("请输入圆的半径：");
        String s = sc.next();
        double radius = Double.valueOf(s);
        double area = PI.doubleValue() * radius * radius;
        System.out.println("圆的面积为：" + area);
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        AreaInterface areaInterface = new AreaImpl();
        areaInterface.calTriangleArea();
        areaInterface.calCircleArea();
    }

}
