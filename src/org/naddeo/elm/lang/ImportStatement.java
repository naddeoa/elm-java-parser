package org.naddeo.elm.lang;

import java.util.Optional;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
@Wither
public class ImportStatement
{
    @NonNull
    String name;
    @NonNull
    Optional<String> alias;
    @NonNull
    Optional<Exposed> exposed;
}
