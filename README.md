# Develop from scratch (commandline)
## Create a java File:
```
mkdir com\example\
```
Then create `Code.java` with the main method.

## Let's code
1. The code
```java
package com.example;

public class Code {
    public static void main(String [] args) {
        System.out.println("code.");
    }
}
```
2. compile it
`C:\commandline>javac com\example\Code.java`

3. execute it
We have to ensure where JVM have to look for the class files. We can do this with `-cp`.
Where can the JVM find the classes in out case? We are already in the right path. 
So we just take the current path (.) then we give the Main class (com.example.Code).
`C:\commandline>java -cp . com.example.Code`

## extend the code
1. we will create new class in a different package.
```
package com.example.utils;

public class Util {
    public static void ping() {
        System.out.println("pong");
    }
}
```

2. now use it in Code class.
```
package com.example;

import com.example.utils.Util;

public class Code {
    public static void main(String [] args) {
        System.out.println("code.");
        Util.ping();
    }
}
```

3. compile each class
Same steps as above.

4. run with -cp
Same: `java -cp . com.example.Code`

5. our folder structure will look like this
```
C:\commandline>tree /f
C:.
¦   readme.md
¦
+---com
    +---example
        ¦   Code.class
        ¦   Code.java
        ¦
        +---utils
                Util.class
                Util.java
```

#Create and execute a JAR or develop without IDE (just commandline)

## create a JAR file
```
C:\commandline>jar -cvf commandline.jar com
added manifest
adding: com/(in = 0) (out= 0)(stored 0%)
adding: com/example/(in = 0) (out= 0)(stored 0%)
adding: com/example/Code.java/(in = 0) (out= 0)(stored 0%)
```

##MANIFEST.MF
This will be automatically created by `jar` command. 
This is also the minimum requierd content for a MANIFEST.MF.
```
Manifest-Version: 1.0
Created-By: 11 (Oracle Corporation)
```
Check also the attributes `Main-Class` and `Class-Path`.

## show content of JAR file
```
C:\commandline>commandline>jar -tf commandline.jar
META-INF/
META-INF/MANIFEST.MF
com/
com/example/
com/example/Code.class
com/example/Code.java
com/example/utils/
com/example/utils/Util.class
com/example/utils/Util.java
```

## execute jar file
`C:\commandline>java -jar commandline.jar`
You will get an error if you dont defined the main attribute (main method)
So lets change this. You need to create the jar file with -e flag.
`C:\commandline>jar -cvfe commandline.jar com.example.Code com`
Lets check the manifest again.
```
Manifest-Version: 1.0
Created-By: 11 (Oracle Corporation)
Main-Class: com.example.Code
```
Looks good. Now execute.
`java -jar commandline.jar`

########################################################

