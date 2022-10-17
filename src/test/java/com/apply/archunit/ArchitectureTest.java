package com.apply.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "com.apply.archunit",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ArchitectureTest {

    //Defining classes names

    @ArchTest
    static ArchRule ClassesThatHaveControllerAnnotation_MustEndWithControllerName =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().haveSimpleNameEndingWith("Controller")
                    .as("Dear developer, all our classes that are annotated as Controller, must have the name finalized with Controller");

    @ArchTest
    static ArchRule ClassesThatHaveRepositoryAnnotation_MustEndWithRepositoryName =
            classes()
                    .that().areAnnotatedWith(Repository.class)
                    .should().haveSimpleNameEndingWith("Repository")
                    .as("Dear developer, all our classes that are annotated as Repository, must have the name finalized with Repository");

    @ArchTest
    static ArchRule ClassesThatHaveServiceAnnotation_MustEndWithServiceName =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Service")
                    .as("Dear developer, all our classes that are annotated as Service, must have the name finalized with Service");

    // Definindo packages names
    @ArchTest
    static ArchRule ClassesThatHaveControllerAnnotation_ShouldResideInTheControllerPackage =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().resideInAPackage("..controller..")
                    .as("Dear developer, all our classes that are annotated as Controller, must reside in the *.controller package");

    @ArchTest
    static ArchRule ClassesThatHaveServiceAnnotation_ShouldResideInTheServicePackage =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .should().resideInAPackage("..service..")
                    .as("Dear developer, all our classes that are annotated as Service, must reside in the *.service package");

    @ArchTest
    static ArchRule ClassesThatHaveRepositoryAnnotation_ShouldResideInTheRepositoryPackage =
            classes()
                    .that().areAnnotatedWith(Repository.class)
                    .should().resideInAPackage("..repository..")
                    .as("Dear developer, all our classes that are annotated as Service, must reside in the *.service package");

    // Defining classes access

    @ArchTest
    static final ArchRule ClassesResidingInControllerPackageCannotKnowRepository =
            noClasses().that().resideInAPackage("..controller..")
                    .should().dependOnClassesThat().resideInAPackage("..repository..")
                    .as("Repository classes cannot be together with Controller classes :(");
}