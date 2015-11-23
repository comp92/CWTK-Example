import javax.swing.JFrame;


public class Test {
	public static void main(String... args) {
		JFrame frame = new JFrame("Test");
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Example());
		frame.setVisible(true);
	}
}