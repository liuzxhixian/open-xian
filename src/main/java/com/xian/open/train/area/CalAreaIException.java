package com.xian.open.train.area;

/**
 * @author xian
 * @description
 * @createTime 2021/10/12 14:57
 */
public class CalAreaIException extends Exception {

    private String msg;

    public CalAreaIException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.out.println(msg);
    }
}
