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
 *		gl				 2020-03-09		      1.00					新建
 *******************************************************************************/

package controller;

import com.alibaba.fastjson.JSON;
import enterprise.framework.core.http.HttpResponse;
import enterprise.framework.core.http.HttpStatus;
import enterprise.framework.core.redis.RedisHandler;
import enterprise.framework.pojo.auth.user.SysAuthUserVO;
import enterprise.framework.utility.file.FileHandler;
import enterprise.framework.utility.generaltools.PrefixEnum;
import enterprise.framework.utility.transform.StrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/file/")
public class FileCenterController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(@NotNull RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @ResponseBody
    @PostMapping("upload")
//    @GetMapping("upload")
    public HttpResponse upload(@RequestParam MultipartFile file, HttpServletRequest request) {
        HttpResponse httpResponse = new HttpResponse();
        try {
            String user_id = request.getHeader("id");
            SysAuthUserVO sysAuthUserVO = new SysAuthUserVO();
            RedisHandler redisHandler = new RedisHandler(redisTemplate);
            HttpResponse response = redisHandler.get(PrefixEnum.USERINFO.toString() + ":" + user_id);
            if (response.status == HttpStatus.SUCCESS.value()) {
                StrHandler strHandler = new StrHandler();
                sysAuthUserVO = JSON.parseObject(strHandler.binaryToStr(response.content.toString()), SysAuthUserVO.class);
            }

            SimpleDateFormat sf_ = new SimpleDateFormat("yyyy-MM");
            String times = sf_.format(new Date());
            String prefix = "/packages/eamp-files/";

            String path = sysAuthUserVO.getLoginName() + "/" + times + "/";
            String fileName = UUID.randomUUID().toString().toUpperCase() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            FileHandler fileHandler = new FileHandler();
            boolean result = fileHandler.upload(file.getBytes(), prefix + path, fileName);
            if (result) {
                httpResponse.status = HttpStatus.SUCCESS.value();
                httpResponse.msg = "上传成功";
                httpResponse.content = path + fileName;
            } else {
                httpResponse.status = HttpStatus.FAIL.value();
                httpResponse.msg = "上传失败";
            }


            return httpResponse;
        } catch (Exception error) {
            httpResponse.msg = error.getMessage();
            httpResponse.status = HttpStatus.ERROR.value();
            return httpResponse;
        }
    }
}
