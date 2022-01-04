package com.rocksoft.synacor
import static Utils.decode

enum Instruction {
	HALT({ state, args -> System.exit 0 }, 0),
	SET({ state, args -> state.register[args[0]] = decode(state, args[1]); state.register[args[0]] }, 2),
	PUSH({ state, args -> state.stack << decode(state, args[0]); state.stack.toString() }, 1),
	POP({ state, args -> state.register[args[0]] = state.stack.pop(); state.stack.toString() }, 1),
	EQ({ state, args -> state.register[args[0]] = decode(state, args[1]) == decode(state, args[2]) ? 1 : 0; state.register[args[0]] }, 3),
	GT({ state, args -> state.register[args[0]] = decode(state, args[1]) > decode(state, args[2]) ? 1 : 0; state.register[args[0]] }, 3),
	JMP({ state, args -> state.marker = decode(state, args[0]) }, 1),
	JT({ state, args -> state.marker = decode(state, args[0]) != 0 ? decode(state, args[1]) : state.marker; state.marker }, 2),
	JF({ state, args -> state.marker = decode(state, args[0]) == 0 ? decode(state, args[1]) : state.marker; state.marker }, 2),
	ADD({ state, args -> state.register[args[0]] = (decode(state,args[1]) + decode(state, args[2])) % 32768; state.register[args[0]]}, 3),
	MULT({ state, args -> state.register[args[0]] = (decode(state, args[1]) * decode(state, args[2])) % 32768; state.register[args[0]] }, 3),
	MOD({ state, args -> state.register[args[0]] = (decode(state, args[1]) % decode(state, args[2])) % 32768; state.register[args[0]] }, 3),
	AND({ state, args -> state.register[args[0]] = (decode(state, args[1]) & decode(state, args[2])) & 32767; state.register[args[0]] }, 3),
	OR({ state, args -> state.register[args[0]] = (decode(state, args[1]) | decode(state, args[2])) & 32767; state.register[args[0]] }, 3),
	NOT({ state, args -> state.register[args[0]] = ~decode(state, args[1]) & 32767; state.register[args[0]] }, 2),
	RMEM({ state, args -> state.register[args[0]] = state.memory[decode(state, args[1])]; state.register[args[0]] }, 2),
	WMEM({ state, args -> state.memory[decode(state, args[0])] = decode(state, args[1]); state.register[args[0]] }, 2),
	CALL({ state, args -> state.stack << state.marker; state.marker = decode(state, args[0]); state.stack.toString() }, 1),
	RET({ state, args -> if (!state.stack) System.exit 0; state.marker = state.stack.pop(); state.marker }, 0),
	OUT({ state, args -> print (decode(state, args[0]) as char); (args[0] as char) }, 1),
	IN({ State state, args -> if (state.steps.available() != 0) state.register[args[0]] = state.steps.read() else state.register[args[0]] = System.in.read() }, 1),
	NOOP({ state, args -> }, 0)


	Instruction(Closure operation, int args) {
		this.operation = operation
		this.args = args
	}

	Closure operation
	int args
}