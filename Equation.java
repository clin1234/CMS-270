public class Equation {

	// data members
	private int lefthandside, righthandside;
	private char operation, unknown;
	private double valueOfUnknown;

	// no-args constructor
	public Equation() {
		lefthandside = 0;
		righthandside = 0;
		operation = ' ';
		unknown = ' ';
	}

	// constructor
	public Equation(String eq) {
		String[] arr = eq.split(" ");

		lefthandside = Integer.parseInt(arr[0]);
		righthandside = Integer.parseInt(arr[4]);
		operation = arr[1].charAt(0);
		unknown = arr[2].charAt(0);
	}

	// solving method
	public void solve() {
		if (operation == '+') {
			valueOfUnknown = righthandside - lefthandside;
		} else if (operation == '-') {
			valueOfUnknown = lefthandside - righthandside;
		} else if (operation == '*') {
			valueOfUnknown = (double) righthandside / lefthandside;
		} else if (operation == '/') {
			valueOfUnknown = (double) lefthandside / righthandside;
		}
	}

	// getters and setters for data members
	public int getLefthandside() {
		return lefthandside;
	}

	public void setLefthandside(int lefthandside) {
		this.lefthandside = lefthandside;
	}

	public int getRighthandside() {
		return righthandside;
	}

	public void setRighthandside(int righthandside) {
		this.righthandside = righthandside;
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
	}

	public char getUnknown() {
		return unknown;
	}

	public void setUnknown(char unknown) {
		this.unknown = unknown;
	}

	public double getValueOfUnknown() {
		return valueOfUnknown;
	}

	public void setValueOfUnknown(double valueOfUnknown) {
		this.valueOfUnknown = valueOfUnknown;
	}

}
