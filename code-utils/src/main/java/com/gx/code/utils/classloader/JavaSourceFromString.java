package com.gx.code.utils.classloader;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class JavaSourceFromString extends SimpleJavaFileObject {
    // name是类名，code是类的内容
    final String code;

    public JavaSourceFromString(URI uri, String code) {
        super(uri, Kind.SOURCE);
        this.code = code;
    }

    public CharSequence getCharContent(boolean ignoreErrors) {
        return code;
    }
}
