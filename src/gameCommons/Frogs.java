package gameCommons;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Frogs extends ArrayList<IFrog> {

	Frogs() {
		super(0);
	}

	public Case lastPos() {
		int minIdx = 0;
		int idx = 0;
		int minOrd = this.get(0).getPosition().ord;
		for (IFrog frog : this) {

			if (frog.getPosition().ord < minOrd) {
				minOrd = frog.getPosition().ord;
				minIdx = idx;
			}
			idx += 1;
		}
		return this.get(minIdx).getPosition();
	}
}
