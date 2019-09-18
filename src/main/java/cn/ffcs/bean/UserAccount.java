package cn.ffcs.bean;

public class UserAccount {
    /**
     * 账户编号
     */
    private int id;
    /**
     * 用户编号
     */
    private int userId;
    /**
     * 用户存款
     */
    private Float deposit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Float getDeposit() {
        return deposit;
    }

    public void setDeposit(Float deposit) {
        this.deposit = deposit;
    }
}
