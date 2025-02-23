package zad2;

public class StringTask implements Runnable{

    private String napis;
    private int n;
    private volatile String result = "";
    private volatile TaskState state = TaskState.CREATED;
    private Thread thread;

    public enum TaskState {
        CREATED, RUNNING, ABORTED, READY ;
    }

    public StringTask(String napis, int n){
        this.napis = napis;
        this.n = n;
    }


    @Override
    public void run() {

        this.state = TaskState.RUNNING;

        try{

            for(int i = 0; i < n; i++){

                if(Thread.currentThread().isInterrupted()){
                    this.state = TaskState.ABORTED;
                    return;
                }

                result += napis;


            }

            this.state = TaskState.READY;

        } catch(Exception e){
            e.printStackTrace();
        }

    }


    public void start(){

        if (this.state == TaskState.CREATED) {

            this.thread = new Thread(this);
            thread.start();

        }

    }


    public void abort(){
        if (state == TaskState.RUNNING && thread != null)
            thread.interrupt();
    }


    public String getResult(){
        return this.result;
    }

    public TaskState getState(){
        return this.state;
    }

    public boolean isDone(){
        return state == TaskState.READY || state == TaskState.ABORTED;
    }

}
