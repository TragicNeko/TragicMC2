package tragicneko.tragicmc.util;

public class Tuple<L, R> {

	private final L left;
	private final R right;

	public Tuple(L left, R right)
	{
		this.left = left;
		this.right = right;
	}

	public L getLeft() { return this.left; }
	public R getRight() { return this.right; }
}
