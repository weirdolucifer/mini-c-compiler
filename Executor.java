import java.util.ArrayList;
import java.util.Map;


public class Executor {
    private ArrayList<String> tokens;
    private Map lineMap;
    String fileDirectory = "test.c";

    CodeGenerator cg;
    public Executor() {
        InputReader ir = new InputReader(fileDirectory);
        tokens = ir.getTokens();
        System.out.println("Tokenization");
        for(int i=0;i<tokens.size();i++)
        {
          System.out.print("<"+tokens.get(i)+"> ");
        }
        System.out.println();
        SyntaxStateMachine ssm = new SyntaxStateMachine(StatementTransitionTable.stt, ExpressionTransitionTable.ett, tokens, ir.getTokensOfEachLine());
        ssm.syntaxHandler();
        if (ssm.isSyntaxOK()){
            SemanticStateMachine semanticSM = new SemanticStateMachine(tokens);
            semanticSM.semanticHandler();
            if (semanticSM.isSemanticOk())
                cg = new CodeGenerator(tokens);
        }
    }

    public static void main(String[] args) {
        new Executor();
    }
}
