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
	}	// end setMixedNumber

	
	
	public void setMixedNumber(int integerPart, 
		int fractionNumerator, int fractionDenominator)
	{
		this.integer = integerPart;	
		Fraction f = new Fraction();
		f.setFraction(fractionNumerator, fractionDenominator);
		this.setMixedNumber(integerPart, f);
	}	// end setMixedNumber

	public int getIntegerPart()
	{	
		if(this.integer < 0)
			this.sign = '-';
		
		return this.integer;	
	}	// end getInteger

	public FractionInterface getFractionPart()
	{
		return fraction;
	}	// end getFraction

	public MixedNumberInterface add(MixedNumberInterface operand)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)operand;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.add(f2);
		MixedNumber mixResult = new MixedNumber(0,fractionResult);
		return mixResult;
			
	} // end add

	public MixedNumberInterface subtract(MixedNumberInterface operand)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)operand;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.subtract(f2);
		MixedNumber mixResult = new MixedNumber(0,fractionResult);
		return mixResult;
	}	// end subtract

	public MixedNumberInterface multiply(MixedNumberInterface multiplier)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)multiplier;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.multiply(f2);
		MixedNumber mixResult = new MixedNumber(0,fractionResult);
		return mixResult;
	}	// end multiply

	public MixedNumberInterface divide(MixedNumberInterface divisor)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)divisor;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		Fraction fractionResult = (Fraction) f1.divide(f2);		
		MixedNumber mixResult = new MixedNumber(0,fractionResult);
		return mixResult;
	}	// end divide


	public boolean equals(Object other)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)other;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		boolean isEqual = f1.equals(f2);
		return isEqual;
	} // end equals


	public int compareTo(MixedNumber other)
	{
		Fraction f1 = (Fraction)this.getFractionalEquivalent();
		MixedNumber m1 = (MixedNumber)other;
		Fraction f2 = (Fraction)m1.getFractionalEquivalent();
		int compareResult = f1.compareTo(f2);
		return compareResult;	
	} // end compareTo

	
	public String toString()
	{
		if(this.fraction != null){
			if(this.integer != 0){
				if(this.sign == '-'){
					return String.valueOf(this.sign) + Math.abs(this.integer) + " " + Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());
				}else{
					return Math.abs(this.integer) + " " + Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());
				}	
			}else{
				if(this.sign == '-'){
					return String.valueOf(this.sign) + Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());
				}else{
					return Math.abs(this.fraction.getNumerator()) + "/" + Math.abs(this.fraction.getDenominator());				
			
				}
			}
		}else{
			return "";
		}
	}
	
	// Private method: reduce a MixedNumber object to lowest term 
        // E.g. -3 50/7 --> -10 1/7;  3 25/10 --> 5 1/2
	private void reduceToLowestForm()
	{
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
	}	

	private void setSign()
	{
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
		}
	}	
	private char getSign()
	{
		return this.sign;
	}
	// You may add more private methods
} // end MixedNumber

