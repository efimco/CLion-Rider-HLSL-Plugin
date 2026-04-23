# HLSL Language Support for CLion/Rider
plugin providing syntax highlighting and DXC validation for HLSL (High Level Shading Language) files.

![Example](example.png)

## Features

- **Syntax highlighting** for keywords, types, built-in functions, semantics, preprocessor directives, numbers, strings, operators, and comments
- **Struct/class name highlighting** — struct, cbuffer, tbuffer, class, interface, enum names and typedef aliases are highlighted at declaration and every usage site, including names declared in transitively `#include`d files
- **Go to declaration** (`Ctrl+Click` / `Ctrl+B`) — jump to function, variable, and type declarations in the current file or any included file
- **Code completion** — keywords, types, built-in functions (with auto-inserted parentheses), semantics (suggested after `:`), local identifiers, and symbols pulled from included files (functions, struct-like names, `#define` macros, and top-level `const` globals)
- **Code folding** — collapse any multi-line `{ ... }` block (functions, structs, cbuffers, control-flow blocks) and block comments, each region independent
- **clang-format integration** — Reformat Code routes HLSL files through a user-configured `clang-format` binary that picks up your `.clang-format` file (walks up from the file's directory)
- **DXC compiler validation** — real-time error and warning annotations powered by the DirectX Shader Compiler
- **"Add DXC pragmas" intention** — inserts `#pragma hlsl profile` / `entry` / `hv` skeleton at the top of the file
- **Line and block commenting** (`Ctrl+/`, `Ctrl+Shift+/`)
- **Brace matching** for `()`, `{}`, `[]`
- **Color settings page** — customize all highlight colors under Settings → Editor → Color Scheme → HLSL

## Supported File Extensions

`hlsl`, `hlsli`, `fx`, `fxh`

Additional extensions can be added via Settings → Editor → File Types → HLSL.

## DXC Validation

The plugin can run the DirectX Shader Compiler (dxc) in the background to show compilation errors and warnings inline.

### Setup

1. Go to **Settings → Tools → HLSL / DXC**
2. The plugin auto-detects `dxc.exe` from your PATH, Windows SDK, or Vulkan SDK
3. Optionally set the path manually, default shader profile (default: `ps_6_6`), entry point (default: `main`), and HLSL version (default: `2021`)

### Per-file Overrides

Use pragma comments at the top of your shader files:

```hlsl
// #pragma hlsl profile vs_6_6
// #pragma hlsl entry VSMain
```

## clang-format

Because HLSL isn't a language clang-format recognizes by extension, the built-in JetBrains ClangFormat integration does not engage on `.hlsl` files. This plugin adds its own integration that transparently routes the Reformat Code action through clang-format.

### Setup

1. Go to **Settings → Tools → HLSL / clang-format**
2. The plugin auto-detects `clang-format` from your PATH. Optionally set the path manually
3. Set a fallback style (LLVM, Google, Chromium, Mozilla, WebKit, Microsoft) for files not covered by a `.clang-format`
4. Use Reformat Code (`Ctrl+Alt+L`, or whatever you've mapped it to — e.g. `Alt+Shift+F`) on an `.hlsl` file

Your `.clang-format` is discovered automatically by walking up from the file's directory. Range formatting (reformat selection) is supported.

## Building

Requires JDK 21.

```
.\gradlew.bat buildPlugin
```

The plugin zip will be in `build/distributions/`.

## Installation

1. Download the latest `.zip` from the [Releases](../../releases) page, or build it yourself (see above)
2. In CLion/IntelliJ, go to **Settings → Plugins → ⚙ → Install Plugin from Disk...**
3. Select the `.zip` file
4. Restart the IDE

## Compatibility

- IntelliJ Platform 2024.1 – 2026.1
- Works alongside the C/C++ plugin without conflicts

## License

This project is licensed under the [MIT License](LICENSE).
