package com.rocksoft.synacor.solution

import java.util.stream.Collectors
import java.util.stream.StreamSupport


class Coins {
	static void main(String[] args) {
		def coins = [2d, 3d, 5d, 7d, 9d]
		def labels = [2d: 'red', 3d: 'corroded', 5d: 'shiny', 7d: 'concave', 9d: 'blue']
		def all = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(new PermutationGenerator(coins), Spliterator.ORDERED), false)
				.collect(Collectors.toCollection(ArrayList::new))
		def solution = all.findAll {(it[0] + it[1] * Math.pow(it[2], 2) + Math.pow(it[3], 3) - it[4]) == 399}[0]
		println solution.collect { labels[it] }.join(', ')
	}
}
