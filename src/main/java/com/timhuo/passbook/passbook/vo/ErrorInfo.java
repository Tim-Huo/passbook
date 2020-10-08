package com.timhuo.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 统一的错误信息
 * @author: Tim_Huo
 * @created: 2020/10/08 20:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo<T> {

    //错误码
    public static final Integer ERROR = -1;

    //特定错误码
    private Integer code;

    //错误信息
    private String Message;

    //请求url
    private String url;

    //请求返回的数据
    private T data;
}
