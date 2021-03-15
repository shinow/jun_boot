package com.ifast.oss.service.impl;

import com.ifast.common.base.CoreServiceImpl;
import com.ifast.common.component.oss.support.FileNameUtils;
import com.ifast.common.component.oss.support.UploadServer;
import com.ifast.common.config.IFastProperties;
import com.ifast.common.utils.FileType;
import com.ifast.oss.dao.FileDao;
import com.ifast.oss.domain.FileDO;
import com.ifast.oss.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

/**
 * <pre>
 * </pre>
 *
 * <small> 2018年3月23日 | Aron</small>
 */
@Service
@AllArgsConstructor
public class FileServiceImpl extends CoreServiceImpl<FileDao, FileDO> implements FileService {

    private IFastProperties ifastConfig;
    private UploadServer uploader;

    @Override
    public String upload(byte[] uploadBytes, String fileName) {
        String url = uploader.upload(uploadBytes, FileNameUtils.getFileName(fileName, ifastConfig));
        FileDO sysFile = new FileDO(FileType.fileType(fileName), url, new Date());
        super.insert(sysFile);
        return url;
    }

    @Override
    public String upload(File file, String fileName) {
        return upload(getBytes(file), fileName);
    }


    private byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
