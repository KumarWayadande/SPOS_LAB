import java.io.*;
import java.util.*;

// Main Macro Processor Class
class MacroProcessor {
    public static void main(String[] args) {
        // Example assembly code containing macros
        String sourceCode = "START\n" +
                "MACRO\n" +
                "INCR &ARG1, &ARG2\n" +
                "A 1, &ARG1\n" +
                "B 1, &ARG2\n" +
                "MEND\n" +
                "INCR X, Y\n" +
                "END";

        PassOne passOne = new PassOne();
        passOne.process(sourceCode);
    }
}

// Class for Pass-I of the macro processor
class PassOne {
    private MacroTable macroTable;
    private ArgumentListArray ala;
    private List<String> intermediateFile;

    public PassOne() {
        macroTable = new MacroTable();
        ala = new ArgumentListArray();
        intermediateFile = new ArrayList<>();
    }

    // Method to process the source code for Pass-I
    public void process(String sourceCode) {
        String[] lines = sourceCode.split("\n");
        boolean insideMacroDefinition = false;
        String currentMacroName = null;
        
        for (String line : lines) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            if (!tokenizer.hasMoreTokens()) continue;
            
            String firstToken = tokenizer.nextToken();
            
            if (firstToken.equalsIgnoreCase("MACRO")) {
                insideMacroDefinition = true;
                continue;
            }

            if (insideMacroDefinition) {
                if (firstToken.equalsIgnoreCase("MEND")) {
                    insideMacroDefinition = false;
                    macroTable.endMacroDefinition();
                    continue;
                } else {
                    if (currentMacroName == null) {
                        // This is the macro name
                        currentMacroName = firstToken;
                        List<String> parameters = new ArrayList<>();
                        while (tokenizer.hasMoreTokens()) {
                            parameters.add(tokenizer.nextToken());
                        }
                        macroTable.startMacroDefinition(currentMacroName, parameters);
                    } else {
                        // Macro body lines
                        macroTable.addMacroBodyLine(line);
                    }
                }
            } else {
                if (macroTable.isMacro(firstToken)) {
                    // It's a macro invocation, not a definition
                    String macroName = firstToken;
                    List<String> arguments = new ArrayList<>();
                    while (tokenizer.hasMoreTokens()) {
                        arguments.add(tokenizer.nextToken());
                    }
                    ala.processArguments(arguments);
                    macroTable.addMacroInvocation(macroName, ala);
                } else {
                    // Normal line, add to intermediate file
                    intermediateFile.add(line);
                }
            }
        }

        // Display results
        macroTable.displayMNT();
        macroTable.displayMDT();
        displayIntermediateFile();
    }

    // Method to display the intermediate file (output of Pass-I)
    private void displayIntermediateFile() {
        System.out.println("\nIntermediate File:");
        for (String line : intermediateFile) {
            System.out.println(line);
        }
    }
}

// Class for managing Macro Definition Table (MDT) and Macro Name Table (MNT)
class MacroTable {
    private List<String> mdt;  // Macro Definition Table
    private Map<String, Integer> mnt;  // Macro Name Table (Macro name -> MDT index)
    private Map<String, List<String>> formalParameters;  // Macro -> Formal parameters
    private int mdtIndex;

    public MacroTable() {
        mdt = new ArrayList<>();
        mnt = new HashMap<>();
        formalParameters = new HashMap<>();
        mdtIndex = 0;
    }

    // Start defining a new macro
    public void startMacroDefinition(String macroName, List<String> parameters) {
        mnt.put(macroName, mdtIndex);  // Record macro name and its starting index in MDT
        formalParameters.put(macroName, parameters);  // Store formal parameters
    }

    // Add a line to the Macro Definition Table (MDT)
    public void addMacroBodyLine(String line) {
        mdt.add(line);
        mdtIndex++;
    }

    // End the current macro definition
    public void endMacroDefinition() {
        mdt.add("MEND");
        mdtIndex++;
    }

    // Check if a token is a macro name
    public boolean isMacro(String token) {
        return mnt.containsKey(token);
    }

    // Handle macro invocation by expanding it using the arguments and macro definition
    public void addMacroInvocation(String macroName, ArgumentListArray ala) {
        int startIndex = mnt.get(macroName);
        List<String> parameters = formalParameters.get(macroName);

        for (int i = startIndex; i < mdt.size(); i++) {
            String macroLine = mdt.get(i);
            if (macroLine.equals("MEND")) break;

            // Replace formal parameters with actual arguments from ALA
            for (int j = 0; j < parameters.size(); j++) {
                macroLine = macroLine.replace(parameters.get(j), ala.getArgument(j));
            }
            System.out.println(macroLine);  // This would go into the intermediate file in Pass-II
        }
    }

    // Display the Macro Name Table (MNT)
    public void displayMNT() {
        System.out.println("\nMacro Name Table (MNT):");
        for (Map.Entry<String, Integer> entry : mnt.entrySet()) {
            System.out.println(entry.getKey() + " -> MDT Index: " + entry.getValue());
        }
    }

    // Display the Macro Definition Table (MDT)
    public void displayMDT() {
        System.out.println("\nMacro Definition Table (MDT):");
        for (int i = 0; i < mdt.size(); i++) {
            System.out.println(i + ": " + mdt.get(i));
        }
    }
}

// Class for Argument List Array (ALA) used to handle macro arguments
class ArgumentListArray {
    private List<String> ala;  // Actual arguments passed during macro invocation

    public ArgumentListArray() {
        ala = new ArrayList<>();
    }

    // Process and store actual arguments passed in the macro invocation
    public void processArguments(List<String> arguments) {
        ala.clear();
        ala.addAll(arguments);
    }

    // Get the argument by index
    public String getArgument(int index) {
        return ala.get(index);
    }
}
