package com.rq.rqwfkj.minsu.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyResult {


    private int code;
    private String message;
    private Object data;
        public static EasyResult success(Object data) {
            EasyResult result = new EasyResult(200, "成功", data);
        return result;}
    public static EasyResult success() {
        return success(null);
        }
    public static EasyResult error(String msg) {
            EasyResult result = new EasyResult(500,"请求失败联系管理员", null);
        return result;
        }



}
