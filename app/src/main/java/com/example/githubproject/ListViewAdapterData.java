package com.example.githubproject;

public class ListViewAdapterData {
    private String workout_name;
    private String workout_type;
    private int num;
    private int tts;

    public ListViewAdapterData(int num, String workout_type, String workout_name, int tts) {
        this.num = num;
        this.workout_type = workout_type;
        this.workout_name = workout_name;
        this.tts = tts;
    }

    public String getWorkout_name() {return this.workout_name;}
    public String getWorkout_type() {return this.workout_type;}
    public int getNum() {return this.num;}
    public int getTts() {return this.tts;}

    public void setWorkout_name(String workout_name) {this.workout_name = workout_name;}
    public void setWorkout_type(String workout_type) {this.workout_type = workout_type;}
    public void setNum(int num) {this.num = num;}
    public void setTts(int tts) {this.tts = tts;}
}
