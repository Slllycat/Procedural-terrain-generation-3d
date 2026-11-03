import java.util.ArrayList;

public class Inputs {
    ArrayList<String> buttons;
    ArrayList<String> keys;
    public Inputs(){
        this.buttons=new ArrayList<>();
        this.keys=new ArrayList<>();
    }
    public void addkey(String key){
        this.keys.add(key);
    }
    public void addbuttom(String button){
        this.buttons.add(button);
    }
    public void removebuttom(String button){
        this.buttons.remove(this.buttons.indexOf(button));
    }
    public void removekey(String key){
        this.keys.remove(this.keys.indexOf(key));
    }
    public Boolean iskeypressed(String key){
        if (this.keys.indexOf(key)>=0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean isbuttonpressed(String button){
        if (this.buttons.indexOf(button)>=0){
            return true;
        }else{
            return false;
        }
    }
    public String outbuttons(){
        return String.join(", ",this.buttons); 
    }
    public String outkeys(){
        return String.join(", ",this.keys); 
    }
}