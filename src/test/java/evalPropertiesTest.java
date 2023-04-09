import java.nio.charset.StandardCharsets;

import ast.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import ql.QLLexer;
import ql.QLParser;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import ast.NExp;
import net.jqwik.api.*;
import org.assertj.core.api.Assertions;


public class evalPropertiesTest {
    static int[][] salesArray;
    Eval eval;

    private static final int MIN_DIMENSION = 5;
    private static final int MAX_DIMENSION = 10;
    private static final int MIN_SALES_VALUE = 0;
    private static final int MAX_SALES_VALUE = 100;


    public static int[][] generateRandomSalesArray() {
        Random random = new Random();
        int rows = MIN_DIMENSION + random.nextInt(MAX_DIMENSION - MIN_DIMENSION + 1);
        int cols = MIN_DIMENSION + random.nextInt(MAX_DIMENSION - MIN_DIMENSION + 1);

        int[][] salesArray = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                salesArray[i][j] = MIN_SALES_VALUE + random.nextInt(MAX_SALES_VALUE - MIN_SALES_VALUE + 1);
            }
        }

        return salesArray;
    }

    @BeforeProperty
    void setUp() {
        salesArray = generateRandomSalesArray();
        eval = new Eval(salesArray);
    }

    // ======================================= Num Expression ==========================================================

    // Property
    @Property
    @Report(Reporting.GENERATED)
    void propNatNotMinus(@ForAll @IntRange(min=1,max=10) Integer n){
        int result = new Eval(salesArray).eval(
                (NExp) parse(Integer.toString(n))
        );
        Assertions.assertThat(result).isNotEqualTo(-1);
    }

    // Property Nat
    @Property
    @Report(Reporting.GENERATED)
    void propNat(@ForAll @IntRange(min=1,max=MIN_DIMENSION) Integer n){
        int result = new Eval(salesArray).eval(
                (NExp) parse(Integer.toString(n))
        );
        Assertions.assertThat(result).isEqualTo(n);
    }

    // Check the property of salesForM function
    @Example
    void propSalesForM() throws IOException {
        int result = new Eval(salesArray).eval(
                (NExp) parse("salesForM(M)")
        );
        int totalS = 0;
        for(int i = 0;i < salesArray.length;i++){
            for(int j = 0; j < salesArray[0].length;j++){
                totalS += salesArray[i][j];
            }
        }
        Assertions.assertThat(result).isEqualTo(totalS);
    }

    // Check the property of salesAt function
    @Property
    @Report(Reporting.GENERATED)
    void propSalesAt(@ForAll @IntRange(min=1,max=MIN_DIMENSION) Integer p, @ForAll @IntRange(min=1,max=MIN_DIMENSION) Integer d) throws IOException {
        int result = new Eval(salesArray).eval(
                (NExp) parse("M[" + Integer.toString(p)+","+Integer.toString(d)+"]")
        );
        Assertions.assertThat(result).isEqualTo(salesArray[p-1][d-1]);
    }

    // Check the property of salesForP function
    @Property
    @Report(Reporting.GENERATED)
    void propSalesP(@ForAll @IntRange(min=1,max=MIN_DIMENSION) Integer p) throws IOException {
        // readData();
        int result = new Eval(salesArray).eval(
                (NExp) parse("salesForP(M," + p + ")")
        );

        int salesP = 0;
        for (int d=0; d<salesArray[0].length; d++){
            salesP += salesArray[p-1][d];
        }
        Assertions.assertThat(salesP).isEqualTo(result);
    }

    // Check the property of salesForD function
    @Property
    @Report(Reporting.GENERATED)
    void propSalesD(@ForAll @IntRange(min=1,max=MIN_DIMENSION) Integer d) throws IOException {
        // readData();
        int result = new Eval(salesArray).eval(
                (NExp) parse("salesForD(M," + d + ")")
        );

        int salesD = 0;
        for (int p=0; p<salesArray.length; p++){
            salesD += salesArray[p][d-1];
        }

        Assertions.assertThat(salesD).isEqualTo(result);
    }

    // Sum
    @Property
    @Report(Reporting.GENERATED)
    void testNumSum(@ForAll @IntRange(min=1,max=10) Integer n, @ForAll @IntRange(min=1,max=10) Integer m){
        int result = new Eval(salesArray).eval(
                (NExp) parse(Integer.toString(n) + " + " + Integer.toString(m))
        );

        Assertions.assertThat(result).isEqualTo(n+m);
    }

    // Diff
    @Property
    @Report(Reporting.GENERATED)
    void testNumDiff(@ForAll @IntRange(min=1,max=10) Integer n, @ForAll @IntRange(min=1,max=10) Integer m){
        int result = new Eval(salesArray).eval(
                (NExp) parse(Integer.toString(n) + " - " + Integer.toString(m))
        );

        Assertions.assertThat(result).isEqualTo(n-m);
    }

    // Number Product
    @Property
    @Report(Reporting.GENERATED)
    void testNumPro(@ForAll @IntRange(min=1,max=10) Integer n, @ForAll @IntRange(min=1,max=10) Integer m){
        int result = new Eval(salesArray).eval(
                (NExp) parse(Integer.toString(n) + " * " + Integer.toString(m))
        );

        Assertions.assertThat(result).isEqualTo(n*m);
    }

    // Division
    @Property
    @Report(Reporting.GENERATED)
    void testDivideByZero(@ForAll @IntRange(min=1,max=10) Integer n) throws BaseEval.DivisionByZeroException {
        Assertions.assertThatThrownBy(() ->
                new Eval(salesArray).eval(
                (NExp) parse("4 / 0")
                )).isInstanceOf(BaseEval.DivisionByZeroException.class);
    }

    // test function size for day
    @Example
    void testSizeD() throws IOException {
        int result = new Eval(salesArray).eval(
                (NExp) parse("size(Day)")
        );
        Assertions.assertThat(result).isEqualTo(salesArray[0].length);
    }

    // test function size for product
    @Example
    void testSizeP() throws IOException {
        // readData();
        int result = new Eval(salesArray).eval(
                (NExp) parse("size(Prod)")
        );
        Assertions.assertThat(result).isEqualTo(salesArray.length);
    }

    // ====================================  set expression testing ================================================


    @Provide
    Arbitrary<String> fixedS() {
        return Arbitraries.of("Day", "Prod");
    }

    // test for 2 dimension variables set expression
    @Property
    void testSexp(@ForAll("fixedS") String str){
        Set<Integer> result = new Eval(salesArray).eval(
                (SExp) parse(str)
        );

        int size = 0;
        if (str=="Day"){
            size = salesArray[0].length;
        } else if (str=="Prod") {
            size = salesArray.length;
        }
        Set<Integer> expected = new HashSet<>();
        for(int i=1; i<=size; i++)
            expected.add(i);
        Assertions.assertThat(expected).isEqualTo(result);
    }

    @Example
    public void testSale() {
        Set<Integer> result = new Eval(salesArray).eval(
                (SExp) parse("Sale")
        );

        Set<Integer> expected = new HashSet<>();
        for (int[] ints : salesArray)
            for (int anInt : ints)
                expected.add(anInt);
        Assertions.assertThat(expected).isEqualTo(result);
    }


    @Provide
    Arbitrary<String> com() {
        return Arbitraries.of(">", "<","=");
    }

    // test set comprehension
    @Property
    void testSetComprehension(@ForAll("fixedS") String str,@ForAll("com") String c,@ForAll @IntRange(min=1,max=MIN_DIMENSION) Integer i){
        String s = "";
        if (str=="Day"){
            s = "d";
        } else if (str=="Prod") {
            s = "p";
        }
        //"{d : Day | d > 2}"
        String input = "{" + s + " : " + str + " | " + s + " " + c + " " + i + "}";
        Set<Integer> result = new Eval(salesArray).eval(
                (SExp) parse(input)
        );
        int min = 0;
        int max = 1;
        if (c == ">"){
            min = i+1;
            if (str=="Day"){
                max = salesArray[0].length;
            } else if (str=="Prod") {
                max = salesArray.length;
            }
        } else if (c=="<") {
            min = 1;
            max = i-1;
        } else if (c=="=") {
            min = i;
            max = i;
        }

        Set<Integer> expected = new HashSet<>();
        for(int j=min; j<= max; j++)
            expected.add(j);
        Assertions.assertThat(expected).isEqualTo(result);
    }

    // test example of union function
    @Example
    void testUnion(){
        Set<Integer> result = new Eval(salesArray).eval(
                (SExp) parse("Day + Prod")
        );

        Set<Integer> expected = new HashSet<>();
        for(int i=1; i<= salesArray.length; i++)
            expected.add(i);
        for(int i=1; i<= salesArray[0].length; i++)
            expected.add(i);
        Assertions.assertThat(expected).isEqualTo(result);
    }

    // test example of difference function
    @Example
    void testDiff(){
        Set<Integer> result = new Eval(salesArray).eval(
                (SExp) parse("Day - Prod")
        );

        Set<Integer> expected = new HashSet<>();
        for(int i=1; i<= salesArray.length; i++)
            expected.add(i);
        for(int i=1; i<= salesArray[0].length; i++)
            expected.remove(i);
        Assertions.assertThat(expected).isEqualTo(result);
    }

    // test example of intersection function
    @Example
    void testIntersection(){
        Set<Integer> result = new Eval(salesArray).eval(
                (SExp) parse("Day & Prod")
        );

        Set<Integer> expected = new HashSet<>();
        int temp = Math.min(salesArray[0].length,salesArray.length);
        for(int i=1; i<= temp; i++)
            expected.add(i);
        Assertions.assertThat(expected).isEqualTo(result);
    }

    // test example of equality
    @Example
    public void testSetEquality() {
        boolean result = new Eval(salesArray).eval(
                (Formula) parse("Day == Day")
        );
        Assertions.assertThat(result).isTrue();

        result = new Eval(salesArray).eval(
                (Formula) parse("Day == Prod")
        );
        Assertions.assertThat(result).isFalse();
    }

    @Provide
    Arbitrary<String> forallOrExist() {
        return Arbitraries.of("forall", "exists");

    }
// ================================================== formula =========================================================
    // test formula
    @Property
    public void testFormula(@ForAll("forallOrExist") String f,@ForAll("com") String c,@ForAll @IntRange(min=MIN_SALES_VALUE,max=MAX_SALES_VALUE) Integer i) {

        // "exists p : Prod . exists d : Day . M[p,d] == 6"
        String input = f + " p : Prod . " + f + " d : Day . M[p,d] " + c + " " + i;
        Boolean result = new Eval(salesArray).eval(
                (Formula) parse(input)
        );

        Set<Integer> checked = new HashSet<>();
        for (int[] ints : salesArray)
            for (int anInt : ints)
                checked.add(anInt);
        boolean b = true;
        if (f == "forall") {
            b = true;
            for (int allInt : checked) {


                if (c == ">") {
                    if (!(allInt > i)) {
                        b = false;
                    }
                } else if (c == "<") {
                    if (!(allInt < i)) {
                        b = false;
                    }
                } else if (c == "=") {
                    if (!(allInt == i)) {
                        b = false;
                    }
                }
            }
        }else if (f == "exists"){
            b = false;
            for (int allInt : checked) {
                if (c == ">") {
                    if (allInt > i) {
                        b = true;
                    }
                } else if (c == "<") {
                    if (allInt < i) {
                        b = true;
                    }
                } else if (c == "=") {
                    if (allInt == i) {
                        b = true;
                    }
                }
            }
        }
        if (b == true) {
            Assertions.assertThat(result).isTrue();
        } else if (b == false) {
            Assertions.assertThat(result).isFalse();
        }
    }

    @Property
    void testCommutativityAddition(@ForAll @IntRange(min = MIN_SALES_VALUE, max = MAX_SALES_VALUE) Integer a,
                                   @ForAll @IntRange(min = MIN_SALES_VALUE, max = MAX_SALES_VALUE) Integer b) {
        boolean result = new Eval(salesArray).eval(
                (Formula) parse(a + " + " + b + " == " + b + " + " + a)
        );
        Assertions.assertThat(result).isTrue();
    }

    @Property
    void testCommutativityMultiplication(@ForAll @IntRange(min = MIN_SALES_VALUE, max = MAX_SALES_VALUE) Integer a,
                                         @ForAll @IntRange(min = MIN_SALES_VALUE, max = MAX_SALES_VALUE) Integer b) {
        boolean result = new Eval(salesArray).eval(
                (Formula) parse(a + " * " + b + " == " + b + " * " + a)
        );
        Assertions.assertThat(result).isTrue();
    }

    // Test if the total sales of all products on a specific day are equal to the sum of sales for each product on that day
    @Property
    void testTotalSalesPerDay(@ForAll @IntRange(min = 1, max = MIN_DIMENSION) int day) {
        Eval eval = new Eval(salesArray);
        int totalSalesPerDay = eval.evalNExp(new SalesForD(new Nat(day)), new Env());
        int sumOfSalesPerProduct = 0;

        for (int product = 1; product <= salesArray.length; product++) {
            sumOfSalesPerProduct += eval.evalNExp(new SalesAt(new Nat(product), new Nat(day)), new Env());
        }

        Assertions.assertThat(totalSalesPerDay).isEqualTo(sumOfSalesPerProduct);
    }

    // Test if the total sales of a specific product over all days are equal to the sum of sales for that product on each day
    @Property
    void testTotalSalesPerProduct(@ForAll @IntRange(min = 1, max = MIN_DIMENSION) int product) {
        Eval eval = new Eval(salesArray);
        int totalSalesPerProduct = eval.evalNExp(new SalesForP(new Nat(product)), new Env());
        int sumOfSalesPerDay = 0;

        for (int day = 1; day <= salesArray[0].length; day++) {
            sumOfSalesPerDay += eval.evalNExp(new SalesAt(new Nat(product), new Nat(day)), new Env());
        }

        Assertions.assertThat(totalSalesPerProduct).isEqualTo(sumOfSalesPerDay);
    }

    // Test if the total sales for all products and days match the sum of sales for each product on each day
    @Test
    void testTotalSales() {
        Eval eval = new Eval(salesArray);
        int totalSales = eval.evalNExp(new SalesForM(), new Env());
        int sumOfSales = 0;

        for (int product = 1; product <= salesArray.length; product++) {
            for (int day = 1; day <= salesArray[0].length; day++) {
                sumOfSales += eval.evalNExp(new SalesAt(new Nat(product), new Nat(day)), new Env());
            }
        }

        Assertions.assertThat(totalSales).isEqualTo(sumOfSales);
    }

    // Test if the total sales of each product are less than or equal to the total sales for all products
    @Property
    void totalSalesPerProductLessThanOrEqualToTotalSales() {
        int totalSales = eval.evalNExp(new SalesForM(), new Env());
        for (int product = 1; product <= salesArray.length; product++) {
            int productSales = eval.evalNExp(new SalesForP(new Nat(product)), new Env());
            Assertions.assertThat(productSales).isLessThanOrEqualTo(totalSales);
        }
    }

    // Test if the total sales of each day are less than or equal to the total sales for all products
    @Property
    void totalSalesPerDayLessThanOrEqualToTotalSales() {
        int totalSales = eval.evalNExp(new SalesForM(), new Env());
        for (int day = 1; day <= salesArray[0].length; day++) {
            int daySales = eval.evalNExp(new SalesForD(new Nat(day)), new Env());
            Assertions.assertThat(daySales).isLessThanOrEqualTo(totalSales);
        }
    }


    // Test if the total sales of each day are less than or equal to the total sales for all products
    @Property
    void totalSalesEqualToSumOfSalesPerDay() {
        int totalSales = eval.evalNExp(new SalesForM(), new Env());
        int sumOfSalesPerDay = 0;
        for (int day = 1; day <= salesArray[0].length; day++) {
            sumOfSalesPerDay += eval.evalNExp(new SalesForD(new Nat(day)), new Env());
        }
        Assertions.assertThat(totalSales).isEqualTo(sumOfSalesPerDay);
    }

    // Test if the total sales for all products are equal to the sum of the total sales per product:
    @Property
    void totalSalesEqualToSumOfSalesPerProduct() {
        int totalSales = eval.evalNExp(new SalesForM(), new Env());
        int sumOfSalesPerProduct = 0;
        for (int product = 1; product <= salesArray.length; product++) {
            sumOfSalesPerProduct += eval.evalNExp(new SalesForP(new Nat(product)), new Env());
        }
        Assertions.assertThat(totalSales).isEqualTo(sumOfSalesPerProduct);
    }

    @Property
    void testThresholdExistsForAllDays(@ForAll @IntRange(min = MIN_SALES_VALUE, max = MAX_SALES_VALUE) int threshold) {
        //int[][] salesArray = generateRandomSalesArray();
        String input = "exists p : Prod . exists d : Day . M[p,d] > " + threshold;
        Boolean result = new Eval(salesArray).eval((Formula) parse(input));

        int numProducts = salesArray.length;
        int numDays = salesArray[0].length;
        Boolean expected = false;

        for (int product = 0; product < numProducts; product++) {
            for (int day = 0; day < numDays; day++) {
                int salesOnDay = salesArray[product][day];
                if (salesOnDay > threshold){
                    expected = true;
                }
            }
        }
        Assertions.assertThat(expected).isEqualTo(result);
    }

    // Test if the average sales per day is between the minimum and maximum sales values
    @Property
    void testAverageSalesPerDay(@ForAll @IntRange(min = 1, max = MIN_DIMENSION) int day) {
        Eval eval = new Eval(salesArray);
        int totalSalesPerDay = eval.evalNExp(new SalesForD(new Nat(day)), new Env());
        int numProducts = salesArray.length;

        double averageSalesPerDay = (double) totalSalesPerDay / numProducts;

        int minSalesPerDay = Integer.MAX_VALUE;
        int maxSalesPerDay = Integer.MIN_VALUE;

        for (int product = 1; product <= numProducts; product++) {
            int sales = eval.evalNExp(new SalesAt(new Nat(product), new Nat(day)), new Env());
            minSalesPerDay = Math.min(minSalesPerDay, sales);
            maxSalesPerDay = Math.max(maxSalesPerDay, sales);
        }

        Assertions.assertThat(averageSalesPerDay).isGreaterThanOrEqualTo(minSalesPerDay);
        Assertions.assertThat(averageSalesPerDay).isLessThanOrEqualTo(maxSalesPerDay);
    }

    //========================================= Generator ==========================================


    // not finished yet

    @Property
    void testNatGenerator(@ForAll("nat") Nat nat) {
        int result = new Eval(salesArray).eval(
                (NExp) parse(Integer.toString(nat.value))
        );
        String s = "" + result + "";
        Assertions.assertThat(s).isEqualTo(Integer.toString(nat.value));
    }

    @Provide
    Arbitrary<Nat> nat() {
        return Arbitraries.integers().between(1, 10).map(Nat::new);
    }

    @Provide
    Arbitrary<SalesAt> salesAts() {
        return Combinators.combine(
                Arbitraries.integers().between(1, salesArray.length).map(Nat::new),
                Arbitraries.integers().between(1, salesArray[0].length).map(Nat::new)
        ).as((product, day) -> new SalesAt(product, day));
    }

    @Property
    @Report(Reporting.GENERATED)
    void testSalesAtGenerator(@ForAll("salesAts") SalesAt salesAt) throws IOException {
        int result = new Eval(salesArray).eval(salesAt);

        int p = ((Nat) salesAt.product).value;
        int d = ((Nat) salesAt.day).value;

        Assertions.assertThat(result).isEqualTo(salesArray[p - 1][d - 1]);
    }


    @Provide
    Arbitrary<Nat> dayNat() {
        return Arbitraries.integers().between(MIN_DIMENSION, MAX_DIMENSION).map(Nat::new);
    }

    @Provide
    Arbitrary<SalesForD> salesForDs() {
        return dayNat().map(SalesForD::new);
    }

    @Property
    @Report(Reporting.GENERATED)
    void testSalesForDGenerator(@ForAll("salesForDs") SalesForD salesForD) throws IOException {
        int result = new Eval(salesArray).eval(salesForD);

        int d = ((Nat) salesForD.day).value;

        int salesD = 0;
        if (d - 1 >= 0 && d - 1 < salesArray[0].length) {
            for (int p = 0; p < salesArray.length; p++) {
                salesD += salesArray[p][d - 1];
            }
        }

        Assertions.assertThat(result).isEqualTo(salesD);
    }

    @Provide
    Arbitrary<Nat> productNat() {
        return Arbitraries.integers().between(MIN_DIMENSION, MAX_DIMENSION).map(Nat::new);
    }

    @Provide
    Arbitrary<SalesForP> salesForPs() {
        return productNat().map(SalesForP::new);
    }

    @Property
    @Report(Reporting.GENERATED)
    void testSalesForPGenerator(@ForAll("salesForPs") SalesForP salesForP) throws IOException {
        int result = new Eval(salesArray).eval(salesForP);

        int p = ((Nat) salesForP.product).value;

        int salesP = 0;
        if (p - 1 >= 0 && p - 1 < salesArray.length) {
            for (int d = 0; d < salesArray[p-1].length; d++) {
                salesP += salesArray[p - 1][d];
            }
        }

        Assertions.assertThat(result).isEqualTo(salesP);
    }

    @Provide
    Arbitrary<BinaryNExp> binaryNExps() {
        Arbitrary<BinNOp> binNOpArbitrary = Arbitraries.of(BinNOp.Kind.values()).map(BinNOp::new);
        Arbitrary<Nat> natArbitrary = Arbitraries.integers().between(1, 100).map(Nat::new);

        return Combinators.combine(
                binNOpArbitrary,
                natArbitrary,
                natArbitrary
        ).as(BinaryNExp::new);
    }

    @Property
    @Report(Reporting.GENERATED)
    void testBinaryNExpGenerator(@ForAll("binaryNExps") BinaryNExp binaryNExp) throws IOException {
        BinNOp.Kind op = binaryNExp.op.kind;
        int lhs = ((Nat) binaryNExp.lhs).value;
        int rhs = ((Nat) binaryNExp.rhs).value;

        try {
            int expectedResult;
            switch (op) {
                case ADD:
                    expectedResult = lhs + rhs;
                    break;
                case DIFF:
                    expectedResult = lhs - rhs;
                    break;
                case MULT:
                    expectedResult = lhs * rhs;
                    break;
                case DIV:
                    if (rhs == 0) {
                        // Skip the test if the divisor is zero, since dividing by zero is undefined.
                        return;
                    }
                    expectedResult = lhs / rhs;
                    break;
                default:
                    throw new IllegalStateException("Unexpected operator: " + op);
            }

            int result = new Eval(salesArray).eval(binaryNExp);
            Assertions.assertThat(result).isEqualTo(expectedResult);
        } catch (BaseEval.DivisionByZeroException e) {
            // If a DivisionByZeroException is thrown, make sure rhs is 0.
            Assertions.assertThat(rhs).isEqualTo(0);
        }
    }

    // BinarySExps genrator
    @Provide
    Arbitrary<BinarySExp> binarySExps(Arbitrary<SExp> sExpArbitrary) {
        Arbitrary<BinSOp> binSOpArbitrary = Arbitraries.of(BinSOp.Kind.values()).map(BinSOp::new);

        return Combinators.combine(
                binSOpArbitrary,
                sExpArbitrary,
                sExpArbitrary
        ).as(BinarySExp::new);
    }




    public ASTNode parse(String input) {
        QLLexer lexer = new QLLexer(CharStreams.fromString(input));
        TokenStream tokens = new CommonTokenStream(lexer);
        QLParser p = new QLParser(tokens);

        ASTBuilder builder = new ASTBuilder();
        QLParser.StartContext context = p.start();
        org.junit.jupiter.api.Assertions.assertEquals(0, p.getNumberOfSyntaxErrors());

        if(context.formula() != null)
            return builder.visitFormula(context.formula());
        if(context.nExp() != null)
            return builder.visitNExp(context.nExp());
        return builder.visitSExp(context.sExp());
    }
}
