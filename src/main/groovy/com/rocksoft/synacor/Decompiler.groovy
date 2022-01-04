package com.rocksoft.synacor


class Decompiler {
	State state
	Decompiler() {
		state = new State()
	}

	void decompile() {
		boolean wasOut = false
		List<String> currOut = []
		int lastOut = 0
		int marker = 0
		StringBuilder dump = new StringBuilder()
		while (marker < state.memory.size()) {
			Integer instMarker = marker
			Integer curr = state.memory[marker++]
			if (curr >= Instruction.values().length) continue
			Instruction instruction = Instruction.values()[curr]
			if (instruction.OUT.ordinal() == instruction.ordinal()) {
				if (!wasOut) lastOut = marker-1
				currOut << (state.memory[marker++] as char).toString()
				wasOut = true
				continue
			}
			if (wasOut && instruction.OUT.ordinal() != instruction.ordinal()) {
				dump.append(String.format("%-15d %-15s %-15s\n", lastOut, "out", currOut.join('').replaceAll(/[\t\n\r]/, ' ')))
				currOut.clear()
				wasOut = false
			}
			List<String> args = []
			instruction.args.times {
				int val = state.memory[marker++]
				args <<  (val in state.registerAddrs ? "r${state.registerAddrs.indexOf(val)+1}" : val.toString())
			}
			String line = String.format("%-15d %-15s %-15s\n", instMarker, instruction.toString().toLowerCase(), args.join(' '))
			dump.append(line)
		}
		new File('src/main/resources/synacor.txt').write(dump.toString())
	}

	static void main(String[] args) {
		new Decompiler().decompile()
	}
}
