package org.naddeo.elm.lang;

import org.naddeo.elm.parser.Displayable;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
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
