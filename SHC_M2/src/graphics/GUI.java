package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;

public class GUI implements ActionListener {
	//
	// PanelElBoard.validate();
	// PanelElBoard.repaint();
	JPanel PanelElDead1 = new JPanel();
	JPanel PanelElDead2 = new JPanel();
	Piece TargetElTeleport;
	int TETI;
	int TETJ;

	boolean Hy_Resurrect;
	boolean Hy_teleport;

	Game le3ba;
	OurButton t;
	OurButton s1;
	OurButton s2;
	JFrame Elle3ba;
	JPanel PanelElBoard = new JPanel();
	JPanel PanelEsmElPlayer = new JPanel();
	int p;
	int q;
	int u;
	int ELI;
	int ELJ;
	JButton v;
	JProgressBar pb1;
	JProgressBar pb2;
	String esmPlayer1;
	String esmPlayer2;
	String b;
	Piece ElTarget;
	Point newPos = new Point(0, 0);
	Direction Eldirection;
	boolean e5tar_trigger;
	boolean e5tar_direction;
	boolean hyst5dm_power;
	boolean e5tar_target;
	boolean e5tar_newPos;
	boolean wa7d_keseb;
	OurButton[][] ArrayElButtons = new OurButton[7][6];
	JButton[][] ArrayElDirections = new JButton[3][3];
	OurButton[][] ArrayElDead1Buttons = new OurButton[2][5];
	OurButton[][] ArrayElDead2Buttons = new OurButton[2][5];
	Piece[][] arrayEldead1 = new Piece[2][5];
	Piece[][] arrayEldead2 = new Piece[2][5];

	Image Soret_Super = null;
	Image Soret_Medic = null;
	Image Soret_Ranged = null;
	Image Soret_Armored = null;
	Image Soret_Unarmored = null;
	Image Soret_Speedster = null;
	Image Soret_Tech = null;
	Image Soret_Sidekick = null;
	Image Soret_Right = null;
	Image Soret_Left = null;
	Image Soret_Up = null;
	Image Soret_Down = null;
	Image Soret_UpRight = null;
	Image Soret_UpLeft = null;
	Image Soret_DownRight = null;
	Image Soret_DownLeft = null;

	int TypeElle3ba;
	Point s;
	Point m;
	boolean ElcompHyhagm;
	Direction DirectionElhgoom;

	public static void main(String[] args) {
		// Should the methods be ?
		// hover buttons should be made
		new GUI().StartGame();
	}

	public void StartGame() {
		// How to display the Background image? not working
		// JLabel lblimage = new JLabel(new ImageIcon("BackGroundImage.jpg"));
		// Frame.getContentPane().add(lblimage, BorderLayout.CENTER);
		TypeElle3ba = Integer.parseInt(JOptionPane
				.showInputDialog("Please enter 1 to play a Multiplayer game , or 2 to play against the Computer !"));
		if (TypeElle3ba == 1) {
			esmPlayer1 = JOptionPane.showInputDialog("Welcome First Player ! , Please Enter your name");
			esmPlayer2 = JOptionPane.showInputDialog("Welcome Second Player ! , Please Enter your name");
		} else if (TypeElle3ba == 2) {
			esmPlayer1 = JOptionPane.showInputDialog("Welcome to the Game ! , Please Enter your name");
			esmPlayer2 = "Computer";
		}
		Player x = new Player(esmPlayer1);
		Player y = new Player(esmPlayer2);
		le3ba = new Game(x, y);

		Elle3ba = new JFrame("SHC");

		Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
		Elle3ba.setIconImage(im);

		Elle3ba.getContentPane().setLayout(null);
		// Elle3ba.getContentPane().setBackground(new Color(0, 0, 0));
		Elle3ba.setBounds(0, 0, 1370, 735);

		Elle3ba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BuildAll(le3ba, Elle3ba);
		Elle3ba.setVisible(true);
	}

	public void BuildAll(Game le3ba, JFrame Elle3ba) {
		// panel.setPreferredSize(new Dimension(1370, 735));
		Elle3ba.getContentPane().removeAll();
		Elle3ba.validate();
		Elle3ba.repaint();

		ImagePanel panel = new ImagePanel("BackGroundImage.jpg");

		panel.setBounds(00, 0, 1370, 735);
		Elle3ba.getContentPane().add(panel);
		Elle3ba.setContentPane(panel);
		Elle3ba.getContentPane().setLayout(null);
		Elle3ba.getContentPane().setBackground(new Color(7, 7, 7));

		BuildPanelElBoard(le3ba, Elle3ba);
		BuildPanelEsmElPlayer(le3ba, Elle3ba);
		if (le3ba.getPlayer1().getDeadCharacters().size() > 0) {
			BuildPanelElDead1(le3ba, Elle3ba);
			PanelElDead1.setVisible(true);
		} else {
			PanelElDead1.setVisible(false);
		}
		if (le3ba.getPlayer2().getDeadCharacters().size() > 0) {
			BuildPanelElDead2(le3ba, Elle3ba);
			PanelElDead2.setVisible(true);
		} else {

			PanelElDead2.setVisible(false);
		}
		BuildElBars(le3ba, Elle3ba);
		BuildPanelElDirections(Elle3ba);
		BuildPanelUsePower(Elle3ba);

		Elle3ba.validate();
		Elle3ba.repaint();
	}

	public void BuildPanelElBoard(Game le3ba, JFrame Elle3ba) {
		PanelElBoard = new JPanel();
		PanelElBoard.setLayout(new GridLayout(7, 6));
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				t = new OurButton("t");
				t.addActionListener(this);
				t.setI(i);
				t.setJ(j);
				// 3mltha kda 3shan null exception
				ArrayElButtons[i][j] = t;
				BuildButton(t, le3ba);
				ArrayElButtons[i][j] = t;
				if (le3ba.getCellAt(i, j).getPiece() != null && le3ba.getCellAt(i, j).getPiece() instanceof SideKick) {
					t.setToolTipText("Owner is " + le3ba.getCellAt(i, j).getPiece().getOwner().getName() + ", Piece is "
							+ le3ba.getCellAt(i, j).getPiece());
				} else if (le3ba.getCellAt(i, j).getPiece() != null
						&& le3ba.getCellAt(i, j).getPiece() instanceof Armored
						&& ((Armored) le3ba.getCellAt(i, j).getPiece()).isArmorUp()) {
					t.setToolTipText("Owner is " + le3ba.getCellAt(i, j).getPiece().getOwner().getName() + ", Piece is "
							+ le3ba.getCellAt(i, j).getPiece() + ", Shield is Up");
				} else if (le3ba.getCellAt(i, j).getPiece() != null
						&& le3ba.getCellAt(i, j).getPiece() instanceof Armored
						&& ((Armored) le3ba.getCellAt(i, j).getPiece()).isArmorUp() == false) {
					t.setToolTipText("Owner is " + le3ba.getCellAt(i, j).getPiece().getOwner().getName() + ", Piece is "
							+ le3ba.getCellAt(i, j).getPiece() + ", Shield is Down");
				} else if (le3ba.getCellAt(i, j).getPiece() != null
						&& le3ba.getCellAt(i, j).getPiece() instanceof Speedster) {
					t.setToolTipText("Owner is " + le3ba.getCellAt(i, j).getPiece().getOwner().getName() + ", Piece is "
							+ le3ba.getCellAt(i, j).getPiece());
				} else if (le3ba.getCellAt(i, j).getPiece() != null
						&& ((ActivatablePowerHero) le3ba.getCellAt(i, j).getPiece()).isPowerUsed() == false) {
					t.setToolTipText("Owner is " + le3ba.getCellAt(i, j).getPiece().getOwner().getName() + ", Piece is "
							+ le3ba.getCellAt(i, j).getPiece() + ", It's Power is not used yet");
				} else if (le3ba.getCellAt(i, j).getPiece() != null
						&& ((ActivatablePowerHero) le3ba.getCellAt(i, j).getPiece()).isPowerUsed()) {
					t.setToolTipText("Owner is " + le3ba.getCellAt(i, j).getPiece().getOwner().getName() + ", Piece is "
							+ le3ba.getCellAt(i, j).getPiece() + ", It's Power is used");
				}
				PanelElBoard.add(t);
			}
		}
		PanelElBoard.setBounds(60, 40, 760, 600);
		PanelElBoard.setVisible(true);
		Elle3ba.getContentPane().add(PanelElBoard);
	}

	public void BuildPanelEsmElPlayer(Game le3ba, JFrame Elle3ba) {
		PanelEsmElPlayer.removeAll();
		PanelEsmElPlayer.setBounds(1050, 200, 100, 36);
		v = new JButton(le3ba.getCurrentPlayer().getName());
		PanelEsmElPlayer.add(v);
		Elle3ba.getContentPane().add(PanelEsmElPlayer);
	}

	public void BuildPanelElDead1(Game le3ba, JFrame Elle3ba) {
		PanelElDead1.removeAll();

		System.out.println("by rebuild panel el dead 1");
		Elle3ba.remove(PanelElDead1);
		Elle3ba.validate();
		Elle3ba.repaint();
		int r = 0;
		int s = 0;
		System.out.println("Size dead 1 howa " + le3ba.getPlayer1().getDeadCharacters().size());
		for (int y = 0; y < le3ba.getPlayer1().getDeadCharacters().size(); y++) {
			arrayEldead1[r][s] = le3ba.getPlayer1().getDeadCharacters().get(y);
			System.out.println("r1 is " + r + " and s1 is " + s);
			s++;
			if (s == 5 && r == 1) {
				break;
			}
			if (s == 5 && r == 0) {
				r++;
				s = 0;
			}
		}

		PanelElDead1.setLayout(new GridLayout(2, 5));
		PanelElDead1.setBounds(900, 50, 400, 100);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				s1 = new OurButton("s1");
				s1.addActionListener(this);
				s1.setI(i);
				s1.setJ(j);

				BuildDead1Button(s1, le3ba);
				s1 = ArrayElDead1Buttons[i][j];
				PanelElDead1.add(s1);

			}
		}
		Elle3ba.add(PanelElDead1);
		Elle3ba.validate();
		Elle3ba.repaint();

	}

	public void BuildPanelElDead2(Game le3ba, JFrame Elle3ba) {

		PanelElDead2.removeAll();
		System.out.println("by rebuild panel el dead 2");

		Elle3ba.remove(PanelElDead2);
		Elle3ba.validate();
		Elle3ba.repaint();
		int r = 0;
		int s = 0;
		System.out.println("Size dead 2 howa " + le3ba.getPlayer2().getDeadCharacters().size());
		for (int y = 0; y < le3ba.getPlayer2().getDeadCharacters().size(); y++) {
			arrayEldead2[r][s] = le3ba.getPlayer2().getDeadCharacters().get(y);
			System.out.println("r2 is " + r + " and s2 is " + s);
			s++;
			if (s == 5 && r == 1) {
				break;
			}
			if (s == 5 && r == 0) {
				r++;
				s = 0;
			}

		}

		PanelElDead2.setLayout(new GridLayout(2, 5));

		PanelElDead2.setBounds(900, 560, 400, 100);

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {

				s2 = new OurButton("s2");
				s2.addActionListener(this);
				s2.setI(i);
				s2.setJ(j);
				BuildDead2Button(s2, le3ba);
				s2 = ArrayElDead2Buttons[i][j];
				PanelElDead2.add(s2);
			}
		}
		Elle3ba.add(PanelElDead2);
		Elle3ba.validate();
		Elle3ba.repaint();
		// PanelElDead2.setVisible(false);
	}

	public void BuildElBars(Game le3ba, JFrame Elle3ba) {

		Player x = le3ba.getPlayer1();
		Player y = le3ba.getPlayer2();
		pb1 = new JProgressBar(JProgressBar.VERTICAL);
		pb2 = new JProgressBar(JProgressBar.VERTICAL);

		pb1.setMinimum(0);
		pb1.setMaximum(8);
		pb1.setBackground(new Color(255, 250, 150));
		pb1.setForeground(new Color(150, 0, 24));

		pb2.setMinimum(0);
		pb2.setMaximum(8);
		pb2.setBackground(new Color(255, 250, 150));
		pb2.setForeground(new Color(150, 0, 24)); // Carmine

		pb1.setValue(x.getPayloadPos());
		pb1.setToolTipText(x.getName() + "'s score is " + x.getPayloadPos());
		pb2.setValue(y.getPayloadPos());
		pb2.setToolTipText(y.getName() + "'s score is " + y.getPayloadPos());

		JButton r1 = new JButton("P1");
		JButton r2 = new JButton("P2");
		r1.setBounds(0, 500, 50, 30);
		r2.setBounds(830, 500, 50, 30);
		pb1.setBounds(10, 200, 30, 300);
		pb2.setBounds(840, 200, 30, 300);
		Elle3ba.getContentPane().add(pb1);
		Elle3ba.getContentPane().add(pb2);
		Elle3ba.getContentPane().add(r1);
		Elle3ba.getContentPane().add(r2);
	}

	public void BuildPanelElDirections(JFrame Elle3ba) {
		JPanel PanelElDirections = new JPanel();
		PanelElDirections.setLayout(new GridLayout(3, 3));
		PanelElDirections.setBounds(1000, 270, 200, 200);

		PanelElDirections.setOpaque(false);

		ArrayElDirections[0][0] = new JButton();
		ArrayElDirections[0][0].setIcon(new ImageIcon("Up_left_arrow.jpg"));
		ArrayElDirections[0][0].addActionListener(this);
		ArrayElDirections[0][0].setActionCommand("Up-Left");
		ArrayElDirections[0][0].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[0][0]);
		ArrayElDirections[0][1] = new JButton();
		ArrayElDirections[0][1].setIcon(new ImageIcon("Up_arrow.jpg"));
		ArrayElDirections[0][1].addActionListener(this);
		ArrayElDirections[0][1].setActionCommand("Up");
		ArrayElDirections[0][1].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[0][1]);
		ArrayElDirections[0][2] = new JButton();
		ArrayElDirections[0][2].setIcon(new ImageIcon("Up_Right_arrow.jpg"));
		ArrayElDirections[0][2].addActionListener(this);
		ArrayElDirections[0][2].setActionCommand("Up-Right");
		ArrayElDirections[0][2].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[0][2]);
		ArrayElDirections[1][0] = new JButton();
		ArrayElDirections[1][0].setIcon(new ImageIcon("Left_arrow.jpg"));
		ArrayElDirections[1][0].addActionListener(this);
		ArrayElDirections[1][0].setActionCommand("Left");
		ArrayElDirections[1][0].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[1][0]);
		ArrayElDirections[1][1] = new JButton("D");
		PanelElDirections.add(ArrayElDirections[1][1]);
		ArrayElDirections[1][2] = new JButton();
		ArrayElDirections[1][2].setIcon(new ImageIcon("Right_arrow.jpg"));
		ArrayElDirections[1][2].addActionListener(this);
		ArrayElDirections[1][2].setActionCommand("Right");
		ArrayElDirections[1][2].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[1][2]);
		ArrayElDirections[2][0] = new JButton();
		ArrayElDirections[2][0].setIcon(new ImageIcon("Down_left_arrow.jpg"));
		ArrayElDirections[2][0].addActionListener(this);
		ArrayElDirections[2][0].setActionCommand("Down-Left");
		ArrayElDirections[2][0].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[2][0]);
		ArrayElDirections[2][1] = new JButton();
		ArrayElDirections[2][1].setIcon(new ImageIcon("Down_arrow.jpg"));
		ArrayElDirections[2][1].addActionListener(this);
		ArrayElDirections[2][1].setActionCommand("Down");
		ArrayElDirections[2][1].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[2][1]);
		ArrayElDirections[2][2] = new JButton();
		ArrayElDirections[2][2].setIcon(new ImageIcon("Down_right_arrow.png"));
		ArrayElDirections[2][2].addActionListener(this);
		ArrayElDirections[2][2].setActionCommand("Down-Right");
		ArrayElDirections[2][2].setContentAreaFilled(false);
		PanelElDirections.add(ArrayElDirections[2][2]);

		Elle3ba.getContentPane().add(PanelElDirections);
	}

	public void BuildButton(OurButton w, Game le3ba) {

		int i = w.getI();
		int j = w.getJ();

		if (q > 0 && q % 6 == 0) {
			u--;
		}
		if (u % 2 == 0) {
			ArrayElButtons[i][j].setBackground(Color.WHITE);
			u++;
			q++;
		} else {
			ArrayElButtons[i][j].setBackground(Color.white);
			u++;
			q++;
		}
		if (le3ba.getCellAt(i, j).getPiece() != null) {
			if (le3ba.getCellAt(i, j).getPiece() instanceof Super) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("SuperX.png"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);
			} else if (le3ba.getCellAt(i, j).getPiece() instanceof Ranged) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("RangedX.png"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);

			} else if (le3ba.getCellAt(i, j).getPiece() instanceof Medic) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("MedicX.jpg"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);

			} else if (le3ba.getCellAt(i, j).getPiece() instanceof Tech) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("TechX.jpg"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);

			} else if (le3ba.getCellAt(i, j).getPiece() instanceof Speedster) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("SpeedsterX.jpg"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);

			} else if (le3ba.getCellAt(i, j).getPiece() instanceof SideKick) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("SidekickX.jpg"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);

			} else if (le3ba.getCellAt(i, j).getPiece() instanceof Armored
					&& ((Armored) le3ba.getCellAt(i, j).getPiece()).isArmorUp() == true) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("ArmoredX.jpg"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);

			} else if (le3ba.getCellAt(i, j).getPiece() instanceof Armored
					&& ((Armored) le3ba.getCellAt(i, j).getPiece()).isArmorUp() == false) {
				ArrayElButtons[i][j].setIcon(new ImageIcon("UnarmoredX.jpg"));
				ArrayElButtons[i][j].setI(i);
				ArrayElButtons[i][j].setJ(j);
			}

		}
	}

	public void BuildDead1Button(OurButton w, Game le3ba) {
		int i = w.getI();
		int j = w.getJ();
		ArrayElDead1Buttons[i][j] = w;

		if (arrayEldead1[i][j] != null) {
			if (arrayEldead1[i][j] instanceof Super) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Super.png"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);
			} else if (arrayEldead1[i][j] instanceof Ranged) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Ranged.jpg"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);
			} else if (arrayEldead1[i][j] instanceof Medic) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Medic.jpg"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);
			} else if (arrayEldead1[i][j] instanceof Tech) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Tech.jpg"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);
			} else if (arrayEldead1[i][j] instanceof Speedster) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Speedster.jpg"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);
			} else if (arrayEldead1[i][j] instanceof SideKick) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Sidekick.jpg"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);

			} else if (arrayEldead1[i][j] instanceof Armored && ((Armored) arrayEldead1[i][j]).isArmorUp() == true) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Armored.jpg"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);

			} else if (arrayEldead1[i][j] instanceof Armored && ((Armored) arrayEldead1[i][j]).isArmorUp() == false) {
				ArrayElDead1Buttons[i][j].setBackground(Color.white);
				ArrayElDead1Buttons[i][j].setIcon(new ImageIcon("Unarmored.jpg"));
				ArrayElDead1Buttons[i][j].setI(i);
				ArrayElDead1Buttons[i][j].setJ(j);
				ArrayElDead1Buttons[i][j].setPiece(arrayEldead1[i][j]);
			}

		}
	}

	public void BuildDead2Button(OurButton w, Game le3ba) {
		int i = w.getI();
		int j = w.getJ();
		ArrayElDead2Buttons[i][j] = w;

		if (arrayEldead2[i][j] != null) {
			if (arrayEldead2[i][j] instanceof Super) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Super.png"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			} else if (arrayEldead2[i][j] instanceof Ranged) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Ranged.jpg"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			} else if (arrayEldead2[i][j] instanceof Medic) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Medic.jpg"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			} else if (arrayEldead2[i][j] instanceof Tech) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Tech.jpg"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			} else if (arrayEldead2[i][j] instanceof Speedster) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Speedster.jpg"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			} else if (arrayEldead2[i][j] instanceof SideKick) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Sidekick.jpg"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			} else if (arrayEldead2[i][j] instanceof Armored && ((Armored) arrayEldead2[i][j]).isArmorUp() == true) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Armored.jpg"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			} else if (arrayEldead2[i][j] instanceof Armored && ((Armored) arrayEldead2[i][j]).isArmorUp() == false) {
				ArrayElDead2Buttons[i][j].setBackground(Color.white);
				ArrayElDead2Buttons[i][j].setIcon(new ImageIcon("Unarmored.jpg"));
				ArrayElDead2Buttons[i][j].setI(i);
				ArrayElDead2Buttons[i][j].setJ(j);
				ArrayElDead2Buttons[i][j].setPiece(arrayEldead2[i][j]);
			}
		}
	}

	public void BuildPanelUsePower(JFrame Elle3ba) {
		JPanel PanelElResurrection = new JPanel();
		PanelElResurrection.setBounds(1200, 510, 100, 35);
		JButton mc = new JButton("Resurrect");
		mc.addActionListener(this);
		PanelElResurrection.add(mc);
		Elle3ba.getContentPane().add(PanelElResurrection);

		JPanel PanelUsePower = new JPanel();
		PanelUsePower.setBounds(910, 510, 100, 35);
		JButton l = new JButton("UsePower");
		l.addActionListener(this);
		PanelUsePower.add(l);
		Elle3ba.getContentPane().add(PanelUsePower);

		JPanel PanelTeleport = new JPanel();
		PanelTeleport.setBounds(1050, 510, 100, 35);
		JButton kk = new JButton("Teleport");
		kk.addActionListener(this);
		PanelTeleport.add(kk);
		PanelTeleport.setVisible(true);
		Elle3ba.getContentPane().add(PanelTeleport);

	}

	public void checkWinner(Game le3ba, JFrame Elle3ba) {

		Player x = le3ba.getPlayer1();
		Player y = le3ba.getPlayer2();
		JFrame Victory = new JFrame("Victory");

		if (x.getPayloadPos() == le3ba.getPayloadPosTarget()) {
			wa7d_keseb = true;
			Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
			Victory.setIconImage(im);
			JLabel t = new JLabel();
			t.setText("                                                         " + esmPlayer1 + " has won the Game!");
			Victory.getContentPane().add(t);
			Victory.setBounds(400, 200, 500, 300);
			Victory.setVisible(true);
		}
		if (y.getPayloadPos() == le3ba.getPayloadPosTarget()) {
			wa7d_keseb = true;
			Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
			Victory.setIconImage(im);
			JLabel t = new JLabel();
			t.setText("                                                         " + esmPlayer2 + " has won the Game!");
			Victory.getContentPane().add(t);
			Victory.setBounds(400, 200, 500, 300);
			Victory.setVisible(true);
		}
	}

	public void PlayGame() throws InvalidPowerUseException, WrongTurnException {
		// Debugging
		System.out.println("e5tar_trigger is " + e5tar_trigger);
		System.out.println("Hy_Resurrect is " + Hy_Resurrect);
		System.out.println("hyst5dm_power is " + hyst5dm_power);
		System.out.println("e5tar_target is " + e5tar_target);
		System.out.println("wa7d_keseb is " + wa7d_keseb);
		System.out.println("e5tar_direction is " + e5tar_direction);

		if (e5tar_trigger && Hy_Resurrect && e5tar_target && e5tar_direction && wa7d_keseb == false) {
			try {
				System.out.println("Target is" + ElTarget);
				((ActivatablePowerHero) le3ba.getCellAt(ELI, ELJ).getPiece()).usePower(Eldirection, ElTarget, null);
				e5tar_trigger = false;
				Hy_Resurrect = false;
				e5tar_target = false;
				e5tar_direction = false;
			} catch (InvalidPowerUseException f) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException f) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}

		if (e5tar_trigger && e5tar_direction && hyst5dm_power == false && wa7d_keseb == false) {
			try {
				System.out.println("da5al fl IF bta3et move ");
				le3ba.getCellAt(ELI, ELJ).getPiece().move(Eldirection);
				System.out.println("3amal Move elmfrood");
				e5tar_trigger = false;
				e5tar_direction = false;

			} catch (UnallowedMovementException f) {

				JFrame Error1 = new JFrame("UnallowedMovementException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);

			} catch (OccupiedCellException f) {
				JFrame Error1 = new JFrame("OccupiedCellException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException f) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText("                                                                   " + f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}

		} else if (e5tar_trigger && hyst5dm_power && e5tar_direction && e5tar_target == false && wa7d_keseb == false) {
			System.out.println("3amal call l usePower bta3t Direciton,nul,null");

			try {
				((ActivatablePowerHero) le3ba.getCellAt(ELI, ELJ).getPiece()).usePower(Eldirection, null, null);
				e5tar_trigger = false;
				hyst5dm_power = false;
				e5tar_direction = false;
			} catch (InvalidPowerUseException f) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException f) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		} else if (e5tar_trigger && Hy_teleport && e5tar_target && e5tar_newPos && wa7d_keseb == false) {
			System.out.println("da5al f usePower el teleportation");
			try {
				((ActivatablePowerHero) le3ba.getCellAt(ELI, ELJ).getPiece()).usePower(null, ElTarget, newPos);
				e5tar_trigger = false;
				Hy_teleport = false;
				e5tar_target = false;
				e5tar_newPos = false;
			} catch (InvalidPowerUseException f) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException f) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		} else if (e5tar_trigger && hyst5dm_power && e5tar_target && wa7d_keseb == false) {
			try {
				((ActivatablePowerHero) le3ba.getCellAt(ELI, ELJ).getPiece()).usePower(null, ElTarget, null);
				e5tar_trigger = false;
				hyst5dm_power = false;
				e5tar_target = false;
			} catch (InvalidPowerUseException f) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException f) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}
		checkWinner(le3ba, Elle3ba);
		BuildAll(le3ba, Elle3ba);
		System.out.println("Wesel l CheckWinner w RebuildAll f a5r playGame");
		if (le3ba.getCurrentPlayer().getName() == "Computer") {
			El3abYaComputer();
		}
	}

	public void El3abYaComputer() {
		int Count = 0;
		Point[] meins = new Point[20];
		Point[] seins = new Point[20];
		Direction[] CompDirections = new Direction[20];

		System.out.println("Wesel l el3ab ya computer");

		for (int i = 0; i < 7; i++) {

			for (int j = 0; j < 6; j++) {

				if (le3ba.getCellAt(i, j).getPiece() != null
						&& le3ba.getCellAt(i, j).getPiece().getOwner().getName() == esmPlayer1) {

					if (le3ba.getCellAt(i + 1, j + 1).getPiece() != null
							&& le3ba.getCellAt(i + 1, j + 1).getPiece().getOwner().getName() == "Computer"
							&& le3ba.getCellAt(i + 1, j + 1).getPiece() instanceof Medic == false
							&& le3ba.getCellAt(i + 1, j + 1).getPiece() instanceof Super == false
							&& le3ba.getCellAt(i + 1, j + 1).getPiece() instanceof Speedster == false) {
						s = new Point(i, j);
						m = new Point(i + 1, j + 1);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.UPLEFT;
						Count++;
					}
					if (le3ba.getCellAt(i + 1, j).getPiece() != null
							&& le3ba.getCellAt(i + 1, j).getPiece().getOwner().getName() == "Computer"
							&& le3ba.getCellAt(i + 1, j).getPiece() instanceof Tech == false
							&& le3ba.getCellAt(i + 1, j).getPiece() instanceof SideKick == false
							&& le3ba.getCellAt(i + 1, j).getPiece() instanceof Speedster == false) {
						s = new Point(i, j);
						m = new Point(i + 1, j);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.UP;
						Count++;
					}
					if (le3ba.getCellAt(i + 1, j - 1).getPiece() != null
							&& le3ba.getCellAt(i + 1, j - 1).getPiece().getOwner().getName() == "Computer"
							&& le3ba.getCellAt(i + 1, j - 1).getPiece() instanceof Super == false
							&& le3ba.getCellAt(i + 1, j - 1).getPiece() instanceof Medic == false
							&& le3ba.getCellAt(i + 1, j - 1).getPiece() instanceof SideKick == false
							&& le3ba.getCellAt(i + 1, j - 1).getPiece() instanceof Speedster == false) {
						s = new Point(i, j);
						m = new Point(i + 1, j - 1);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.UPRIGHT;
						Count++;
					}
					if (le3ba.getCellAt(i, j + 1).getPiece() != null
							&& le3ba.getCellAt(i, j + 1).getPiece().getOwner().getName() == "Computer"
							&& le3ba.getCellAt(i, j + 1).getPiece() instanceof Tech == false
							&& le3ba.getCellAt(i, j + 1).getPiece() instanceof Speedster == false) {
						s = new Point(i, j);
						m = new Point(i, j + 1);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.LEFT;
						Count++;
					}
					if (le3ba.getCellAt(i, j - 1).getPiece() != null
							&& le3ba.getCellAt(i, j - 1).getPiece().getOwner().getName() == "Computer"
							&& le3ba.getCellAt(i, j - 1).getPiece() instanceof Tech == false
							&& le3ba.getCellAt(i, j - 1).getPiece() instanceof Speedster == false) {
						s = new Point(i, j);
						m = new Point(i, j - 1);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.RIGHT;
						Count++;
					}
					if (le3ba.getCellAt(i - 1, j + 1).getPiece() != null
							&& le3ba.getCellAt(i - 1, j + 1).getPiece().getOwner().getName() == "Computer"
							&& le3ba.getCellAt(i - 1, j + 1).getPiece() instanceof Super == false
							&& le3ba.getCellAt(i - 1, j + 1).getPiece() instanceof Medic == false
							&& le3ba.getCellAt(i - 1, j + 1).getPiece() instanceof Speedster == false) {
						s = new Point(i, j);
						m = new Point(i - 1, j + 1);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.DOWNLEFT;
						Count++;
					}

					if (le3ba.getCellAt(i - 1, j).getPiece() != null
							&& le3ba.getCellAt(i - 1, j).getPiece().getOwner().getName() != esmPlayer1
							&& le3ba.getCellAt(i - 1, j).getPiece() instanceof Tech == false
							&& le3ba.getCellAt(i - 1, j).getPiece() instanceof Speedster == false) {
						System.out.println("El AI bta3 down shghal");
						s = new Point(i, j);
						m = new Point(i - 1, j);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.DOWN;
						Count++;
					}
					if (le3ba.getCellAt(i - 1, j - 1).getPiece() != null
							&& le3ba.getCellAt(i - 1, j - 1).getPiece().getOwner().getName() == "Computer"
							&& le3ba.getCellAt(i - 1, j - 1).getPiece() instanceof Super == false
							&& le3ba.getCellAt(i - 1, j - 1).getPiece() instanceof Medic == false
							&& le3ba.getCellAt(i - 1, j - 1).getPiece() instanceof Speedster == false) {
						s = new Point(i, j);
						m = new Point(i - 1, j - 1);
						seins[Count] = s;
						meins[Count] = m;
						CompDirections[Count] = Direction.DOWNRIGHT;
						Count++;
					}
				}
			}
		}
		if (ElcompHyhagm) {
			int rnd = (int) (Math.random() * Count);
			DirectionElhgoom = CompDirections[rnd];
			int meinI = meins[rnd].x;
			int meinJ = meins[rnd].y;
			int seinI = seins[rnd].x;
			int seinJ = seins[rnd].y;

			try {
				System.out.println("da5al fl IF bta3et move ");
				le3ba.getCellAt(meinI, meinJ).getPiece().move(DirectionElhgoom);
				System.out.println("3amal Move elmfrood");
				e5tar_trigger = false;
				e5tar_direction = false;

			} catch (UnallowedMovementException f) {

				JFrame Error1 = new JFrame("UnallowedMovementException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);

			} catch (OccupiedCellException f) {
				JFrame Error1 = new JFrame("OccupiedCellException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException f) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText("                                                                   " + f.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}

		}

	}

	public void actionPerformed(ActionEvent e) {
		String c = null;
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			c = btn.getActionCommand();
		}

		if (c == "UsePower" && hyst5dm_power == false) {
			System.out.println("UsePower is on");
			hyst5dm_power = true;
		} else if (c == "UsePower" && hyst5dm_power) {
			System.out.println("UsePower is off");
			hyst5dm_power = false;
		}

		if (c == "Teleport" && Hy_teleport == false) {
			System.out.println("Teleporting is on");
			Hy_teleport = true;
		} else if (c == "Teleport" && Hy_teleport) {
			System.out.println("Teleporting is off");
			Hy_teleport = false;
		}

		if (c == "Resurrect" && Hy_Resurrect == false) {
			Hy_Resurrect = true;
			System.out.println("Resurrection is on");
		} else if (c == "Resurrect" && Hy_Resurrect) {
			System.out.println("Resurrection is off");
			Hy_Resurrect = false;

		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {

				if (e.getSource() == ArrayElDead1Buttons[i][j]) {
					System.out.println("da5al el dead 1 ");
					ElTarget = arrayEldead1[i][j];
					e5tar_target = true;
					System.out.println("Eshtaghal 3la dead 1");
				}
			}
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {

				if (e.getSource() == ArrayElDead2Buttons[i][j]) {
					System.out.println("da5al el dead 2");
					ElTarget = arrayEldead2[i][j];
					e5tar_target = true;
					System.out.println("Eshtaghal 3la dead 2");
				}
			}
		}

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (e.getSource() == ArrayElButtons[i][j]) {
					if (e5tar_trigger == true && hyst5dm_power) {
						e5tar_target = true;
						TETI = ((OurButton) e.getSource()).getI();
						TETJ = ((OurButton) e.getSource()).getJ();
						ElTarget = le3ba.getCellAt(TETI, TETJ).getPiece();

						System.out.println("Action Listener e5tyar Eltarget");
						try {
							PlayGame();
						} catch (InvalidPowerUseException e1) {
							JFrame Error1 = new JFrame("InvalidPowerUseException");
							Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
							Error1.setIconImage(im);
							Error1.setBounds(400, 200, 500, 300);
							JLabel disp = new JLabel();
							disp.setText(e1.getMessage());
							Error1.add(disp);
							Error1.setVisible(true);
						} catch (WrongTurnException e1) {
							JFrame Error1 = new JFrame("WrongTurnException");
							Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
							Error1.setIconImage(im);
							Error1.setBounds(400, 200, 200, 200);
							JLabel disp = new JLabel();
							disp.setText(e1.getMessage());
							Error1.add(disp);
							Error1.setVisible(true);
						}
					}
				}
			}
		}
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (e.getSource() == ArrayElButtons[i][j]) {
					if (e5tar_trigger == true && Hy_teleport) {
						e5tar_target = true;
						TETI = ((OurButton) e.getSource()).getI();
						TETJ = ((OurButton) e.getSource()).getJ();
						ElTarget = le3ba.getCellAt(TETI, TETJ).getPiece();

						System.out.println("Action Listener e5tyar Target el Teleport");
						int ICOR = Integer
								.parseInt(JOptionPane
										.showInputDialog("Please Enter the Vertical Co-ordinate of the Teleportation"))
								- 1;
						int JCOR = Integer.parseInt(JOptionPane
								.showInputDialog("Please Enter the Horizontal Co-ordinate of the Teleportation")) - 1;
						e5tar_newPos = true;
						newPos.x = ICOR;
						newPos.y = JCOR;
						System.out.println("Action Listener ICOR");
						try {
							PlayGame();
						} catch (InvalidPowerUseException e1) {
							JFrame Error1 = new JFrame("InvalidPowerUseException");
							Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
							Error1.setIconImage(im);
							Error1.setBounds(400, 200, 500, 300);
							JLabel disp = new JLabel();
							disp.setText(e1.getMessage());
							Error1.add(disp);
							Error1.setVisible(true);
						} catch (WrongTurnException e1) {
							JFrame Error1 = new JFrame("WrongTurnException");
							Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
							Error1.setIconImage(im);
							Error1.setBounds(400, 200, 200, 200);
							JLabel disp = new JLabel();
							disp.setText(e1.getMessage());
							Error1.add(disp);
							Error1.setVisible(true);
						}

					}
				}
			}
		}
		if (e5tar_target == false) {
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					if (e.getSource() == ArrayElButtons[i][j] && le3ba.getCellAt(i, j).getPiece() != null) {
						e5tar_trigger = true;
						ELI = ((OurButton) e.getSource()).getI();
						ELJ = ((OurButton) e.getSource()).getJ();
						System.out.println("Action Listener e5tyar Piece mn el Panel shghal");
					}
				}
			}
		}
		if (c == "Up-Left" && e5tar_trigger) {
			e5tar_direction = true;
			Eldirection = Direction.UPLEFT;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}
		if (c == "Up" && e5tar_trigger) {
			System.out.println("e5tar UP");
			e5tar_direction = true;
			Eldirection = Direction.UP;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}
		if (c == "Up-Right" && e5tar_trigger) {
			e5tar_direction = true;
			Eldirection = Direction.UPRIGHT;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}
		if (c == "Left" && e5tar_trigger) {
			e5tar_direction = true;
			Eldirection = Direction.LEFT;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}

		if (c == "Right" && e5tar_trigger) {
			e5tar_direction = true;
			Eldirection = Direction.RIGHT;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}

		if (c == "Down-Left" && e5tar_trigger) {
			e5tar_direction = true;
			Eldirection = Direction.DOWNLEFT;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}

		if (c == "Down" && e5tar_trigger) {
			e5tar_direction = true;
			Eldirection = Direction.DOWN;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}
		if (c == "Down-Right" && e5tar_trigger) {
			e5tar_direction = true;
			Eldirection = Direction.DOWNRIGHT;
			try {
				PlayGame();
			} catch (InvalidPowerUseException e1) {
				JFrame Error1 = new JFrame("InvalidPowerUseException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 500, 300);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			} catch (WrongTurnException e1) {
				JFrame Error1 = new JFrame("WrongTurnException");
				Image im = Toolkit.getDefaultToolkit().getImage("BackGroundImage.jpg");
				Error1.setIconImage(im);
				Error1.setBounds(400, 200, 200, 200);
				JLabel disp = new JLabel();
				disp.setText(e1.getMessage());
				Error1.add(disp);
				Error1.setVisible(true);
			}
		}
	}
}
