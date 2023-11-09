package com.calculator.backend.services.impl;


import com.calculator.backend.entity.Equation;
import com.calculator.backend.entity.Root;
import com.calculator.backend.exception.EquationException;
import com.calculator.backend.services.EquationValidator;
import com.calculator.backend.util.Pair;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

@Service
public class EquationValidatorExp4j implements EquationValidator {

    private static final String NAME_OF_VARIABLE = "x";
    private static final String EQUAL = "=";

    @Override
    public void validate(Equation equation) {
        var bodyEqual = parseExpressionEqual(equation.getBody());
        var expression = new ExpressionBuilder(bodyEqual.getValue1())
                .variables(NAME_OF_VARIABLE)
                .build();
        validateExpression(expression);
        equation.getRoots().stream()
                .map(Root::getRoot)
                .forEach(rootValue -> validateRoots(expression, rootValue, bodyEqual.getValue2()));
    }

    private void validateRoots(Expression expression, double root, double equal) {
        var res = expression.setVariable(NAME_OF_VARIABLE, root).evaluate();
        if (res != equal) {
            throw new EquationException(String.format("root %f is not correct", root));
        }
    }

    private Pair<String, Double> parseExpressionEqual(String body) {
        checkExistingVariableAndEqualSign(body);
        String[] parsedBody = body.split(EQUAL);
        if (parsedBody[0].contains(NAME_OF_VARIABLE)) {
            return new Pair<>(parsedBody[0], convertEqualToDouble(parsedBody[1]));
        }
        return new Pair<>(parsedBody[1], convertEqualToDouble(parsedBody[0]));
    }

    private void checkExistingVariableAndEqualSign(String body) {
        if (body.contains(EQUAL) ) {
            throw new EquationException("Equal does not exist");
        }
        if(body.contains(NAME_OF_VARIABLE)) {
            throw new EquationException("Variable does not exist");
        }
    }

    private double convertEqualToDouble(String equalPart) {
        try {
            return Double.parseDouble(equalPart);
        } catch (Exception e) {
            throw new EquationException("Equal part is not correct");
        }
    }

    private void validateExpression(Expression expression) {
        var validationResult = expression.validate();
        if (!validationResult.isValid()) {
            throw new EquationException(String.join("| ", validationResult.getErrors()));
        }
    }
}
