package com.dzj.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Devin13 on 2018/8/18.
 * @version 1.0
 */
public class FFMpegTest {

    private String ffmpegEXE;

    public FFMpegTest(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath,String videoOutputPath) throws Exception{
        // ffmpeg -i input.mp4 output.avi
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add(videoOutputPath);

        //Process用Java调用Windows的cmd窗口去执行一些命令
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
        FFMpegTest ffMpeg= new FFMpegTest("D:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffMpeg.convertor("C:\\Users\\旗云百里\\Desktop\\VID_20180818_100759.mp4","C:\\Users\\旗云百里\\Desktop\\123.avi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
