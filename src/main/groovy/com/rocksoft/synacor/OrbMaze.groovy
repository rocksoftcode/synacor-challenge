package com.rocksoft.synacor


class OrbMaze {
	static def grid = [
			['*', '8', '-', '1'],
			['4', '*', '11', '*'],
			['+', '4', '-', '18'],
			['', '-', '9', '*']
	]

	static List<String> answer
	static Map<String,?> initial = [
			x      : 0,
			y      : 3,
			orb    : 22,
			op     : '',
			history: []
	]
	static LinkedList<Map> queue = []
	static void main(String[] args) {
		queue << initial
		while (queue) {
			def val = queue.remove(0).clone()
			def x = val.x
			def y = val.y
			def orb = val.orb
			def op = val.op
			def history = val.history

			String current = grid[y][x]
			if (current ==~ /\d+/) {
				if (op == '+') {
					orb += current.toInteger()
				} else if (op == '*') {
					orb *= current.toInteger()
				} else if (op == '-') {
					orb -= current.toInteger()
				}
				op = ''
			} else {
				op = current
			}

			if (x == 3 && y == 0) {
				if (orb == 30) {
					answer = history
					break
				}
			} else if (orb > 0 && orb < 75 && history.size() < 12) {
				if (y > 0) {
					queue.addFirst([x: x, y: y - 1, orb: orb, op: op, history: history + ['north']])
				}
				if (x < 3) {
					queue.addFirst([x: x + 1, y: y, orb: orb, op: op, history: history + ['east']])
				}
				if (y < 3 && x > 0) {
					queue.addFirst([x: x, y: y + 1, orb: orb, op: op, history: history + ['south']])
				}
				if (x > 0 && y < 3) {
					queue.addFirst([x: x - 1, y: y, orb: orb, op: op, history: history + ['west']])
				}
			}
		}
		println answer.join(' ')
	}
}
