package com.dzj.devinsvminiapi.controller;

import com.dzj.UserService;
import com.dzj.mapper.UsersMapper;
import com.dzj.pojo.Users;
import com.dzj.utils.JsonResult;

import com.dzj.vo.UsersVo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * @author Devin13 on 2018/8/15.
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BasicController{

    @Autowired
    private UserService userService;

    //上传头像
    @PostMapping("/uploadFace")
    public JsonResult uploadFace(String userId,
                                 @RequestParam("file") MultipartFile[] files) throws Exception {

        if(StringUtils.isBlank(userId)){
            return JsonResult.errorMsg("用户id为空！");
        }

        //文件保存的命名空间
        String fileSpace = "D:/WXinfo/";
        //保存到数据库中的相对路径
        String uploadPathDB = userId + "/face/";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {
                String fileName = files[0].getOriginalFilename();
                //System.out.println(fileName);
                if (StringUtils.isNotBlank(fileName)) {
                    //文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + fileName;
                    //设置数据库保存路径
                    uploadPathDB +=  fileName;

                    File outFile = new File(finalFacePath);
                    if (!outFile.exists()) {
                        outFile.getParentFile().mkdirs();
                        outFile.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
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

        Users users = new Users();
        users.setId(userId);
        users.setFaceImage(uploadPathDB);
        userService.updateUserInfo(users);

        System.out.println(uploadPathDB);
        return JsonResult.ok(uploadPathDB);
    }

    @PostMapping("/query")
    public JsonResult query(String userId) {
        if(StringUtils.isBlank(userId)){
            return JsonResult.errorMsg("用户id为空！");
        }
        Users users = userService.queryUserInfo(userId);
        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(users,usersVo);
        return JsonResult.ok(usersVo);

    }
}
