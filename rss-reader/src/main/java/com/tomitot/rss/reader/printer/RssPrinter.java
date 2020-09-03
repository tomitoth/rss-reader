package com.tomitot.rss.reader.printer;

import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndFeed;

@Component
public class RssPrinter {

	private ChannelPrinter channelPrinter;
	private EntryPrinter entryPrinter;

	public RssPrinter(ChannelPrinter channelPrinter, EntryPrinter entryPrinter) {
		this.channelPrinter = channelPrinter;
		this.entryPrinter = entryPrinter;
	}

	public void printRss(SyndFeed feed, boolean isArtConvertionNeeded) {
		channelPrinter.printChannel(feed, isArtConvertionNeeded);
		feed.getEntries().stream().forEach(entry -> entryPrinter.printEntry(entry, isArtConvertionNeeded));
	}


}
