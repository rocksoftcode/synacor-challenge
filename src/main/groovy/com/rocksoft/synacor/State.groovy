package com.rocksoft.synacor


class State {
	List<Integer> memory = []
	List<Integer> registerAddrs = [32768,32769,32770,32771,32772,32773,32774,32775]
	Map<Integer, Integer> register = registerAddrs.collectEntries { [(it): 0] }
	Stack stack = []
	int marker = 0
	List<String> debug = []
	FileInputStream steps = new FileInputStream("src/main/resources/steps.txt")
}
