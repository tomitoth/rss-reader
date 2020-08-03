package com.tomitot.rss.reader.printer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEntry;

@Component
public class EntryTitlePrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntryTitlePrinter.class);

	public void printEntry(SyndEntry entry) {
		DateFormat dateFormat = new SimpleDateFormat();
		LOGGER.info(entry.getTitle() + " | " + dateFormat.format(entry.getPublishedDate()));
	}

}
