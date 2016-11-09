#!/bin/sh

if ! [ -f ./bin/elm-parser.jar ]; then
    echo "Build first with 'ant'"
    echo "You can probably get it through your package manager on Linux, or 'brew isntall ant' on a mac."
    exit 1
fi

echo '**DBG_IMPORT_STMT import MyModule' | java -jar bin/elm-parser.jar
echo '**DBG_IMPORT_STMT import MyModule.Submodule as Somethingelse' | java -jar bin/elm-parser.jar
echo '**DBG_IMPORT_STMT import ModuleDefinition.Submodule exposing (Html, li, ul)' | java -jar bin/elm-parser.jar
echo '**DBG_MODULE_DEFINITION module Html' | java -jar bin/elm-parser.jar
echo '**DBG_MODULE_DEFINITION module Html exposing (a,b, C)' | java -jar bin/elm-parser.jar
