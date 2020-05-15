package com.variation.message;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.variation.message");

        noClasses()
            .that()
            .resideInAnyPackage("com.variation.message.service..")
            .or()
            .resideInAnyPackage("com.variation.message.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.variation.message.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
