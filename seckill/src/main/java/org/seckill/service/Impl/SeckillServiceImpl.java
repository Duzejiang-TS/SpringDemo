package org.seckill.service.Impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entiy.Seckill;
import org.seckill.entiy.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Devin13 on 2018/8/2.
 * @version 1.0
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //这是一个用来混淆MD5的盐值字符串，乱打的
    private final String slat = "sadfdas129073!@#DFF$T%$Ttq243t$T#%EW";

    @Resource
    private SeckillDao seckillDao;

    @Resource
    private RedisDao redisDao;

    @Resource
    private SuccessKilledDao successKilledDao;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {

        //缓存优化，使用redis
        //1.访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            //2.访问数据库
            seckill = seckillDao.queryById(seckillId);
            if(seckill == null){
                return new Exposer(false,seckillId);
            }else{
                //3.放入redis
                redisDao.putSeckill(seckill);
            }
        }

//        Seckill seckill = seckillDao.queryById(seckillId);

//        if(seckill == null){
//            return new Exposer(false,seckillId);
//        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()){
            return new Exposer(false,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转化特定字符串过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {
        if(md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");//秒杀数据重写
        }
        //执行秒杀逻辑：1.减库存  2.记录购买行为
        Date nowTime = new Date();
       try {
           //2.记录购买行为
           int insertcount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
           if(insertcount <= 0) {
               throw new RepeatKillException("seckill repeated");
           }else {
               //1.减库存
               int updateCount =  seckillDao.reduceNumber(seckillId,nowTime);
               if(updateCount <= 0){
                   //没有更新记录，秒杀结束
                   throw new SeckillCloseException("seckill is close");
               } else{
                   //秒杀成功
                   SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                   return new SeckillExcution(seckillId,SeckillStatEnum.SUCCESS,successKilled);
               }
           }
       } catch (SeckillCloseException e1){
           throw e1;
       } catch (RepeatKillException e2){
           throw e2;
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           //将所有编译期异常转换为运行期异常
           throw new SeckillException("seckill unner error:" + e.getMessage());
       }
    }
}
