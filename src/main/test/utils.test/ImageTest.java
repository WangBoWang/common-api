package utils.test;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.Test;
import utils.ImageUtil;

import java.io.IOException;

/**
 * 图片测试
 * @author wangb
 * @version 1.0.0
 * @since 2019/12/16
 */
public class ImageTest {

    @Test
    public void compressPicture() throws IOException {
//        String imageUrl = "C:\\Users\\wangb\\Desktop\\年前技术预研\\photoCompression\\01.jpg";
//        File file = new File(imageUrl);
//        ImageUtil.zipImageFile(imageUrl,1150,960,1f,"x2");
        Thumbnails.of("C:\\Users\\wangb\\Desktop\\年前技术预研\\photoCompression\\01.png")
                .scale(0.5f)
                .outputQuality(0.5f)
                .outputFormat("jpg")
                .toFile("C:\\Users\\wangb\\Desktop\\年前技术预研\\photoCompression\\01xxx.jpg");
    }

    @Test
    public void compoundImage() throws Exception{
        String parentFilePath = "C:\\Users\\wangb\\Desktop\\年前技术预研\\标准原图";
        String outPath = "C:\\Users\\wangb\\Desktop\\年前技术预研\\标准原图\\compound.jpg";
        ImageUtil.compoundImage(parentFilePath,outPath);
    }
}
