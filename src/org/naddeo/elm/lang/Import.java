package org.naddeo.elm.lang;

import java.util.Optional;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class Import
{
    @NonNull
    String name;
    @NonNull
    Optional<String> alias;
    @NonNull
    Optional<Exposed> exposed;
}
