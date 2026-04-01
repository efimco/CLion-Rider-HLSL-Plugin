package com.hlsl;

import com.intellij.lang.Language;

public class HlslLanguage extends Language {
    public static final HlslLanguage INSTANCE = new HlslLanguage();

    private HlslLanguage() {
        super("HLSL");
    }
}
