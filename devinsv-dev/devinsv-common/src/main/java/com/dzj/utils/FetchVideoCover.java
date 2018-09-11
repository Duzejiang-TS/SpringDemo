package com.dzj.utils;

/**
 * @author Devin13 on 2018/9/9.
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FetchVideoCover {

    private String ffmpegEXE;

    public FetchVideoCover(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void getCover(String videoInputPath,String coverOutputPath) throws IOException {
        //从视频中截图
        //ffmpeg.exe -ss 00:00:01 -i source.mp4 -vframes 1 output.jpg
        //            截视频第一秒图   视频         第一帧    图片名和格式
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-ss");
        command.add("00:00:01");
        command.add("-i");
        command.add(videoInputPath);
        command.add("-vframes");
        command.add("1");
        command.add(coverOutputPath);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ((line = br.readLine()) != null){
        }

        if(br != null)
            br.close();
        if(inputStreamReader != null)
            inputStreamReader.close();
        if(errorStream != null)
            errorStream.close();
    }

    public static void main(String[] args) {
        FetchVideoCover ffmpeg = new FetchVideoCover("D:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffmpeg.getCover("C:\\Users\\旗云百里\\Desktop\\VID_20180818_100759.mp4","C:\\Users\\旗云百里\\Desktop\\图片.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
