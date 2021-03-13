package io.github.akkhadka.webstore.controller.viewmodels;

import io.github.akkhadka.webstore.controller.BusinessRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ViewModelBase {
    private HashMap<String,String> brokenRules = new HashMap<>();
    protected abstract  void Validate();

    public HashMap<String,String> getBrokenRules()
    {
        brokenRules.clear();
        Validate();
        return brokenRules;
    }
    protected void addBrokenRule(String ruleName,String businessRule)
    {
        brokenRules.put(ruleName,businessRule);
    }

}
