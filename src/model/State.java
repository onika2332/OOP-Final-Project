package model;

public enum State {
	Hidden,   // Hidden state is for point which can't see because it's inside object
	Available,   // Can be seen by camera
	OnSide,     // point have this type is on the side of object
	Light        // this point is highlight by camera
}
