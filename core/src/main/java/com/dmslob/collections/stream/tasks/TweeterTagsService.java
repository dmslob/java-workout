package com.dmslob.collections.stream.tasks;

import java.util.*;
import java.util.stream.Collectors;

public class TweeterTagsService {

	public Set<String> findAndSortTagsByFrequency(List<String> tweets) {
		final Map<String, Long> tweetToCountMap = tweets.stream()
				.flatMap(lineWithTweets -> Arrays.stream(lineWithTweets.split(" ")))
				.filter(tweet -> tweet.matches("#\\w+"))
				//.filter(tweet -> tweet.startsWith("#"))
				.collect(Collectors.groupingBy(tweet -> tweet, Collectors.counting()));

		final Map<String, Long> sortedByCount = tweetToCountMap.entrySet()
				.stream()
				.sorted((Map.Entry.<String, Long> comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap::new));

		return sortedByCount.keySet();
	}
}
