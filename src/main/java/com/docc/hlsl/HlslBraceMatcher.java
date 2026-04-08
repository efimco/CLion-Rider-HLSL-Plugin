package com.docc.hlsl;

import com.docc.hlsl.psi.HlslTokenTypes;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HlslBraceMatcher implements PairedBraceMatcher {
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(HlslTokenTypes.LPAREN, HlslTokenTypes.RPAREN, false),
            new BracePair(HlslTokenTypes.LBRACE, HlslTokenTypes.RBRACE, true),
            new BracePair(HlslTokenTypes.LBRACKET, HlslTokenTypes.RBRACKET, false),
    };

    @Override
    public BracePair @NotNull [] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
