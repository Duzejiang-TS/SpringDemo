package com.dzj;

import com.dzj.pojo.Videos;
import com.dzj.utils.PagedResult;

import java.util.List;

/**
 * @author Devin13 on 2018/9/5.
 * @version 1.0
 */
public interface VideoService {

    //保存视频
    public String saveVideo(Videos video);

    //修改视频封面
    public void updateVideo(String videoId, String coverPath);

    //分页查询视频列表
    public PagedResult getAllVideos(Videos videos,Integer isSaveRecord,Integer page,Integer paseSize);
}
