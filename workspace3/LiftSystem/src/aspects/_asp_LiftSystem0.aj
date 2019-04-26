package aspects;


import com.liftmania.Lift;
import com.liftmania.LiftController;
import com.liftmania.gui.Shaft;
import com.liftmania.gui.LiftsVisualiser;
import java.util.ArrayList;
import java.util.List;

import larva.*;
public aspect _asp_LiftSystem0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_LiftSystem0.initialize();
}
}
}