package org.naddeo.elm.lang;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class ModuleDefinition
{
    @NonNull
    String name;

    @NonNull
    Exposed exposes;
}
