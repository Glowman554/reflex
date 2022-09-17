package gq.glowman554.reflex.types;

import java.util.Arrays;

public class ReflexDoubleArray implements ReflexArray<Double> {
	private final double[] arr;

	public ReflexDoubleArray(double[] arr) {
		this.arr = arr;
	}

	@Override
	public Double get(int idx) {
		return this.arr[idx];
	}

	@Override
	public String toString() {
		return "ReflexDoubleArray{" +
				"arr=" + Arrays.toString(arr) +
				'}';
	}
}
