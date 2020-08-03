package com.tomitot.rss.reader;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.tomitot.rss.reader.config.Config;
import com.tomitot.rss.reader.printer.RssPrinter;

public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		SimpleCommandLinePropertySource arguments = new SimpleCommandLinePropertySource(args);

		try(var context = new AnnotationConfigApplicationContext(Config.class);) {
			context.getEnvironment().getPropertySources().addFirst(arguments);

			RssReader rssReader = context.getBean(RssReader.class);
			SyndFeed feed = rssReader.read();
			RssPrinter rssPrinter = context.getBean(RssPrinter.class);
			rssPrinter.printRss(feed);
		} catch (IllegalArgumentException | FeedException | IOException e) {
			LOGGER.error(e.getMessage());
		}

	}

}
