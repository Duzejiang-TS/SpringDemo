package org.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

/**
 * @author Devin13 on 2018/7/27.
 * @version 1.0
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = "showUploadPage",method = RequestMethod.GET)
    public String showUploadPage(){
        return "file";
    }

    @RequestMapping(value = "doUpload",method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file){
        if(!file.isEmpty()){
            //System.out.println("1");
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),new File("D:\\ceshi\\",file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
