
public enum Task {

    Write(1),
    Read(0);

    private final int taskCode;  

    private Task(int code){
        taskCode = code;
    }

    public int getTaskCode(){
        return taskCode;
    }




}