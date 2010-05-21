/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mulesource.glu.parameter;

public class ParameterDefBoolean extends ParameterDef
{
    private boolean defaultVal;
    
    public ParameterDefBoolean(String desc)
    {
        this(desc, true, false);
    }
        
    public ParameterDefBoolean(String desc, boolean defaultVal)
    {
        this(desc, false, defaultVal);
    }
    
    public ParameterDefBoolean(String desc, boolean required, boolean defaultVal)
    {
        super("boolean", desc, required);
        this.defaultVal = defaultVal;
    }

    @Override
    protected Object getDefaultVal()
    {
        return defaultVal;
    }
}


