package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entiy.SuccessKilled;

/**
 * @author Devin13 on 2018/7/31.
 * @version 1.0
 */
public interface SuccessKilledDao {

    //插入购买明细，可过滤重复（联合主键）
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);

    //根据id查询SuccessKilled并携带秒杀产品对象实体
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);

}
