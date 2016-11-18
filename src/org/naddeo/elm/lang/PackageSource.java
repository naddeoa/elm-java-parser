package org.naddeo.elm.lang;

import java.util.stream.Stream;

public interface PackageSource
{
    Stream<ElmPackage> getPackageStream();
}
