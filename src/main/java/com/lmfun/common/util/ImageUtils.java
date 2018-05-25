package com.lmfun.common.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Random;


public class ImageUtils {
    private static final Logger logger = LogManager.getLogger(ImageUtils.class);
    /**
     * 生成图片验证码
     * @param width
     * @param height
     * @param code
     * @param os
     */
    public static void imageCodeGeneration(int width, int height, String code, OutputStream os) {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("思源", Font.PLAIN, 34));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(15);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        for (int i = 0; i < code.length(); i++) {
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            g.drawString(code.charAt(i) + "", 16 * i + (width - 18 * code.length()) / 2, (height + 18) / 2);
        }
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成随即色
     * @param fontColor
     * @param backgroundColor
     * @return
     */
    public static Color getRandColor(int fontColor, int backgroundColor) {
        Random random = new Random();
        if(fontColor > 255) {
            fontColor = 255;
        }
        if(backgroundColor > 255) {
            backgroundColor = 255;
        }
        int r = fontColor + random.nextInt(backgroundColor - fontColor);
        int g = fontColor + random.nextInt(backgroundColor - fontColor);
        int b = fontColor + random.nextInt(backgroundColor - fontColor);
        return new Color(r, g, b);
    }
    
	/**
	 * 将网络图片进行Base64位编码
	 * 
	 * @return
	 */
	public static String encodeImgageToBase64(String imageUrl) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		ByteArrayOutputStream outputStream = null;
		try {
			URL uri =  new URL(imageUrl);
			BufferedImage bufferedImage = ImageIO.read(uri);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(outputStream.toByteArray()));
	}
	
	public static void exportImageByUrl(HttpServletResponse response, String imageUrl, String exportName){
	    URL url;
        ByteArrayOutputStream outputStream = null;
        try {
            url = new URL(imageUrl);
            BufferedImage bufferedImage = ImageIO.read(url);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
              
            response.setContentType("image/JPEG");
            String fileName = "attachment;filename="+ exportName + ".jpg";
            response.setHeader("Content-disposition",new String(fileName.getBytes("gbk"),"ISO-8859-1"));
            response.setContentType("application/pdf;charset=GBK"); 
            response.getOutputStream().write(outputStream.toByteArray());    
            response.getOutputStream().flush();   
        } catch (MalformedURLException e) {
            logger.error("根据qrcode获得URL出错", e);
        }catch (IOException e) {
            logger.error("根据qrcode生成流出错", e);
        } 
	}
}
