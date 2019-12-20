package utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片工具类
 * @author wangb
 * @version 1.0.0
 * @since 2019/12/16
 */
public class ImageUtil {

    public static void main(String[] args) {
        String sourceFilePath = "C:\\Users\\wangb\\Desktop\\年前技术预研\\photoCompression\\01.png";
        String thumbFilePath = "C:\\Users\\wangb\\Desktop\\年前技术预研\\photoCompression\\01.jpg";
        compressImage(sourceFilePath, thumbFilePath, 2, 1f);
    }


    /**
     * 图片等比例压缩和放大
     * 说明：支持按比例、指定压缩质量压缩图片
     * 目前：不精确
     *
     * @param sourceFilePath 图片原地址
     * @param thumbFilePath  压缩后图片地址
     * @param compressRate   压缩比例
     * @param quality        压缩质量
     */
    public static void compressImage(String sourceFilePath, String thumbFilePath, double compressRate,
                                     float quality) {
        if (compressRate <= 0) {
            throw new IllegalArgumentException("compressRate must greater than 0");
        }
        if (quality <= 0) {
            throw new IllegalArgumentException("quality must greater than 0");
        }
        FileOutputStream out = null;
        try {
            File sourceImageFile = new File(sourceFilePath);
            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
            //原图高
            int sourceHeight = sourceImage.getHeight(null);
            //原图宽
            int sourceWidth = sourceImage.getWidth(null);
            // 处理后图片高度
            int thumbHeight = (int) (compressRate * sourceHeight);
            // 处理后图片宽度
            int thumbWidth = (int) (compressRate * sourceWidth);

            out = new FileOutputStream(thumbFilePath);
            BufferedImage tag = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_3BYTE_BGR);
            tag.getGraphics().drawImage(sourceImage, 0, 0, thumbHeight, thumbWidth, null);

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality(quality, true);
            encoder.encode(tag, jep);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void wef() {
//        double srcScale = (double) srcHeight / srcWidth;
//        /**缩略图宽高算法*/
//        if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
//            if (srcScale >= scale || 1 / srcScale > scale) {
//                if (srcScale >= scale) {
//                    deskHeight = (int) comBase;
//                    deskWidth = srcWidth * deskHeight / srcHeight;
//                } else {
//                    deskWidth = (int) comBase;
//                    deskHeight = srcHeight * deskWidth / srcWidth;
//                }
//            } else {
//                if ((double) srcHeight > comBase) {
//                    deskHeight = (int) comBase;
//                    deskWidth = srcWidth * deskHeight / srcHeight;
//                } else {
//                    deskWidth = (int) comBase;
//                    deskHeight = srcHeight * deskWidth / srcWidth;
//                }
//            }
//        } else {
//            deskHeight = srcHeight;
//            deskWidth = srcWidth;
//        }
    }

    /**
     * 直接指定压缩后的宽高：
     * (先保存原文件，再压缩、上传)
     * 壹拍项目中用于二维码压缩
     *
     * @param oldFile   要进行压缩的文件全路径
     * @param width     压缩后的宽度
     * @param height    压缩后的高度
     * @param quality   压缩质量
     * @param smallIcon 文件名的小小后缀(注意，非文件后缀名称),入压缩文件名是yasuo.jpg,则压缩后文件名是yasuo(+smallIcon).jpg
     * @return 返回压缩后的文件的全路径
     */
    public static String zipImageFile(String oldFile, int width, int height,
                                      float quality, String smallIcon) {
        if (oldFile == null) {
            return null;
        }
        String newImage = null;
        try {
            /**对服务器上的临时文件进行处理 */
            File file = new File(oldFile);
            Image srcFile = ImageIO.read(file);
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
            String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
            /** 压缩后的文件名 */
            newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());
            /** 压缩之后临时存放位置 */
            FileOutputStream out = new FileOutputStream(newImage);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality(quality, true);
            encoder.encode(tag, jep);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }

    /**
     * 保存文件到服务器临时路径(用于文件上传)
     *
     * @param fileName
     * @param is
     * @return 文件全路径
     */
    public static String writeFile(String fileName, InputStream is) {
        if (fileName == null || fileName.trim().length() == 0) {
            return null;
        }
        try {
            /** 首先保存到临时文件 */
            FileOutputStream fos = new FileOutputStream(fileName);
            byte[] readBytes = new byte[512];// 缓冲大小
            int readed = 0;
            while ((readed = is.read(readBytes)) > 0) {
                fos.write(readBytes, 0, readed);
            }
            fos.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }


    public static void compoundImage(String parentFilePath, String outPath) {
        File parentFile = new File(parentFilePath);
        File[] imageFiles = parentFile.listFiles();
        BufferedImage[] images = new BufferedImage[imageFiles.length];

        try {
            int width = 0;
            int height = 0;
            int drawTotalWidth = 0;
            int drawTotalHeight = 0;
            int drawStartY = 0;
            for (int i=0;i<imageFiles.length;i++){
                images[i] = ImageIO.read(imageFiles[i]);
                drawTotalWidth = drawTotalWidth>images[i].getWidth()?drawTotalWidth:images[i].getWidth();
                drawTotalHeight += images[i].getHeight();
            }
            BufferedImage writer = new BufferedImage(drawTotalWidth, drawTotalHeight, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = writer.getGraphics();
            for (BufferedImage image : images) {
                width = image.getWidth();
                height = image.getHeight();
                graphics.drawImage(image, 0, drawStartY, width, height, null);
                drawStartY += height;
            }
            ImageIO.write(writer, "jpg", new File(outPath));
            graphics.dispose();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
