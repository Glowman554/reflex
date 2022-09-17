package gq.glowman554.reflex.types;

import java.util.Arrays;

public class ReflexBooleanArray implements ReflexArray<Boolean> {
	private final boolean[] arr;

	public ReflexBooleanArray(boolean[] arr) {
		this.arr = arr;
	}

	@Override
	public Boolean get(int idx) {
		return this.arr[idx];
	}

	@Override
	public String toString() {
		return "ReflexBooleanArray{" +
				"arr=" + Arrays.toString(arr) +
				'}';
	}
}
