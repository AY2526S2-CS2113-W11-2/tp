package seedu.duke.module;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModuleBook {
    private final Map<String, Module> modules = new LinkedHashMap<>();

    public Module getOrCreate(String code) {
        return modules.computeIfAbsent(code.toUpperCase(), Module::new);
    }

    public Collection<Module> getModules() {
        return modules.values();
    }

    public int totalTaskCount() {
        return modules.values().stream()
                .mapToInt(module -> module.getTasks().size())
                .sum();
    }
}
