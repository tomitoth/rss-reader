package com.tomitot.rss.reader.printer;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;

@Component
public class EntryPrinter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntryPrinter.class);

	private AsciiArtConverter artConverter;
	private EntryTitlePrinter entryTitlePrinter;

	public EntryPrinter(AsciiArtConverter artConverter, EntryTitlePrinter entryTitlePrinter) {
		super();
		this.artConverter = artConverter;
		this.entryTitlePrinter = entryTitlePrinter;
	}

	public void printEntry(SyndEntry entry, boolean isArtConvertionNeeded) {
		printTitleWithPublishedDate(entry);
		printDescription(entry);
		printLink(entry);
		if(isArtConvertionNeeded) {
			printImage(entry);
		}
		LOGGER.info("");
	}

	protected void printTitleWithPublishedDate(SyndEntry entry) {
		entryTitlePrinter.printEntry(entry);
	}

	protected void printDescription(SyndEntry entry) {
		String description = entry.getDescription().getValue();
		if(description != null && !description.isBlank() ) {
			LOGGER.info(description);
		}
	}

	protected void printLink(SyndEntry entry) {
		String link = entry.getLink();
		LOGGER.info(link);
	}

	protected void printImage(SyndEntry entry) {
		List<SyndEnclosure> enclosures = entry.getEnclosures();
		if(!enclosures.isEmpty()) {
			String url = enclosures.get(0).getUrl();
			try {
				LOGGER.info(artConverter.getImageArt(url));
			} catch (IOException e) {
				LOGGER.error("Error 404 - " + url);
			}
		}
	}

}
