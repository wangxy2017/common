package com.wxy.common.tool;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CaptchaUtils {

    /**
     * 生成验证码图片
     *
     * @return
     */
    public static Map<String, String> generate() {
        Map<String, String> data = new HashMap<>(2);
        String randomCode = getRandomCode(4, "0oO1lIi");
        BufferedImage imageFromCode = getImageFromCode(randomCode, 100, 50, 1, false, false, false, Color.WHITE, new Color(45, 120, 244), new Color(45, 120, 244));
        String base64 = toBase64(imageFromCode);
        data.put("code", randomCode);
        data.put("captcha", base64);
        return data;
    }

    /**
     * 转换base64
     *
     * @param image
     * @return
     */
    private static String toBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
            ImageIO.write(image, "png", baos);//写入流中
            byte[] bytes = baos.toByteArray();//转换成字节
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
            base64 = base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            return "data:image/png;base64," + base64;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成随机验证码
     *
     * @param length  长度
     * @param exChars 排除的字符
     * @return
     */
    private static String getRandomCode(int length, String exChars) {
        int i = 0;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (i < length) {
            int t = random.nextInt(123);
            if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0)) {
                sb.append((char) t);
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 写图片
     *
     * @param code
     * @param width
     * @param height
     * @param interLine
     * @param randomLocation
     * @param backColor
     * @param lineColor
     * @param foreColor
     * @return
     */
    private static BufferedImage getImageFromCode(String code, int width, int height, int interLine, boolean randomLocation, boolean shear, boolean stars, Color backColor, Color lineColor, Color foreColor) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random r = new Random();
        // 绘制背景
        g.setColor(backColor == null ? getRandomColor() : backColor);
        g.fillRect(0, 0, width, height);
        // 绘制干扰线
        if (interLine > 0) {
            int x = r.nextInt(4), y = 0;
            int x1 = width - r.nextInt(4), y1 = 0;
            for (int i = 0; i < interLine; i++) {
                g.setColor(lineColor == null ? getRandomColor() : lineColor);
                y = r.nextInt(height - r.nextInt(4));
                y1 = r.nextInt(height - r.nextInt(4));
                g.drawLine(x, y, x1, y1);
            }
        }
        // 写验证码
        int fsize = (int) (height * 0.8);//字体大小为图片高度的80%
        int fx = 5;
        int fy = fsize;
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fsize));
        for (int i = 0; i < code.length(); i++) {
            fy = randomLocation ? (int) ((Math.random() * 0.3 + 0.6) * height) : fy;//每个字符高低是否随机
            g.setColor(foreColor == null ? getRandomColor() : foreColor);
            g.drawString(code.charAt(i) + "", fx, fy);
            fx += (width / code.length()) * (Math.random() * 0.3 + 0.8); //依据宽度浮动
        }
        // 扭曲图片
        if (shear) {
            shearX(g, width, height, backColor);
            shearY(g, width, height, backColor);
        }
        // 添加噪点
        if (stars) {
            float yawpRate = 0.05f;// 噪声率
            int area = (int) (yawpRate * width * height);//噪点数量
            for (int i = 0; i < area; i++) {
                int xxx = r.nextInt(width);
                int yyy = r.nextInt(height);
                int rgb = getRandomColor().getRGB();
                image.setRGB(xxx, yyy, rgb);
            }
        }
        // 封笔
        g.dispose();
        return image;
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {
        Random random = new Random();
        int period = 2;
        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);
        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (2.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }
    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        Random random = new Random();
        int period = random.nextInt(40) + 10; // 50;
        boolean borderGap = true;
        int frames = 20;
        int phase = random.nextInt(2);
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (2.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }
    }

    /**
     * 随机颜色
     *
     * @return
     */
    private static Color getRandomColor() {
        Random r = new Random();
        return new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
}
