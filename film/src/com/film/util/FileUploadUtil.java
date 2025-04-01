package com.film.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	/**
     * 处理文件上传方法封装(单张图片)
     * @param imageFile
     * @param request
     * @return
     * @throws Exception
     */
    public static String fileUpload(MultipartFile imageFile,HttpServletRequest request) throws Exception{
        String fileName = null;
        if(!imageFile.isEmpty()){
            //获取项目跟路径
            String filePath = request.getServletContext().getRealPath("/");
            //获取项目名
            String projectName = request.getContextPath();
            //将项目跟路劲下的项目名称置为空，因为图片需要在项目外的webapp下面存放,sub截取下标为1的字符
            filePath=filePath.replace(projectName.substring(1),"");
            System.out.println("filePath"+filePath);
            //重新生成文件名字
            fileName = DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
            //将文件保存到指定目录
            imageFile.transferTo(new File(filePath+"staticimage/"+fileName));
        }
        //返回文件名字供保存
        return fileName;
    }
}
