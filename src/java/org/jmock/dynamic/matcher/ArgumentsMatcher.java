/* Copyright (c) 2000-2003, jMock.org. See LICENSE.txt */
package org.jmock.dynamic.matcher;

import org.jmock.Constraint;
import org.jmock.dynamic.Invocation;

import java.util.List;

public class ArgumentsMatcher
        extends StatelessInvocationMatcher 
{
    private Constraint[] constraints;

    public ArgumentsMatcher(Constraint[] constraints) {
        ArgumentsMatcher.this.constraints = (Constraint[])constraints.clone();
    }

    public boolean matches(Invocation invocation) {
        return constraints.length == invocation.getParameterValues().size()
                && matchesValues(invocation.getParameterValues());
    }

    private boolean matchesValues(List list) {
        for (int i = 0; i < constraints.length; ++i) {
            if (!constraints[i].eval(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Constraint[] getConstraints() {
        return (Constraint[])constraints.clone();
    }

    public String toString() {
        return writeTo(new StringBuffer()).toString();
    }

    public boolean equals(Object other) {
        return other != null && toString().equals(other.toString());
    }

    public StringBuffer writeTo(StringBuffer buffer) {
        buffer.append("Arguments [");
        for (int i = 0; i < constraints.length; ++i) {
            buffer.append(constraints[i]).append(", ");
        }
        buffer.append("]");
        return buffer;
    }
}