// Generated from QL.g by ANTLR 4.8
package ql;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, DAY=11, PRODUCT=12, SALE=13, MATRIX=14, SALES_FOR_M=15, SALES_FOR_D=16, 
		SALES_FOR_P=17, SIZE=18, FORALL=19, EXISTS=20, EQ=21, LT=22, GT=23, NEQ=24, 
		LTE=25, GTE=26, PLUS=27, MINUS=28, ASTRIX=29, SLASH=30, AMPER=31, AND=32, 
		OR=33, IMPLY=34, EQUIV=35, NOT=36, ID=37, NUMBER=38, LINE_COMMENT=39, 
		WS=40;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "DAY", "PRODUCT", "SALE", "MATRIX", "SALES_FOR_M", "SALES_FOR_D", 
			"SALES_FOR_P", "SIZE", "FORALL", "EXISTS", "EQ", "LT", "GT", "NEQ", "LTE", 
			"GTE", "PLUS", "MINUS", "ASTRIX", "SLASH", "AMPER", "AND", "OR", "IMPLY", 
			"EQUIV", "NOT", "ID", "NUMBER", "LINE_COMMENT", "WS", "A", "B", "C", 
			"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", 
			"R", "S", "T", "U", "V", "W", "X", "Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'.'", "'('", "')'", "'['", "','", "']'", "'{'", "'|'", 
			"'}'", null, null, null, null, null, null, null, null, null, null, null, 
			"'<'", "'>'", "'!='", "'<='", "'>='", "'+'", "'-'", "'*'", "'/'", "'&'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "DAY", 
			"PRODUCT", "SALE", "MATRIX", "SALES_FOR_M", "SALES_FOR_D", "SALES_FOR_P", 
			"SIZE", "FORALL", "EXISTS", "EQ", "LT", "GT", "NEQ", "LTE", "GTE", "PLUS", 
			"MINUS", "ASTRIX", "SLASH", "AMPER", "AND", "OR", "IMPLY", "EQUIV", "NOT", 
			"ID", "NUMBER", "LINE_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public QLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "QL.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u017c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\5\26\u00e0\n\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36"+
		"\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3!\5!\u00ff\n!\3\"\3\"\3\"\3\"\3\"\5\""+
		"\u0106\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u0112\n#\3$\3$\3$\3$\3$\3"+
		"$\3$\5$\u011b\n$\3%\3%\3%\3%\3%\5%\u0122\n%\3&\3&\7&\u0126\n&\f&\16&\u0129"+
		"\13&\3\'\6\'\u012c\n\'\r\'\16\'\u012d\3(\3(\3(\3(\7(\u0134\n(\f(\16(\u0137"+
		"\13(\3(\3(\5(\u013b\n(\3(\5(\u013e\n(\3(\3(\3)\6)\u0143\n)\r)\16)\u0144"+
		"\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3"+
		"\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3"+
		":\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\2\2D\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\2k\2m\2o\2q\2s\2u\2"+
		"w\2y\2{\2}\2\177\2\u0081\2\u0083\2\u0085\2\3\2!\4\2C\\c|\5\2\62;C\\c|"+
		"\3\2\62;\4\2\f\f\17\17\5\2\13\f\17\17\"\"\4\2CCcc\4\2DDdd\4\2EEee\4\2"+
		"FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4"+
		"\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWw"+
		"w\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u016d\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3"+
		"\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3\u0087\3\2\2\2\5\u0089\3\2\2"+
		"\2\7\u008b\3\2\2\2\t\u008d\3\2\2\2\13\u008f\3\2\2\2\r\u0091\3\2\2\2\17"+
		"\u0093\3\2\2\2\21\u0095\3\2\2\2\23\u0097\3\2\2\2\25\u0099\3\2\2\2\27\u009b"+
		"\3\2\2\2\31\u009f\3\2\2\2\33\u00a4\3\2\2\2\35\u00a9\3\2\2\2\37\u00ab\3"+
		"\2\2\2!\u00b5\3\2\2\2#\u00bf\3\2\2\2%\u00c9\3\2\2\2\'\u00ce\3\2\2\2)\u00d5"+
		"\3\2\2\2+\u00df\3\2\2\2-\u00e1\3\2\2\2/\u00e3\3\2\2\2\61\u00e5\3\2\2\2"+
		"\63\u00e8\3\2\2\2\65\u00eb\3\2\2\2\67\u00ee\3\2\2\29\u00f0\3\2\2\2;\u00f2"+
		"\3\2\2\2=\u00f4\3\2\2\2?\u00f6\3\2\2\2A\u00fe\3\2\2\2C\u0105\3\2\2\2E"+
		"\u0111\3\2\2\2G\u011a\3\2\2\2I\u0121\3\2\2\2K\u0123\3\2\2\2M\u012b\3\2"+
		"\2\2O\u012f\3\2\2\2Q\u0142\3\2\2\2S\u0148\3\2\2\2U\u014a\3\2\2\2W\u014c"+
		"\3\2\2\2Y\u014e\3\2\2\2[\u0150\3\2\2\2]\u0152\3\2\2\2_\u0154\3\2\2\2a"+
		"\u0156\3\2\2\2c\u0158\3\2\2\2e\u015a\3\2\2\2g\u015c\3\2\2\2i\u015e\3\2"+
		"\2\2k\u0160\3\2\2\2m\u0162\3\2\2\2o\u0164\3\2\2\2q\u0166\3\2\2\2s\u0168"+
		"\3\2\2\2u\u016a\3\2\2\2w\u016c\3\2\2\2y\u016e\3\2\2\2{\u0170\3\2\2\2}"+
		"\u0172\3\2\2\2\177\u0174\3\2\2\2\u0081\u0176\3\2\2\2\u0083\u0178\3\2\2"+
		"\2\u0085\u017a\3\2\2\2\u0087\u0088\7<\2\2\u0088\4\3\2\2\2\u0089\u008a"+
		"\7\60\2\2\u008a\6\3\2\2\2\u008b\u008c\7*\2\2\u008c\b\3\2\2\2\u008d\u008e"+
		"\7+\2\2\u008e\n\3\2\2\2\u008f\u0090\7]\2\2\u0090\f\3\2\2\2\u0091\u0092"+
		"\7.\2\2\u0092\16\3\2\2\2\u0093\u0094\7_\2\2\u0094\20\3\2\2\2\u0095\u0096"+
		"\7}\2\2\u0096\22\3\2\2\2\u0097\u0098\7~\2\2\u0098\24\3\2\2\2\u0099\u009a"+
		"\7\177\2\2\u009a\26\3\2\2\2\u009b\u009c\5Y-\2\u009c\u009d\5S*\2\u009d"+
		"\u009e\5\u0083B\2\u009e\30\3\2\2\2\u009f\u00a0\5q9\2\u00a0\u00a1\5u;\2"+
		"\u00a1\u00a2\5o8\2\u00a2\u00a3\5Y-\2\u00a3\32\3\2\2\2\u00a4\u00a5\5w<"+
		"\2\u00a5\u00a6\5S*\2\u00a6\u00a7\5i\65\2\u00a7\u00a8\5[.\2\u00a8\34\3"+
		"\2\2\2\u00a9\u00aa\5k\66\2\u00aa\36\3\2\2\2\u00ab\u00ac\5w<\2\u00ac\u00ad"+
		"\5S*\2\u00ad\u00ae\5i\65\2\u00ae\u00af\5[.\2\u00af\u00b0\5w<\2\u00b0\u00b1"+
		"\5]/\2\u00b1\u00b2\5o8\2\u00b2\u00b3\5u;\2\u00b3\u00b4\5k\66\2\u00b4 "+
		"\3\2\2\2\u00b5\u00b6\5w<\2\u00b6\u00b7\5S*\2\u00b7\u00b8\5i\65\2\u00b8"+
		"\u00b9\5[.\2\u00b9\u00ba\5w<\2\u00ba\u00bb\5]/\2\u00bb\u00bc\5o8\2\u00bc"+
		"\u00bd\5u;\2\u00bd\u00be\5Y-\2\u00be\"\3\2\2\2\u00bf\u00c0\5w<\2\u00c0"+
		"\u00c1\5S*\2\u00c1\u00c2\5i\65\2\u00c2\u00c3\5[.\2\u00c3\u00c4\5w<\2\u00c4"+
		"\u00c5\5]/\2\u00c5\u00c6\5o8\2\u00c6\u00c7\5u;\2\u00c7\u00c8\5q9\2\u00c8"+
		"$\3\2\2\2\u00c9\u00ca\5w<\2\u00ca\u00cb\5c\62\2\u00cb\u00cc\5\u0085C\2"+
		"\u00cc\u00cd\5[.\2\u00cd&\3\2\2\2\u00ce\u00cf\5]/\2\u00cf\u00d0\5o8\2"+
		"\u00d0\u00d1\5u;\2\u00d1\u00d2\5S*\2\u00d2\u00d3\5i\65\2\u00d3\u00d4\5"+
		"i\65\2\u00d4(\3\2\2\2\u00d5\u00d6\5[.\2\u00d6\u00d7\5\u0081A\2\u00d7\u00d8"+
		"\5c\62\2\u00d8\u00d9\5w<\2\u00d9\u00da\5y=\2\u00da\u00db\5w<\2\u00db*"+
		"\3\2\2\2\u00dc\u00e0\7?\2\2\u00dd\u00de\7?\2\2\u00de\u00e0\7?\2\2\u00df"+
		"\u00dc\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0,\3\2\2\2\u00e1\u00e2\7>\2\2\u00e2"+
		".\3\2\2\2\u00e3\u00e4\7@\2\2\u00e4\60\3\2\2\2\u00e5\u00e6\7#\2\2\u00e6"+
		"\u00e7\7?\2\2\u00e7\62\3\2\2\2\u00e8\u00e9\7>\2\2\u00e9\u00ea\7?\2\2\u00ea"+
		"\64\3\2\2\2\u00eb\u00ec\7@\2\2\u00ec\u00ed\7?\2\2\u00ed\66\3\2\2\2\u00ee"+
		"\u00ef\7-\2\2\u00ef8\3\2\2\2\u00f0\u00f1\7/\2\2\u00f1:\3\2\2\2\u00f2\u00f3"+
		"\7,\2\2\u00f3<\3\2\2\2\u00f4\u00f5\7\61\2\2\u00f5>\3\2\2\2\u00f6\u00f7"+
		"\7(\2\2\u00f7@\3\2\2\2\u00f8\u00f9\7(\2\2\u00f9\u00ff\7(\2\2\u00fa\u00fb"+
		"\5S*\2\u00fb\u00fc\5m\67\2\u00fc\u00fd\5Y-\2\u00fd\u00ff\3\2\2\2\u00fe"+
		"\u00f8\3\2\2\2\u00fe\u00fa\3\2\2\2\u00ffB\3\2\2\2\u0100\u0101\7~\2\2\u0101"+
		"\u0106\7~\2\2\u0102\u0103\5o8\2\u0103\u0104\5u;\2\u0104\u0106\3\2\2\2"+
		"\u0105\u0100\3\2\2\2\u0105\u0102\3\2\2\2\u0106D\3\2\2\2\u0107\u0108\7"+
		"?\2\2\u0108\u0112\7@\2\2\u0109\u010a\5c\62\2\u010a\u010b\5k\66\2\u010b"+
		"\u010c\5q9\2\u010c\u010d\5i\65\2\u010d\u010e\5c\62\2\u010e\u010f\5[.\2"+
		"\u010f\u0110\5w<\2\u0110\u0112\3\2\2\2\u0111\u0107\3\2\2\2\u0111\u0109"+
		"\3\2\2\2\u0112F\3\2\2\2\u0113\u0114\7>\2\2\u0114\u0115\7?\2\2\u0115\u011b"+
		"\7@\2\2\u0116\u0117\5c\62\2\u0117\u0118\5]/\2\u0118\u0119\5]/\2\u0119"+
		"\u011b\3\2\2\2\u011a\u0113\3\2\2\2\u011a\u0116\3\2\2\2\u011bH\3\2\2\2"+
		"\u011c\u0122\7#\2\2\u011d\u011e\5m\67\2\u011e\u011f\5o8\2\u011f\u0120"+
		"\5y=\2\u0120\u0122\3\2\2\2\u0121\u011c\3\2\2\2\u0121\u011d\3\2\2\2\u0122"+
		"J\3\2\2\2\u0123\u0127\t\2\2\2\u0124\u0126\t\3\2\2\u0125\u0124\3\2\2\2"+
		"\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128L\3"+
		"\2\2\2\u0129\u0127\3\2\2\2\u012a\u012c\t\4\2\2\u012b\u012a\3\2\2\2\u012c"+
		"\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012eN\3\2\2\2"+
		"\u012f\u0130\7\'\2\2\u0130\u0131\7\'\2\2\u0131\u0135\3\2\2\2\u0132\u0134"+
		"\n\5\2\2\u0133\u0132\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0135"+
		"\u0136\3\2\2\2\u0136\u013d\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u013e\7\2"+
		"\2\3\u0139\u013b\7\17\2\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\u013e\7\f\2\2\u013d\u0138\3\2\2\2\u013d\u013a\3\2"+
		"\2\2\u013e\u013f\3\2\2\2\u013f\u0140\b(\2\2\u0140P\3\2\2\2\u0141\u0143"+
		"\t\6\2\2\u0142\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0142\3\2\2\2\u0144"+
		"\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\b)\3\2\u0147R\3\2\2\2\u0148"+
		"\u0149\t\7\2\2\u0149T\3\2\2\2\u014a\u014b\t\b\2\2\u014bV\3\2\2\2\u014c"+
		"\u014d\t\t\2\2\u014dX\3\2\2\2\u014e\u014f\t\n\2\2\u014fZ\3\2\2\2\u0150"+
		"\u0151\t\13\2\2\u0151\\\3\2\2\2\u0152\u0153\t\f\2\2\u0153^\3\2\2\2\u0154"+
		"\u0155\t\r\2\2\u0155`\3\2\2\2\u0156\u0157\t\16\2\2\u0157b\3\2\2\2\u0158"+
		"\u0159\t\17\2\2\u0159d\3\2\2\2\u015a\u015b\t\20\2\2\u015bf\3\2\2\2\u015c"+
		"\u015d\t\21\2\2\u015dh\3\2\2\2\u015e\u015f\t\22\2\2\u015fj\3\2\2\2\u0160"+
		"\u0161\t\23\2\2\u0161l\3\2\2\2\u0162\u0163\t\24\2\2\u0163n\3\2\2\2\u0164"+
		"\u0165\t\25\2\2\u0165p\3\2\2\2\u0166\u0167\t\26\2\2\u0167r\3\2\2\2\u0168"+
		"\u0169\t\27\2\2\u0169t\3\2\2\2\u016a\u016b\t\30\2\2\u016bv\3\2\2\2\u016c"+
		"\u016d\t\31\2\2\u016dx\3\2\2\2\u016e\u016f\t\32\2\2\u016fz\3\2\2\2\u0170"+
		"\u0171\t\33\2\2\u0171|\3\2\2\2\u0172\u0173\t\34\2\2\u0173~\3\2\2\2\u0174"+
		"\u0175\t\35\2\2\u0175\u0080\3\2\2\2\u0176\u0177\t\36\2\2\u0177\u0082\3"+
		"\2\2\2\u0178\u0179\t\37\2\2\u0179\u0084\3\2\2\2\u017a\u017b\t \2\2\u017b"+
		"\u0086\3\2\2\2\17\2\u00df\u00fe\u0105\u0111\u011a\u0121\u0127\u012d\u0135"+
		"\u013a\u013d\u0144\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}