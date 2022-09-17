# reflex

Load json into a Java class using the reflection api.

## What data types are supported?

- double
- boolean
- String
- int
- long
- ReflexDoubleArray
- ReflexBooleanArray
- ReflexStringArray
- ReflexIntArray
- ReflexLongArray
- ReflexCustomArray

You can use a custom class as a datatype too. The class needs to contain @ReflexFiled's, and it needs to be != null

## Example

```java
// Test.java

import gq.glowman554.reflex.ReflexField;

public class Test { 
    @ReflexField
    public String hello;
}
```

```java
import gq.glowman554.reflex.Reflex;
import gq.glowman554.reflex.loaders.ReflexJsonLoader;

// Main.java
public class Main {
    public static void main(String[] args) {
        Test t = (Test) new Reflex(new ReflexJsonLoader("{ \"hello\": \"hello world\"}")).load(new Test());
        System.out.println(t.hello); // Prints: hello world
    }
}
```

A full example can be found [here](https://github.com/Glowman554/reflex/blob/main/src/test/java/TestJson.java).