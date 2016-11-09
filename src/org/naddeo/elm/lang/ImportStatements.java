package org.naddeo.elm.lang;

import com.google.common.collect.ImmutableList;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class ImportStatements
{
    public static ImportStatements EMPTY = ImportStatements.builder().importStatements(ImmutableList.of()).build();

    @NonNull
    @Singular
    ImmutableList<ImportStatement> importStatements;
}
