package educationpracticumAdditionally;

/**
 *
 * @author nazarov
 */
public class BackgroundThread extends Thread {
    private Thread t;
    private final String threadName;
    DoIt di;
    boolean disableThread = true;
    
    // отключение потока
    public void disable(){
        disableThread = false;
    }
    
    public BackgroundThread( String name, DoIt di) {
        threadName = name;
        this.di = di;
        //System.out.println("Creating " +  threadName );
   }

 
    @Override
   public void run() {
       while(disableThread){
        di.doIt(); 
        System.out.println("Runned process");
       }
       System.out.println("End Process");
   
   }
   
    @Override
   public void start () {
      //System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
