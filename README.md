# rss-reader
Basic command line rss reader Spring application

com.tomitot:rss-reader has dependency to io.korhner:asciimg which is not in Maven central
repository. asciimg project source can be found in this repository.

Run com.tomitot.rss.reader.App and define -Dinput=yourRssFeed VM argument.
Input can be link or path to RSS file.

Optionally -DconvertToAscii=true VM argument can be provided, which will convert
the images in the feed into ASCII arts. By default its value is false.

Example useage #1:
-Dinput=https://index.hu/24ora/rss/ -DconvertToAscii=true

Example useage #2:
-Dinput=c:/temp/rss.xml -DconvertToAscii=true
