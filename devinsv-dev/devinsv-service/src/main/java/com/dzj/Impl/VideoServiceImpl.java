package com.dzj.Impl;

import com.dzj.VideoService;
import com.dzj.mapper.SearchRecordsMapper;
import com.dzj.mapper.VideosMapper;
import com.dzj.mapper.VideosMapperCustom;
import com.dzj.pojo.SearchRecords;
import com.dzj.pojo.Videos;
import com.dzj.utils.PagedResult;
import com.dzj.vo.VideosVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Devin13 on 2018/9/5.
 * @version 1.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideosMapper videosMapper;

    @Autowired
    private VideosMapperCustom videosMapperCustom;

    @Autowired
    private SearchRecordsMapper searchRecordsMapper;

    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public String saveVideo(Videos video) {
        //当刚刚上传视频没有点赞数时，insertSelective可以保证值为默认值0
        String id  = sid.nextShort();
        video.setId(id);
        videosMapper.insertSelective(video);

        return id;
    }

    @Transactional
    @Override
    public void updateVideo(String videoId, String coverPath) {

        Videos video = new Videos();
        video.setId(videoId);
        video.setCoverPath(coverPath);
        videosMapper.updateByPrimaryKeySelective(video);
    }

    @Transactional
    @Override
    public PagedResult getAllVideos(Videos videos,Integer isSaveRecord,
                                    Integer page, Integer paseSize) {
        String desc = videos.getVideoDesc();
        if(isSaveRecord != null && isSaveRecord ==1){
            SearchRecords record = new SearchRecords();
            String recordId = sid.nextShort();
            record.setId(recordId);
            record.setContent(desc);
            searchRecordsMapper.insert(record);
        }

        PageHelper.startPage(page,paseSize);
        List<VideosVo> list = videosMapperCustom.queryAllVideos();
        PageInfo<VideosVo> pageList = new PageInfo<>(list);

        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }
}
