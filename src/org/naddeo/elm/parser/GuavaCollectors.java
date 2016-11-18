package org.naddeo.elm.parser;

import java.util.stream.Collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import static java.util.stream.Collector.Characteristics.UNORDERED;

public class GuavaCollectors
{
    public static <T> Collector<T, ImmutableList.Builder<T>, ImmutableList<T>> immutableList()
    {
        return Collector.of(ImmutableList.Builder::new, ImmutableList.Builder::add,
                (l, r) -> l.addAll(r.build()), ImmutableList.Builder<T>::build);
    }

    public static <T> Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> immutableSet()
    {
        return Collector.of(ImmutableSet.Builder::new, ImmutableSet.Builder::add,
                (l, r) -> l.addAll(r.build()), ImmutableSet.Builder<T>::build, UNORDERED);
    }
}
