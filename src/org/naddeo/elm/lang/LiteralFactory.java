package org.naddeo.elm.lang;

import lombok.Value;

@Value
public class LiteralFactory
{
    public Literal<Boolean> of(Boolean value)
    {
        return Literal.<Boolean>builder().value(value).build();
    }

    public Literal<Integer> of(Integer value)
    {
        return Literal.<Integer>builder().value(value).build();
    }

    public Literal<Double> of(Double value)
    {
        return Literal.<Double>builder().value(value).build();
    }

    public Literal<String> of(String value)
    {
        return Literal.<String>builder().value(value).build();
    }

    public Literal<Character> of(Character value)
    {
        return Literal.<Character>builder().value(value).build();
    }
}
