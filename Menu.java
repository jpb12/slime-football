import javax.swing.JApplet;

public class Menu extends JApplet{
	
	private static final long serialVersionUID = -8075058726269617770L;

	@Override
	public void init(){
		this.setSize(725, 365);
		Match match = new Match();
		this.add(match.getPitch());
	}
}
