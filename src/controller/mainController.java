package controller;

import java.util.List;
import java.util.ArrayList;
import model.Camera;
import model.Object;

public class mainController {
    public static void main(String[] args) {
        // we read file and init Room, Object, Camera here    

        // After read, create list to save objects, cameras
        List<Camera> cameras = new ArrayList<Camera>();
        // Add all camera have been initialized to list
        // each camera, operate checkCameraOnFrame(Room room), if true --> addd

        
        // operate room.setStateForAllPoint()
        // we use 3 FOR loop for x, y, z and loop all point
        // each point, we operate the following method with order :
        // checkPointInLightField();
    }
}
