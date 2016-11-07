package org.naddeo.elm.parser.test.data;

import org.naddeo.elm.parser.type.BooleanValue;
import org.naddeo.elm.parser.type.FloatValue;
import org.naddeo.elm.parser.type.IntegerValue;
import org.naddeo.elm.parser.type.ObjectValue;
import org.naddeo.elm.parser.type.StringValue;
import org.naddeo.elm.parser.type.Value;
import org.naddeo.elm.parser.type.ValueFactory;
import org.naddeo.elm.parser.type.VariableValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.naddeo.elm.parser.BaseGrammarTest.VALUE_FACTORY;

@RequiredArgsConstructor
public enum ValueInput implements TestData<Value> {
    STRING_VALUE(TestObject.<Value>builder()
            .parserInput("\"string type\"")
            .expectedClass(StringValue.class)
            .pojoValue(VALUE_FACTORY.of("string type"))
            .build()),

    FLOAT_VALUE(TestObject.<Value>builder()
            .parserInput("55.52")
            .pojoValue(VALUE_FACTORY.of(55.52))
            .expectedClass(FloatValue.class)
            .build()),

    INTEGER_VALUE(TestObject.<Value>builder()
            .parserInput("100")
            .pojoValue(VALUE_FACTORY.of(100))
            .expectedClass(IntegerValue.class)
            .build()),

    VARIABLE_VALUE(TestObject.<Value>builder()
            .parserInput("$varName")
            .pojoValue(VALUE_FACTORY.ofVariable("varName"))
            .expectedClass(VariableValue.class)
            .build()),

    OBJECT_VALUE(TestObject.<Value>builder()
            .parserInput("Object")
            .pojoValue(VALUE_FACTORY.ofObject("Object"))
            .expectedClass(ObjectValue.class)
            .build()),

    BOOLEAN_VALUE(TestObject.<Value>builder()
            .parserInput("true")
            .pojoValue(new ValueFactory().of(true))
            .expectedClass(BooleanValue.class)
            .build());

    @Getter
    private final TestObject<Value> test;

    @Override
    public String toString()
    {
        return testName();
    }
}
