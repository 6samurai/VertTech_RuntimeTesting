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
before ( int DestLift) : (call(* *.openLiftDoor(..)) && args(DestLift) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){

_cls_LiftSystem0 _cls_inst = _cls_LiftSystem0._get_cls_LiftSystem0_inst();
_cls_inst.DestLift = DestLift;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 22/*openLiftDoor*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 22/*openLiftDoor*/);
}
}
before ( int currentFloorUp) : (call(* *.animateUp(..)) && args(currentFloorUp) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){

_cls_LiftSystem0 _cls_inst = _cls_LiftSystem0._get_cls_LiftSystem0_inst();
_cls_inst.currentFloorUp = currentFloorUp;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 26/*animateUp*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 26/*animateUp*/);
}
}
before ( Lift liftNumber,int floorNumber) : (call(* *.moveLift(..)) && args(liftNumber,floorNumber) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){

_cls_LiftSystem0 _cls_inst = _cls_LiftSystem0._get_cls_LiftSystem0_inst();
_cls_inst.liftNumber = liftNumber;
_cls_inst.floorNumber = floorNumber;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 20/*moveLift*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 20/*moveLift*/);
}
}
before ( int floor) : (call(* *.callLiftToFloor(..)) && args(floor) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){

_cls_LiftSystem0 _cls_inst = _cls_LiftSystem0._get_cls_LiftSystem0_inst();
_cls_inst.floor = floor;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 18/*callLiftToFloor*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 18/*callLiftToFloor*/);
}
}
before ( int currentFloorDown) : (call(* *.animateDown(..)) && args(currentFloorDown) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){

_cls_LiftSystem0 _cls_inst = _cls_LiftSystem0._get_cls_LiftSystem0_inst();
_cls_inst.currentFloorDown = currentFloorDown;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 28/*animateDown*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 28/*animateDown*/);
}
}
before ( int Lift) : (call(* *.closeLiftDoor(..)) && args(Lift) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){

_cls_LiftSystem0 _cls_inst = _cls_LiftSystem0._get_cls_LiftSystem0_inst();
_cls_inst.Lift = Lift;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 24/*closeLiftDoor*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 24/*closeLiftDoor*/);
}
}
}