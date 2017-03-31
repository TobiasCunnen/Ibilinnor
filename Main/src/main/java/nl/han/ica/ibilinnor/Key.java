package nl.han.ica.ibilinnor;

public class Key {
	
	private char key;
	private boolean pressedKey;

	public Key(char key) {
		this.key=key;
		pressedKey=false;
	}

	public boolean isPressedKey() {
		return pressedKey;
	}

	public void setPressedKey(boolean pressedKey) {
		this.pressedKey = pressedKey;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}
}
