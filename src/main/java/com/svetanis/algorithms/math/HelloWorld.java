package com.svetanis.algorithms.math;

import java.util.function.Function;

// 2667. Create Hello World Function

public final class HelloWorld {

	public static Function<Object[], String> createHelloWorld() {
		return (Object[] args) -> "Hello World";
	}

	public static void main(String[] args) {
		Function<Object[], String> hwf = createHelloWorld();
		String greeting = hwf.apply(new Object[] {});
		System.out.println(greeting);
	}
}