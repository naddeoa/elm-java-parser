package org.naddeo.elm.features.imports;

import java.util.function.Predicate;

import org.naddeo.elm.lang.Module;

public interface ModuleImportOrganizer
{
    Module organizeImports(Context context, Predicate<Module> module);
}
