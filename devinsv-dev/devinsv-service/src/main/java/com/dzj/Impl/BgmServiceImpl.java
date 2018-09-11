package com.dzj.Impl;

import com.dzj.BgmService;
import com.dzj.mapper.BgmMapper;
import com.dzj.pojo.Bgm;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Devin13 on 2018/8/17.
 * @version 1.0
 */
@Service
public class BgmServiceImpl implements BgmService {

    @Autowired
    BgmMapper bgmMapper;

    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public List<Bgm> queryBgmList() {
        List<Bgm> list = bgmMapper.selectAll();
        return list;
    }

    @Transactional
    @Override
    public Bgm queryBgmById(String bgmId) {
        return bgmMapper.selectByPrimaryKey(bgmId);
    }
}
