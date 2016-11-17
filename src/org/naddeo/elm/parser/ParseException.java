package org.naddeo.elm.parser;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * This exception is thrown when an error occurs while parsing a GraphQL document.
 * Check the message for human readable details on why the parse failed and what
 * was expected.
 */
@Value
@EqualsAndHashCode(callSuper = false)
public class ParseException extends RuntimeException
{
    String message;
    Exception cause;

    public ParseException(String message)
    {
        super(message);
        this.message = message;
        this.cause = null;
    }

    public ParseException(String message, Exception cause)
    {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }
}
