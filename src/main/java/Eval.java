import ast.*;

import java.util.*;
import java.util.stream.Collectors;

public class Eval extends BaseEval {
    //-----------------------!! DO NOT MODIFY !!-------------------------
    private int[][] M;
    public Eval(int[][] M) {
        this.M = M;
    }
    //-------------------------------------------------------------------

    @Override
    protected Integer evalNExp(NExp exp, Env env) {
        //Nat
        if(exp instanceof Nat){
            return ((Nat) exp).value;
        }//end of Nat
        //SalesForM
        if(exp instanceof SalesForM){
            int totalS = 0;
            for(int i = 0;i < M.length;i++){
                for(int j = 0; j < M[0].length;j++){
                    totalS += M[i][j];
                }
            }
            return totalS;
        }
        //SalesAt
        if(exp instanceof SalesAt){
            return M[evalNExp(((SalesAt) exp).product,env)-1][evalNExp(((SalesAt) exp).day,env)-1];
        }
        //SalesForP
        if(exp instanceof SalesForP){
            int prodS = 0;
            for(int i=0;i<M[0].length;i++){
                prodS+=M[evalNExp(((SalesForP) exp).product,env)-1][i];
            }
            return prodS;
        }
        //SalesFarD
        if(exp instanceof SalesForD){
            int dayS = 0;
            for(int i=0;i< M.length;i++){
                dayS+=M[i][evalNExp(((SalesForD) exp).day,env)-1];
            }
            return dayS;
        }
        //BinaryNExp
        if(exp instanceof BinaryNExp){
            int left = evalNExp(((BinaryNExp) exp).lhs,env);
            int right = evalNExp(((BinaryNExp) exp).rhs,env);
            if(((BinaryNExp) exp).op.kind==BinNOp.ADD().kind){
                return left+right;
            }
            if(((BinaryNExp) exp).op.kind==BinNOp.DIFF().kind){
                return left-right;
            }
            if(((BinaryNExp) exp).op.kind==BinNOp.MULT().kind){
                return left*right;
            }
            if(((BinaryNExp) exp).op.kind==BinNOp.DIV().kind){
                if(right == 0){
                    throw new DivisionByZeroException();
                }
                return left/right;
            }
        }
        //Size
        if(exp instanceof Size){
            if(((Size) exp).sExp instanceof Type){
                return evalSExp(((Size) exp).sExp,env).size();
            }
        }//end of Size
        if(exp instanceof Var){
            int i = env.lookup(((Var) exp).name);
            if(i==-1) {
                throw new UnboundVariableException();
            } else {
                return i;
            }
        }
        return 0;
    }

    @Override
    protected Set<Integer> evalSExp(SExp exp, Env env) {
        //BinarySExp
        if(exp instanceof BinarySExp){
            if(((BinarySExp) exp).op.kind== BinSOp.Kind.UNION){
                Set<Integer> set = new HashSet<>();
                for(int i=1; i<= evalSExp(((BinarySExp) exp).lhs,env).size(); i++)
                    set.add(i);
                for(int i=1; i<= evalSExp(((BinarySExp) exp).rhs,env).size(); i++)
                    set.add(i);
                return set;
            }
            if(((BinarySExp) exp).op.kind== BinSOp.Kind.DIFF){
                Set<Integer> set = new HashSet<>();
                for(int i=1; i<= evalSExp(((BinarySExp) exp).lhs,env).size(); i++)
                    set.add(i);
                for(int i=1; i<= evalSExp(((BinarySExp) exp).rhs,env).size(); i++)
                    set.remove(i);
                return set;
            }
            if(((BinarySExp) exp).op.kind== BinSOp.Kind.INTER){
                Set<Integer> set = new HashSet<>();
                for(int i=1; i<= M.length; i++)
                    set.add(i);
                return set;
            }
        }//end of BinarySExp
        //Type
        if(exp instanceof Type) {
            if (((Type) exp).kind == Type.DAY().kind) {
                Set<Integer> days = new HashSet<>();
                for(int i=1; i<= M[0].length; i++)
                    days.add(i);
                return days;
            }
            if (((Type) exp).kind == Type.PRODUCT().kind) {
                Set<Integer> products = new HashSet<>();
                for (int i = 1; i <= M.length; i++)
                    products.add(i);
                return products;
            }
            if (((Type) exp).kind == Type.SALE().kind) {
                Set<Integer> sales = new HashSet<>();
                for (int i = 0; i < M.length; i++) {
                    for (int j = 0; j < M[0].length; j++) {
                        sales.add(M[i][j]);
                    }
                }
                return sales;
            }
        }
        if(exp instanceof SetCompr){
            Set<Integer> set = new HashSet<>(evalSExp(((SetCompr) exp).type,env));
            Set<Integer> output = new HashSet<>();
            List<Integer> arr = new ArrayList<>(set);
            String s = ((SetCompr) exp).var.name;
            for (int i : set)
                arr.add(i);
            for (int i : set){
                env.push(s,arr.get(i));
            }
            for(int i : set){
                if(evalFormula(((SetCompr) exp).formula,env)) {
                    output.add(env.lookup(s));
                    env.pop();
                }else{
                    env.pop();
                }
            }
            return output;
        }

        return null;
    }

    @Override
    protected Boolean evalFormula(Formula formula, Env env) {
        // AtomicN
        if(formula instanceof AtomicN){
            int left = evalNExp(((AtomicN) formula).lhs, env);
            int right = evalNExp(((AtomicN) formula).rhs, env);
            if(((AtomicN) formula).relNOp.kind==RelNOp.EQ().kind){
                return left==right;
            }
            if(((AtomicN) formula).relNOp.kind==RelNOp.NEQ().kind){
                return left!=right;
            }
            if(((AtomicN) formula).relNOp.kind==RelNOp.LT().kind){
                return left<right;
            }
            if(((AtomicN) formula).relNOp.kind==RelNOp.LTE().kind){
                return left<=right;
            }
            if(((AtomicN) formula).relNOp.kind==RelNOp.GT().kind){
                return left>right;
            }
            if(((AtomicN) formula).relNOp.kind==RelNOp.GTE().kind) {
                return left >= right;
            }
        }
        //AtomicS
        if (formula instanceof AtomicS){
            if(((AtomicS) formula).relSOp.kind==RelSOp.EQ().kind){
                return evalSExp(((AtomicS) formula).lhs,env).equals(evalSExp(((AtomicS) formula).rhs, env));
            }
        }
        //BinryConn
        if (formula instanceof Binary){
            boolean left = evalFormula(((Binary) formula).lhs,env);
            boolean right = evalFormula(((Binary) formula).rhs,env);
            if(((Binary) formula).binConn.kind == BinaryConn.AND().kind){
                return left && right;
            }
            if(((Binary) formula).binConn.kind == BinaryConn.OR().kind){
                return left || right;
            }
            if(((Binary) formula).binConn.kind == BinaryConn.IMPLY().kind){
                return !left || right;
            }
            if(((Binary) formula).binConn.kind == BinaryConn.EQUIV().kind) {
                return left == right;
            }
        }
        if (formula instanceof Unary){
            if(((Unary) formula).unConn.kind == UnaryConn.NOT().kind){
                return !evalFormula(((Unary) formula).formula,env);
            }
        }
    if (formula instanceof Quantified){
        Set<Integer> set = new HashSet<>(evalSExp(((Quantified) formula).type,env));
        List<Integer> arr = new ArrayList<>(set);
        String s = ((Quantified) formula).var.name;
        for (int i : set)
            arr.add(i);
        for (int i : set){
            env.push(s,arr.get(i));
        }
        //forAll
        if(((Quantified) formula).quantifier.kind == Quantifier.FORALL().kind){
            boolean b = true;
            for(int i : set){
                if(!(evalFormula(((Quantified) formula).formula,env))) {
                    b = false;
                    env.pop();
                }else{
                    env.pop();
                }
            }
            return b;
        }
        //exist
        if(((Quantified) formula).quantifier.kind == Quantifier.EXISTS().kind){
            boolean b = false;
            for(int i : set){
                if(evalFormula(((Quantified) formula).formula,env)) {
                    b = true;
                    env.pop();
                }else{
                    env.pop();
                }
            }
            return b;
        }

    }
        return false;
    }
}
