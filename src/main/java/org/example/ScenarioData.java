package org.example;

import java.util.List;

public class ScenarioData {
    private final String scenarioName;
    private final List<String> steps;

    public ScenarioData(String scenarioName, List<String> steps) {
        this.scenarioName = scenarioName;
        this.steps = steps;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public List<String> getSteps() {
        return steps;
    }
}
