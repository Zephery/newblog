package com.myblog.util;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by Zephery on 2017/2/18.
 */
public class ImageUtil {
    private final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public  static File thimage(File file) {
        try {
            System.out.println(FileUtils.sizeOf(file));
            if (FileUtils.sizeOf(file) < 2000*1000) {
                return file;
            }
            Thumbnails.of(file).scale(1f).outputQuality(0.25f).toFile(file);
        } catch (Exception e) {
            logger.error("图片缩放错误：" + e);
        }
        return file;
    }

    public static void main(String args[]) {
        try {
            File file = new File("src/main/webapp/images/bnr.jpg");
            thimage(file);
        } catch (Exception e) {
            logger.error("" + e);
        }
    }
}
