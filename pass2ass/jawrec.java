import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Main Assembler Class
class Assembler {
    public static void main(String[] args) {
        // Example assembly code as a string
        String assemblyCode = "start 100\n" +
                "movr ax 05\n" +
                "mover bx 10\n" +
                "up: add ax bx\n" +
                "movem a ='5'\n" +
                "origin up\n" +
                "ltorg\n" +
                "movem b ='7'\n" +
                "ds a 02\n" +
                "dc b 10\n" +
                "end";

        // Pass-I: Symbol and Literal Table generation, Intermediate Code generation
        PassOne passOne = new PassOne();
        passOne.assembleFromString(assemblyCode);

        // Pass-II: Final machine code generation
        PassTwo passTwo = new PassTwo(passOne.getSymbolTable(), passOne.getLiteralTable(), passOne.getIntermediateRepresentation());
        passTwo.generateMachineCode();
    }
}

// Class representing the Pass-One phase of the assembler
class PassOne {
    private SymbolTable symbolTable;
    private LiteralTable literalTable;
    private IntermediateRepresentation intermediateRepresentation;
    private int locationCounter;

    public PassOne() {
        symbolTable = new SymbolTable();
        literalTable = new LiteralTable();
        intermediateRepresentation = new IntermediateRepresentation();
        locationCounter = 0;
    }

    // Method to accept assembly code as a string
    public void assembleFromString(String assemblyCode) {
        try (BufferedReader reader = new BufferedReader(new StringReader(assemblyCode))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display the symbol and literal tables
        symbolTable.display();
        literalTable.display();

        // Display intermediate code
        intermediateRepresentation.display();
    }

    private void processLine(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        String label = null;
        String opcode;
        String operand = null;

        if (tokenizer.hasMoreTokens()) {
            String firstToken = tokenizer.nextToken();

            // Check if first token is a label
            if (firstToken.endsWith(":")) {
                label = firstToken.substring(0, firstToken.length() - 1);
                if (!symbolTable.contains(label)) {
                    symbolTable.addSymbol(label, locationCounter);
                }
                opcode = tokenizer.nextToken();
            } else {
                opcode = firstToken;
            }

            if (tokenizer.hasMoreTokens()) {
                operand = tokenizer.nextToken();
            }

            Instruction instruction = new Instruction(label, opcode, operand);
            generateIntermediateCode(instruction);

            updateLocationCounter(opcode, operand);
        }
    }

    private void generateIntermediateCode(Instruction instruction) {
        intermediateRepresentation.addLine(instruction.toString());
    }

    private void updateLocationCounter(String opcode, String operand) {
        // Simple logic to update location counter based on the type of instruction
        if (opcode.equalsIgnoreCase("START")) {
            locationCounter = Integer.parseInt(operand);
        } else if (opcode.equalsIgnoreCase("END")) {
            // Handle end
        } else {
            // For simplicity, assume each instruction takes one memory word
            locationCounter++;
        }
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public LiteralTable getLiteralTable() {
        return literalTable;
    }

    public IntermediateRepresentation getIntermediateRepresentation() {
        return intermediateRepresentation;
    }
}

// Class representing the Pass-Two phase of the assembler
class PassTwo {
    private SymbolTable symbolTable;
    private LiteralTable literalTable;
    private IntermediateRepresentation intermediateRepresentation;

    public PassTwo(SymbolTable symbolTable, LiteralTable literalTable, IntermediateRepresentation intermediateRepresentation) {
        this.symbolTable = symbolTable;
        this.literalTable = literalTable;
        this.intermediateRepresentation = intermediateRepresentation;
    }

    // Method to generate final machine code
    public void generateMachineCode() {
        ArrayList<String> intermediateCode = intermediateRepresentation.getIntermediateCode();
        System.out.println("\nFinal Machine Code:");
        for (String line : intermediateCode) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            String label = null;
            String opcode;
            String operand = null;

            if (tokenizer.hasMoreTokens()) {
                opcode = tokenizer.nextToken();

                if (tokenizer.hasMoreTokens()) {
                    operand = tokenizer.nextToken();
                }

                // Resolve the operand (either symbol or literal)
                String resolvedOperand = resolveOperand(operand);
                System.out.println(opcode + " " + resolvedOperand);
            }
        }
    }

    private String resolveOperand(String operand) {
        if (operand == null) return "";

        // Check if operand is a symbol (in the symbol table)
        if (symbolTable.contains(operand)) {
            return String.valueOf(symbolTable.getAddress(operand));
        }

        // Check if operand is a literal (in the literal table)
        if (operand.startsWith("'") && operand.endsWith("'")) {
            return String.valueOf(literalTable.getLiteralAddress(operand));
        }

        // If operand is a number or a register (like ax, bx), return as is
        return operand;
    }
}

// Class representing the Symbol Table
class SymbolTable {
    private HashMap<String, Integer> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<>();
    }

    public void addSymbol(String label, int address) {
        symbolTable.put(label, address);
    }

    public Integer getAddress(String label) {
        return symbolTable.get(label);
    }

    public boolean contains(String label) {
        return symbolTable.containsKey(label);
    }

    public void display() {
        System.out.println("Symbol Table:");
        symbolTable.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}

// Class representing the Literal Table
class LiteralTable {
    private ArrayList<String> literals;

    public LiteralTable() {
        literals = new ArrayList<>();
    }

    public void addLiteral(String literal) {
        if (!literals.contains(literal)) {
            literals.add(literal);
        }
    }

    public int getLiteralAddress(String literal) {
        return literals.indexOf(literal);
    }

    public void display() {
        System.out.println("Literal Table:");
        for (int i = 0; i < literals.size(); i++) {
            System.out.println(literals.get(i) + " at address " + i);
        }
    }
}

// Class to handle Intermediate Code Representation
class IntermediateRepresentation {
    private ArrayList<String> intermediateCode;

    public IntermediateRepresentation() {
        intermediateCode = new ArrayList<>();
    }

    public void addLine(String line) {
        intermediateCode.add(line);
    }

    public void display() {
        System.out.println("Intermediate Code:");
        intermediateCode.forEach(System.out::println);
    }

    public ArrayList<String> getIntermediateCode() {
        return intermediateCode;
    }
}

// Class representing an Instruction (with label, opcode, operand)
class Instruction {
    private String label;
    private String opcode;
    private String operand;

    public Instruction(String label, String opcode, String operand) {
        this.label = label;
        this.opcode = opcode;
        this.operand = operand;
    }

    public String getLabel() {
        return label;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getOperand() {
        return operand;
    }

    @Override
    public String toString() {
        return (label != null ? label + ": " : "") + opcode + " " + operand;
    }
}
