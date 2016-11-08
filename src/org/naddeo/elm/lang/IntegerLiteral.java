package org.naddeo.elm.lang;

import org.naddeo.elm.parser.Displayable;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class IntegerLiteral extends Literal<Integer> implements Displayable
{
    @NonNull
    Integer value;

    @Override
    public String getDisplay()
    {
        return Integer.toString(value);
    }
}
