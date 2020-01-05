package com.dmslob.web;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class Snippet {

    public static void main(String[] args) {

        String img = "https://study.com/cimages/videopreview/videopreview-full/059tmsbnjx.jpg";
        System.out.println(loadImage(img).toString());
    }

    /**
     * Performs HTTP GET request
     *
     * @param address the URL of the connection
     * @return HTTP status code
     * @throws IOException
     */
    public static int httpGet(URL address) throws IOException {
        HttpURLConnection con = (HttpURLConnection) address.openConnection();
        return con.getResponseCode();
    }

    private static Optional<Image> loadImage(String imageUrl) {
        Optional<Image> empty = Optional.empty();
        try {
            String imageName = "loadedImage.png";
            BufferedImage bufferedImage = ImageIO.read(new URL(imageUrl));
            File file = new File(imageName);
            if (!file.exists()) {
                file.createNewFile();
            }
            ImageIO.write(bufferedImage, "png", file);
            return Optional.of(bufferedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empty;
    }
}
