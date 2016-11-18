package org.naddeo.elm.features.imports;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import org.naddeo.elm.lang.ImportStatement;
import org.naddeo.elm.lang.ImportStatements;
import org.naddeo.elm.lang.Module;
import org.naddeo.elm.parser.GuavaCollectors;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AlphabeticalModuleImportOrganizer implements ModuleImportOrganizer
{

    private static final ImmutableMap<ImportOrganizationOption, BiFunction<Context, Module, Module>> IMPORT_LOGIC = ImmutableMap.<ImportOrganizationOption, BiFunction<Context, Module, Module>>builder()
            .put(ImportOrganizationOption.ALPHABETICAL, AlphabeticalModuleImportOrganizer::alphabeticalOrganizer)
            .build();

    @Override
    public Module organizeImports(Context context, Predicate<Module> modulePredicate)
    {
        // Use the options in the context to determine which import functions to apply to the modules
        List<BiFunction<Context, Module, Module>> importLogicFunctions = context.getImportOrganizationOptions().stream()
                .map(IMPORT_LOGIC::get)
                .collect(Collectors.toList());

        // Apply each of the collections of functions to each of the modules that match the predicate
        Stream<Module> moduleStream = context.getProject().getModuleStream().filter(modulePredicate);
        for (BiFunction<Context, Module, Module> importLogicFunction : importLogicFunctions) {
            moduleStream = moduleStream.map(module -> importLogicFunction.apply(context, module));
        }

        return moduleStream.findFirst().orElseThrow(() -> new RuntimeException("There were multiple modules matching the supplied predicate"));
    }

    private static Module alphabeticalOrganizer(Context context, Module module)
    {
        ImmutableList<ImportStatement> sortedImports = module.getImportStatementStream()
                .sorted((import1, import2) -> import1.getName().compareTo(import2.getName()))
                .collect(GuavaCollectors.immutableList());

        ImportStatements importStatements = module.getImportStatements().withImportStatements(sortedImports);
        return module.withImportStatements(importStatements);
    }
}
