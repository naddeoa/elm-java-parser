package org.naddeo.elm.lang;

import org.naddeo.elm.parser.Displayable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
public class BooleanLiteral extends Literal<Boolean> implements Displayable
{
    @NonNull
    Boolean value;

    @Override
    public String getDisplay()
    {
        return value ? "True" : "False";
    }
}
