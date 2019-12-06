package gameCommons;

public class Case {
	public final int absc;
	public final int ord;

	public Case(int absc, int ord) {
		super();
		this.absc = absc;
		this.ord = ord;
	}

	public boolean equals(Object o) {
		if (getClass() == o.getClass()) {
			Case obj = (Case) o;
			return this.absc == obj.absc && this.ord == obj.ord;
		} else {
			return false;
		}
	}
}
