package larva;



import com.liftmania.Lift;
import com.liftmania.LiftController;
import com.liftmania.gui.Shaft;
import com.liftmania.gui.LiftsVisualiser;
import java.util.ArrayList;
import java.util.List;

import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_LiftSystem1 implements _callable{

public static LinkedHashMap<_cls_LiftSystem1,_cls_LiftSystem1> _cls_LiftSystem1_instances = new LinkedHashMap<_cls_LiftSystem1,_cls_LiftSystem1>();

_cls_LiftSystem0 parent;
public static Lift l;
public static boolean moveLift;
public Lift lift;
int no_automata = 1;
 public int id ;
 public int floor ;
 public boolean liftIsMoving =false ;
 public boolean doorIsOpen =false ;
 public int openLiftCount =0 ;
public Clock closeDoorClock = new Clock(this,"closeDoorClock");
public Clock moveLiftClock = new Clock(this,"moveLiftClock");

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_LiftSystem1( Lift lift) {
parent = _cls_LiftSystem0._get_cls_LiftSystem0_inst();
this.lift = lift;
}

public void initialisation() {

id =lift .getId ();
parent.floor =lift .getFloor ();
_cls_LiftSystem0.pw .println ("floor: "+parent.floor +" id:"+id +"liftIsMoving "+liftIsMoving +" doorIsOpen"+doorIsOpen );

   closeDoorClock.reset();
   moveLiftClock.reset();
}

public static _cls_LiftSystem1 _get_cls_LiftSystem1_inst( Lift lift) { synchronized(_cls_LiftSystem1_instances){
_cls_LiftSystem1 _inst = new _cls_LiftSystem1( lift);
if (_cls_LiftSystem1_instances.containsKey(_inst))
{
_cls_LiftSystem1 tmp = _cls_LiftSystem1_instances.get(_inst);
 return _cls_LiftSystem1_instances.get(_inst);
}
else
{
 _inst.initialisation();
 _cls_LiftSystem1_instances.put(_inst,_inst);
 return _inst;
}
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_LiftSystem1)
 && (lift == null || lift.equals(((_cls_LiftSystem1)o).lift))
 && (parent == null || parent.equals(((_cls_LiftSystem1)o).parent)))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_LiftSystem1_instances){
_performLogic_LiftProperties(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_LiftSystem1[] a = new _cls_LiftSystem1[1];
synchronized(_cls_LiftSystem1_instances){
a = _cls_LiftSystem1_instances.keySet().toArray(a);}
for (_cls_LiftSystem1 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_LiftSystem1_instances){
_cls_LiftSystem1_instances.remove(this);}
synchronized(closeDoorClock){
closeDoorClock.off();
closeDoorClock._inst = null;
closeDoorClock = null;}
synchronized(moveLiftClock){
moveLiftClock.off();
moveLiftClock._inst = null;
moveLiftClock = null;}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_LiftProperties = 15;

public void _performLogic_LiftProperties(String _info, int... _event) {

_cls_LiftSystem0.pw.println("[LiftProperties]AUTOMATON::> LiftProperties("+lift + " " + ") STATE::>"+ _string_LiftProperties(_state_id_LiftProperties, 0));
_cls_LiftSystem0.pw.flush();

if (0==1){}
else if (_state_id_LiftProperties==15){
		if (1==0){}
		else if ((_occurredEvent(_event,30/*setMoving*/)) && (!doorIsOpen &&!liftIsMoving )){
		liftIsMoving =true ;
_cls_LiftSystem0.pw .println ("Lift is moving");

		_state_id_LiftProperties = 13;//moving to state moveLift
parent.floor =lift .getFloor ();
_cls_LiftSystem0.pw .println ("move floor: "+parent.floor );

		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,34/*openDoors*/)) && (!doorIsOpen &&!liftIsMoving &&openLiftCount %2 ==0 )){
		openLiftCount ++;
doorIsOpen =true ;
closeDoorClock .reset ();
_cls_LiftSystem0.pw .println ("Door is open");

		_state_id_LiftProperties = 12;//moving to state doorOpen
parent.floor =lift .getFloor ();
_cls_LiftSystem0.pw .println ("open door floor: "+parent.floor );

		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,30/*setMoving*/))){
		_cls_LiftSystem0.pw .println ("Door is closed and idle");

		_state_id_LiftProperties = 15;//moving to state doorClosed
id =lift .getId ();
parent.floor =lift .getFloor ();
_cls_LiftSystem0.pw .println ("floor: "+parent.floor +" id:"+id +"liftIsMoving "+liftIsMoving +" doorIsOpen"+doorIsOpen );

		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,32/*closeDoors*/)) && (moveLiftClock .compareTo (3 )>0 )){
		_cls_LiftSystem0.pw .println ("Lift moved after more than 3 seconds error");

		_state_id_LiftProperties = 11;//moving to state errorState
		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,32/*closeDoors*/)) && (openLiftCount %2 ==1 )){
		_cls_LiftSystem0.pw .println ("open and close door not alternating - close");

		_state_id_LiftProperties = 11;//moving to state errorState
		_goto_LiftProperties(_info);
		}
}
else if (_state_id_LiftProperties==12){
		if (1==0){}
		else if ((_occurredEvent(_event,32/*closeDoors*/)) && (doorIsOpen &&!liftIsMoving &&closeDoorClock .compareTo (3 )>=0 &&openLiftCount %2 ==1 )){
		openLiftCount --;
moveLiftClock .reset ();
doorIsOpen =false ;
_cls_LiftSystem0.pw .println ("Door is closed due to timer");

		_state_id_LiftProperties = 15;//moving to state doorClosed
id =lift .getId ();
parent.floor =lift .getFloor ();
_cls_LiftSystem0.pw .println ("floor: "+parent.floor +" id:"+id +"liftIsMoving "+liftIsMoving +" doorIsOpen"+doorIsOpen );

		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,30/*setMoving*/)) && (liftIsMoving )){
		_cls_LiftSystem0.pw .println ("Move call received when door is open 2"+liftIsMoving );

		_state_id_LiftProperties = 11;//moving to state errorState
		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,34/*openDoors*/)) && (parent.floor <parent.minFloor ||parent.floor >parent.maxFloor )){
		_cls_LiftSystem0.pw .println ("Lift moved beyond specified limit");

		_state_id_LiftProperties = 11;//moving to state errorState
		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,34/*openDoors*/)) && (openLiftCount %2 ==0 )){
		_cls_LiftSystem0.pw .println ("open and close door not alternating - open");

		_state_id_LiftProperties = 11;//moving to state errorState
		_goto_LiftProperties(_info);
		}
}
else if (_state_id_LiftProperties==13){
		if (1==0){}
		else if ((_occurredEvent(_event,34/*openDoors*/)) && (!doorIsOpen &&liftIsMoving &&openLiftCount %2 ==0 )){
		openLiftCount ++;
doorIsOpen =true ;
liftIsMoving =false ;
closeDoorClock .reset ();
_cls_LiftSystem0.pw .println ("Destination reached from moving +"+parent.floor );
if (!parent.serviceList .isEmpty ())parent.serviceList .remove (Integer .valueOf (parent.floor ));
else if (!parent.firstIter )parent.missedOpen =true ;

		_state_id_LiftProperties = 12;//moving to state doorOpen
parent.floor =lift .getFloor ();
_cls_LiftSystem0.pw .println ("open door floor: "+parent.floor );

		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,30/*setMoving*/)) && (!doorIsOpen &&liftIsMoving )){
		_cls_LiftSystem0.pw .println ("Lift moving to next floor : "+liftIsMoving );

		_state_id_LiftProperties = 13;//moving to state moveLift
parent.floor =lift .getFloor ();
_cls_LiftSystem0.pw .println ("move floor: "+parent.floor );

		_goto_LiftProperties(_info);
		}
		else if ((_occurredEvent(_event,30/*setMoving*/)) && (parent.serviceList .isEmpty ())){
		_cls_LiftSystem0.pw .println ("Lift is moving when no service is present");

		_state_id_LiftProperties = 11;//moving to state errorState
		_goto_LiftProperties(_info);
		}
}
}

public void _goto_LiftProperties(String _info){
_cls_LiftSystem0.pw.println("[LiftProperties]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_LiftProperties(_state_id_LiftProperties, 1));
_cls_LiftSystem0.pw.flush();
}

public String _string_LiftProperties(int _state_id, int _mode){
switch(_state_id){
case 11: if (_mode == 0) return "errorState"; else return "!!!SYSTEM REACHED BAD STATE!!! errorState "+new _BadStateExceptionLiftSystem().toString()+" ";
case 15: if (_mode == 0) return "doorClosed"; else return "doorClosed";
case 12: if (_mode == 0) return "doorOpen"; else return "doorOpen";
case 13: if (_mode == 0) return "moveLift"; else return "moveLift";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}