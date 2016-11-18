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
public class Project implements ImportStatementSource, ModuleSource, PackageSource
{
    @Singular
    @NonNull
    ImmutableList<ElmPackage> elmPackages;

    public Stream<ElmPackage> getPackageStream()
    {
        return this.elmPackages.stream();
    }

    public Stream<Module> getModuleStream()
    {
        return this.getPackageStream().flatMap(ElmPackage::getModuleStream);
    }

    @Override
    public Stream<ImportStatement> getImportStatementStream()
    {
        return this.getModuleStream().flatMap(Module::getImportStatementStream);
    }
}
