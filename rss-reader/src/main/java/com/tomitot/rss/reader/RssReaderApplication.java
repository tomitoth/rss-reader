package com.tomitot.rss.reader;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.tomitot.rss.reader.config.Config;
import com.tomitot.rss.reader.printer.RssPrinter;

public class RssReaderApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(RssReaderApplication.class);
	private static final String INPUT_HELP_TEXT = "link or path to rss file \n "
			+ "e.g. https://index.hu/24ora/rss/ or c:/temp/rss.xml";
	private static final String GENERATE_ASCII_ART_FROM_IMAGES = "generate ascii art from images";

	public static void main(String[] args) {

		Options options = new Options();
		options.addOption("h", "help", false, "");
		options.addRequiredOption("i", "input", true, INPUT_HELP_TEXT);
		options.addOption("ascii", "convertToAscii", false, GENERATE_ASCII_ART_FROM_IMAGES);

		try (var context = new AnnotationConfigApplicationContext(Config.class);) {
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(options, args);
			if(cmd.hasOption("h")) {
				printHelp(options);
				return;
			}
			String input = cmd.getOptionValue("i");
			RssReader rssReader = context.getBean(RssReader.class);
			SyndFeed feed = rssReader.read(input);
			RssPrinter rssPrinter = context.getBean(RssPrinter.class);
			boolean isArtConvertionNeeded = cmd.hasOption("ascii");
			rssPrinter.printRss(feed, isArtConvertionNeeded);
		} catch (IllegalArgumentException | FeedException | IOException | ParseException e) {
			printHelp(options);
			LOGGER.error(e.getMessage());
		}

	}

	private static void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("RSS reader", options);
	}

}
