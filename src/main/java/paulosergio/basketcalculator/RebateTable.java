package paulosergio.basketcalculator;

/**
 * Represents a Table of rebate percentage according to number of different books in the basket
 *
 * @author Paulo Sergio Mendonca
 * @version 1.0;
 */
public final class RebateTable {
	public static final float NO_BOOKS = 0.0f;
	public static final float NO_REBATE_1_BOOK = 1.0f;
	public static final float REBATE_2_BOOKS = 0.95f;
	public static final float REBATE_3_BOOKS = 0.90f;
	public static final float REBATE_4_BOOKS = 0.80f;
	public static final float REBATE_5_BOOKS = 0.75f;
	
	public static final float[] REBATE_TABLE = { NO_BOOKS, NO_REBATE_1_BOOK, REBATE_2_BOOKS, REBATE_3_BOOKS,
			REBATE_4_BOOKS, REBATE_5_BOOKS };

	private RebateTable() {}
	
	/**
	 * Accesses the percentage applicable to the cost of the books
	 * @param numberOfBooks the number of different books in the group
	 * @return percentage of the total value applicable to the group
	 */
	public static float getRebate(int numberOfBooks) {
		return REBATE_TABLE[numberOfBooks];
	}
}
