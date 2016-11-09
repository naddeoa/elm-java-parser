package org.naddeo.elm.lang;

import com.google.common.collect.ImmutableList;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class Exposed
{
    public static final Exposed NOTHING = Exposed.builder().exports(ImmutableList.of()).build();

    @NonNull
    @Singular
    ImmutableList<String> exports;
}
