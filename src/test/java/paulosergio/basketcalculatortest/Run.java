package paulosergio.basketcalculatortest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Run {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(BasketCalculatorTestSuite.class);

	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
		 
	      if(result.wasSuccessful()) {
	    	  System.out.println("Tests were run with success.");
	    	  System.out.println("from " + result.getRunCount() + " tests, " + result.getFailureCount() + " failed.");
	      }
	}

}
