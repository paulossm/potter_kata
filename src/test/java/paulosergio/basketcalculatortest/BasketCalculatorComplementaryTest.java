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


import paulosergio.basketcalculator.ShoppingBasketCalculator;
import paulosergio.basketcalculator.RebateTable;

@RunWith(value = Parameterized.class)
public class BasketCalculatorComplementaryTest {
	/**	 
	 * supplies the BasketCalculatorComplementaryTest class with test cases defined by myself
	 * to complement defined test cases.
	 *
	 * @return a collection of test cases and expected values.
	 */
	@Parameters(name = "{0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{"Test Full Price - No books", new Integer[] {}, 0.0f * RebateTable.NO_BOOKS},
			{"Test Full Price - one book of type 5", new Integer[] {5}, 8.0f * RebateTable.NO_REBATE_1_BOOK},
			{"Test Full Price - four books of same type", new Integer[] {4,4,4,4}, (8.0f * 4.0f * RebateTable.NO_REBATE_1_BOOK)},
			{"Test Full Price - five books of same type", new Integer[] {3,3,3,3,3}, (8.0f * 5.0f * RebateTable.NO_REBATE_1_BOOK)},
			
			{"Test 1 Full Price + 25% Rebate", new Integer[] {1, 1, 2, 3, 4, 5}, (8.0f + (8.0f * 5.0f * RebateTable.REBATE_5_BOOKS))},
			{"Test 1 Full Price + 20% Rebate", new Integer[] {1, 1, 2, 3, 4}, (8.0f + (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS))},
			{"Test 1 Full Price + 10% Rebate", new Integer[] {1, 1, 2, 3}, (8.0f + (8.0f * 3.0f * RebateTable.REBATE_3_BOOKS))},
			
			{"Test 2x 10% Rebate", new Integer[] {1, 1, 2, 2, 3, 3}, (2 * (8.0f * 3.0f * RebateTable.REBATE_3_BOOKS))},
			{"Test 2x 20% Rebate", new Integer[] {1, 1, 2, 2, 3, 3, 4, 4}, (2 * (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS))},
			{"Test 2x 25% Rebate", new Integer[] {1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, (2 * (8.0f * 5.0f * RebateTable.REBATE_5_BOOKS))},
			
			{"Test 10% + 20% Rebate", new Integer[] {1, 1, 2, 2, 3, 3, 4}, (8.0f * 3.0f * RebateTable.REBATE_3_BOOKS) + (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS)},
			
			{"Test 6x 20% Rebate instead of 3x 10% + 3x 25% Rebates", new Integer[] {
					1, 2, 3, 1, 2, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 1, 2, 3, 4, 5
					}, (6 * (8.0f * 4.0f * RebateTable.REBATE_4_BOOKS))},
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

	public BasketCalculatorComplementaryTest() {}
	
	/*
	 * Test implementation.
	 * 
	 * This is supplied by the parameters method data()
	 */
	@Test
	public void basketCalculatorComplementaryTest() {
		float computedPrice = ShoppingBasketCalculator.processCheckout(shoppingBasket);
		assertThat(expectedPrice, is(equalTo(computedPrice)));
	}
}
