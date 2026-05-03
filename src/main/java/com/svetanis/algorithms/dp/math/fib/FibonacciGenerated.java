class FibonacciGenerated {
    public int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}

		int fib1 = 1;
		int fib2 = 1;
		int fib = 1;
		for (int i = 3; i <= n; i++) {
			fib = fib1 + fib2;
			fib1 = fib2;
			fib2 = fib;
		}
		return fib;
    }
}