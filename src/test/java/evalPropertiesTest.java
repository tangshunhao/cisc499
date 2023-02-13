import java.nio.charset.StandardCharsets;

import ast.*;
import net.jqwik.api.constraints.IntRange;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.Assert;
import org.junit.BeforeClass;
import ql.QLLexer;
import ql.QLParser;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Objects;

import ast.NExp;
import net.jqwik.api.*;
import org.assertj.core.api.Assertions;

public class evalPropertiesTest {
    static int[][] salesArray;
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

    // Check the property of salesAt function
    @Property
    @Report(Reporting.GENERATED)
    void propSalesAt(@ForAll @IntRange(min=1,max=5) Integer d, @ForAll @IntRange(min=1,max=4) Integer p){
        int result = new Eval(salesArray).eval(
                (NExp) parse("M[" + Integer.toString(d)+","+Integer.toString(p)+"]")
        );

        Assert.assertEquals(salesArray[d][p], result);
    }

    //
    @Property
    @Report(Reporting.GENERATED)
    void testSize(@ForAll @IntRange(min=5,max=5) Integer d) {
        int result = new Eval(salesArray).eval(
                (NExp) parse("size(Day)")
        );
        Assertions.assertThat(result).isEqualTo(d);
    }

    @BeforeClass
    public static void readData() throws IOException {
        File file = new File(
                URLDecoder.decode(Objects.requireNonNull(TestEval.class.getClassLoader()
                        .getResource("salesArray.csv")).getFile(), StandardCharsets.UTF_8)
        );

        salesArray = CSVReader.read(file);
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
