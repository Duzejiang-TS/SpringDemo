package org.seckill.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entiy.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Devin13 on 2018/8/2.
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
        System.out.println(list);
    }

    @Test
    public void getById() {
        int id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 12345678910L;
        String md5 = "f13b041c6d158177113737e76902ff30";
        try{
            SeckillExcution seckillExcution = seckillService.executeSeckill(id,phone,md5);
            logger.info("seckillExcution={}",seckillExcution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e1){
            logger.error(e1.getMessage());
        }


    }
}