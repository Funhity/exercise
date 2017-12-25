package com.huaxia.sso.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;

/**
 * 华夏信财-OCC
 * com.huaxia.sso.mvc.controller
 * 作者-Liu zhilai
 * 说明：
 * 2017/2/14 11:43
 * 2017华夏信财-版权所有
 */
@Controller
@RequestMapping("/code")
public class PictureCheckCodeController {

    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc > 255) {
            fc = 255;
        }

        if(bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    @RequestMapping("/PictureCheckCode")
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        byte width = 70;
        byte height = 17;
        BufferedImage image = new BufferedImage(width, height, 1);
        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        Random random = new Random();
        Font mFont = new Font("华文宋体", 1, 17);
        g.setColor(this.getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(mFont);
        g.setColor(this.getRandColor(180, 200));

        int itmp;
        int session;
        for(int sRand = 0; sRand < 130; ++sRand) {
            itmp = random.nextInt(width - 1);
            session = random.nextInt(height - 1);
            int encode = random.nextInt(6) + 1;
            int color = random.nextInt(12) + 1;
            BasicStroke g2d_word = new BasicStroke(2.0F, 0, 2);
            Line2D.Double trans = new Line2D.Double((double)itmp, (double)session, (double)(itmp + encode), (double)(session + color));
            g2d.setStroke(g2d_word);
            g2d.draw(trans);
        }

        String var18 = "";
        boolean var19 = false;

        for(session = 0; session < 4; ++session) {
            if(random.nextInt(2) == 1) {
                itmp = random.nextInt(26) + 65;
            } else {
                itmp = random.nextInt(10) + 48;
            }

            char var21 = (char)itmp;
            var18 = var18 + String.valueOf(var21);
            Color var23 = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110));
            g.setColor(var23);
            Graphics2D var24 = (Graphics2D)g;
            AffineTransform var25 = new AffineTransform();
            var25.rotate((double)random.nextInt(45) * 3.14D / 180.0D, (double)(15 * session + 10), 6.0D);
            float scaleSize = random.nextFloat() + 0.5F;
            if((double)scaleSize < 0.8D || scaleSize > 1.1F) {
                scaleSize = 1.0F;
            }

            var25.scale((double)scaleSize, (double)scaleSize);
            var24.setTransform(var25);
            g.drawString(String.valueOf(var21), 15 * session + 10, 14);
        }

        HttpSession var20 = request.getSession(true);
        var20.setAttribute("randCheckCode", encodeByMD5(var18.toLowerCase()));
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }


    public String encode(String str, String algorithm) {
        if(str == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();

            try {
                MessageDigest e = MessageDigest.getInstance(algorithm);
                e.update(str.getBytes());
                byte[] bs = e.digest();

                for(int i = 0; i < bs.length; ++i) {
                    int v = bs[i] & 255;
                    if(v < 16) {
                        sb.append(0);
                    }

                    sb.append(Integer.toHexString(v));
                }
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            return sb.toString().toUpperCase();
        }
    }

    public String encodeByMD5(String str) {
        return this.encode(str, "MD5");
    }
}
