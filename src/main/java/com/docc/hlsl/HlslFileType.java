package com.docc.hlsl;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class HlslFileType extends LanguageFileType {
    public static final HlslFileType INSTANCE = new HlslFileType();

    private HlslFileType() {
        super(HlslLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "HLSL File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "HLSL shader file";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "hlsl";
    }

    @Override
    public Icon getIcon() {
        return HlslIcons.FILE;
    }
}
