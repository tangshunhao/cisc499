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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import ast.NExp;
import net.jqwik.api.*;
import org.assertj.core.api.Assertions;
import java.util.Arrays;



public class evalPropertiesTest {
    static int[][] salesArray;

    @BeforeProperty
    public void readData() throws IOException {
        File file = new File(
                URLDecoder.decode(Objects.requireNonNull(TestEval.class.getClassLoader()
                        .getResource("salesArray.csv")).getFile(), StandardCharsets.UTF_8)
        );
        salesArray = CSVReader.read(file);
    }


    //check testArray
    @Provide
    public static Arbitrary<int[][]> testArray() {
        Arbitrary<int[]> row =  Arbitraries.integers().array(int[].class).ofSize(4);
        Arbitrary<int[][]> matrix = row.array(int[][].class).ofMinSize(1).ofMaxSize(10);
        return matrix;
    }

    // Check the property of salesAt function
    @Property
    @Report(Reporting.GENERATED)
    void propTestArray(@ForAll("testArray") int[][] testArray) throws IOException {

        int result = new Eval(testArray).eval(
                (NExp) parse("2")
        );
        Assertions.assertThat(result).isEqualTo(2);
    }



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
    void propNat(@ForAll @IntRange(min=1,max=10) Integer n){
        int result = new Eval(salesArray).eval(
                (NExp) parse(Integer.toString(n))
        );
        Assertions.assertThat(result).isEqualTo(n);
    }

    // Check the property of salesForM function
    @Example
    void propSalesForM() throws IOException {
        readData();
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
    void propSalesAt(@ForAll @IntRange(min=1,max=4) Integer p, @ForAll @IntRange(min=1,max=5) Integer d) throws IOException {
        // readData();
        int result = new Eval(salesArray).eval(
                (NExp) parse("M[" + Integer.toString(p)+","+Integer.toString(d)+"]")
        );
        Assertions.assertThat(result).isEqualTo(salesArray[p-1][d-1]);
    }

    // Check the property of salesForP function
    @Property
    @Report(Reporting.GENERATED)
    void propSalesP(@ForAll @IntRange(min=1,max=4) Integer p) throws IOException {
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
    void propSalesD(@ForAll @IntRange(min=1,max=5) Integer d) throws IOException {
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
        // readData();
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

    // set expression testing ---------------------------------------------


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
    void testSetComprehension(@ForAll("fixedS") String str,@ForAll("com") String c,@ForAll @IntRange(min=1,max=4) Integer i){
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
        for(int i=1; i<= 5; i++)
            expected.add(i);
        for(int i=1; i<= 4; i++)
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
        for(int i=1; i<= 5; i++)
            expected.add(i);
        for(int i=1; i<= 4; i++)
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
        for(int i=1; i<= 4; i++)
            expected.remove(i);
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

    // test formula
    @Property
    public void testFormula(@ForAll("forallOrExist") String f,@ForAll("com") String c,@ForAll @IntRange(min=1,max=4) Integer i) {

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
