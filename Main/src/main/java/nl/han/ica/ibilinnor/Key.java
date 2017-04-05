package nl.han.ica.ibilinnor;

/**
 * De key klasse wordt gebruikt om de toetsen die ingedrukt kunnen worden aan te
 * maken en daarnaast wordt ook bijgehouden of die toets ingedrukt is. De keys
 * worden in de klasse Character aangemaakt en daar in een arraylist opgeslagen.
 * Ook wordt daar de waarde pressedKey verandert (met behulp van een setter) als
 * de toets wordt ingedrukt.
 * 
 * @author Tobias
 *
 */
public class Key {

	private char key;
	private boolean pressedKey;

	public Key(char key) {
		this.key = key;
		pressedKey = false;
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
