package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 題庫題目
 * @TableName question_bank_question
 */
@TableName(value ="question_bank_question")
@Data
public class QuestionBankQuestion implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 題庫 id
     */
    private Long questionBankId;

    /**
     * 題目 id
     */
    private Long questionId;

    /**
     * 創建用户 id
     */
    private Long userId;

    /**
     * 創建時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}