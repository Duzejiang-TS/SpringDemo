package org.seckill.model;
/**
 * @author Devin13 on 2018/8/3.
 * @version 1.0
 */
public class SeckillInsertModel {
    private long seckillId;
    private long userPhone;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }
}
