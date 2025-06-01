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

    private boolean isUpperDownCaseInputRightFormat(String data){
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
}
