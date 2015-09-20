import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputFileAnalyzer {
	
	static String token;
	static String windowName;
	static int windowLayout;
	static int panelLayout;
	static int textWidth;
	static int windowWidth, windowHeight;
	static int numRows, numColumns, hGaps, vGaps;
	static ArrayList<String> buttonList;
	static ArrayList<String> labelList;
	static ArrayList<String> radioList;
	
	InputFileAnalyzer() throws Exception {
		
		String expression = null;
		StringTokenizer tokens;
		buttonList = new ArrayList<String>();
		labelList = new ArrayList<String>();
		radioList = new ArrayList<String>();
		
		// Create a File instance
		File file = new File("input.txt");
		
		// Create a Scanner for the file
		Scanner input = new Scanner(file);
		
		// Read a data from a file
		while(input.hasNext()) {
			expression = expression + " " + input.next();
		}
		tokens = new StringTokenizer(expression);
		
		// lexical and syntax analysis by using StringTokenizer
		while (tokens.hasMoreTokens()) {  // hasn't gone thru all the "tokens"
		    token = tokens.nextToken();    // get next "token" from input string
		    if (token.equals("End.")) {
		    	break;
		    }
		    while (tokens.hasMoreTokens()) {
			    token = tokens.nextToken();
			    if (token.equals("End;")) {
			    	break;
			    } else if (token.equals("Window")) {
			    	token = tokens.nextToken();
			    	windowName = token.replaceAll("\"", "");
			    	token = tokens.nextToken();
			    	setWindowSize(token);
			    	token = tokens.nextToken();
			    	if (token.equals("Layout")) {
				    	while (!token.contains(":")) {
					    	token = tokens.nextToken();
					    	if (token.contains("Flow")) {
						    	windowLayout = 0;				    		
					    	} else if (token.equals("Grid")) {
					    		panelLayout = 1;
					    		setGridParameters(token);
					    	}	
				    	}
			    	}
			    } else if (token.equals("TextField")) {
			    	while (!token.contains(";")) {
				    	token = tokens.nextToken();
				    	textWidth = Integer.parseInt(token.replaceFirst(";",""));
			    	}
			    } else if (token.equals("Panel")) {
			    	token = tokens.nextToken();
			    	while (!token.contains(";")) {
				    	token = tokens.nextToken();
				    	if (token.contains("Flow")) {
				    		panelLayout = 0;
				    	} else if (token.contains("Grid")) {
				    		panelLayout = 1;
				    		setGridParameters(token);
				    	}
				    	
			    	}
			    } else if (token.equals("Button")) {
			    	while (!token.contains(";")) {
				    	token = tokens.nextToken();
				    	buttonList.add(token.replaceAll("\"|;",""));
			    	}
			    } else if (token.equals("Label")) {
			    	while (!token.contains(";")) {
				    	token = tokens.nextToken();
				    	labelList.add(token.replaceAll("\"|;",""));
			    	}
			    } else if (token.equals("Group")) {
			    	token = tokens.nextToken();
			    	while (!token.contains(";")) {
				    	token = tokens.nextToken();
				    	radioList.add(token.replaceAll("\"|;",""));
			    	}
			    }
		    }
		}
		// Close the file
		input.close();

	}
	
	private static void setWindowSize(String token) {
    	Matcher m = Pattern.compile("[0-9]+").matcher(token);
    	for (int i = 0; i < 2; i++) {
    		if (m.find()) {
    			if (i == 0) {
    				windowWidth = Integer.parseInt(m.group());
    			} else { 
    				windowHeight = Integer.parseInt(m.group());
    			}
    		}
    	}
	}
	
	private static void setGridParameters(String token) {
    	Matcher m = Pattern.compile("[0-9]+").matcher(token);
    	for (int i = 0; i < 4; i++) {
    		if (m.find()) {
    			if (i == 0) {
    				numRows = Integer.parseInt(m.group());
    			} else if (i == 1) {
    				numColumns = Integer.parseInt(m.group());
    			} else if (i == 2) {
    				hGaps = Integer.parseInt(m.group());
    			} else {
    				vGaps = Integer.parseInt(m.group());
    			}
    		}
    	}
	}
	
	public int getWWidth() {
		return windowWidth;
	}
	
	public int getWHeight() {
		return windowHeight;
	}
	
	public String getWName() {
		return windowName;
	}
	public int getWLayout() {
		return windowLayout;
	}
	
	public int getPLayout() {
		return panelLayout;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getHGaps() {
		return hGaps;
	}
	
	public int getVGaps() {
		return vGaps;
	}
	
	public int getTextWidth() {
		return textWidth;
	}
	
	public ArrayList<String> getButtonList() {
		return buttonList;
	}
	
	public ArrayList<String> getLabelList() {
		return labelList;
	}
	
	public ArrayList<String> getRadioList() {
		return radioList;
	}
}
