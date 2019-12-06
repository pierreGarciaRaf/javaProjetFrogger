package gameCommons;

public class Case {
	public final int absc;
	public final int ord;

	/**
	 * represents a postion, can't be modified
	 * @param absc horizontal attribute.
	 * @param ord vertical attribute.
	 */
	public Case(int absc, int ord) {
		super();
		this.absc = absc;
		this.ord = ord;
	}

	/**
	 * equality definition. (absc == absc && ord == ord)
	 * @param Object to transform and compare to case.
	 */
	public boolean equals(Object o) {
		if (getClass() == o.getClass()) {
			Case obj = (Case) o;
			return this.absc == obj.absc && this.ord == obj.ord;
		} else {
			return false;
		}
	}
}
