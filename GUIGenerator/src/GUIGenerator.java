import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUIGenerator implements ActionListener {
	
	static JFrame outputFrame;
	static JPanel panel1, panel2, panel3, panel4;
	static Container container;
	static JTextField textField;
	static ArrayList<JButton> button;
	static ArrayList<JLabel> label;
	static ArrayList<JRadioButton> radio;
	static InputFileAnalyzer inputAnalyzer;
	
	public static void main(String[] args) throws Exception {
		
		// instantiates an InputFileAnalyzer object
		inputAnalyzer = new InputFileAnalyzer();
		
		// instantiates ArrayList objects
		button = new ArrayList<JButton>();
		label = new ArrayList<JLabel>();		
		radio = new ArrayList<JRadioButton>();
		
		// instantiates a GUIGenerator object that invokes viewer() method
		GUIGenerator guiGenerator = new GUIGenerator();
		guiGenerator.viewer();
		
	}
	
	public void viewer() {
		
		// instantiates a JFrame object
		outputFrame = new JFrame();
		
		// sets window size
		outputFrame.setSize(this.inputAnalyzer.getWWidth(),this.inputAnalyzer.getWHeight());
		
		// sets window name
		outputFrame.setTitle(this.inputAnalyzer.getWName());
		outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		container = outputFrame.getContentPane();
		if (this.inputAnalyzer.getWLayout() == 0) { // uses flow layout
			container.setLayout(new FlowLayout());
		} else { // uses grid layout
			if (this.inputAnalyzer.getHGaps() != 0 && this.inputAnalyzer.getVGaps() != 0) {
				container.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns(),
						this.inputAnalyzer.getHGaps(),this.inputAnalyzer.getVGaps()));
			} else {
				container.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns()));
			}
		}
		
		// instantiates a JPanel object
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		if (this.inputAnalyzer.getPLayout() == 0) { // uses flow layout
			panel1.setLayout(new FlowLayout());
			panel2.setLayout(new FlowLayout());
			panel3.setLayout(new FlowLayout());
		} else { // uses grid layout
			if (this.inputAnalyzer.getHGaps() != 0 && this.inputAnalyzer.getVGaps() != 0) {
				panel1.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns(),
						this.inputAnalyzer.getHGaps(),this.inputAnalyzer.getVGaps()));
				panel2.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns(),
						this.inputAnalyzer.getHGaps(),this.inputAnalyzer.getVGaps()));
				panel3.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns(),
						this.inputAnalyzer.getHGaps(),this.inputAnalyzer.getVGaps()));
				panel4.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns(),
						this.inputAnalyzer.getHGaps(),this.inputAnalyzer.getVGaps()));
			} else {
				panel1.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns()));
				panel2.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns()));
				panel3.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns()));
				panel4.setLayout(new GridLayout
						(this.inputAnalyzer.getNumRows(),this.inputAnalyzer.getNumColumns()));
			}
		}
		
		// instantiates a JTextField object. sets text field width
		textField = new JTextField("", this.inputAnalyzer.getTextWidth());
		container.add(textField);
		panel1.add(textField);

		// creates button objects as the number of elements in buttonList
		int i = 0;
		Iterator<String> it = this.inputAnalyzer.getButtonList().iterator();
		while (it.hasNext()) {
			button.add(new JButton(it.next()));
			container.add(button.get(i));
			panel2.add(button.get(i));
			i++;
		}

		// creates radio button objects as the number of elements in radioList
		i = 0;
		it = this.inputAnalyzer.getRadioList().iterator();
		while (it.hasNext()) {
			radio.add(new JRadioButton(it.next()));
			container.add(radio.get(i));
			panel3.add(radio.get(i));
			i++;
		}
		
		// creates label objects as the number of elements in labelList
		i = 0;
		it = this.inputAnalyzer.getLabelList().iterator();
		while (it.hasNext()) {
			label.add(new JLabel(it.next()));
			container.add(label.get(i));
			panel4.add(label.get(i));
			i++;
		}
		
		outputFrame.add(panel1);
		outputFrame.add(panel2);
		outputFrame.add(panel3);
		outputFrame.setVisible(true);
	}
	// event handler for JButton, JTextField events.
	public void actionPerformed( ActionEvent event ) {}
}
