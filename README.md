## Mini project : The traveling saleman problem

### Members and contribution:

1.  Nguyễn Văn Lực - 20176812 
    *  Create project
    * Implemented City, CityManager classes, Displayable interface 
    * Build GUI (App class) and connect API of each part together 
2. Vũ Ngọc Anh - 20176685 
    * Implemented MyLine class
3. Vũ Tùng Lâm - 20184283 
    * Implemented Tour class and Solver using backtracking algorithm


Link video demo: https://youtu.be/DKKdk_jpGHE


### Requirements for installation

1. Java JDK
2. JavaFX (included in this repository)
3. Maven

#### To install **Maven** (on Ubuntu):

```
    sudo apt install maven
```
See more at : https://maven.apache.org/install.html
### To run the project :

Clone the repository and run :

```
cd salemanProblem/
mvn clean
mvn package
mvn assembly:single
```
Then : 

For Ubuntu:
```

java --module-path javafx-sdk-15/lib --add-modules javafx.controls -jar target/salemanProblem-1.0-jar-with-dependencies.jar
```
For Mac OS X:
```
java --module-path javafx-sdk-14.0.1/lib --add-modules javafx.controls -jar target/salemanProblem-1.0-jar-with-dependencies.jar
```

Or simplify:

```
cd salemanProblem/
mvn clean package assembly:single
```
Then : 

For Ubuntu:
```

java --module-path javafx-sdk-15/lib --add-modules javafx.controls -jar target/salemanProblem-1.0-jar-with-dependencies.jar
```
For Mac OS X:
```
java --module-path javafx-sdk-14.0.1/lib --add-modules javafx.controls -jar target/salemanProblem-1.0-jar-with-dependencies.jar
```
