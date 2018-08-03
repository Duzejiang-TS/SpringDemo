package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entiy.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * @author Devin13 on 2018/8/2.
 * @version 1.0
 */
public interface SeckillService {

    //查询所有秒杀记录
    List<Seckill> getSeckillList();

    //根据ID查询单个秒杀记录
    Seckill getById(long seckillId);

    //秒杀开启时输出秒杀接口地址，负责输出系统时间和秒杀时间
    //防止用户根据规则自己推出秒杀地址
    Exposer exportSeckillUrl (long SeckillId);

    //执行秒杀操作，md5是用来核对身份，防止用户url被篡改
    SeckillExcution executeSeckill(long seckillId,long userPhone,String md5)
        throws SeckillException,RepeatKillException,SeckillCloseException;

}
