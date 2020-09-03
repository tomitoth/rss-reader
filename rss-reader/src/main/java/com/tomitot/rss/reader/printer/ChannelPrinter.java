package com.tomitot.rss.reader.printer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndFeed;

@Component
public class ChannelPrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChannelPrinter.class);

	@Autowired
	private AsciiArtConverter artConverter;

	public void printChannel(SyndFeed feed, boolean isArtConvertionNeeded) {
		LOGGER.info("Channel Title: " + feed.getTitle());
		LOGGER.info("Channel Description: " + feed.getDescription());
		DateFormat dateFormat = new SimpleDateFormat();
		LOGGER.info("Published: " + dateFormat.format(feed.getPublishedDate()));

		if(isArtConvertionNeeded) {
			String url = feed.getImage().getUrl();
			try {
				LOGGER.info(artConverter.getImageArt(url));
			} catch (IOException e) {
				LOGGER.error("Image: Error 404 - " + url);
			}
		} else {
			LOGGER.info("");
		}
	}

}
