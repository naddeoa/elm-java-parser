package org.naddeo.elm.features.imports;

import com.google.common.collect.ImmutableSet;

import org.naddeo.elm.lang.Project;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Wither
@Builder
public class Context
{
    @NonNull
    Project project;

    @NonNull
    ImmutableSet<ImportOrganizationOption> importOrganizationOptions;
}
