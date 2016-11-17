package org.naddeo.elm.lang;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Module
{

    @NonNull
    ModuleDefinition moduleDefinition;

    @NonNull
    ImportStatements importStatements;
}
