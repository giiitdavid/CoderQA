package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 題目
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 標題
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 標簽列表（json 數組）
     */
    private String tags;

    /**
     * 推薦答案
     */
    private String answer;

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}