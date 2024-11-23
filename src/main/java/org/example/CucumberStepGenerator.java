package org.example;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.example.ScenarioData;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class CucumberStepGenerator {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java CucumberStepGenerator <feature file> <output directory>");
            return;
        }

        String featureFilePath = args[0];
        String outputDirectory = args[1];

        List<ScenarioData> scenarios = parseFeatureFile(featureFilePath);

        for (ScenarioData scenario : scenarios) {
            String className = scenario.getScenarioName().replaceAll("[^a-zA-Z0-9]", "") + "Steps";
            String outputFilePath = outputDirectory + "/" + className + ".java";
            generateStepDefinition(scenario, outputFilePath, className);
            System.out.println("Generated class: " + className);
        }
    }

    private static List<ScenarioData> parseFeatureFile(String featureFilePath) throws IOException {
        List<ScenarioData> scenarios = new ArrayList<>();
        List<String> currentSteps = new ArrayList<>();
        String currentScenario = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(featureFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("Scenario:")) {
                    if (currentScenario != null) {
                        scenarios.add(new ScenarioData(currentScenario, new ArrayList<>(currentSteps)));
                        currentSteps.clear();
                    }
                    currentScenario = line.substring("Scenario:".length()).trim();
                } else if (line.matches("^(Given|When|Then|And|But) .+")) {
                    currentSteps.add(line);
                }
            }
            if (currentScenario != null) {
                scenarios.add(new ScenarioData(currentScenario, currentSteps));
            }
        }
        return scenarios;
    }

    private static void generateStepDefinition(ScenarioData scenario, String outputFilePath, String className) throws IOException {
        List<Map<String, String>> methods = extractStepDefinitions(scenario.getSteps());
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("packageName", "org.example.steps");
        templateData.put("className", className);
        templateData.put("methods", methods);

        String templateFile = "src/main/resources/templates/StepDefinition.mustache";
        renderTemplate(templateFile, outputFilePath, templateData);
    }

    private static List<Map<String, String>> extractStepDefinitions(List<String> steps) {
        List<Map<String, String>> methods = new ArrayList<>();
        Pattern stepPattern = Pattern.compile("^(Given|When|Then|And|But) (.+)$");

        for (String step : steps) {
            Matcher matcher = stepPattern.matcher(step);
            if (matcher.find()) {
                String stepType = matcher.group(1);
                String stepText = matcher.group(2);
                String methodName = stepText.replaceAll("[^a-zA-Z0-9]", "_");

                Map<String, String> methodData = new HashMap<>();
                methodData.put("stepType", stepType);
                methodData.put("stepText", stepText);
                methodData.put("methodName", methodName);

                methods.add(methodData);
            }
        }
        return methods;
    }

    private static void renderTemplate(String templateFile, String outputFilePath, Map<String, Object> data) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(new FileReader(templateFile), "stepDefinition");

        File outputFile = new File(outputFilePath);
        outputFile.getParentFile().mkdirs();

        try (Writer writer = new FileWriter(outputFile)) {
            mustache.execute(writer, data).flush();
        }
    }
}


