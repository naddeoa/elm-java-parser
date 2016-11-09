JFlex/Cup parser for Elm
===

I started this project out of a desire to improve the tooling around elm. Its
brand new, so don't try and use it or anything. So far, it can parse the module
and import statements into an object model. The first iteration/POC is going to
be using that object model to provovide auto organize imports for elm source.

=== Building ===

```
ant
```

The unit tests demonstrate the types of things that the parser currently
accepts. It uses special debug tokens to jump straight into specific rules in
the langauge. Upon succesful parse, the object model will be spit out.

```
echo '**DBG_IMPORT_STMT import MyModule' | java -jar bin/graphql-parser.jar
> ImportStatement(name=MyModule, alias=Optional.empty, exposed=Optional.empty)

echo '**DBG_IMPORT_STMT import MyModule.Submodule as Somethingelse' | java -jar bin/graphql-parser.jar
> ImportStatement(name=MyModule.Submodule, alias=Optional[Somethingelse], exposed=Optional.empty)

echo '**DBG_IMPORT_STMT import ModuleDefinition.Submodule exposing (Html, li, ul)' | java -jar bin/graphql-parser.jar
> ImportStatement(name=ModuleDefinition.Submodule, alias=Optional.empty, exposed=Optional[Exposed(exports=[Html, li, ul])])

echo '**DBG_MODULE_DEFINITION module Html' | java -jar bin/graphql-parser.jar
> ModuleDefinition(name=Html, exposes=Exposed(exports=[]))

echo '**DBG_MODULE_DEFINITION module Html exposing (a,b, C)' | java -jar bin/graphql-parser.jar
> ModuleDefinition(name=Html, exposes=Exposed(exports=[a, b, C]))
```

This is the first step to generating an object model that closely resembles the
source, but may not closely resemle the actual grammar that the compiler agrees
with. This would be consumed (in my projects) by code completion server. That
server would keep instances of these in memory and offer completion via an API
(rest or otherwise, not too important).

I'm a little unsure about the project at the moment. The Elm community seems to
be converging on using `elm-lang` for something very much like this. I think
this approach has a few advantages though.

* I think the JVM is more portable and accessible than Haskell is at the moment.
    In order to build the `elm-format` project you need to have `stack`
    installed. Currently, the ubuntu repositories have an incredibly out of date
    version and the fpcomplete deb servers just return `Not authorized`. They
    offer a shell script that you can pipe directly off the internet, full of
    sudo commands that I'm not comfortable with.

 * In the end, it will just serialize as JSON (much like what is planned for
     `elm-format`) so it can be consumed in a more flexible way. That said, any
     JVM language can directly use it as a library, which includes Haskell via
     Frege.

 This may go no where, but I at least want the POC up and running to provide
 import optimization for Vim before thorwing in the towel.

