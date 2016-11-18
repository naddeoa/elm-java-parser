package org.naddeo.elm.lang;

import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder
public class ElmPackage implements ImportStatementSource, ModuleSource
{
    @Singular
    @NonNull
    ImmutableList<Module> modules;

    public Stream<Module> getModuleStream()
    {
        return this.modules.stream();
    }

    public Stream<ImportStatement> getImportStatementStream()
    {
        return this.getModuleStream().flatMap(Module::getImportStatementStream);
    }
}
