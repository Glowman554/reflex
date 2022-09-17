package gq.glowman554.reflex.types;

import java.util.Arrays;

public class ReflexIntArray implements ReflexArray<Integer> {
	private final int[] arr;

	public ReflexIntArray(int[] arr) {
		this.arr = arr;
	}

	@Override
	public Integer get(int idx) {
		return this.arr[idx];
	}

	@Override
	public String toString() {
		return "ReflexIntArray{" +
				"arr=" + Arrays.toString(arr) +
				'}';
	}
}
