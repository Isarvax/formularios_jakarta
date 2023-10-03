package co.edu.cue.jakartaee.utilities;

import java.util.HashMap;

public class GeneratorAlerts {
    public static HashMap<String,String> generateMessageStudent(String name, String career) {
        HashMap<String,String> errores=new HashMap<>();
        if(name==""||name.isBlank()){
            errores.put("name","Name required");
        }
        if (career==""||career.isBlank()){
            errores.put("career","Career required");
        }
        return errores;
    }


    public static HashMap<String,String> generateMessageGrade(String grade) {
        HashMap<String,String> errores=new HashMap<>();
        try {
            Integer intGrade=Integer.parseInt(grade);
            if (intGrade<=12){
                errores.put("grade","Grade without existence");
                return errores;
            }
        } catch (NumberFormatException e) {
            errores.put("grade","Invalid grade");
            return errores;
        }
        return errores;
    }

    public static HashMap<String,String> generateMessageSubject(String subject) {
        HashMap<String,String> errores=new HashMap<>();
        if(subject==""||subject.isBlank()){
            errores.put("subject","Subject required");
        }
        return errores;
    }

    public static HashMap<String,String> generateMessageTeacher(String name, String email) {
        HashMap<String,String> errores=new HashMap<>();
        if(name==""||name.isBlank()){
            errores.put("name","Name required");
        }
        if (email==""||email.isBlank()){
            errores.put("email","Email required");
        }
        return errores;
    }

}
