package org.naddeo.elm.features.imports;

import com.google.common.collect.ImmutableList;

import org.naddeo.elm.lang.ImportStatement;
import org.naddeo.elm.lang.ImportStatements;
import org.naddeo.elm.lang.Module;
import org.naddeo.elm.parser.GuavaCollectors;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AlphabeticalImportOrganizer implements ImportOrganizer
{

    @Override
    public Module organizeImports(Module module)
    {
        ImmutableList<ImportStatement> sortedImports = module.getImportStatementStream()
                .sorted((import1, import2) -> import1.getName().compareTo(import2.getName()))
                .collect(GuavaCollectors.immutableList());

        ImportStatements importStatements = module.getImportStatements().withImportStatements(sortedImports);
        return module.withImportStatements(importStatements);
    }
}
