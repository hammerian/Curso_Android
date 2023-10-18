package com.example.taskeando;

import android.net.Uri;

public class Taskita {

    private String taskName;

    private String taskDescription;

    private String taskType;

    private boolean taskEnd;

    private Uri taskImg;

    public Taskita() {
    }

    public Taskita(String taskName, String taskDescription, String taskType, boolean taskEnd, Uri taskImg) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskType = taskType;
        this.taskEnd = taskEnd;
        this.taskImg = taskImg;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public boolean isTaskEnd() {
        return taskEnd;
    }

    public void setTaskEnd(boolean taskEnd) {
        this.taskEnd = taskEnd;
    }

    public Uri getTaskImg() {
        return taskImg;
    }

    public void setTaskImg(Uri taskImg) {
        this.taskImg = taskImg;
    }
}
