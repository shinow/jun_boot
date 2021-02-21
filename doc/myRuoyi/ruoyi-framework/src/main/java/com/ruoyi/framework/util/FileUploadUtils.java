package com.ruoyi.framework.util;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.utils.Md5Utils;
import com.ruoyi.common.utils.file.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传工具类
 * 
 * @author ruoyi
 */
public class FileUploadUtils
{
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 52428800;

    /**
     * 默认上传的地址
     */
    private static final String defaultBaseDir = Global.getProfile();

    /**
     * 默认的文件名最大长度
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";
    public static String netBaseUrl = BaiduBosUtils.baseUrl;
    /**计数*/
    private static int counter = 0;


    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(defaultBaseDir, file);
        }
        catch (Exception e)
        {
            throw new IOException(e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException
    {

        String originalFilename = file.getOriginalFilename();
        int fileNamelength = originalFilename.length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(originalFilename, fileNamelength,
                    FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        assertAllowed(file);
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //String fileName = encodingFilename(originalFilename, extension);
        /*同一文件文件名将一样*/
        String fileName = FileUtils.getFileUUID(file.getInputStream())+extension;
        if (Global.isNetFile()){
            BaiduBosUtils.upload(file.getInputStream(), fileName);
        }else {
            File localFile = getAbsoluteFile(baseDir, fileName);
            file.transferTo(localFile);
        }
        return fileName;
    }

    /**
     * 获取文件对象 ,不存在则创建
     * @param dirUrl 文件所在目录
     * @param filename 文件名
     * @return 文件对象
     * @throws IOException
     */
    private static final File getAbsoluteFile(String dirUrl, String filename) throws IOException
    {
        File desc = new File(dirUrl,filename);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * MD5编码文件名
     * @param filename 文件名
     * @param extension 后缀 例如 .jpg
     * @return
     */
    private static final String encodingFilename(String filename, String extension)
    {
        filename = filename.replace("_", " ");
        filename = Md5Utils.hash(filename + System.nanoTime() + counter++) + extension;
        return filename;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file) throws FileSizeLimitExceededException
    {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException("not allowed upload upload", size, DEFAULT_MAX_SIZE);
        }
    }
    /**
     * 获取文件上传路径,web路径
     * <br>固定好就不能变,系统上上下下都是这个
     */
    public static String getWebProfile()
    {
        if (Global.isNetFile()){
            return netBaseUrl;
        }else {
            return Global.getConfig("ruoyi.webProfile");
        }
    }

    /**
     * 获取云存储文件流
     * @param fileName
     * @return
     */
    public static InputStream downWebFile(String fileName) {
       return BaiduBosUtils.downWebFile(fileName);
    }

    /**
     * 删除云存储文件
     * @param fileName
     */
    public static void deleteWebFile(String fileName) {
        BaiduBosUtils.deleteWebFile(fileName);
    }
}
