package com.epam.mjc;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] splitString = signatureString.split("\\(");
        String sigString = splitString[0];
        String argString = splitString[1];
        Map<String, String> signatureMap = parseSignature(sigString);
        List<MethodSignature.Argument> arguments = parseArguments(argString.substring(0, argString.length() - 1));
        MethodSignature methodSignature = new MethodSignature(signatureMap.get("methodName"), arguments);
        methodSignature.setAccessModifier(signatureMap.get("accessModifier"));
        methodSignature.setReturnType(signatureMap.get("returnType"));
        return methodSignature;
    }

    private Map<String, String> parseSignature(String signature) {
        Map<String, String> signatureMap = new HashMap<>();
        String[] splitString = signature.split(" ");
        int pointer = 0;
        if (splitString.length == 3) {
            signatureMap.put("accessModifier", splitString[pointer++]);
        }
        signatureMap.put("returnType", splitString[pointer++]);
        signatureMap.put("methodName", splitString[pointer]);
        return signatureMap;
    }

    private List<MethodSignature.Argument> parseArguments(String argumentsString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        if (!argumentsString.isEmpty()) {
            String[] splitString = argumentsString.split(", ");
            for (String s : splitString) {
                String[] arg = s.split(" ");
                arguments.add(new MethodSignature.Argument(arg[0], arg[1]));
            }
        }
        return arguments;
    }

}



