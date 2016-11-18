package org.naddeo.elm.lang;

import java.util.stream.Stream;

public interface ImportStatementSource
{

    Stream<ImportStatement> getImportStatementStream();
}
