package org.psnbtech;


import java.lang.annotation.Annotation;

public abstract @interface VisibleForTesting {
    int NONE = 0;
    int PACKAGE_PRIVATE = 1;
    int PRIVATE = 2;
    int PROTECTED = 3;

    public int otherwise();


}
