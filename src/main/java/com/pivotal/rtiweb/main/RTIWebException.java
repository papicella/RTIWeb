package com.pivotal.rtiweb.main;

public class RTIWebException extends Exception
{
    public RTIWebException()
    {
    }

    public RTIWebException(final Throwable cause)
    {
        super(cause);
    }

    public RTIWebException
            (final String msg,
             final Throwable cause)
    {
        super(msg, cause);
    }

    public RTIWebException(final String msg)
    {
        super(msg);
    }
}
