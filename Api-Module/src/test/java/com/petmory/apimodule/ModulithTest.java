package com.petmory.apimodule;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.core.DependencyDepth;
import org.springframework.modulith.docs.Documenter;

public class ModulithTest {

    @Test
    void test() {
        ApplicationModules modules = ApplicationModules.of("com.pawith");


        modules.forEach(System.out::println);

        Documenter.DiagramOptions diagramOptions = Documenter.DiagramOptions.defaults()
            .withElementsWithoutRelationships(Documenter.DiagramOptions.ElementsWithoutRelationships.VISIBLE)
            .withStyle(Documenter.DiagramOptions.DiagramStyle.C4)
            .withDependencyDepth(DependencyDepth.IMMEDIATE);



        new Documenter(modules)
            .writeModulesAsPlantUml(diagramOptions)
//            .writeDocumentation()
            .writeIndividualModulesAsPlantUml(diagramOptions);
    }
}
