package org.naddeo.elm;

import java.util.function.Function;
import java.util.stream.Stream;

import lombok.Data;
import lombok.NonNull;

@Data
public class StreamSizeData<T>
{
    @NonNull
    private final Function<T, Stream> streamFunction;
    @NonNull
    private final Long expectedResults;
    @NonNull
    private final Class streamReturnType;
}
