package org.naddeo.elm.lang;

import org.naddeo.elm.parser.Displayable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class CharacterLiteral extends Literal<Character> implements Displayable
{
    @NonNull
    Character value;

    @Override
    public String getDisplay()
    {
        return Character.toString(value);
    }
}
