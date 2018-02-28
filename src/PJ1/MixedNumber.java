/**
 *********************************************************************************
 *
 * This class represents a mixed number which consist of sign (+ or -), integer 
 * and fraction parts of a number. Example: -10 3/5, 0 1/2, -0 3/4, 4 5/6
 * 
 * Requirements:
 * 1. Must use the given Fraction and FractionInterface to store fraction part
 * 2. Implement interfaces: MixedNumberInterface and Comparable (i.e. compareTo())
 * 3. Implement methods equals() and toString() from class Object
 * 4. Must work for both positive and negative mixed numbers
 * 5. Must reduce to mixed number to lowest term, e.g. 3 22/4 --> 8 1/2 
 * 6. Must reduce result mixed number to lowest term for operations 
 *    add(), subtract(), multiply() and divide(), e.g. see test cases
 * 7. For input 2 3/-10 & 2 -3/-10, must convert them to 
 *    -2 3/10 & 2 3/10 respectively (see Hint 2. below)
 * 8. Must throw only Project1Exception in case of errors
 * 9. Must not add new or modify existing data fields
 * 10.Must not add new or modify existing public methods
 * 11.May add private methods
 *
 * More info:
 *
 * 1. You need to downcast reference parameter MixedNumberInterface to 
 *    MixedNumber if you want to use it as MixedNumber. 
 *    See add, subtract, multiply and divide methods
 *
 * 2. The following private methods will be useful
 *
 *    // Private method: reduce a MixedNumber object to lowest term 
 *    // E.g. -3 50/7 --> -10 1/7;  3 25/10 --> 5 1/2
 *    private void reduceToLowestForm()
 *
 *    // Private method: convert a MixedNumber to new Fraction object
 *    // object. E.g. -7 1/7 --> -50/7;  3 1/8 --> 25/8
 *    private FractionInterface getFractionalEquivalent()
 *           
 * 3. Use "this" to access this object if it is needed
 *
 * 4. You need to downcast reference parameter FractionInterface to Fraction if  
 *    you want to use it as Fraction. 
 *
 * 5. More Fraction class info
 *
 *    It works for both positive and negative fractions
 *    For input 3/-10 & -3/-10, they are converted to -3/10 & 3/10 respectively 
 *    All fraction objects are reduced to lowest term, e.g. 10/20 --> 1/2
 *    Additional methods in Fraction:
 *
 *		public Fraction()  // default value: 0/1
 *		public Fraction(int num, int den)
 *		public boolean equals(Object other)
 *		public int compareTo(Fraction other)
 *		public String toString() // e.g. "3/5" or "-10/11"
 *
 ************************************************************************************/


package PJ1;

public class MixedNumber implements MixedNumberInterface, Comparable<MixedNumber>
{

        // Fields:

	// both integer and fraction parts are forced to >= 0
	// sign of a mixed number is stored as '+' or '-'

	private char              sign;	    // '+' or '-'
	private int               integer;  // whole number portion >= 0
	private FractionInterface fraction; // fractional portion in lowest terms;


	// Methods:

	private void setSign()
	{
		//Fraction f = new Fraction(integer, 1);
		//this.sign = f.getSign();
		
		
		if ((integer > 0) && fraction.getSign() == '+')
			this.sign = '+';
		else if((integer < 0) && fraction.getSign() == '-'){
			this.sign = '+';
			fraction.setSign('+');
		}
		else if ((integer == 0) && fraction.getSign() == '-'){
			this.sign = '-';
			this.fraction.setSign('+');
		}
		else if ((integer == 0) && fraction.getSign() == '+'){
			this.sign = '+';
		}

		else if((integer > 0) && fraction.getSign() == '-'){
			this.sign = '-';
			this.fraction.setSign('+');
			this.integer = -1 * this.integer;
		}
		else if((integer < 0) && fraction.getSign() == '+'){
			this.sign = '-';
			//this.integer = -1 * this.integer;
		}
		
	}	
	private char getSign()
	{
		return this.sign;
	}
	
	public MixedNumber()
	{
		setMixedNumber(0, 0, 1);
	}	// end default constructor

	public MixedNumber(int integerPart, int fractionNumerator, 
				int fractionDenominator)
	{
				
                setMixedNumber(integerPart, fractionNumerator, fractionDenominator);
	}	// end constructor

	public MixedNumber(int integerPart, FractionInterface fractionPart)
	{
		setMixedNumber(integerPart, fractionPart);
	}	// end constructor


	public void setMixedNumber(int integerPart, FractionInterface fractionPart)
	{	
		this.integer = integerPart;
		
		this.fraction = fractionPart;
		if(fractionPart != null){
			this.reduceToLowestForm();
			this.setSign();
		}
		//System.out.println("Mixed Number set. Integer - " + String.valueOf(this.integer)+ ". Fraction - " + fraction.toString());
		
		// add statements
		// set this object to the given values
		// make sure to reduce to lowest term 
		// check for exception cases
	}	// end setMixedNumber

	
	
	public void setMixedNumber(int integerPart, 
		int fractionNumerator, int fractionDenominator)
	{

		this.integer = integerPart;
		
		
		
		Fraction f = new Fraction();
		f.setFraction(fractionNumerator, fractionDenominator);
		this.setMixedNumber(integerPart, f);
		//this.reduceToLowestForm();
		// add statements
		// set this object to the given values
		// make sure to reduce to lowest term 
		// check for exception cases
	}	// end setMixedNumber

	public int getIntegerPart()
	{
		// add statements
		// retrieve integer portion wth the correct sign
		if(this.integer < 0)
			//this.integer = -1*this.integer;
			this.sign = '-';
		
		return this.integer;
		//return this.getIntegerPart();
		//return 0;  // change this
	}	// end getInteger

	public FractionInterface getFractionPart()
	{
		// retrieve fraction portion, assume + value
		return fraction;
	}	// end getFraction

	public MixedNumberInterface add(MixedNumberInterface operand)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)operand;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.add(f2);
		MixedNumber mixResult = new MixedNumber(0,fractionResult);
		//mixResult.reduceToLowestForm();
		return mixResult;
				// add statements
		// convert MixedNumber object to Fraction object
		// Use Fraction's add() method to obtain Fraction result
		// convert result to a new lowest term MixedNumber object
		// hint: return new MixedNumber(0,result);
		//return null; // change it
	} // end add

	public MixedNumberInterface subtract(MixedNumberInterface operand)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)operand;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.subtract(f2);
		MixedNumber mixResult = new MixedNumber(0,fractionResult);
		//mixResult.reduceToLowestForm();
		return mixResult;

		// add statements
		// convert MixedNumber object to Fraction object
		// Use Fraction's substract() method to obtain Fraction result
		// convert result to a new lowest term MixedNumber object
		// hint: return new MixedNumber(0,result);
		//return null; // change it
	}	// end subtract

	public MixedNumberInterface multiply(MixedNumberInterface multiplier)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)multiplier;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.multiply(f2);
		MixedNumber mixResult = new MixedNumber(0,fractionResult);
		//mixResult.reduceToLowestForm();
		return mixResult;

		// add statements
		// convert MixedNumber object to Fraction object
		// Use Fraction's multiply() method to obtain Fraction result
		// convert result to a new lowest term MixedNumber object
		// hint: return new MixedNumber(0,result);
		//return null; // change it
	}	// end multiply

	public MixedNumberInterface divide(MixedNumberInterface divisor)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)divisor;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.divide(f2);
		
			MixedNumber mixResult = new MixedNumber(0,fractionResult);
			//mixResult.reduceToLowestForm();
		return mixResult;

		// add statements
		// convert MixedNumber object to Fraction object
		// Use Fraction's divide() method to obtain Fraction result
		// convert result to a new lowest term MixedNumber object
		// hint: return new MixedNumber(0,result);
		// check for exception cases
		//return null; // change it
	}	// end divide


	public boolean equals(Object other)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)other;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		boolean isEqual = f1.equals(f2);
		//mixResult.reduceToLowestForm();
		return isEqual;

		// add statements
		// possible solution:
                // convert MixedNumber objects to Fraction objects
		// Use Fraction's equals() method to obtain boolean result
		//return false; // change it
	} // end equals


	public int compareTo(MixedNumber other)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)other;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.subtract(f2);
		
		int compareResult = f1.compareTo(f2);
		return compareResult;
		//MixedNumber mixResult = new MixedNumber(0,fractionResult);
		//mixResult.reduceToLowestForm();
		
		//return mixResult;

		// add statements
		// possible solution:
                // convert MixedNumber objects to Fraction objects
		// Use Fraction's compareTo() method to obtain result
		//return 0; // change it
	} // end compareTo

	
	public String toString()
	{
		if(this.fraction != null){
			
			if(this.integer != 0){
				if(this.sign == '-')
					return String.valueOf(this.sign) + Math.abs(this.integer) + " " + Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());
				else
					return Math.abs(this.integer) + " " + Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());
					
			}else{
				if(this.sign == '-')
					return String.valueOf(this.sign) + Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());
				else
					return Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());
				
			}
		}else{
			return "";
		}
	}
	
	/*
	 * good
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public String toString2()
	{
		if(this.fraction != null){
//			return  String.valueOf(this.integer) + " " + this.fraction.toString();
			
			if(this.integer < 0)
			{
				return String.valueOf(this.integer) + " " + this.fraction.toString();
			}else if(this.integer > 0){
				return String.valueOf(this.integer) + " " + this.fraction.toString();
			}else{
				return this.fraction.toString();
			}
		}
			else{
				return "";
			}
		// possible solution:
		// together with sign, integer and Fraction's toString() method 
                // to obtain string value
		// add statements
		//return null; // change it
	} // end toString


	// Private method: reduce a MixedNumber object to lowest term 
        // E.g. -3 50/7 --> -10 1/7;  3 25/10 --> 5 1/2
	private void reduceToLowestForm()
	{
		// add statements
		if(fraction == null)
			return;
		else{
			int num = Math.abs(this.fraction.getNumerator());
			int den = Math.abs(this.fraction.getDenominator());
			if(num > den){
				int tempIntPart = num/den;
				int newInt = Math.abs(this.getIntegerPart()) + tempIntPart;
				int newMod = num%den;
				Fraction newFr = new Fraction(newMod,den);
				this.integer = newInt;
				newFr.setSign(this.fraction.getSign());
				this.fraction = newFr;
				
			}
		}
		
	}	


	// Private method: convert a MixedNumber to new Fraction object
        // object. E.g. -7 1/7 --> -50/7;  3 1/8 --> 25/8
	private FractionInterface getFractionalEquivalent()
	{
		int tempIntPart = Math.abs(this.getIntegerPart());
		int num = tempIntPart * this.fraction.getDenominator() + this.fraction.getNumerator();
		Fraction f = new Fraction(num,this.fraction.getDenominator()); 
		f.setSign(this.getSign());
		return f;
		// add statements
		//return null; // changeit
	}	

	// You may add more private methods
} // end MixedNumber

