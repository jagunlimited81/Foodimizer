# Foodimizer

---

## About

This is a project for IT 386 Principles of Software Engineering

---

## Development

### Getting Started

1. Download [Intellij Community Edition](https://www.jetbrains.com/idea/download/#section=windows) from JetBrains
2. Setup IntelliJ
    1. [IntelliJ doocumentaion link](https://www.jetbrains.com/help/idea/getting-started.html)
3. New Project from VCS (Version Control Set)
4. Enter your GitHub Credentials
5. Select Foodimizer, then click clone
6. On the Title Bar, click **File | Settings | Build, Execution, Deployment | Build Tools | Maven**
    1. Check **Use plugin registry**
    2. In **Runner**, check the box **Delegate IDE build/run actions to Maven**
7. While still in the Settings window, navigate to **Build, Execution, Deployment | Compiler | Java Compiler**
    1. Set **Project bytecode version** to 19
    2. set Foodimizer **Target Bytecode Version** to 19
8. Click **Apply** then **Ok** to close the Settings Window
9. Click **File | Project Structure...** and ensure SDK and Language level are set to Java 19
10. Click on the Maven Module on the right of the IDE and click the Refresh Icon to Reload all Maven Projects. This will
    download all dependencies.

### Running Foodimizer from IDE

1. In the **Project** window, Navigate to ```.../src/main/java/Main.java```
2. Right click **Main.java** and select **Run 'Main.main()'** or press Ctrl+Shift+F10
3. After closing Foodimizer, you should now see a new **Run Configuration** called Main. This run configuration tells
   Intellij where to run your program from. You should now be able to hit the green arrow on the ribbon bar or press
   Shift+F10 to run the Main class.

### Creating the Foodimizer Executable

1. Click the **Maven Module | Plugins | assembly | assembly:single** and then click the green arrow in the Maven module
   window
2. The executable should be placed in ```.../target/Foodimizer-1.0-SNAPSHOT-jar-with-dependencies.jar```
3. To run the executable, pick one of three ways:
    1. Right click it in the Project window and click Run
    2. Open File explorer and navigate to Foodimizer. Double click on the .jar file
    3. Open a terminal and cd to Foodimizer. run ```java -jar Foodimizer-1.0-SNAPSHOT-jar-with-dependencies.jar```

---

## FAQ

#### Q: My program suddenly does not work anymore

A: Try removing the FoodimizerDB folder from $(user.home)

#### Q: Why is there so much red text in the console?

A: Its Hibernate [INFO] driver logs. Not much we can really do about it. It can safely be ignored most of the time,
but can be useful at times.

#### Q: what version of Java are we using?

A: Java version 19

#### Q: How are dependencies managed?

A: Dependencies are managed through the ```pom.xml``` file. The ```pom.xml``` file is required for maven

#### Q: What is the build schedule?

A: Builds are created on every commit to the main branch. They are stored in github actions.

#### Q: Do I have to use IntelliJ? I want to use Eclipse... etc.

A: IntelliJ is not required. This project uses maven, so any IDE that can interact with maven should be useable.

---

## Authors

- jagunlimited81
- Redakted
- nrlevy868
- snahuy
- ToMeHarris
