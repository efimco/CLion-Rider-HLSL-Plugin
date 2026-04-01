package com.hlsl.psi;

import com.hlsl.HlslLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class HlslTokenType extends IElementType {
    public HlslTokenType(@NonNls @NotNull String debugName) {
        super(debugName, HlslLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "HlslTokenType." + super.toString();
    }
}
