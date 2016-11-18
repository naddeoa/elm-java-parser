package org.naddeo.elm.lang;

import java.util.stream.Stream;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
@Wither
public class Module implements ImportStatementSource
{

    @NonNull
    ModuleDefinition moduleDefinition;

    @NonNull
    ImportStatements importStatements;

    public Stream<ImportStatement> getImportStatementStream()
    {
        return importStatements.getImportStatementStream();
    }
}
