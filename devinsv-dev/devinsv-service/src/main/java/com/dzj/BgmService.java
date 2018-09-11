package com.dzj;

import com.dzj.pojo.Bgm;

import java.util.List;

/**
 * @author Devin13 on 2018/8/17.
 * @version 1.0
 */
public interface BgmService {

    //查询背景音乐
    public List<Bgm> queryBgmList();

    //根据id查询背景音乐
    public Bgm queryBgmById(String bgmId);
}
