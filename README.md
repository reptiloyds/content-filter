# Content Filter CLI Utility
Java utility for processing text files line by line. Each line is classified as `int`, `float`, or `string`, and written to the corresponding output file.

## Requirements
- Language: Java 21
- Build: Apache Maven 3.9.9 + Maven Shade Plugin (fat jar)
- Third party libraries:
   picocli 4.7.7 https://mvnrepository.com/artifact/info.picocli/picocli/4.7.7

## Path Handling
The program uses relative paths based on the current working directory:
When running as a .jar, all output and input paths are resolved relative to the directory from which the .jar was launched.
When running from IntelliJ IDEA, paths are resolved relative to the project root directory.

## Build & Run Instructions
### From IntelliJ IDEA:
1. Setup run configuration like this:
   -o outputPath -p prefix_ -s input1.txt input2.txt
2. Run from ru.naumov.contentfilter.app.EntryPoint.main();

### From console:
1. Build by maven or use release jar
2. Run jar from it`s directory like this:
   java -jar content-filter.jar -o outputPath -p prefix_ -s input1.txt input2.txt