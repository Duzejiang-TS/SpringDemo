package com.dzj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

	private String ffmpegEXE;
	
	public MergeVideoMp3(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}

	public void convertor1(String videoInputPath,String videoOutputPath) throws IOException {
		//将视频去除音频
		//ffmpeg -y -i source.mp4 -an -vcodec copy output.mp4
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		command.add("-y");
		command.add("-i");
		command.add(videoInputPath);
		command.add("-an");
		command.add("-vcodec");
		command.add("copy");
		command.add(videoOutputPath);

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

	public void convertor2(String videoInputPath, String mp3InputPath,
			double seconds, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i input.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
//					输入视频  输入背景音乐	长度为七秒	新视频名字
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-i");
		command.add(mp3InputPath);
		
		command.add("-t");
		command.add(String.valueOf(seconds));
		
		command.add("-y");
		command.add(videoOutputPath);
		
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		
		String line = "";
		while ( (line = br.readLine()) != null ) {
		}
		
		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}
		
	}

	public static void main(String[] args) {
		MergeVideoMp3 ffmpeg = new MergeVideoMp3("D:\\ffmpeg\\bin\\ffmpeg.exe");
		try {
			ffmpeg.convertor1("C:\\Users\\旗云百里\\Desktop\\VID_20180818_100759.mp4","C:\\Users\\旗云百里\\Desktop\\去音频.mp4");
			ffmpeg.convertor2("C:\\Users\\旗云百里\\Desktop\\去音频.mp4", "C:\\Users\\旗云百里\\Desktop\\music.mp3", 6, "C:\\Users\\旗云百里\\Desktop\\成果.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
