package com.david.coderqa.model.dto.questionBank;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 编辑題庫请求
 *
 * @author <a href="https://github.com/giiitdavid/">David</a>

 */
@Data
public class QuestionBankEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    private static final long serialVersionUID = 1L;
}