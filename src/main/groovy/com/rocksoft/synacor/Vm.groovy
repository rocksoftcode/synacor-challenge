package com.rocksoft.synacor

class Vm {
	State state

	Vm(boolean overrideTeleport = false) {
		state = Loader.load()
		if (overrideTeleport) {
			new Overrides(state).teleport()
		}
	}

	void run() {
		while (true) {
			Integer curr = state.memory[state.marker++]
			if (curr > 21) {
				System.err.println("Encountered invalid instruction $curr at ${state.marker - 1}"); return
			}

			Instruction instruction = Instruction.values()[curr]
			int[] args = new int[instruction.args]
			instruction.args.times {args[it] = state.memory[state.marker++]}
			def debug = "${state.marker - instruction.args - 1} ${instruction.toString()} ${args.collect {it in state.registerAddrs ? 'r' + (state.registerAddrs.indexOf(it) + 1) : it}}"
			debug += " ${instruction.operation(state, args)}"
			state.debug << debug
		}
	}

	static void main(String[] args) {
		new Vm(args.length >= 1 && args[0] == '--override-teleport')
				.run()
	}
}
