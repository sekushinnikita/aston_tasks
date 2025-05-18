package ru.astondevs.week3;

public class MyIOValidation{
    private boolean isEndOfInputRightFormat(String data){
        String[] string = data.split("\n");
        for (String s : string) {
            if (!s.endsWith("."))
                return false;
        }
        return true;
    }

    private boolean isUpperLowerCaseInputRightFormat(String data){
        String[] string = data.split("\n");
        for (String s : string) {
            if (!Character.isUpperCase(s.charAt(0)))
                return false;
            for (int i = 1; i < s.length() ; i++){
                if (!Character.isLowerCase(s.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    private boolean isNullOrDoesntExist(String data){
        String[] string = data.split("\n");
        for (String s : string) {
            if (!(s == null || s.isEmpty())) {
                return false;
            }
        }
        return true;
    }

    private String formatEndOfInputRightFormat(String data) {
        if (!data.endsWith(".")) {
            data += ".";
        }
        return data;
    }

    private String formatUpperLowerCaseInputRightFormat(String data) {
        String[] lines = data.split("\n");
        StringBuilder formattedData = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String s = lines[i].trim();
            if (s.isEmpty()) continue;
            s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            formattedData.append(s);
            if (i < lines.length - 1) {
                formattedData.append("\n");
            }
        }
        return formattedData.toString();
    }

    private String formatNullOrDoesntExist(String data) {
        String[] lines = data.split("\n");
        StringBuilder formattedData = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String s = lines[i].trim();
            if (s == null || s.isEmpty()) {
                s = "There's no data.";
            }
            formattedData.append(s);
            if (i < lines.length - 1) {
                formattedData.append("\n");
            }
        }
        return formattedData.toString();
    }

    public String inputValidChanging(String data) {
        data = formatEndOfInputRightFormat(data);
        data = formatUpperLowerCaseInputRightFormat(data);
        data = formatNullOrDoesntExist(data);
        return data;
    }

    public boolean isInputTextValid(String data){
        boolean isValid = true;
        isValid = isEndOfInputRightFormat(data);
        isValid = isUpperLowerCaseInputRightFormat(data);
        isValid = isNullOrDoesntExist(data);
        return isValid;
    }
}
