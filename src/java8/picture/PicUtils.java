package java8.picture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PicUtils {
    /**

     * 根据图片url获取图片

     *

     * @param url

     * @return

     * @throws IOException

     */

    public static BufferedImage getBufferedImageFromUrl(String url) throws IOException {

        if (url.startsWith("https://") || url.startsWith("http://")) {

            return ImageIO.read(new URL(url));

        } else {

            return ImageIO.read(new File(url));

        }

    }

    /**
     *
     * @Title: 构造图片
     * @Description: 生成水印并返回java.awt.image.BufferedImage
     * @param buffImg 源文件(BufferedImage)
     * @param waterFile 水印文件(BufferedImage)
     * @param x 距离右下角的X偏移量
     * @param y  距离右下角的Y偏移量
     * @param alpha  透明度, 选择值从0.0~1.0: 完全透明~完全不透明
     * @return BufferedImage
     * @throws IOException
     */
    public static BufferedImage overlyingImage(BufferedImage buffImg, BufferedImage waterImg, int x, int y, float alpha) throws IOException {

        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        int waterImgWidth = waterImg.getWidth();
        // 获取层图的宽度
        int waterImgHeight = waterImg.getHeight();
        // 获取层图的高度
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return buffImg;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = overlyingImage(getBufferedImageFromUrl("/Users/nuc/Desktop/background.jpeg"),
                getBufferedImageFromUrl("/Users/nuc/Desktop/1.jpeg"), 100, 100, 1.0f);
        ImageIO.write(bufferedImage, "jpeg", new File("/Users/nuc/Desktop/2.jpeg"));
    }


}
