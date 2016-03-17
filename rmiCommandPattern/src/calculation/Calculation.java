package calculation;

import java.math.BigDecimal;

/**
 * Interface that describes a calculation.
 * 
 * @author Daniel May, Michael Borko
 * @version 20160317.1
 *
 */
public interface Calculation {

	/**
	 * calculates stores the result
	 */
	public void calculate();

	/**
	 * returns the stored result
	 * 
	 * @return result as BigDecimal
	 */
	public BigDecimal getResult();
}