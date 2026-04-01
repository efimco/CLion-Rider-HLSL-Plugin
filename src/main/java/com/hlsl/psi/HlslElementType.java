package com.hlsl.psi;

import com.hlsl.HlslLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HlslElementType extends IElementType {
    public HlslElementType(@NonNls @NotNull String debugName) {
        super(debugName, HlslLanguage.INSTANCE);
    }
}
