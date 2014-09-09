#vraptor-modelform

A plugin to generate html forms based on model classes.

##Installing

Add to your pom:

```xml
    <dependency>
    	<groupId>br.com.caelum.vraptor</groupId>
    	<artifactId>vraptor-modelform</artifactId>
    	<version>4.0.0-SNAPSHOT</version>
    </dependency>
```
    
##Using it

Suppose you have the following model class:

```java
public class User {
    private String name;
    private Calendar birthday;
    
    //getters and setters or constructor with fields
}
```

To create a model based on `User` class, inject a ModelForm instance of User in your controller:

```java

public class UserController {
    @Inject
    private ModelForm<User> form;
    @Inject
    private Result result;
    
    @Get
    public void add() {
        result.include("form", form);
    }
    
}
```

Then, in your in the add.jsp file, you can use the form to generate the html to create a new user:

```jsp
<%@ taglib prefix="forms" uri="http://vraptor.org/jsp/taglib/modelform" %>

<html>
    <body>
        <forms:formFor form="${form}"/>
    </body>
</html>

```

This will generate the required html form to create a new user.

Now, in your controller you can create a method receiving the User built with the form data:

```java

public class UserController {
    //fields and other methods...
        
    @Post
    public void add(User user) {
        System.out.println("user received from form: " + user);
    }
    
}
```