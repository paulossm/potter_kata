package paulosergio.basketcalculatortest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import paulosergio.basketcalculator.RebateTable;
import paulosergio.basketcalculator.ShoppingBasketCalculator;

@RunWith(value = Parameterized.class)
public class BasketCalculatorTest {
	/**	 
	 * supplies the BasketCalculatorTest class with test cases and expected values.
	 *
	 * @return a collection of test cases and expected values.
	 */
	@Parameters(name = "{0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			// Basic Test Cases
			{"Test Full Price - No books", new Integer[] {}, 0.0f * RebateTable.NO_BOOKS},
			{"Test Full Price - one book of type 1", new Integer[] {1}, 8.0f * RebateTable.NO_REBATE_1_BOOK},
			{"Test Full Price - one book of type 2", new Integer[] {2}, 8.0f * RebateTable.NO_REBATE_1_BOOK},
			{"Test Full Price - one book of type 3", new Integer[] {3}, 8.0f * RebateTable.NO_REBATE_1_BOOK},
			{"Test Full Price - one book of type 4", new Integer[] {4}, 8.0f * RebateTable.NO_REBATE_1_BOOK},
			{"Test Full Price - one book of type 5", new Integer[] {5}, 8.0f * RebateTable.NO_REBATE_1_BOOK},
			{"Test Full Price - two books of same type", new Integer[] {3, 3}, (8.0f * 2.0f * RebateTable.NO_REBATE_1_BOOK)},
			{"Test Full Price - three books of same type", new Integer[] {2,2,2}, (8.0f * 3.0f * RebateTable.NO_REBATE_1_BOOK)},
			
			//Simple Rebates Test Cases
			{"Test 5% Rebate - two different books", new Integer[] {1, 2}, (8.0f * 2.0f * RebateTable.REBATE_2_BOOKS)},
			{"Test 10% Rebate - three different books", new Integer[] {1, 3, 5}, (8.0f * 3.0f * RebateTable.REBATE_3_BOOKS)},
			{"Test 20% Rebate - four different books", new Integer[] {1, 3, 4, 5}, (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS)},
			{"Test 25% Rebate - five different books", new Integer[] {1, 2, 3, 4, 5}, (8.0f * 5.0f * RebateTable.REBATE_5_BOOKS)},
			
			// Several Rebates Test Cases
			{"Test 1 Full Price + 5% Rebate", new Integer[] {1, 1, 2}, (8.0f + (8.0f * 2.0f * RebateTable.REBATE_2_BOOKS))},
			{"Test 2x 5% Rebate", new Integer[] {1, 1, 2, 2}, (2 * (8.0f * 2.0f * RebateTable.REBATE_2_BOOKS))},
			{"Test 5% + 20% Rebate", new Integer[] {1, 1, 2, 3, 3, 4}, (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS) + (8.0f * 2.0f * RebateTable.REBATE_2_BOOKS)},
			{"Test 1 Full Price + 25% Rebate", new Integer[] {1, 2, 2, 3, 4, 5}, (8.0f + (8.0f * 5.0f * RebateTable.REBATE_5_BOOKS))},
			
			// Edge Cases Test
			{"Test 2x 20% Rebate instead of 10% + 25% Rebates", new Integer[] {1, 1, 2, 2, 3, 3, 4, 5}, (2 * (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS))},
			{"Test 2x 20% + 3x 25% instead of 4x 25% + 10% Rebates", new Integer[] {
					1, 1, 1, 1, 1,
					2, 2, 2, 2, 2,
					3, 3, 3, 3,
					4, 4, 4, 4, 4,
					5, 5, 5, 5},
					(3 * (8.0f * 5.0f * RebateTable.REBATE_5_BOOKS) + 2 * (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS))
			}
		});
	}
	
	@Parameter
    public String message;

	/*
	 * An array that represents a basket of books.
	 * every position means one book and the number inside it represents the type of the book.
	 */
    @Parameter(1)
    public Integer[] shoppingBasket;
    
    /*
	 * expected price of the basket after applying rebates.
	 */
    @Parameter(2)
    public float expectedPrice;

	public BasketCalculatorTest() {}
	
	/*
	 * Test implementation.
	 * 
	 * This is supplied by the parameters method data()
	 */
	@Test
	public void basketCalculatorTest() {
		float computedPrice = ShoppingBasketCalculator.processCheckout(shoppingBasket);
		assertThat(expectedPrice, is(equalTo(computedPrice)));
	}
}
