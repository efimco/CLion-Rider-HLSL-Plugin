package com.docc.hlsl.psi;

import com.docc.hlsl.HlslFileType;
import com.docc.hlsl.HlslLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class HlslFile extends PsiFileBase {
    public HlslFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, HlslLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return HlslFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "HLSL File";
    }
}
