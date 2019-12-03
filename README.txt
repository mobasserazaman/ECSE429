
Compile and run GenerateMutants.java to generate Fault List.txt  (Questions_1_2 folder)
- javac GenerateMutants.java
- java GenerateMutants Arithmetics.java

Compile and run InjectMutants.java to generate mutated softwares  (Questions_1_2 folder)
- javac InjectMutants.java
- java InjectMutants

For Part3 move to KillMutants folder and run pitest using the following commands

- mvn clean
- mvn test

## For normal test running
- mvn org.pitest:pitest-maven:mutationCoverage

## For parallel test running 
- mvn -T 4 surefire:test org.pitest:pitest-maven:mutationCoverage

Reports are generated in the KillMutants/target/pit-reports/com.group17.pitest folder


