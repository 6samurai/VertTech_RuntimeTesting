package aspects;


import com.liftmania.Lift;
import com.liftmania.LiftController;
import com.liftmania.gui.Shaft;
import com.liftmania.gui.LiftsVisualiser;
import java.util.ArrayList;
import java.util.List;

import larva.*;
public aspect _asp_LiftSystem1 {

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_LiftSystem1.initialize();
}
}
before ( Lift l,boolean moveLift) : (call(* Lift.setMoving(..)) && target(l) && args(moveLift) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){
Lift lift;
lift =l ;

_cls_LiftSystem1 _cls_inst = _cls_LiftSystem1._get_cls_LiftSystem1_inst( lift);
_cls_inst.l = l;
_cls_inst.moveLift = moveLift;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 30/*setMoving*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 30/*setMoving*/);
}
}
before ( Lift l) : (call(* Lift.openDoors(..)) && target(l) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){
Lift lift;
lift =l ;

_cls_LiftSystem1 _cls_inst = _cls_LiftSystem1._get_cls_LiftSystem1_inst( lift);
_cls_inst.l = l;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 34/*openDoors*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 34/*openDoors*/);
}
}
before ( Lift l) : (call(* Lift.closeDoors(..)) && target(l) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_LiftSystem0.lock){
Lift lift;
lift =l ;

_cls_LiftSystem1 _cls_inst = _cls_LiftSystem1._get_cls_LiftSystem1_inst( lift);
_cls_inst.l = l;
_cls_inst._call(thisJoinPoint.getSignature().toString(), 32/*closeDoors*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 32/*closeDoors*/);
}
}
}