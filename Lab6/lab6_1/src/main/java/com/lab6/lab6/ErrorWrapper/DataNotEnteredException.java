package com.lab6.lab6.ErrorWrapper;

public class DataNotEnteredException extends Exception{
	public DataNotEnteredException()
    {
    }

    public DataNotEnteredException(String message)
    {
        super(message);
    }
}
