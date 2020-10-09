package com.timhuo.passbook.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 通用响应对象
 * @author: Tim_Huo
 * @created: 2020/10/09 21:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    /**
     * 错误码。正确返回 0
     */
    private Integer errorCode;

    /**
     * 错误信息，正确返回SUCCESS
     */
    private String errorMsg;

    /**
     * 返回值对象
     */
    private Object data;

    /**
     * 正确的响应构造函数
     *
     * @param data 返回值对象
     */
    public Response(Object data) {
        this.data = data;
    }

    /**
     * 空响应
     *
     * @auther: Tim_Huo
     * @return: ResponseVO
     * @date: 2020/10/9 9:27 下午
     */
    public static Response success() {
        return new Response();
    }

    /**
     * 错误响应
     *
     * @auther: Tim_Huo
     * @param: errorMsg
     * @return: ResponseVO
     * @date: 2020/10/9 9:28 下午
     */
    public static Response failure(String errorMsg) {
        return new Response(-1, errorMsg, null);
    }

}
