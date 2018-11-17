
/**
 * @author Emma Dreist
 * 
 * Assignment 1: Aces up
 * Date: 2-2-17
 * Worked with: Lauren, Magnus, Amanda, and Kalia 
 * 
 */

import java.util.*;

/**
 * 
 * @author Emma Dreist
 * 
 *         This class makes the controls for the game, it starts by declaring
 *         variables. It creates an array list of cards for the deck, an array
 *         list of array lists of playing cards for the four card piles, an
 *         array list of playing cards for the discard pile, and an integer of
 *         the player's score.
 *
 */
public class GameController {

	// DO NOT CHANGE OR ADD FIELDS BELOW.
	// YOU MUST USE THESE FIELDS TO IMPLEMENT YOUR GAME.

	private ArrayList<PlayingCard> deck;
	private ArrayList<PlayingCard>[] list;
	private ArrayList<PlayingCard> discardPile;
	private int playerScore;
	// WRITE YOUR CODE AND JAVADOC HERE:
	// SHUFFLE DECK OF CARDS USING Collections.shuffle METHOD

	/**
	 * This method does not take in any parameters. It first creates four new
	 * array lists based off the original list of array lists. It initiates
	 * array lists for the deck and the discard pile. It then creates two new
	 * arrays, one of integers for the ranks of the cards, and one of characters
	 * for the suits of the card. Finally the method creates new cards with the
	 * values from the arrays created above, and adds the cards to the deck,
	 * after the cards are added to the deck, it prints out the cards in the
	 * deck, discard pile, and at the end of each list.
	 */
	public GameController() {
		list = new ArrayList[4];
		list[0] = new ArrayList<PlayingCard>();
		list[1] = new ArrayList<PlayingCard>();
		list[2] = new ArrayList<PlayingCard>();
		list[3] = new ArrayList<PlayingCard>();
		deck = new ArrayList<PlayingCard>();
		discardPile = new ArrayList<PlayingCard>();
		int[] rank = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
		char[] suit = { 'H', 'D', 'S', 'C' };
		for (int i = 0; i <= rank.length - 1; i++) {
			for (int n = 0; n <= suit.length - 1; n++) {
				PlayingCard card = new PlayingCard(rank[i], suit[n]);
				deck.add(card);
			}
		}
	}

	/**
	 * This method returns the current playing card at a specified place in a
	 * specified list. It first checks if the list at the specified location is
	 * empty, if it is it returns null. Then it checks to see if the given index
	 * is valid for the list specified, if the index is not available it returns
	 * null. If both of these tests fail, it returns the card in the specified
	 * list, at the specified index.
	 * 
	 * @param integer:
	 *            listNum
	 * @param integer:
	 *            index
	 * @return Playing card in the list that is given and at the index given
	 */
	public PlayingCard getCard(int listNum, int index) {
		if (list[listNum].isEmpty()) {
			return null;
		} else if (list[listNum].size() - 1 < index) {
			return null;
		} else {
			return list[listNum].get(index);
		}
	}

	/**
	 * This method returns the player's score
	 * 
	 * @return Integer of the player's score
	 */
	public int getScore() {
		return playerScore;
	}

	/**
	 * This method does not return anything. It deals out a four cards from the
	 * deck, one to each list of cards. If first checks to make sure the deck
	 * has at least four cards to deal out, then it loops through each list,
	 * adding one card to the end of each list, removing that card from the deck
	 * and onto the end of the list. It then prints out the cards in the deck,
	 * discard pile, and at the end of each list.
	 */
	public void deal() {
		if (deck.size() >= 4) {
			for (int i = 0; i < 4; i++) {
				list[i].add(deck.remove(0));
			}
		}
		print();
	}

	/**
	 * This method does not take in any parameters as it moves the last card
	 * from the specified list into the discard pile, it then adds the rank of
	 * that card to the player's score. It first creates a new variable of an
	 * array list of playing cards that equals the specified list of cards. It
	 * then checks if that list is not empty, if its not, it creates a new
	 * variable for the card at the end of that list. Then it goes through each
	 * one of the four lists, and when the list is not the one that is specified
	 * and the list is not empty, it creates a new variable for the last card in
	 * that list. It then compares the suits of the two last cards, and if they
	 * are the same it compares the ranks. If the rank of the last card of the
	 * list specified, then that card is added to the discard pile and the rank
	 * is added to the players score. If the suit is not the same or the rank of
	 * the last card on the list specified is smaller than the last card of the
	 * list being looped, the method checks other rows until the criteria are
	 * met to discard the card. It then prints out the cards in the deck,
	 * discard pile, and at the end of each list.
	 * 
	 * @param integer:
	 *            listNum
	 */
	public void discard(int listNum) {
		ArrayList<PlayingCard> discardList = list[listNum];
		if (!discardList.isEmpty()) {
			PlayingCard discarded = discardList.get(discardList.size() - 1);
			for (int i = 0; i < 4; i++) {
				if (i != listNum && list[i].size() != 0) {
					PlayingCard lastCard = list[i].get(list[i].size() - 1);
					if (discarded.getSuit() == lastCard.getSuit()) {
						if (discarded.getRank() > lastCard.getRank()) {
							discardPile.add(discarded);
							discardList.remove(discarded);
							playerScore += discardPile.get(discardPile.size() - 1).getRank();
							print();
						}
					}
				}
			}

		}
	}

	/**
	 * This method lets the user know when they are out of moves. It first
	 * checks if the deck is not empty, if that is true, the program returns
	 * true. It then checks to see if each of the lists are empty, and if the
	 * deck is empty and one of the lists are also empty, the program returns
	 * true. It then checks if any card can be discarded, and if this is true
	 * then the program also returns true, if not the program returns false. It
	 * then prints out the cards in the deck, discard pile, and at the end of
	 * each list.
	 * 
	 * @return boolean
	 */
	public boolean moreMoves() {
		if (!deck.isEmpty()) {
			return true;
		}
		if (deck.isEmpty()
				&& (((list[0].isEmpty()) || (list[1].isEmpty()) || (list[2].isEmpty()) || (list[3].isEmpty())))) {
			return true;
		}
		if (deck.isEmpty()) {
			for (int i = 0; i < list.length; i++) {
				ArrayList<PlayingCard> moveList = list[i];
				if (!moveList.isEmpty()) {
					PlayingCard lastCard = moveList.get(moveList.size() - 1);
					for (int n = 0; n < 4; n++) {
						if (n != i && list[n].size() != 0) {
							PlayingCard mover = list[n].get(list[n].size() - 1);
							if (lastCard.getSuit() == mover.getSuit()) {
								if (lastCard.getRank() > mover.getRank()) {
									return true;
								}
							}
						}
					}
				}
			}
		} else {
			return false;
		}
		return false; 
	}

	/**
	 * This method does not return anything as it allows a card that may not be
	 * discarded to an empty list if one is available. If there is an empty list
	 * it removes the card from the its original list and moves it to the empty
	 * list. To do so the program first checks to make sure the specified list
	 * is not empty, if it contains cards it creates a new variable for the last
	 * card of that given list. Then it goes through the other lists to see if
	 * one is empty. Once it finds an empty list, it moves the card to that
	 * empty list, and removes it from its original list. It then prints out the
	 * cards in the deck, discard pile, and at the end of each list.
	 * 
	 * @param Integer:
	 *            listNum
	 */
	public void move(int listNum) {
		if (!list[listNum].isEmpty()) {
			PlayingCard card = list[listNum].get(list[listNum].size() - 1);
			if (list[0].isEmpty()) {
				list[0].add(card);
				list[listNum].remove(card);
				print();
			} else if (list[1].isEmpty()) {
				list[1].add(card);
				list[listNum].remove(card);
				print();
			} else if (list[2].isEmpty()) {
				list[2].add(card);
				list[listNum].remove(card);
				print();
			} else if (list[3].isEmpty()) {
				list[3].add(card);
				list[listNum].remove(card);
				print();
			}
		}
	}

	/**
	 * This method does not return anything, it starts a new game my removing
	 * all the cards from each list and adding them to the discard pile. It then
	 * takes the cards from the discard pile and adds them back into the deck.
	 * Once all the cards are added to the deck, it shuffles the deck and resets
	 * the player's score to 0. It then prints out the cards in the deck,
	 * discard pile, and at the end of each list.
	 */
	public void startNewGame() {
		for (int i = 0; i < list.length; i++) {
			for (int n = list[i].size() - 1; n >= 0; n--)
				discardPile.add(list[i].remove(n));
		}
		for (int i = discardPile.size() - 1; i >= 0; i--) {
			deck.add(discardPile.remove(i));
		}
		print();
		Collections.shuffle(deck);
		playerScore = 0;
		deal();
	}

	/**
	 * This class returns a boolean of if you have or haven't won the game. It
	 * first checks to make sure the deck is empty, and if it is it loops
	 * through each one of the lists, and makes sure each one contains an ace.
	 * It then prints out the cards in the deck, discard pile, and at the end of
	 * each list.
	 * 
	 * @return
	 */
	public boolean win() {
		int counter = 0;
		if (deck.isEmpty()) {
			for (int i = 0; i < list.length; i++) {
					if (list[i].get(0).getRank() == 1 && list[i].size() ==1) {
						counter += 1;
					}
					if (counter == 4) {
						return true;
					} else {
						return false;
				}
			}
		}
		return false;

	}

	/**
	 * This method prints out the number of cards in the deck, and in discard
	 * pile. It then prints out the last card in each of the lists.
	 */
	public void print() {
		System.out.print("deck: " + deck.toString());
		System.out.print("list 1: " + list[0].toString());
		System.out.print("list 2: " + list[1].toString());
		System.out.print("list 3: " + list[2].toString());
		System.out.print("list 4: " + list[3].toString());
		System.out.print("discard pile: " + discardPile.toString());

	}
}