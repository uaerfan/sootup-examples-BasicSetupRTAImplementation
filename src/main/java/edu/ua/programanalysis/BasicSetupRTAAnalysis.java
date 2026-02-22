package edu.ua.programanalysis;

import java.util.Collections;
import java.util.List;

import sootup.callgraph.CallGraph;
import sootup.callgraph.CallGraphAlgorithm;
import sootup.callgraph.RapidTypeAnalysisAlgorithm;
import sootup.core.IdentifierFactory;
import sootup.core.inputlocation.AnalysisInputLocation;
import sootup.core.signatures.MethodSignature;
import sootup.core.types.ClassType;
import sootup.java.bytecode.inputlocation.JavaClassPathAnalysisInputLocation;
import sootup.java.core.JavaProject;
import sootup.java.core.language.JavaLanguage;
import sootup.java.core.views.JavaView;

public class BasicSetupRTAAnalysis {

  public static void main(String[] args) {

    AnalysisInputLocation app =
        new JavaClassPathAnalysisInputLocation("target/classes");

    JavaProject project =
        JavaProject.builder(new JavaLanguage(17))
            .addInputLocation(app)
            .build();

    JavaView view = project.createView();


    IdentifierFactory idf = project.getIdentifierFactory();

    ClassType classType = idf.getClassType("demo.TargetProgramRTA");

    MethodSignature entryMethodSignature =
        idf.getMethodSignature(
            classType,
            "main",
            "void",
            List.of("java.lang.String[]")
        );


    CallGraphAlgorithm rta = 
        new RapidTypeAnalysisAlgorithm(view);

    CallGraph cg_rta = 
        rta.initialize(Collections.singletonList(entryMethodSignature));
    
    System.out.println("=== Call Graph (RTA) === ");
    System.out.println(cg_rta);

  }
}


