package graphics;

import javax.swing.JButton;

import model.pieces.Piece;

@SuppressWarnings("serial")
public class OurButton extends JButton {
	int i;
	int j;
	Piece x; 
	
	public Piece getPiece() {
		return x;
	}

	public void setPiece(Piece x) {
		this.x = x;
	}

	public OurButton(String string) {
		super();
		this.setName(string);
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void setI(int i) {
		this.i = i;
	}

	public void setJ(int j) {
		this.j = j;
	}
}