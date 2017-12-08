package com.cheung.android.base.baseuiframe.utils;

import android.os.Environment;
import android.os.StatFs;

import com.cheung.android.base.baseuiframe.log.Print;

import java.io.File;

/**
 * author: C_CHEUNG
 * created on: 2017/12/8
 * description: SD卡工具类
 */
public final class SDcardUtil {
    private SDcardUtil() { }

    /** 默认根目录名称 */
    private static String sRootDir = "BaseUIFrame";
    /** 默认缓存目录名称 */
    public static final String CACHE_DIR = "cache";
    /** 默认日志目录名称 */
    public static final String LOG_DIR = "logs";

    /** 默认根目录 */
    private static String sRootDirPath = null;
    /** 默认缓存目录 */
    private static String sCacheDirPath = null;
    /** 默认日志的目录 */
    private static String sLogDirPath = null;

    /**
     * 设置SD卡文件存放根目录名称
     */
    public static void setRootDirName(String dirName) {
        sRootDir = dirName;
    }

    /**
     * SD卡是否可用
     *
     * @return true:可用,false:不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 初始化目录
     */
    public static void initDir() {

        //默认根目录
        String downloadRootPath = File.separator + sRootDir + File.separator;
        //默认缓存目录
        String cacheDownloadPath = downloadRootPath + CACHE_DIR + File.separator;
        //默认日志目录
        String logDownloadPath = downloadRootPath + LOG_DIR + File.separator;

        try {
            if (isCanUseSD()) {
                File root = Environment.getExternalStorageDirectory();

                sRootDirPath = checkDir(root.getPath() + downloadRootPath);
                sCacheDirPath = checkDir(root.getPath() + cacheDownloadPath);
                sLogDirPath = checkDir(root.getPath() + logDownloadPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String checkDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean created = file.mkdirs();
        }
        return file.getPath();
    }

    /**
     * 计算sdcard上的剩余空间.
     *
     * @return the int
     */
    public static int freeSpaceOnSD() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdFreeMB =
            ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / 1024 * 1024;
        return (int) sdFreeMB;
    }

    /**
     * 获取下载根目录
     */
    public static String getRootDir() {
        if (sRootDirPath == null) {
            initDir();
        }
        return sRootDirPath;
    }


    /**
     * 获取缓存目录
     */
    public static String getCacheDirPath() {
        if (sCacheDirPath == null) {
            initDir();
        }
        return sCacheDirPath;
    }

    /**
     * 获取日志文件的目录
     */
    public static String getLogDirPath() {
        if (sLogDirPath == null) {
            initDir();
        }
        return sLogDirPath;
    }
}
