package GestionBudget.budget.execption;

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
