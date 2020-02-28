/*******************************************************************************
 * Copyright(c) 2019 Enterprise.Framework All rights reserved. / Confidential
 * ClassInformation:
 *		1.ProgramName:Enterprise.Framework.Core
 *		2.ClassName:HttpManager.cs
 *		3.FunctionDescription:核心组件 — 模拟请求处理器
 *		4.Call:
 *		5.CalledBy:
 *		6.TableAccessed:
 *		7.TableUpdated:
 *		8.Input:
 *		9.Output:
 *	    10.Return:
 *       11.Others:
 * EditResume:
 *	   Author				Date			  version			   ChangeContent 
 *		gl				 2020-02-04		        1.00					新建
 *******************************************************************************/

package controller.filecenter;

import enterprise.framework.core.http.HttpResponse;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/v1/fileCenter/")
public class FileCenterController {

    @ResponseBody
    @PostMapping("upload")
    public HttpResponse upload(@RequestParam MultipartFile file) throws IOException {
        File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!destFile.exists()) {
            destFile = new File("");
        }
        System.out.println("file path:" + destFile.getAbsolutePath());
        SimpleDateFormat sf_ = new SimpleDateFormat("yyyy-MM");
        String times = sf_.format(new Date());
        File upload = new File(destFile.getAbsolutePath(), "picture/" + times);

        //若目标文件夹不存在，则创建
        if (!upload.exists()) {
            upload.mkdirs();
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(upload.getAbsolutePath() + "/" + file.getOriginalFilename());
        Files.write(path, bytes);
        return new HttpResponse();
    }
}
