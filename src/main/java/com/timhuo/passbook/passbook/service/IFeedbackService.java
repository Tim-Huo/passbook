package com.timhuo.passbook.passbook.service;

import com.timhuo.passbook.passbook.vo.Feedback;
import com.timhuo.passbook.passbook.vo.Response;

public interface IFeedbackService {

    /**
     * 创建评论
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    Response createFeedback(Feedback feedback);

    /**
     * 获取用户评论
     * @param userId 用户ID
     * @return {@link Response}
     */
    Response getFeedback(Long userId);
}
