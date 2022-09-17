package gq.glowman554.reflex.types;

import java.util.Arrays;

public class ReflexCustomArray<T> implements ReflexArray<T> {
	private final Instantiate instantiate;
	private T[] arr;

	private ReflexCustomArray(Instantiate instantiate) {
		this.instantiate = instantiate;
	}

	@SuppressWarnings("rawtypes")
	public static ReflexCustomArray from(Instantiate i) {
		return new ReflexCustomArray<>(i);
	}

	@Override
	public T get(int idx) {
		return this.arr[idx];
	}

	public Instantiate getInstantiate() {
		return instantiate;
	}

	public void array(T[] a) {
		this.arr = a;
	}

	@Override
	public String toString() {
		return "ReflexCustomArray{" +
				"instantiate=" + instantiate +
				", arr=" + Arrays.toString(arr) +
				'}';
	}

	public interface Instantiate {
		Object init();
	}

	@Override
	public int len()
	{
		return arr.length;
	}
}
