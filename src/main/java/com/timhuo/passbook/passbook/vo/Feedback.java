package com.timhuo.passbook.passbook.vo;

import com.google.common.base.Enums;
import com.timhuo.passbook.passbook.constant.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户评论
 * @author: Tim_Huo
 * @created: 2020/10/08 18:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    //用户 id
    private Long userId;

    //评论类型
    private String type;

    //PassTemplate RowKey, 如果是 app 类型的评论, 则没有
    private String templateId;

    //评论内容
    private String comment;

    public boolean validate() {

        FeedbackType feedbackType = Enums.getIfPresent(
                FeedbackType.class, this.type.toUpperCase()
        ).orNull();

        return !(null == feedbackType || null == comment);
    }
}
