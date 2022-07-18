# Quality Guidelines
The following is a comprehensive guide for maintaining a quality, robust, and *consistent* codebase. Many of these guides can transfer between the API and UI sides of the program, but any differences between the two will be separated as necessary. 

# Documentation
Documentation is an absolute must. Where possible, provide ample amounts of comments to help others understand how your code works. Although it may seem redundant, it's important to make things as clear and concise for people who may not be very familiar with the codebase.

## Documenting custom functions
When creating a function, the use of Javadoc is required to provide a summary of what the function does as well as what arguments are required.

When using Javadoc, the function summary should be informative but concise; the summary should also line up with the name of the function.
```java
// example of bad jsdoc
/**
   * Checks if product is in cart.
   * If product is in cart, then it will add 1 to the quantity.
   * If product is not in cart, then it will add product to cart.
   * @param product product to be added to cart
   */
 public void addItemToCart(Product product) => { // bad; the java written lines up more with a function called isInCart()
	 //...code
 }
```java
// example of good javadoc
/**
   * Adds product to cart.
   * If product is in cart, then it will add 1 to the quantity.
   * If product is not in cart, then it will add product to cart.
   * @param product product to be added to cart
   */
  public void addItemToCart(Product product) { // good javadoc
	  // ...code
  }

```
## Commenting code

Commenting code is not just useful; it is an absolute must. Thorough commenting allows clarity so people who may not know much (or anything) about the codebase. A comment should provide concise, informative, and detailed information about the code and what it does. 

A good rule of thumb is to comment every few lines. Again, although it may seem redundant, this clarity is required to ensure a deep understanding of how code works for yourself and others.

Use single-line comments where applicable; however, multi-line comments are beneficial where a large amount of comments in one place (such as directing people where to go  in code). 
```java
public static int addSum(int... input) {  
    int sum = 0;  
  for (int i : input) { // iterates through the input  
  sum = sum + i; // adds the number  
  }  
    return sum; // returns the sum  
}
```



## Package names
Package names are to be lowercase while class names are to be `PascalCase`; no exceptions. For example:
```java
import com.Revature.Utils.logger; // incorrect
import com.revature.utils.Logger; // correct
```
## Plain Old Java Objects (POJOs)
POJOs should have the following:

 1. Class declaration
 2. Private fields
 3. Constructors 
 4. Getters/Setters
 5. `toString()` , `hashCode()`, and  `equals()`

If the POJO makes use of JPA and Hibernate, **do not use Lombok under any circumstances**. Lombok processes code before compilation, and although it may work at first, at some point, it *will* cause issues down the line. The only exception is Data Transfer Objects (DTOs).

Additionally, if the POJO has a no-args constructor, it should make use of the `super();` function, just for readability purposes. The following is an example of two POJOs; one without Lombok, and one with.
### Standard POJO:
```java
package dev.richard;

import java.util.Objects;

public class ExamplePOJO {
    private String name;
    private int age;
    private String job;

    public ExamplePOJO() {
        super();
    }

    public ExamplePOJO(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "ExamplePOJO{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", job='" + job + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamplePOJO that = (ExamplePOJO) o;
        return age == that.age && Objects.equals(name, that.name) && Objects.equals(job, that.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, job);
    }
}
```
 ### Lombok POJO:
 ```java
 package dev.richard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExamplePOJO {
    private String name;
    private int age;
    private String job;
}
```
## Interfaces
Interfaces are to be adjectives in most cases, such as `Serializable`; however, if it is used to present a class (such as `List`).
## Repositories 
Spring repositories are to be named very briefly but descriptive. Simply put, a repository is to be named `[Entity]Repository`. If it is for an entity called User, it should be called `UserRepository`. This interface should extend `JpaRepository <Entity, Object>`; ensure the interface has the `@Repository` annotation. The following is an example of a potential `User` repository. 

```java
package dev.richard.repositories;
import dev.richard.common.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    @Query("from User u where u.username = :username")
    List<User> findUsersByUsername(String username);

    List<User> findAll();
    
    @Query("select u from User u where u.username = ?1")
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    @Query("select u from User u where u.email = ?1 and u.password = ?2")
    Optional<User> findUserByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> findUserByUsernameAndPassword(String username, String password);
}
```
## Various formatting conventions
Curly braces are to be same-line and tabs are to be four spaces per tab.

## Variable naming
Local variables are to be `camelCase` in almost all cases, with the exception of very common names (such as username and password). 
```java 
// java example
package dev.richard;

public class Example {
	int exampleint = 15; // bad name
	int exampleInt = 15; // good name
}
```
Static variables are to be written in `SCREAMING_SNAKE_CASE`. This does not apply to a static *reference*. 
```java
// java example
public class Universe {
	static final int MEANING_OF_LIFE = 42;
}

public class Example {
	public static void main(String[] args) {
		System.out.println(Universe.MEANING_OF_LIFE); // 42
	}
}
```

## Function declaration
Functions, like variables, are to be `camelCase`. 
```java
// java example
public class Example {
	public static void helloWorld() {
		System.out.println("Hello, world!");
	}
	public static void main(String[] args) {
		helloWorld();
	}
}

```

Like variable names, functions (and required arguments) should be named after what they do. Keep it as concise as reasonably possible while still relating to its job. (Javadoc has been omitted)
```java
// java good example
public int addSum(int... input) {
	// ...code
}
// java bad example
public int as(int... x) {
	// ...code
}
```
## Conditional statements
Conditional statements, if single line and relatively simple, could be replaced with a ternary operator (though is not required). Ensure that the operator is separated by newlines and tabbed, to make readability easier.
```java
// java example
public static void main(String[] args) {
        String ternaryExample = (5 == 5)
	        ? "This statement is true" 
	        : "Something has gone very wrong";
        System.out.println(ternaryExample);
}
```
Otherwise, if statements are to use same-line curly braces, and formatting does not change regardless of how many lines the statement might have. 
## Conclusion
This concludes quality conventions required for the final project. If you require any assistance, DM Richard for more information.
