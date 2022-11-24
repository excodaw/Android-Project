package com.example.githubproject;

public class ListViewAdapterData {
    private int id;
    private String workout_type;
    private String workout_name;
    private int tts;

    public int get_id() {return this.id;}
    public String getworkout_type() {return this.workout_type;}
    public String getWorkout_name() {return this.workout_name;}
    public int getTts() {return this.tts;}

    public void set_id(int id) {this.id = id;}
    public void setWorkout_type(String workout_type) {this.workout_type = workout_type;}
    public void setWorkout_name(String workout_name) {this.workout_name = workout_name;}
    public void setTts(int tts) {this.tts = tts;}
}
