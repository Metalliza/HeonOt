package io.github.lee0701.heonot.inputmethod.event;

public class InputCharEvent extends Event {
	private Object character;

	public InputCharEvent(Object character) {
		this.character = character;
	}

	public Object getCharacter() {
		return character;
	}
}
