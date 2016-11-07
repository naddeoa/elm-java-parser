package org.naddeo.elm.parser.type;

import org.naddeo.elm.parser.Renderable;

/**
 * A Value represents a scalar type, such as a string or integer.
 *
 * @author Anthony Naddeo anthony.naddeo@gmail.com
 */
public abstract class Value<T> implements Renderable {

    abstract public T getValue();

    /**
     * @return A ValueType that denotes what this represents in GraphQL.
     * Both variable and stringValues will have a type of type of String, but
     * they represent different GraphQL types.
     */
    abstract public ValueType getType();

    @Override
    public String toString()
    {
        return getType() + ":" + getValue();
    }
}
