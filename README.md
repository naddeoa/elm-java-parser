JFlex/Cup template project
===

This is a template for starting a JFlex/Cup project. Build with `ant`.

That will bring up a repl (ish) for the DSL in this package. Here is some sample
input.

```
**DBG_VALUE 1.2
```

You might have to hit ctrl+D and then enter to get the parser to stop. It will
accept that input as a Float, print and exit. Feel free to gut the existing
parser/lexer and replace with whatever.
