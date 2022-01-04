package com.rocksoft.synacor


class Utils {
	static int decode(State state, int value) {
		if (value in state.registerAddrs) return state.register[value]
		value
	}
}
