package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 賬號
     */
    private String userAccount;

    /**
     * 密碼
     */
    private String userPassword;

    /**
     * 微信開放平台id
     */
    private String unionId;

    /**
     * 公眾號openId
     */
    private String mpOpenId;

    /**
     * 用户名稱
     */
    private String userName;

    /**
     * 用户頭像
     */
    private String userAvatar;

    /**
     * 用户簡介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban/vip
     */
    private String userRole;

    /**
     * 會員過期時間
     */
    private Date vipExpireTime;

    /**
     * 會員兌換碼
     */
    private String vipCode;

    /**
     * 會員編號
     */
    private Long vipNumber;

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