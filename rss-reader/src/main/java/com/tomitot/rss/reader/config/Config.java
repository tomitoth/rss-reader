package com.tomitot.rss.reader.config;

import java.awt.Font;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rometools.rome.io.SyndFeedInput;

import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.BestCharacterFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.AsciiToStringConverter;

@Configuration
@ComponentScan(value = "com.tomitot.rss.reader")
public class Config {

	@Bean
	public AsciiImgCache imgCache() {
		return AsciiImgCache.create(new Font("Arial", Font.BOLD, 8));
	}

	@Bean
	public BestCharacterFitStrategy fitStrategy() {
		return new StructuralSimilarityFitStrategy();
	}

	@Bean
	public AsciiToStringConverter stringConverter() {
		return new AsciiToStringConverter(imgCache(), fitStrategy());
	}

	@Bean
	public SyndFeedInput syndFeedInput() {
		return new SyndFeedInput();
	}

}