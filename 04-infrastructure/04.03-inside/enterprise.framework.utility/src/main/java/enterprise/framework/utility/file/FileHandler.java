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
 *		gl				 2020-03-19		        1.00					新建
 *******************************************************************************/

package enterprise.framework.utility.file;


import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    /**
     * 上传文件
     *
     * @param bytes    文件字节
     * @param filePath 文件地址 不包含默认地址
     * @return
     * @throws IOException
     */
    public boolean upload(byte[] bytes, String filePath, String fileName) throws IOException {
        try {
//            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
//            if (!destFile.exists()) {
//                destFile = new File("");
//            }
//            String defaultPath = destFile.getAbsolutePath();
//
//            File upload = new File(defaultPath, filePath);
            File upload = new File(filePath);
            //若目标文件夹不存在，则创建
            if (!upload.exists()) {
                upload.mkdirs();
            }

            Path path = Paths.get(upload.getAbsolutePath() + "/" + fileName);
            Files.write(path, bytes);
            return true;
        } catch (Exception error) {
            System.out.println("文件上传异常:" + error.getMessage());
            return false;
        }
    }

    /**
     * 创建单个文件
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean createFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {// 判断文件是否存在
            System.out.println("目标文件已存在" + filePath);
            return false;
        }
        if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
            System.out.println("目标文件不能为目录！");
            return false;
        }
        if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
            // 如果目标文件所在的文件夹不存在，则创建父文件夹
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
                System.out.println("创建目标文件所在的目录失败！");
                return false;
            }
        }
        try {
            if (file.createNewFile()) {// 创建目标文件
                System.out.println("创建文件成功:" + filePath);
                return true;
            } else {
                System.out.println("创建文件失败！");
                return false;
            }
        } catch (IOException e) {// 捕获异常
//            e.printStackTrace();
            System.out.println("创建文件失败！" + e.getMessage());
            return false;
        }
    }


    /**
     * 创建目录
     *
     * @param destDirName 需要创建目录的路径
     * @return
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {// 判断目录是否存在
            System.out.println("创建目录失败，目标目录已存在！");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
            destDirName = destDirName + File.separator;
        }
        if (dir.mkdirs()) {// 创建目标目录
            System.out.println("创建目录成功！" + destDirName);
            return true;
        } else {
            System.out.println("创建目录失败！");
            return false;
        }
    }


    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (!file.exists()) {
            return true;
        }

        // 如果file不是一个文件
        if (!file.isFile()) {
            return false;
        }

        boolean flag = file.delete();
        if (!flag) {
            System.gc();
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 只删除目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;

        }
        File dirFile = new File(dir);
        // 如果dir对应的文件目录不存在
        if ((!dirFile.exists())) {
            //logger.info("目录：" + dir + "不存在！");
            return true;
        }
        // 如果dir不是一个目录
        if (!dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        return flag;
    }

    /**
     * 递归删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectoryRecursion(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件目录不存在
        if ((!dirFile.exists())) {
            //logger.info("目录：" + dir + "不存在！");
            return true;
        }

        // 如果dir不是一个目录
        if (!dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectoryRecursion(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            //logger.error("删除目录" + dir +"失败！");
            return false;
        }
        // 删除当前目录
        flag = dirFile.delete();
        //logger.info("删除目录" + dir + " "+flag);
        return flag;
    }
}
