package gq.glowman554.reflex.types;

import java.util.Arrays;

public class ReflexLongArray implements ReflexArray<Long>
{
	private final long[] arr;

	public ReflexLongArray(long[] arr)
	{
		this.arr = arr;
	}

	@Override
	public Long get(int idx)
	{
		return this.arr[idx];
	}

	@Override
	public String toString()
	{
		return "ReflexIntArray{" + "arr=" + Arrays.toString(arr) + '}';
	}

	@Override
	public int len()
	{
		return arr.length;
	}
}
