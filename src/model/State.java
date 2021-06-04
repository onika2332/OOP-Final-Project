package model;

public enum State {
	Hidden,   // Hidden state is for point which can't seen by camera 
	InsideCamera, // Seen by camera
	InsideObject, // Inside object, camera can't see
	Available   // Can be seen by camera
}
