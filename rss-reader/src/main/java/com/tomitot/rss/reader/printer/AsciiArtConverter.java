package com.tomitot.rss.reader.printer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.korhner.asciimg.image.converter.AsciiToStringConverter;

@Component
public class AsciiArtConverter {

	@Autowired
	private AsciiToStringConverter stringConverter;

	public String getImageArt(String url) throws IOException {
		BufferedImage portraitImage = ImageIO.read(new URL(url));
		return stringConverter.convertImage(portraitImage).toString();
	}

}
