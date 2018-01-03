package tcp.objectserver;

public class MyCalculation extends WorkRequest {
	private int n;
	private int[] values;

	public MyCalculation(int n) {
		this.n = n;
		values = new int[n];
	}

	public Object execute() {
		for (int i = 0; i < values.length; i++)
			values[i] = i * i;
		return values;
	}
}
