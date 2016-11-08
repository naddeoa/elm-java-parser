package org.naddeo.elm.lang;

import org.naddeo.elm.parser.Displayable;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class StringLiteral extends Literal<String> implements Displayable
{
    @NonNull
    String value;

    @Override
    public String getDisplay()
    {
        return value;
    }
}
