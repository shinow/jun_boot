package com.ruoyi.common.utils.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;

import java.io.*;

/**
 * 文件处理工具类
 * 
 * @author ruoyi
 */
public class FileUtils extends FileUtil
{
    public static void main(String[] args) {
    }

    /**
     * 获取文件的唯一标识符
     * @param in
     * @return
     */
    public static String getFileUUID(InputStream in){
        return SecureUtil.md5(in);
    }

    /**
     * 获取文件的唯一标识符
     * @param file
     * @return
     */
    public static String getFileUUID(File file){
        return SecureUtil.md5(file);
    }
    /**
     * 输出指定文件的byte数组
     * 
     * @param filePath 文件路径
     * @param os 输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
