package com.docc.hlsl.parser;

import com.docc.hlsl.HlslLanguage;
import com.docc.hlsl.lexer.HlslLexer;
import com.docc.hlsl.psi.HlslFile;
import com.docc.hlsl.psi.HlslTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class HlslParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE =
            new IFileElementType(HlslLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new HlslLexer();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new HlslParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return HlslTokenTypes.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return HlslTokenTypes.WHITESPACES;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return HlslTokenTypes.STRINGS;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return com.intellij.psi.impl.source.tree.LeafPsiElement.class
                .isAssignableFrom(node.getClass())
                ? new com.intellij.psi.impl.source.tree.LeafPsiElement(node.getElementType(), node.getText())
                : new com.intellij.psi.impl.source.tree.CompositePsiElement(node.getElementType()) {};
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new HlslFile(viewProvider);
    }
}
