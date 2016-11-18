package org.naddeo.elm.features.imports;

import lombok.Value;

@Value
public class ModuleDoesNotExistException extends RuntimeException
{
    String message;
}
