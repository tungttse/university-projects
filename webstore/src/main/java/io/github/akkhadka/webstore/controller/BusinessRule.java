package io.github.akkhadka.webstore.controller;

public class BusinessRule {
    private String property;
    private String rule;

    public String getProperty() {
        return property;
    }

    public BusinessRule(String property, String rule) {
        this.property = property;
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }


}
