package org.naddeo.elm.lang;

import java.util.stream.Stream;

public interface ModuleSource
{
    Stream<Module> getModuleStream();
}
