package com.hlsl.highlighting;

import com.hlsl.HlslLanguage;
import com.hlsl.psi.HlslTokenTypes;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Annotator that highlights struct/cbuffer/tbuffer names at both declaration and usage sites.
 * First collects all declared struct-like names, then highlights every matching identifier.
 */
public class HlslStructNameAnnotator implements Annotator {

    private static final Set<String> DECLARING_KEYWORDS = Set.of(
            "struct", "cbuffer", "tbuffer", "class", "enum", "interface"
    );

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!element.getLanguage().is(HlslLanguage.INSTANCE)) return;

        IElementType type = element.getNode().getElementType();
        if (type != HlslTokenTypes.IDENTIFIER) return;

        String text = element.getText();
        Set<String> structNames = getStructNames(element.getContainingFile());
        if (structNames.contains(text)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(HlslSyntaxHighlighter.STRUCT_NAME)
                    .create();
        }
    }

    private static Set<String> getStructNames(@NotNull PsiFile file) {
        Set<String> names = new HashSet<>();
        PsiElement child = file.getFirstChild();
        while (child != null) {
            collectStructNames(child, names);
            child = child.getNextSibling();
        }
        return names;
    }

    private static void collectStructNames(PsiElement element, Set<String> names) {
        IElementType type = element.getNode().getElementType();
        // If this is a STRUCT_NAME token (from the lexer), collect it
        if (type == HlslTokenTypes.STRUCT_NAME) {
            names.add(element.getText());
        }
        // Also check KEYWORD followed by IDENTIFIER pattern via siblings
        if (type == HlslTokenTypes.KEYWORD && DECLARING_KEYWORDS.contains(element.getText())) {
            PsiElement next = skipWhitespace(element.getNextSibling());
            if (next != null) {
                IElementType nextType = next.getNode().getElementType();
                if (nextType == HlslTokenTypes.IDENTIFIER || nextType == HlslTokenTypes.STRUCT_NAME) {
                    names.add(next.getText());
                }
            }
        }
        // Recurse into children
        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            collectStructNames(child, names);
        }
    }

    private static PsiElement skipWhitespace(PsiElement element) {
        while (element != null && element.getNode().getElementType() == com.intellij.psi.TokenType.WHITE_SPACE) {
            element = element.getNextSibling();
        }
        return element;
    }
}
