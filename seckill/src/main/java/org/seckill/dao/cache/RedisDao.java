package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import org.seckill.entiy.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Devin13 on 2018/8/3.
 * @version 1.0
 */
public class RedisDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public Seckill getSeckill(long seckillId) {
        //redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                byte[] bytes = jedis.get(key.getBytes());
                //缓存重获取到
                if(bytes != null) {
                    //空对象
                    Seckill seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                    //seckill被反序列化
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        //set object(seckill)->序列化->byte[]
        Jedis jedis = jedisPool.getResource();

        try {
            String key = "seckill:" + seckill.getSeckillId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            int timeout = 60 * 60;//秒
            return jedis.setex(key.getBytes(), timeout, bytes);
        } finally {
            jedis.close();
        }
    }

}
