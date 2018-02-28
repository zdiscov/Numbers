package PJ1;
public class Fraction implements FractionInterface, Comparable < Fraction > {
 private int num;private int den;public Fraction() {
  setFraction(0, 1);
 }
 public Fraction(int num, int den) {
  setFraction(num, den);
 }
 public void setFraction(int num, int den) {
  this.num = num;
  if (den == 0) throw new Project1Exception("Denominator is 0");
  this.den = den;
  adjustSigns();
  reduceFractionToLowestTerms();
 }
 public char getSign() {
  char sign;
  if (num >= 0) sign = '+';
  else sign = '-';
  return sign;
 }
 public void setSign(char sign) {
  num = Math.abs(num);
  den = Math.abs(den);
  if (sign == '-') num = -num;
 }
 public int getNumerator() {
  return num;
 }
 public int getDenominator() {
  return den;
 }
 public FractionInterface add(FractionInterface secondFraction) {
  Fraction op = (Fraction) secondFraction;
  int newNumerator = num * op.den + op.num * den;
  int newDenominator = den * op.den;
  Fraction r = new Fraction(newNumerator, newDenominator);
  return r;
 }
 public FractionInterface subtract(FractionInterface secondFraction) {
  Fraction op = (Fraction) secondFraction;
  int newNumerator = num * op.den - op.num * den;
  int newDenominator = den * op.den;
  Fraction r = new Fraction(newNumerator, newDenominator);
  return r;
 }
 public FractionInterface multiply(FractionInterface secondFraction) {
  Fraction mult = (Fraction) secondFraction;
  int newNumerator = num * mult.num;
  int newDenominator = den * mult.den;
  Fraction r = new Fraction(newNumerator, newDenominator);
  return r;
 }
 public FractionInterface divide(FractionInterface secondFraction) {
  Fraction div = (Fraction) secondFraction;
  if (div.num == 0) throw new Project1Exception("Divisor is 0");
  int newNumerator = num * div.den;
  int newDenominator = den * div.num;
  Fraction r = new Fraction(newNumerator, newDenominator);
  return r;
 }
 public boolean equals(Object other) {
  boolean result;
  if ((other == null) || (getClass() != other.getClass())) result = false;
  else {
   Fraction otherFraction = (Fraction) other;
   int one = num * otherFraction.den;
   int two = den * otherFraction.num;
   result = (one == two);
  }
  return result;
 }
 public int compareTo(Fraction other) {
  int result = 0;
  int one = num * other.den;
  int two = den * other.num;
  if (one < two) result = -1;
  else if (one > two) result = +1;
  return result;
 }
 public String toString() {
  return num + "/" + den;
 }
 private void reduceFractionToLowestTerms() {
  int gcd = GCD(Math.abs(num), Math.abs(den));
  num = num / gcd;
  den = den / gcd;
 }
 private int GCD(int integerOne, int integerTwo) {
  int result;
  if (integerOne % integerTwo == 0) result = integerTwo;
  else result = GCD(integerTwo, integerOne % integerTwo);
  return result;
 }
 private void adjustSigns() {
  if (((num > 0) && (den < 0)) || ((num < 0) && (den < 0))) {
   num = -num;
   den = -den;
  }
 }
}