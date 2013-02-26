/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package breed;

/**
 *
 * @author maxsmiley
 */
public class Tasks {
    int tasksSize = 50;
    Task tasks[] = new Task[tasksSize];

    Task completed[] = new Task[tasksSize];
    Task inProgress[] = new Task[tasksSize];
    Organism organism;
    int taskCount = 0;

    public Tasks(){

    }

    public Tasks(Organism o){
        setOrganism(o);
    }

    public Tasks(Organism o, int size){

    }

    public void addTask(Task t){
        if(containsTask(t.getName())){
            if(getTask(t.getName()).getState()==Task.STATIC){
//            if(getTask(t.getName()).isComplete()) //If previouis task is already finished
            getTask(t.getName()).start(); //then start a new one
            }
        }else{
            addTaskType(t);
            tasks[taskCount-1].start();
        }
        
    }

    public void runTasks(){
       for(int i= 0; i< getTaskCount(); i ++){
                tasks[i].run();
                //System.out.println(tasks[i].getName()+" "+tasks[i].getProgress());
            }
    }

    public void addTaskType(Task t){
        tasks[taskCount] = t;
        taskCount++;
    }

    public Task getTask(String name){
        for(int i = 0; i < taskCount; i ++){
            if(tasks[i].getName() == name){
                return tasks[i];
            }
        }
        return null;
    }

    public Task getTask(int i ){
        if(i <= taskCount){
                return tasks[i];
            }
        
        return null;
    }

    public boolean containsTask(String name){
        for(int i = 0; i < taskCount; i ++){
            if(tasks[i].getName() == name){
                return true;
            }
        }
        return false;
    }

    public Task[] getCompleted() {
        return completed;
    }

    public void setCompleted(Task[] completed) {
        this.completed = completed;
    }

    public Task[] getInProgress() {
        return inProgress;
    }

    public void setInProgress(Task[] inProgress) {
        this.inProgress = inProgress;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void setTasks(Task[] tasks) {
        this.tasks = tasks;
    }

    public int getTasksSize() {
        return tasksSize;
    }

    public void setTasksSize(int tasksSize) {
        this.tasksSize = tasksSize;
    }


}
