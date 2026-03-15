package seedu.duke.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.exception.DukeException;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleBook;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public ModuleBook load() throws DukeException {
        ModuleBook moduleBook = new ModuleBook();
        if (!Files.exists(filePath)) {
            ensureParentDirectory();
            return moduleBook;
        }

        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Task task = decodeTask(line);
                Module module = moduleBook.getOrCreate(task.getModuleCode());
                module.getTasks().add(task);
            }
            return moduleBook;
        } catch (IOException e) {
            throw new DukeException("Failed to read storage file: " + e.getMessage());
        }
    }

    public void save(ModuleBook moduleBook) throws DukeException {
        ensureParentDirectory();
        List<String> lines = new ArrayList<>();
        for (Module module : moduleBook.getModules()) {
            for (Task task : module.getTasks().asUnmodifiableList()) {
                lines.add(task.encode());
            }
        }
        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks: " + e.getMessage());
        }
    }

    private Task decodeTask(String line) throws DukeException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 4) {
            throw new DukeException("Corrupted task entry: " + line);
        }
        String moduleCode = parts[0];
        String type = parts[1];
        boolean isDone = parseDone(parts[2]);
        String description = parts[3];

        switch (type) {
        case "T":
            return new Todo(moduleCode, description, isDone);
        default:
            throw new DukeException("Unsupported task type: " + type);
        }
    }

    private boolean parseDone(String raw) throws DukeException {
        if ("1".equals(raw)) {
            return true;
        }
        if ("0".equals(raw)) {
            return false;
        }
        throw new DukeException("Invalid done flag: " + raw);
    }

    private void ensureParentDirectory() throws DukeException {
        try {
            Path parent = filePath.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create storage directory: " + e.getMessage());
        }
    }
}
