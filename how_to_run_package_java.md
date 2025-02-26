# first compile 

```bash
javac -d bin src/**/*.java
```

# how to run
```bash
java -cp bin:res main.Main
```

or no compile

```bash
java -cp bin:res main.Main
```
-cp bin tells Java to look in the bin directory for the compiled classes. The main.main_game is the fully qualified class name (including the package main).
and :res to say where is the resource folder
