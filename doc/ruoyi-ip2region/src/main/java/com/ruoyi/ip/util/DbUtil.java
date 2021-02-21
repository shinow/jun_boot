package com.ruoyi.ip.util;

import java.io.File;
import java.lang.reflect.Method;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import com.ruoyi.ip.config.RegionConfig;

/**
 * <p>File：DbUtil.java</p>
 * <p>Title: db方式工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2019 2019年6月14日 下午4:16:23</p>
 * <p>Company:  </p>
 * @author zmr
 * @version 1.0
 */
public class DbUtil
{
    public static String getCityInfo(String ip, RegionConfig config)
    {
        return getCityInfo(ip, config.getDbpath(), config.getAlgorithm());
    }

    public static String getCityInfo(String ip, String dbPath, int algorithm)
    {
        File file = new File(dbPath);
        if (file.exists() == false)
        {
            System.out.println("Error: Invalid ip2region.db file");
        }
        try
        {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);
            // define the method
            Method method = null;
            switch (algorithm)
            {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }
            DataBlock dataBlock = null;
            if (Util.isIpAddress(ip) == false)
            {
                System.out.println("Error: Invalid ip address");
            }
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            searcher.close();
            return dataBlock.getRegion();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}