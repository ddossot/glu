/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mulesource.glu.exception;

import org.mulesource.glu.component.api.HTTP;

public class GluDuplicateKeyException extends GluException
{
    public GluDuplicateKeyException()
    {
        this("Duplicate key");
    }
    
    public GluDuplicateKeyException(String message)
    {
        super(HTTP.INTERNAL_SERVER_ERROR, message);
    }
}


