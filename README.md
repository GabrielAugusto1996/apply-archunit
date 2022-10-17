# Introduction

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/ch5qxnnb24ajcarwjxza.png)

Many times when we start a project, the biggest difficulty we have is when organizing our packages, classes and definitions of which class can have access to a certain other class, after defeating this difficulty, our next challenge is to maintain this organization and document for future developers or even us, how we defined the architecture of our project, this is what you will see next!

# Why Architectural Testing?

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/30bdjmei0heqm1xxom62.png)

As mentioned before, in addition to organizing our project more and defining a convention, we managed to have documentation in a very friendly way, as seen below, we saw a rule informing where the classes annotated with **Entity** should reside:

```java
@ArchTest
    static final ArchRule entities_must_reside_in_a_domain_package =
            classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..domain..")
                    .as("Entities must reside in the domain package");
```

But how about we get our hands-on now? :)

# What technologies will we use?

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/ngn93gk3iqvmt26uvys9.png)

For this article in question, we will need to have the following technologies in our project:

1. [Java 8+](https://www.java.com/pt-BR/download/help/whatis_java.html): Java Programming Language in version 8+
2. [Maven](https://maven.apache.org/): Dependency management tool in Java.
3. [ArchUnit](https://www.archunit.org/): Tool that will be used for the development of our architectural tests
4. [Spring](https://spring.io/): Programming framework

I will not go into too much of all the technologies used because it is not our purpose of this article.

## Setting up our project:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/vc5dbouy55a2xq6iyn5z.png)

In order for us to use **archunit** in our project, we will need to add the following dependency:

```xml
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>0.18.0</version>
    <scope>test</scope>
</dependency>
```

After that, we will create a single class called **ArchitectureTest** in our test package, which will have the following implementation at this first moment:

```java

@AnalyzeClasses(
        packages = "YourPackage",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ArchitectureTest {
}
```

**Note:** In the example above, an option was inserted so that the tests were not included in our validation, but if you want to define a pattern in the name of your tests too, feel free. :)

# Now, let's get to what interests us! =D

![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/wju6w43jd9ilcgf3341b.png)

Together, we will define a simple architecture in which we will have **Controller** classes that will only be able to call **Service** classes and that will only be able to call **Repository** classes, as seen in the drawing below:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/3waa6n7a3gn6hnfsbpw6.png)

After that, we will define how the name of each class will look and finally, what will be the package that it will reside in, that said, let's create our architecture? :)

# Defining the name of our classes:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/o28rw5zmucv52lfvx23z.png)

In the 1st step we will define what the name of our classes should be, since we are extremely creative people, we are going to use the name of each annotation at the end of our class, why has no one ever thought of this before? :D

```java
    @ArchTest
    static ArchRule ClassesThatHaveControllerAnnotation_MustEndWithControllerName =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().haveSimpleNameEndingWith("Controller")
            .as("Dear developer, all our classes that are annotated as Controller, must have the name finalized with Controller");
```

As seen above, the use of **ArchUnit** tests are very simple and very intuitive, within our test we are informing that every class that is annotated as **Controller**, must have the name finalized as **Controller* * and in addition we leave a tip for the next developer who doesn't do it the way we planned, using ".as(....)", isn't that cool? :)

I forced an error, changing the name of our class to **UserJohn**, look how cool what happened:


![Image description](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/jr6m9hh77rb49b5xe31z.png)

After that, we just create the rules for our other classes, but that's for you to try :)

If you can't, don't worry, just open the project's [GitHub](https://github.com/GabrielAugusto1996/apply-archunit) :)

# Defining the residence of our classes:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/miwqb0ul4jxdmhqdusrt.png)

In the 2nd step we will define where each class will reside, the test will be very similar to the previous one, so let's go? :)

```java
@ArchTest
    static ArchRule ClassesThatHaveControllerAnnotation_MustResideInTheControllerPackage =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().resideInAPackage("..controller..")
                    .as("Dear developer, all our classes that are annotated as Controller, must reside in the *.controller package");
```

With the example above, we inform in ".resideInAPackage(...)", which will be the place where our class will actually reside.

# Defining which class will call which class:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/t3rc7mpw5stpdqie13cv.png)

Finally, to conclude our challenge, we need to define which class will call the other one, so that in this way, we have control of which class will be able to know the other, for that we will follow the example below:

```java

 @ArchTest
    static final ArchRule ClassesResidingInControllerPackageCannotKnowRepository =
            noClasses().that().resideInAPackage("..controller..")
                    .should().dependOnClassesThat().resideInAPackage("..repository..")
            .as("Repository classes cannot be together with Controller classes :(");

```

In the example above, we defined that no class that resides in the **Controller** package can depend on any class that resides in the **Repository** package, what a sad love story, isn't it? :D

# Thanks guys =D

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/n7hbpeodptbcdpqoxds0.png)

I thank all of you who read my article and who accompany me in the creation of my content :)

[ArchUnit](https://www.archunit.org/getting-started), has great documentation and helps me a lot in the projects I run in my day to day, what do you think about presenting this in your projects? :)

- Linkedin: https://www.linkedin.com/in/gabriel-augusto-1b4914145/
- Dev.io: https://github.com/GabrielAugusto1996/apply-archunit