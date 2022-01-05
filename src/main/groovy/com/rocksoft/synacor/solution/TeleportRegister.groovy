package com.rocksoft.synacor.solution


class TeleportRegister {

	public static final long UPPER = 32768

	static long f(long n, long r7) {
		long a = powMod(r7 + 1, n, UPPER)
		long mod = powMod(r7 + 1, n, r7 * UPPER)
		long b = (mod ?: r7) - 1
		return ((a * ((r7 + 1) * (r7 + 1) + r7) + b * (2 * r7 + 1) / r7) as long) % UPPER
	}

	static long powMod(long base, long exponent, long mod, long res = 1) {
		for (long i = 0; i < exponent; i++) res = ((res * base) as long) % mod
		return res
	}

	static void main(String[] args) {
		for (long r7 = 1; r7 < UPPER; r7++) {
			if (f(f(r7, r7), r7) == 6) {
				println r7
				return
			}
		}
	}
}