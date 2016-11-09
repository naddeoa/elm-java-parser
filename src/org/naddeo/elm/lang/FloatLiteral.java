package org.naddeo.elm.lang;

import org.naddeo.elm.parser.Displayable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class FloatLiteral extends Literal<Double> implements Displayable
{
    @NonNull
    Double value;

    @Override
    public String getDisplay()
    {
        return Double.toString(value);
    }
}
