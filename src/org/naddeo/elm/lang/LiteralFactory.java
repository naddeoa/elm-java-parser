package org.naddeo.elm.lang;

import lombok.Value;

@Value
public class LiteralFactory
{
    public BooleanLiteral of(Boolean value)
    {
        return BooleanLiteral.builder().value(value).build();
    }

    public IntegerLiteral of(Integer value)
    {
        return IntegerLiteral.builder().value(value).build();
    }

    public FloatLiteral of(Double value)
    {
        return FloatLiteral.builder().value(value).build();
    }

    public StringLiteral of(String value)
    {
        return StringLiteral.builder().value(value).build();
    }

    public CharacterLiteral of(Character value)
    {
        return CharacterLiteral.builder().value(value).build();
    }
}
