package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 題庫
 * @TableName question_bank
 */
@TableName(value ="question_bank")
@Data
public class QuestionBank implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 標題
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 圖片
     */
    private String picture;

    /**
     * 創建用户 id
     */
    private Long userId;

    /**
     * 編輯時間
     */
    private Date editTime;

    /**
     * 創建時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 是否刪除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}