package com.rocksoft.synacor


class Loader {
	static State load() {
		State state = new State()
		FileInputStream binstr =  new FileInputStream(new File('src/main/resources/challenge.bin'))
		while (binstr.available() != 0) {
			byte[] buffer = new byte[2]
			binstr.read(buffer)
			int lower = buffer[0] < 0 ? 256 + buffer[0] : buffer[0];
			int upper = buffer[1] < 0 ? 256 + buffer[1] : buffer[1];
			state.memory << (upper << 8) + lower;
		}
		state
	}
}
