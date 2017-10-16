package paulosergio.basketcalculator;

import paulosergio.basketcalculator.RebateTable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;

/**
 * Represents a Shopping Basket Calculator. it calculates all possible
 * combination of books in the shopping basket. The calculator must
 * automatically calculate the largest rebate possible.
 *
 * @author Paulo Sergio Mendonca
 * @version 1.0;
 */
public class ShoppingBasketCalculator {

	/*
	 * Processes a shopping basket with Potter's books and apply rebates according
	 * to the rules
	 * 
	 * @param booksInBasket a list of books in the basket
	 * 
	 * @return the final cost of the shopping basket
	 */
	public static final float processCheckout(Integer[] booksInBasket) {
		float rebate;
		if (booksInBasket.length <= 0) {
			// When basket is empty
			rebate = RebateTable.NO_BOOKS;
			return rebate;
		} else {
			// basket's not empty
			Map<Integer, Integer> mappedBooks = mapBasketByBookType(booksInBasket);
			Integer[] booksByType = groupBooksByType(mappedBooks);
			Arrays.sort(booksByType, Collections.reverseOrder());
			Integer[] groupsForRebate = countGroups(booksByType);
			adjustCheaperRebates(groupsForRebate);

			return calculateFinalPrice(groupsForRebate);
		}
	}

	/*
	 * Identifies and maps different types of books in the basket
	 * 
	 * @param booksInBasket list of books in the basket
	 * 
	 * @return mappedBooks a map of books and types
	 */
	private static Map<Integer, Integer> mapBasketByBookType(Integer[] booksInBasket) {
		Map<Integer, Integer> mappedBooks = new HashMap<Integer, Integer>();
		for (int i = 0; i < booksInBasket.length; i++) {
			if (mappedBooks.get(booksInBasket[i]) == null) {
				mappedBooks.put(booksInBasket[i], 0);
			}
			mappedBooks.put(booksInBasket[i], mappedBooks.get(booksInBasket[i]) + 1);
		}
		return mappedBooks;
	}

	/*
	 * Groups books by type in a list of types and its amount
	 * 
	 * @param basket the list of books in basket
	 * 
	 * @param booksInBasket
	 * 
	 * @return booksByType the list of grouped books Every position means the amount
	 * of books of the type. i.e: booksByType[0] represents number of books of the
	 * type1
	 */
	private static Integer[] groupBooksByType(Map<Integer, Integer> mappedBooks) {
		Integer[] booksByType = { 0, 0, 0, 0, 0 };
		Set<Integer> books = mappedBooks.keySet();
		for (int livro : books) {
			booksByType[livro - 1] = mappedBooks.get(livro);
		}
		return booksByType;
	}

	/*
	 * Counts how many groups of books can be formed
	 * 
	 * @param booksByType list of books sorted by type
	 * 
	 * @return groups a list of groups candidates to have a rebate applied
	 */
	private static Integer[] countGroups(Integer[] booksByType) {
		Integer[] groups = { 0, 0, 0, 0, 0 };
		int aux = booksByType[0];
		for (int i = 0, counter = 0; i < aux; i++) {
			for (int j = 0; j < booksByType.length; j++) {
				if (booksByType[j] >= 1) {
					counter++;
					booksByType[j]--;
				}
			}
			if (counter > 0) {
				groups[counter - 1]++;
			}
			counter = 0;
		}
		return groups;
	}

	/*
	 * replaces every pair of 5 distinct books and 3 distinct books by a 2 groups of
	 * 4 distinct books, which are cheaper.
	 * 
	 * @param groupsOfBooks list of groups candidate to have a rebate
	 */
	private static void adjustCheaperRebates(Integer[] groupsOfBooks) {
		int pairsOf3And5 = (groupsOfBooks[2] <= groupsOfBooks[4]) ? groupsOfBooks[2] : groupsOfBooks[4];
		int numberOfPairs = 0;
		for (int i = 0; i < pairsOf3And5; i++) {
			numberOfPairs++;
		}
		groupsOfBooks[2] = groupsOfBooks[2] - numberOfPairs;
		groupsOfBooks[4] = groupsOfBooks[4] - numberOfPairs;
		groupsOfBooks[3] = groupsOfBooks[3] + (2 * numberOfPairs);
	}

	/*
	 * applies a discount in the value according to the number of different books in
	 * the group
	 * 
	 * @param orderValue total cost of the group of books
	 * 
	 * @param rebatePercentage percentage to be applied over the total cost
	 * 
	 * @return a new value which have been discounted
	 */
	public static final float applyRebate(float orderValue, float rebatePercentage) {
		return orderValue * rebatePercentage;
	}

	/*
	 * Calculates the total cost of the basket
	 * 
	 * @param groupedBooks list of books grouped by different types
	 * 
	 * @return the final cost of the shopping basket
	 */
	private static float calculateFinalPrice(Integer[] groupedBooks) {
		float groupPriceWithoutRebate = 0.0f;
		float finalPrice = 0.0f;
		for (int i = 0; i < groupedBooks.length; i++) {
			groupPriceWithoutRebate = ((i + 1) * groupedBooks[i] * 8.0f);
			finalPrice = finalPrice
					+ ShoppingBasketCalculator.applyRebate(groupPriceWithoutRebate, RebateTable.getRebate(i + 1));
		}

		return finalPrice;
	}
}
