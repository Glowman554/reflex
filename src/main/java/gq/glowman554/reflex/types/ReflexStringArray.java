package gq.glowman554.reflex.types;

import java.util.Arrays;

public class ReflexStringArray implements ReflexArray<String> {
	private final String[] arr;

	public ReflexStringArray(String[] arr) {
		this.arr = arr;
	}

	@Override
	public String get(int idx) {
		return this.arr[idx];
	}

	@Override
	public String toString() {
		return "ReflexStringArray{" +
				"arr=" + Arrays.toString(arr) +
				'}';
	}

	@Override
	public int len()
	{
		return arr.length;
	}
}
