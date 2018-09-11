package com.dzj.devinsvminiapi.controller;

import com.dzj.BgmService;
import com.dzj.VideoService;
import com.dzj.enums.VideoStatusEnum;
import com.dzj.pojo.Bgm;
import com.dzj.pojo.Users;
import com.dzj.pojo.Videos;
import com.dzj.utils.FetchVideoCover;
import com.dzj.utils.JsonResult;
import com.dzj.utils.MergeVideoMp3;
import com.dzj.utils.PagedResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author Devin13 on 2018/8/18.
 * @version 1.0
 */
@RestController
//content-type=multipart/form-data
@RequestMapping(value = "/video",headers = "content-type=application/json;charset=UTF-8")
public class VideoController extends BasicController{

    @Autowired
    private BgmService bgmService;

    @Autowired
    private VideoService videoService;

    //上传视频
    @PostMapping("/upload")
    public JsonResult upload(String userId,String bgmId,double videoSeconds,
                                 int videoWidth,int videoHeight,String desc,
                                 @RequestParam("file") MultipartFile file) throws Exception {

        if(StringUtils.isBlank(userId)){
            return JsonResult.errorMsg("用户id为空！");
        }

        //文件保存的命名空间
        //String fileSpace = "D:/WXinfo/";
        //保存到数据库中的相对路径
        String uploadPathDB = userId + "/video/";
        String coverPathDB = "/" + userId + "/video/";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        //文件上传的最终保存路径
        String finalVideoPath = "";
        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                String fileNamePerfix = fileName.split("\\.")[0];

                //System.out.println(fileName);
                if (StringUtils.isNotBlank(fileName)) {
                    //文件上传的最终保存路径
                    finalVideoPath = FILE_SPACE + uploadPathDB + fileName;
                    //设置数据库保存路径
                    uploadPathDB +=  fileName;
                    coverPathDB += fileNamePerfix + ".jpg";

                    File outFile = new File(finalVideoPath);
                    if (!outFile.exists()) {
                        outFile.getParentFile().mkdirs();
                        outFile.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                return JsonResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        //判断bgmId是否为空，如果不为空，就说明用户要替换背景音乐
        //就要查询bgm的信息，并且合并视频，生产新的视频
        if(StringUtils.isNotBlank(bgmId)) {
            Bgm bgm = bgmService.queryBgmById(bgmId);
            String mp3InputPath = FILE_SPACE + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3(FFMPEG_EXE);
            String videoInputPath = finalVideoPath;
            String noVocieName = UUID.randomUUID().toString();
            //先去掉原始背景音视频名
            String videomidName = noVocieName + "中转.mp4";
            //加入新的背景音视频名
            String videoOutputName = noVocieName + ".mp4";

            String midDB = "/" + userId + "/video/"+ videomidName;
            String midVideoPath = FILE_SPACE + midDB;
            uploadPathDB = "/" + userId + "/video/"+ videoOutputName;


            finalVideoPath = FILE_SPACE + uploadPathDB;

            tool.convertor1(videoInputPath,midVideoPath);
            tool.convertor2(midVideoPath,mp3InputPath,videoSeconds,finalVideoPath);
        }
//        System.out.println("uploadPathDB=" + uploadPathDB);
//        System.out.println("finalVideoPath=" + finalVideoPath);

        //对视频进行截图
        FetchVideoCover ffmpeg = new FetchVideoCover(FFMPEG_EXE);
        ffmpeg.getCover(finalVideoPath,FILE_SPACE + coverPathDB);

        Videos videos = new Videos();
        videos.setAudioId(bgmId);
        videos.setUserId(userId);
        videos.setVideoDesc(desc);
        videos.setVideoPath(uploadPathDB);
        videos.setVideoSeconds((float)videoSeconds);
        videos.setVideoWidth(videoWidth);
        videos.setVideoHeight(videoHeight);
        videos.setCoverPath(coverPathDB);
//        videos.setLikeCounts();
        videos.setStatus(VideoStatusEnum.SUCCESS.value);
        videos.setCreateTime(new Date());

        String videoId = videoService.saveVideo(videos);

        return JsonResult.ok(videoId);
    }

    //上传封面
    @PostMapping("/uploadCover")
    public JsonResult uploadCover(String userId,String videoId,
                             @RequestParam("file") MultipartFile file) throws Exception {

        if(StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)){
            return JsonResult.errorMsg("用户id或视频id为空！");
        }

        //文件保存的命名空间
        //String fileSpace = "D:/WXinfo/";
        //保存到数据库中的相对路径
        String uploadPathDB = userId + "/video/";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;

        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                //System.out.println(fileName);
                if (StringUtils.isNotBlank(fileName)) {
                    //文件上传的最终保存路径
                    String finalCoverPath = FILE_SPACE + uploadPathDB + fileName;
                    //设置数据库保存路径
                    uploadPathDB +=  fileName;

                    File outFile = new File(finalCoverPath);
                    if (!outFile.exists()) {
                        outFile.getParentFile().mkdirs();
                        outFile.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } else {
                return JsonResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        videoService.updateVideo(videoId,uploadPathDB);

        return JsonResult.ok();
    }

    /*分页和搜索查询视频列表
     *isSaveRecord：1 -需要保存
     *              0 -不保存
     */
    @PostMapping(value = "/showAll")
    public JsonResult showAll(@RequestBody Videos videos,Integer isSaveRecord, Integer page) throws Exception{
        if(page == null){
            page = 1;
        }

        PagedResult result = videoService.getAllVideos(videos,isSaveRecord,page,PAGE_SIZE);
        return JsonResult.ok(result);
    }

}
