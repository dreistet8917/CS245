/**
 * @author Emma Dreist
 * Worked with: Lauren Diou
 * 
 * This class programs the controls for the tic-tac-toe game.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/**
 * File: TTTPanel.java Author: Heather Amthauer Description: This class makes
 * the tic-tac-toe panel The user is allowed to select a one-player version or a
 * two-player version. One-player version is not implemented Two-player version
 * is turn based. First X marks a square then O moves.
 */


public class TTTPanel extends JPanel {
	private String name1;
	private String name2;
	private String turn; // keep track if it is X or O's turn
	private boolean onePlay; // keep track of one player or two player

	private JPanel topPanel; // panel to hold radio buttons and info
	private JPanel radioPanel;
	private JRadioButton onePlayer; // for user to select if they want to play
	// agains tthe computer
	private JRadioButton twoPlayer; // for user to select if they want to play
	// against another human
	private ButtonGroup group; // for mutual exclsion of player selection
	private JLabel info; // to communicate instructions to user

	private JPanel panel; // panel for buttons for squares
	private JButton upperR; // upper right corner
	private JButton upperM; // upper middle
	private JButton upperL; // upper left corner
	private JButton middleR; // middle right
	private JButton middleM; // middle of board
	private JButton middleL; // middle left
	private JButton lowerR; // lower right corner
	private JButton lowerM; // lower middle
	private JButton lowerL; // lower left corner

	private JButton reset; // button to reset board
	private JPanel onePlayerPanel;
	private JPanel twoPlayerPanel;
	private JTextField enterOne;
	private JTextField enterTwo;
	private JTextField enterTwoTwo;
	private JButton submitOneName;
	private JButton submitTwoName;
	private String name;
	private String playerOneName;
	private String playerTwoName;
	private JLabel nameOneLabel;
	private JLabel nameTwoLabel;
	private JLabel nameTwoTwoLabel;

	private Font font1 = new Font("Courier", Font.BOLD, 50);// so X and O are
	// larger

	/**
	 * Constructor that initalizes all the components for GUI
	 *
	 */
	public TTTPanel() {
		// set the layput for the panel that will hold all the game components
		setLayout(new BorderLayout());
		// make radio buttons for player options
		onePlayer = new JRadioButton("One Player");
		twoPlayer = new JRadioButton("Two Player");

		// make the radio buttons mutual exclusive
		group = new ButtonGroup();
		group.add(onePlayer);
		group.add(twoPlayer);

		// Add item listeners to radio buttons so we can tell when they have
		// been selected
		ItemListener oneplay = new RadioOnePlayer();
		onePlayer.addItemListener(oneplay);
		ItemListener twoplay = new RadioTwoPlayer();
		twoPlayer.addItemListener(twoplay);

		// initialize top panel that will hold radio buttons
		topPanel = new JPanel();
		// set the layout so things will be added horizontially
		topPanel.setLayout(new GridLayout(2, 1));
		radioPanel = new JPanel();
		// add radio buttons
		radioPanel.add(onePlayer);
		radioPanel.add(twoPlayer);
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.LINE_AXIS));
		// initalize info and make the text red
		info = new JLabel("Select how many players");
		info.setForeground(Color.red);
		// add the info
		radioPanel.add(info);
		topPanel.add(radioPanel);
		// add the top panel to the top of the TTT panel
		add(topPanel, BorderLayout.NORTH);

		// initialize the reset button
		reset = new JButton("Reset");
		// action listener for reset
		ActionListener resetBoard = new ResetAction();
		reset.addActionListener(resetBoard);
		// add reset button to bottom of the TTT panel
		add(reset, BorderLayout.SOUTH);

		// action listeners for all the squares
		ActionListener takeSquare = new TakeSquareAction();

		// add square buttons in 3x3 grid
		panel = new JPanel();
		// this sets the layout to 3x3 with a 5 pixel border
		// that goes around the grid cells
		panel.setLayout(new GridLayout(3, 3, 5, 5));

		// for all buttons, set them to blank text
		// add action listener then
		// add to panel
		upperL = new JButton(" ");
		upperL.setFont(font1);
		upperL.addActionListener(takeSquare);
		panel.add(upperL);

		upperM = new JButton(" ");
		upperM.setFont(font1);
		upperM.addActionListener(takeSquare);
		panel.add(upperM);

		upperR = new JButton(" ");
		upperR.setFont(font1);
		upperR.addActionListener(takeSquare);
		panel.add(upperR);

		middleL = new JButton(" ");
		middleL.setFont(font1);
		middleL.addActionListener(takeSquare);
		panel.add(middleL);

		middleM = new JButton(" ");
		middleM.setFont(font1);
		middleM.addActionListener(takeSquare);
		panel.add(middleM);

		middleR = new JButton(" ");
		middleR.setFont(font1);
		middleR.addActionListener(takeSquare);
		panel.add(middleR);

		lowerL = new JButton(" ");
		lowerL.setFont(font1);
		lowerL.addActionListener(takeSquare);
		panel.add(lowerL);

		lowerM = new JButton(" ");
		lowerM.setFont(font1);
		lowerM.addActionListener(takeSquare);
		panel.add(lowerM);

		lowerR = new JButton(" ");
		lowerR.setFont(font1);
		lowerR.addActionListener(takeSquare);
		panel.add(lowerR);

		onePlayerPanel = new JPanel();
		onePlayerPanel.setLayout(new BoxLayout(onePlayerPanel, BoxLayout.LINE_AXIS));

		twoPlayerPanel = new JPanel();
		twoPlayerPanel.setLayout(new BoxLayout(twoPlayerPanel, BoxLayout.LINE_AXIS));

		enterOne = new JTextField(10);
		nameOneLabel = new JLabel("Enter your name: ");

		enterTwo = new JTextField(10);
		nameTwoLabel = new JLabel("Enter player one's name: ");

		enterTwoTwo = new JTextField(10);
		nameTwoTwoLabel = new JLabel("Enter player two's name: ");

		ActionListener submitOneAction = new submitOnePlayer();
		submitOneName = new JButton("Submit");
		submitOneName.addActionListener(submitOneAction);
		ActionListener submitTwoAction = new submitTwoPlayer();
		submitTwoName = new JButton("Submit");
		submitTwoName.addActionListener(submitTwoAction);

		onePlayerPanel.add(nameOneLabel);
		onePlayerPanel.add(enterOne);
		onePlayerPanel.add(submitOneName);

		twoPlayerPanel.add(nameTwoLabel);
		twoPlayerPanel.add(enterTwo);
		twoPlayerPanel.add(nameTwoTwoLabel);
		twoPlayerPanel.add(enterTwoTwo);
		twoPlayerPanel.add(submitTwoName);
		// add the grid panel to the center of the TTTPanel
		add(panel, BorderLayout.CENTER);
		disableAllButtons();// make sure the user selects one or two player
		// first

	}

	/**
	 * This class will reset the text for all the buttons to a blank and enable
	 * them all again so they can be clicked. It also clears all the text feilds
	 * whenever the reset button is clicked, and enables them for the user to 
	 * enter another name.
	 */
	private class ResetAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// set text to blank for all buttons and color to white
			upperL.setText(" ");
			upperL.setBackground(Color.WHITE);
			middleL.setText(" ");
			middleL.setBackground(Color.WHITE);
			lowerL.setText(" ");
			lowerL.setBackground(Color.WHITE);

			upperR.setText(" ");
			upperR.setBackground(Color.WHITE);
			middleR.setText(" ");
			middleR.setBackground(Color.WHITE);
			lowerR.setText(" ");
			lowerR.setBackground(Color.WHITE);

			upperM.setText(" ");
			upperM.setBackground(Color.WHITE);
			middleM.setText(" ");
			middleM.setBackground(Color.WHITE);
			lowerM.setText(" ");
			lowerM.setBackground(Color.WHITE);

			// clear all of the player options
			group.clearSelection();

			// enable player options
			onePlayer.setEnabled(true);
			twoPlayer.setEnabled(true);

			// set info text
			info.setText("Select the amount of players");

			topPanel.remove(onePlayerPanel);
			enterOne.setText("");
			enterOne.setEnabled(true);
			topPanel.remove(twoPlayerPanel);
			enterTwo.setText("");
			enterTwo.setEnabled(true);
			enterTwoTwo.setText("");
			enterTwoTwo.setEnabled(true);
			submitOneName.setEnabled(true);
			submitTwoName.setEnabled(true);
			// make it so the user has to select player option first disable
			// buttons
			disableAllButtons();
		}

	}

	/**
	 * RadioOnePlayer class sets the game up for one player setting 
	 * the turn to X, and disabling the two player radio button. It also 
	 * adds the one player panel I created in the public TTT Panel class 
	 * to the top panel. It then sets the info on the top panel to "Enter
	 * player name."
	 * 
	 */
	private class RadioOnePlayer implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			turn = "X";
			// set onePlay
			onePlay = true;

			// disable two player option
			twoPlayer.setEnabled(false);
			// you don't want people to switch versions.

			// set info to say it's Your turn then start the one player game
			topPanel.add(onePlayerPanel);
			info.setText("Enter player name");
		}
	}

	/**
	 * RadioTwoPlayer class sets the game up for two players It will be turn
	 * based. X is first. This class sets the turn to X, starting off the first
	 * player. It then disables the one player radio button, and adds the two player 
	 * panel created in the public TTT class, to the top panel. It then sets the info
	 * to "Enter players name."
	 * 
	 */
	private class RadioTwoPlayer implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			// set the turn to X
			turn = "X";

			// set onePlay to false
			onePlay = false;

			// disable one player option
			onePlayer.setEnabled(false);

			// set info to say it's X's turn then start the two player game
			topPanel.add(twoPlayerPanel);
			info.setText("Enter players names");
		}
	}

	/**
	 * TakeSquareAction class When square buttons are clicked - it will mark the
	 * square appropriately. Appropriate moves are based on if it is two player
	 * or one player.
	 */

	private class TakeSquareAction implements ActionListener {
		/**
		 * this method implements the two player mode. It creates
		 * a new JButton that gets the event source of being clicked
		 * and then sets the button that is being clicked to the
		 * corresponding text (X or O). It then checks for the win or
		 * a draw between the two players.
		 */
		public void actionPerformed(ActionEvent event) {
			// two player option
			if (!onePlay) {
				// get the button that was pressed
				JButton b = (JButton) event.getSource();
				// X's turn
				if (turn.equals("X")) {
					// set the button text to X
					b.setText("X");
					b.setOpaque(true);
					b.setBackground(Color.MAGENTA.brighter());
					// disable the button
					b.setEnabled(false);
					// check if it won the game
					if (checkForWin()) {
						info.setText(enterTwo.getText() + " has won!");
						disableAllButtons();
					} else {
						// else check for a draw
						if (checkForDraw()) {
							info.setText("The game is a tie!");
						}
						// if game is not over - it is O's turn
						else {
							turn = "O";
							info.setText("It's " + enterTwoTwo.getText() + "'s turn!");
						}
					}

				}
				// O's turn
				else {
					// set the button text to X
					b.setText("O");
					b.setOpaque(true);
					b.setBackground(Color.CYAN);
					// disable the button
					b.setEnabled(false);
					// check if it won the game
					if (checkForWin()) {
						info.setText(enterTwoTwo.getText() + " has won!");
						disableAllButtons();
					} else {
						// else check for a draw
						if (checkForDraw()) {
							info.setText("The game is a tie!");
						}
						// if game is not over - it is O's turn
						else {
							turn = "X";
							info.setText("It's " + enterTwo.getText() + "'s turn!");
						}

					}
				}
				// one player option
				/**
				 * This else statement sets up one player mode. It first asks
				 * for the button clicked by the user and sets it to an X. The computer
				 * then sets a counter move based on the button clicked by the user. 
				 */
			} else {
				JButton a = (JButton) event.getSource();
				// set the button text to X
				a.setText("X");
				a.setOpaque(true);
				a.setBackground(Color.MAGENTA.brighter());
				// disable the button
				a.setEnabled(false);
				info.setText("It's the computer's turn!");
				// check if it won the game
				if (checkForWin()) {
					System.out.println(checkForWin());
					info.setText(enterOne.getText() + " has won!");
					disableAllButtons();
					return;
				} else {
					// check if it won the game
					if (winning()) { // if there are two o's in a row
						System.out.println("winning");
						if (checkForWin()) {
							System.out.println("winning: checking for win");
							info.setText("The computer has won!");
							disableAllButtons();
							return;
						} else {
							if (checkForDraw()) {
								System.out.println("winning: checking for draw");
								info.setText("The game is a tie");
								disableAllButtons();
								return;
							}
						}
					} else if (blocking()) { // there are two x's in a row
						System.out.println("blocking");
						if (checkForWin()) {
							System.out.println("blocking: checking for win");
							info.setText("The computer has won!");
							disableAllButtons();
							return;
						} else {
							if (checkForDraw()) {
								System.out.println("winning: checking for draw");
								info.setText("The game is a tie");
								disableAllButtons();
								return;
							}
						}
					} else if (middleM.getText().equals(" ")) { 
						System.out.println("middle");
						middleM.setText("O");
						middleM.setOpaque(true);
						middleM.setBackground(Color.CYAN);
						middleM.setEnabled(false);
						if (checkForWin()) {
							System.out.println("middle: checking for win");
							info.setText("The computer has won!");
							disableAllButtons();
							return;
						} else {
							if (checkForDraw()) {
								System.out.println("middle: checking for draw");
								info.setText("The game is a tie");
								disableAllButtons();
								return;
							}
						}
					} else if ((upperL.getText().equals("X")) || (upperR.getText().equals("X")) || (lowerL.getText().equals("X")) || (lowerR.getText().equals("X"))) {
						playSide();
					} else if (playCorners()) { // check to see if any corners
												// empty
						System.out.println("corners");
						if (checkForWin()) {
							System.out.println("corners: checking for win");
							info.setText("The computer has won!");
							disableAllButtons();
							return;
						} else {
							if (checkForDraw()) {
								System.out.println("corners: checking for draw");
								info.setText("The game is a tie");
								disableAllButtons();
								return;
							}
						}
					} else if (playSide()) { // check to see if a side square is
												// empty
						System.out.println("sides");
						if (checkForWin()) {
							System.out.println("sides: checking for win");
							info.setText("The computer has won!");
							disableAllButtons();
							return;
						}
					} else {
						if (checkForWin()) {
							System.out.println("final check for win");
							info.setText("The computer has won!");
							disableAllButtons();
							return;
						} else {
							if (checkForDraw()) {
								System.out.println("final check for draw");
								info.setText("It's a tie!");
								disableAllButtons();
								return;
							}
						}
					}
					turn = "X";
					info.setText("It's " + enterOne.getText() + "'s turn!");
				}

			}
		}
	}

	/* You need to create code for your counter move */

	/**
	 * checkForWin This goes through all the options for a three in a row win
	 * 
	 * @return true if there are three X's or three O's in a row, otherwise
	 *         false
	 */
	public boolean checkForWin() {
		if (((upperR.getText().equals(upperM.getText())) && (upperR.getText().equals(upperL.getText()))
				&& (!upperR.getText().equals(" ")))
				|| ((middleR.getText().equals(middleM.getText())) && (middleR.getText().equals(middleL.getText()))
						&& (!middleR.getText().equals(" ")))
				|| ((lowerR.getText().equals(lowerM.getText())) && (lowerR.getText().equals(lowerL.getText()))
						&& (!lowerR.getText().equals(" ")))
				|| ((upperR.getText().equals(middleR.getText())) && (upperR.getText().equals(lowerR.getText()))
						&& (!upperR.getText().equals(" ")))
				|| ((upperM.getText().equals(middleM.getText())) && (upperM.getText().equals(lowerM.getText()))
						&& (!upperM.getText().equals(" ")))
				|| ((upperL.getText().equals(middleL.getText())) && (upperL.getText().equals(lowerL.getText()))
						&& (!upperL.getText().equals(" ")))
				|| ((upperR.getText().equals(middleM.getText())) && (upperR.getText().equals(lowerL.getText()))
						&& (!upperR.getText().equals(" ")))
				|| ((upperL.getText().equals(middleM.getText())) && (upperL.getText().equals(lowerR.getText()))
						&& (!upperL.getText().equals(" ")))) {
			return true;
		} else
			return false;

	}

	/**
	 * checkForDraw This goes through and sees if all the squares are not blank.
	 * If all the squares are marked then it is a draw
	 * 
	 * @return true if none of the squares are blank.
	 */
	public boolean checkForDraw() {
		if (!upperR.getText().equals(" ") && !upperM.getText().equals(" ") && !upperL.getText().equals(" ")
				&& !middleR.getText().equals(" ") && !middleM.getText().equals(" ") && !middleL.getText().equals(" ")
				&& !lowerR.getText().equals(" ") && !lowerM.getText().equals(" ") && !lowerL.getText().equals(" ")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method checks if there are any possible wins for the computer (O). 
	 * It goes through every possible option for there to be three O's in a row, and
	 * if it finds an option it will set the needed square to O, to complete the win. 
	 * If there is an option where the computer can win, it will set that button to O,
	 * and return true. 
	 * @return
	 */
	public boolean winning() {
		if (upperL.getText().equals("O") && upperM.getText().equals("O") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("O") && upperR.getText().equals("O") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("O") && middleL.getText().equals("O") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("O") && middleM.getText().equals("O") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("O") && middleM.getText().equals("O") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("O") && lowerL.getText().equals("O") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (lowerL.getText().equals("O") && lowerM.getText().equals("O") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (lowerR.getText().equals("O") && lowerM.getText().equals("O") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (lowerL.getText().equals("O") && middleM.getText().equals("O") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (lowerR.getText().equals("O") && middleM.getText().equals("O") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (lowerR.getText().equals("O") && middleR.getText().equals("O") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("O") && middleR.getText().equals("O") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("O") && lowerR.getText().equals("O") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("O") && upperR.getText().equals("O") && upperM.isEnabled()) {
			upperM.setText("O");
			upperM.setOpaque(true);
			upperM.setBackground(Color.CYAN);
			upperM.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("O") && lowerL.getText().equals("O") && middleL.isEnabled()) {
			middleL.setText("O");
			middleL.setOpaque(true);
			middleL.setBackground(Color.CYAN);
			middleL.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("O") && lowerM.getText().equals("O") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("O") && lowerR.getText().equals("O") && upperR.isEnabled()) {
			middleR.setText("O");
			middleR.setOpaque(true);
			middleR.setBackground(Color.CYAN);
			middleR.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("O") && lowerL.getText().equals("O") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (lowerL.getText().equals("O") && lowerR.getText().equals("O") && lowerM.isEnabled()) {
			lowerM.setText("O");
			lowerM.setOpaque(true);
			lowerM.setBackground(Color.CYAN);
			lowerM.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("O") && middleR.getText().equals("O") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("O") && middleM.getText().equals("O") && middleR.isEnabled()) {
			middleR.setText("O");
			middleR.setOpaque(true);
			middleR.setBackground(Color.CYAN);
			middleR.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("O") && middleM.getText().equals("O") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (middleM.getText().equals("O") && middleR.getText().equals("O") && middleL.isEnabled()) {
			middleL.setText("O");
			middleL.setOpaque(true);
			middleL.setBackground(Color.CYAN);
			middleL.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("O") && middleM.getText().equals("O") && upperM.isEnabled()) {
			upperM.setText("O");
			upperM.setOpaque(true);
			upperM.setBackground(Color.CYAN);
			upperM.setEnabled(false);
			return true;
		} else if (middleM.getText().equals("O") && lowerM.getText().equals("O") && upperM.isEnabled()) {
			upperM.setText("O");
			upperM.setOpaque(true);
			upperM.setBackground(Color.CYAN);
			upperM.setEnabled(false);
			return true;
		} else if (middleR.getText().equals("O") && lowerM.getText().equals("O") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (middleR.getText().equals("O") && upperM.getText().equals("O") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("O") && upperM.getText().equals("O") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("O") && lowerM.getText().equals("O") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("O") && middleM.getText().equals("O") && lowerM.isEnabled()) {
			lowerM.setText("O");
			lowerM.setOpaque(true);
			lowerM.setBackground(Color.CYAN);
			lowerM.setEnabled(false);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks if there are any possible wins for the user (X). 
	 * It goes through every possible option for there to be three X's in a row, and
	 * if it finds an option it will set the needed square to O, to block the user from
	 * getting three X's in a row. If there is an option where the computer can win, 
	 * it will set that button to O and return true. 
	 * @return
	 */
	public boolean blocking() {
		if (upperL.getText().equals("X") && upperM.getText().equals("X") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("X") && upperR.getText().equals("X") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("X") && middleL.getText().equals("X") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("X") && middleM.getText().equals("X") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("X") && middleM.getText().equals("X") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("X") && lowerL.getText().equals("X") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (lowerL.getText().equals("X") && lowerM.getText().equals("X") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (lowerR.getText().equals("X") && lowerM.getText().equals("X") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (lowerL.getText().equals("X") && middleM.getText().equals("X") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (lowerR.getText().equals("X") && middleM.getText().equals("X") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (lowerR.getText().equals("X") && middleR.getText().equals("X") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("X") && middleR.getText().equals("X") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("X") && lowerR.getText().equals("X") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("X") && upperR.getText().equals("X") && upperM.isEnabled()) {
			upperM.setText("O");
			upperM.setOpaque(true);
			upperM.setBackground(Color.CYAN);
			upperM.setEnabled(false);
			return true;
		} else if (upperL.getText().equals("X") && lowerL.getText().equals("X") && middleL.isEnabled()) {
			middleL.setText("O");
			middleL.setOpaque(true);
			middleL.setBackground(Color.CYAN);
			middleL.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("X") && lowerM.getText().equals("X") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("X") && lowerR.getText().equals("X") && middleR.isEnabled()) {
			middleR.setText("O");
			middleR.setOpaque(true);
			middleR.setBackground(Color.CYAN);
			middleR.setEnabled(false);
			return true;
		} else if (upperR.getText().equals("X") && lowerL.getText().equals("X") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (lowerL.getText().equals("X") && lowerR.getText().equals("X") && lowerM.isEnabled()) {
			lowerM.setText("O");
			lowerM.setOpaque(true);
			lowerM.setBackground(Color.CYAN);
			lowerM.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("X") && middleR.getText().equals("X") && middleM.isEnabled()) {
			middleM.setText("O");
			middleM.setOpaque(true);
			middleM.setBackground(Color.CYAN);
			middleM.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("X") && middleM.getText().equals("X") && middleR.isEnabled()) {
			middleR.setText("O");
			middleR.setOpaque(true);
			middleR.setBackground(Color.CYAN);
			middleR.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("X") && middleM.getText().equals("X") && lowerM.isEnabled()) {
			lowerM.setText("O");
			lowerM.setOpaque(true);
			lowerM.setBackground(Color.CYAN);
			lowerM.setEnabled(false);
			return true;
		} else if (middleM.getText().equals("X") && middleR.getText().equals("X") && middleL.isEnabled()) {
			middleL.setText("O");
			middleL.setOpaque(true);
			middleL.setBackground(Color.CYAN);
			middleL.setEnabled(false);
			return true;
		} else if (middleM.getText().equals("X") && lowerM.getText().equals("X") && upperM.isEnabled()) {
			upperM.setText("O");
			upperM.setOpaque(true);
			upperM.setBackground(Color.CYAN);
			upperM.setEnabled(false);
			return true;
		} else if (middleR.getText().equals("X") && lowerM.getText().equals("X") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (middleR.getText().equals("X") && upperM.getText().equals("X") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("X") && upperM.getText().equals("X") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (middleL.getText().equals("X") && lowerM.getText().equals("X") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else if (upperM.getText().equals("X") && middleM.getText().equals("X") && lowerM.isEnabled()) {
			lowerM.setText("O");
			lowerM.setOpaque(true);
			lowerM.setBackground(Color.CYAN);
			lowerM.setEnabled(false);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks all the corner boxes and sees if any of them are open. 
	 * If there is an open corner, and there are no possibilities for a win or a 
	 * block, the computer will then set that open corner to O and return true.
	 * @return 
	 */
	public boolean playCorners() {
		if (upperL.getText().equals(" ") && upperL.isEnabled()) {
			upperL.setText("O");
			upperL.setOpaque(true);
			upperL.setBackground(Color.CYAN);
			upperL.setEnabled(false);
			return true;
		} else if (upperR.getText().equals(" ") && upperR.isEnabled()) {
			upperR.setText("O");
			upperR.setOpaque(true);
			upperR.setBackground(Color.CYAN);
			upperR.setEnabled(false);
			return true;
		} else if (lowerR.getText().equals(" ") && lowerR.isEnabled()) {
			lowerR.setText("O");
			lowerR.setOpaque(true);
			lowerR.setBackground(Color.CYAN);
			lowerR.setEnabled(false);
			return true;
		} else if (lowerL.getText().equals(" ") && lowerL.isEnabled()) {
			lowerL.setText("O");
			lowerL.setOpaque(true);
			lowerL.setBackground(Color.CYAN);
			lowerL.setEnabled(false);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks all the side boxes and sees if any of them are open. 
	 * If there is a side box corner, and there are no possibilities for a win or a 
	 * block, the computer will then set that open side to O and return true.
	 * @return 
	 */
	public boolean playSide() {
		if (upperM.getText().equals(" ") && upperM.isEnabled()) {
			upperM.setText("O");
			upperM.setOpaque(true);
			upperM.setBackground(Color.CYAN);
			upperM.setEnabled(false);
			return true;
		} else if (middleR.getText().equals(" ") && middleR.isEnabled()) {
			middleR.setText("O");
			middleR.setOpaque(true);
			middleR.setBackground(Color.CYAN);
			middleR.setEnabled(false);
			return true;
		} else if (lowerM.getText().equals(" ") && lowerM.isEnabled()) {
			lowerM.setText("O");
			lowerM.setOpaque(true);
			lowerM.setBackground(Color.CYAN);
			lowerM.setEnabled(false);
			return true;
		} else if (middleL.getText().equals(" ") && middleL.isEnabled()) {
			middleL.setText("O");
			middleL.setOpaque(true);
			middleL.setBackground(Color.CYAN);
			middleL.setEnabled(false);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * disableAllButtons Sets all of the square buttons to false so the user
	 * cannot click them
	 */
	public void disableAllButtons() {
		upperR.setEnabled(false);
		upperM.setEnabled(false);
		upperL.setEnabled(false);
		middleR.setEnabled(false);
		middleM.setEnabled(false);
		middleL.setEnabled(false);
		lowerR.setEnabled(false);
		lowerM.setEnabled(false);
		lowerL.setEnabled(false);
	}

	/**
	 * startGame Enables all of the square buttons
	 */
	public void startGame() {
		upperR.setEnabled(true);
		upperM.setEnabled(true);
		upperL.setEnabled(true);
		middleR.setEnabled(true);
		middleM.setEnabled(true);
		middleL.setEnabled(true);
		lowerR.setEnabled(true);
		lowerM.setEnabled(true);
		lowerL.setEnabled(true);
	}
	
	/**
	 * This class programs the submit button for one player.
	 * It takes in a JTextField, and if that text field is not
	 * empty, or does not contain just spaces, it will set the info
	 * to "It's the name input by user 's name". It then disables the
	 * text field and the submit button, then it starts a new game. If
	 * the user enters no name, or a name consisting of all spaces, it 
	 * shows an error message asking for a valid name. 
	 * @author emadreist
	 *
	 */
	private class submitOnePlayer implements ActionListener {
		JTextField in;

		public submitOnePlayer() {
			in = enterOne;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component frame = new TTTFrame();
			name = in.getText();
			if (name.isEmpty() || enterOne.getText().equals(" ") || name.contains("  ")) {
				JOptionPane.showConfirmDialog(frame, "Please enter a valid name");
			} else {
				enterOne.setEnabled(false);
				submitOneName.setEnabled(false);
				info.setText("It's " + name + "'s turn");
				startGame();
			}
		}
	}
	
	/**
	 * This class programs the submit button for two player.
	 * It takes in two JTextFields, and if one of the text field is 
	 * not empty, or does not contain just spaces, it will set the info
	 * to "It's the first name entered 's name". It then disables both
	 * text fields and the submit button, then it starts a new game. If
	 * the user enters no name, or a name consisting of all spaces in either
	 * of the text boxes, it will present an error message asking for a
	 * valid name. 
	 * @author emadreist
	 *
	 */
	private class submitTwoPlayer implements ActionListener {
		JTextField playerone;
		JTextField playertwo;

		public submitTwoPlayer() {
			playerone = enterTwo;
			playertwo = enterTwoTwo;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Component frame = new TTTFrame();
			playerOneName = enterTwo.getText();
			playerTwoName = enterTwoTwo.getText();
			if (playerOneName.isEmpty() && !playerTwoName.isEmpty() || enterTwo.getText().equals(" ") || playerOneName.contains("  ")) {
				JOptionPane.showMessageDialog(frame, "Player X, please enter your name");
			} else if (!playerOneName.isEmpty() && playerTwoName.isEmpty() || enterTwoTwo.getText().equals(" ") || playerOneName.contains("  ") || playerTwoName.contains("  ")) {
				JOptionPane.showMessageDialog(frame, "Player O, please enter your name");
			} else if ((playerOneName.isEmpty() && playerTwoName.isEmpty()) || (playerOneName.contains("  ") && (playerTwoName.contains("  ")))) {
				JOptionPane.showMessageDialog(frame, "Please enter two valid names");
			} else {
				enterTwo.setEnabled(false);
				enterTwoTwo.setEnabled(false);
				submitTwoName.setEnabled(false);
				info.setText("It's " + playerOneName + "'s" + "turn");
				startGame();
			}
		}

	}

}
