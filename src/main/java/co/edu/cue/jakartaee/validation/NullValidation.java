package co.edu.cue.jakartaee.validation;

import co.edu.cue.jakartaee.exceptions.InputException;

public class NullValidation {
    public static Boolean nullCreateStudent(String name,String career){
        if(!(name.equals("") || career.equals(""))) {
            return true;
        }else {
            try {
                throw new InputException("Information is missing");
            }catch (InputException ie){
                return false;
            }
        }
    }

    public static Boolean nullCreateTeacher(String name,String email){
        if(!(name.equals("") || email.equals(""))) {
            return true;
        }else {
            try {
                throw new InputException("Information is missing");
            }catch (InputException ie){
                return false;
            }
        }
    }

    public static Boolean nullCreateGrade(String grade){
        if(!(grade==null || Integer.parseInt(grade)<=12)) {
            return true;
        }else {
            try {
                throw new InputException("Information is missing");
            }catch (InputException | NumberFormatException ie){
                return false;
            }
        }
    }

    public static Boolean nullCreateSubject(String subject) {
        if (!(subject.equals(""))) {
            return true;
        } else {
            try {
                throw new InputException("Information is missing");
            } catch (InputException ie) {
                return false;
            }
        }
    }
}
