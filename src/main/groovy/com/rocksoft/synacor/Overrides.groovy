package com.rocksoft.synacor


class Overrides {
	State state

	Overrides(State state) {
		this.state = state
	}

	void teleport() {
		state.memory[5451] = Instruction.JT.ordinal()
		state.memory[5480] = Instruction.SET.ordinal()
		state.memory[5481] = state.registerAddrs[7]
		state.memory[5482] = 25734
		state.memory[5483] = Instruction.SET.ordinal()
		state.memory[5484] = state.registerAddrs[0]
		state.memory[5485] = 6
		state.memory[5486] = Instruction.SET.ordinal()
		state.memory[5487] = state.registerAddrs[1]
		state.memory[5488] = 1
		state.memory[5489] = Instruction.NOOP.ordinal()
		state.memory[5490] = Instruction.NOOP.ordinal()
		state.memory[5491] = Instruction.EQ.ordinal()
		state.memory[5492] = state.registerAddrs[1]
		state.memory[5493] = state.registerAddrs[0]
		state.memory[5494] = 6
		state.memory[5495] = Instruction.JF.ordinal()
		state.memory[5496] = state.registerAddrs[1]
		state.memory[5497] = 5579
	}
}
