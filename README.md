# cucumber-step-generator
Java code generator which generate the cucumber feature scenario and steps.

## Steps to generate the step java files

1. Add the cucumber feature file under `test/resource/templates` folder.
2. Execute the gradle task `generateStepDefinitions`.
3. Generated step java classed will be saved under the `test/org/example/steps, Generated Java classes will be grouped by scenarios present in feature file.
