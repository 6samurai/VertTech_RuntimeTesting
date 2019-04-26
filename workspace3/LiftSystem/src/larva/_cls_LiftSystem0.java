package larva;



import com.liftmania.Lift;
import com.liftmania.LiftController;
import com.liftmania.gui.Shaft;
import com.liftmania.gui.LiftsVisualiser;
import java.util.ArrayList;
import java.util.List;

import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_LiftSystem0 implements _callable{

public static PrintWriter pw; 
public static _cls_LiftSystem0 root;

public static LinkedHashMap<_cls_LiftSystem0,_cls_LiftSystem0> _cls_LiftSystem0_instances = new LinkedHashMap<_cls_LiftSystem0,_cls_LiftSystem0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\Owner\\Desktop\\GitHub Projects\\Ver_Tech\\RuntimeVer_Testing\\VertTech_RuntimeTesting\\workspace3\\LiftSystem/src/output_LiftSystem.txt");

root = new _cls_LiftSystem0();
_cls_LiftSystem0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_LiftSystem0 parent; //to remain null - this class does not have a parent!
int no_automata = 0;
 public List <Integer >serviceList =new ArrayList <Integer >();
 public List <Integer >openDoorCount =new ArrayList <Integer >();
 public List <Integer >closeDoorCount =new ArrayList <Integer >();
 public int tempVal =0 ;
 public boolean service =false ;
 public boolean missedOpenUp =false ;
 public boolean firstIter =true ;
 public boolean missedOpenDown =false ;
 public boolean missedOpen =false ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_LiftSystem0() {
}

public void initialisation() {
}

public static _cls_LiftSystem0 _get_cls_LiftSystem0_inst() { synchronized(_cls_LiftSystem0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_LiftSystem0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_LiftSystem0_instances){
}
}

public void _call_all_filtered(String _info, int... _event){

_cls_LiftSystem1[] a1 = new _cls_LiftSystem1[1];
synchronized(_cls_LiftSystem1._cls_LiftSystem1_instances){
a1 = _cls_LiftSystem1._cls_LiftSystem1_instances.keySet().toArray(a1);}
for (_cls_LiftSystem1 _inst : a1)
if (_inst != null){
_inst._call(_info, _event); 
_inst._call_all_filtered(_info, _event);
}
}

public static void _call_all(String _info, int... _event){

_cls_LiftSystem0[] a = new _cls_LiftSystem0[1];
synchronized(_cls_LiftSystem0_instances){
a = _cls_LiftSystem0_instances.keySet().toArray(a);}
for (_cls_LiftSystem0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_LiftSystem0_instances){
_cls_LiftSystem0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}


public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}