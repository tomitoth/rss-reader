package com.tomitot.rss.reader.printer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rometools.rome.feed.synd.SyndFeed;

@Component
public class RssPrinter {

	@Autowired
	private ChannelPrinter channelPrinter;

	@Autowired
	private EntryPrinter entryPrinter;

	public void printRss(SyndFeed feed) {
		channelPrinter.printChannel(feed);
		feed.getEntries().stream().forEach(entry -> entryPrinter.printEntry(entry));
	}


}
