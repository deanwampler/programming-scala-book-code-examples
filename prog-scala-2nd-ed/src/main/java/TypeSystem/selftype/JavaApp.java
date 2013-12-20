// code-examples/TypeSystem/selftype/JavaApp.java

package selftype;

public abstract class JavaApp {
  public interface Persistence {
    public void startPersistence();
  }

  public interface Midtier {
    public void startMidtier();
  }

  public interface UI {
    public void startUI();
  }

  private Persistence persistence;
  private Midtier midtier;
  private UI ui;

  public JavaApp(Persistence persistence, Midtier midtier, UI ui) {
    this.persistence = persistence;
    this.midtier = midtier;
    this.ui = ui;
  }
  
  public void run() {
    persistence.startPersistence();
    midtier.startMidtier();
    ui.startUI();
  }
}
