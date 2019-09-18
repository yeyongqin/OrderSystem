package cn.ffcs.bean;

import java.util.Date;

public class User {
    /**
     * 用户编号
     * 表字段 : user.id
     */
    private Integer id;

    /**
     * 手机号
     * 表字段 : user.phone
     */
    private String phone;

    /**
     * 密码
     * 表字段 : user.userPassword
     */
    private String userPassword;

    /**
     * 头像
     * 表字段 : user.headImage
     */
    private String headImage;

    /**
     * 创建时间
     * 表字段 : user.createTime
     */
    private Date createTime;

    /**
     * 是否有效 0有效1无效
     * 表字段 : user.dataFlag
     */
    private Integer dataFlag;

    /**
     * 用户名称
     * 表字段：user.name
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Integer dataFlag) {
        this.dataFlag = dataFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}