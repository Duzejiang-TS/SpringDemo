package com.dzj.mapper;

import com.dzj.pojo.Videos;
import com.dzj.utils.MyMapper;
import com.dzj.vo.VideosVo;

import java.util.List;

public interface VideosMapperCustom extends MyMapper<Videos> {

    public List<VideosVo> queryAllVideos();
}