package com.tomitot.rss.reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

@Component
public class RssReader {

	@Value("${input}")
	private String input;

	@Autowired
	private SyndFeedInput syndFeedInput;

	public SyndFeed read() throws IllegalArgumentException, FeedException, IOException {

		if(input == null) {
			throw new IllegalArgumentException("Input is not defined");
		}

		URL	feedUrl = input.startsWith("http") ? new URL(input) : new URL("file:" + input);
		return syndFeedInput.build(new InputStreamReader(feedUrl.openStream()));
	}

}
