package org.naddeo.elm.lang;

import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
@Wither
public class ImportStatements
{
    public static ImportStatements EMPTY = ImportStatements.builder().importStatements(ImmutableList.of()).build();

    @NonNull
    @Singular
    ImmutableList<ImportStatement> importStatements;

    public Stream<ImportStatement> getImportStatementStream()
    {
        return this.importStatements.stream();
    }
}
