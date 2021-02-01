import java.util.ArrayList;
import java.util.List;

public class Records {

	private static List<ArrayList<String>> records;
	private static boolean initialized=false;
	
	public static boolean isInitialized() {
		return initialized;
	}
	
	public static void initialize() {
		records=new ArrayList<ArrayList<String>>();
		initialized=true;
	}
	
	/**
	 * Adds one element to the conversation. If conversation doesn't exist, it creates one
	 * @param operator_01 first user
	 * @param operator_02 second user
	 * @param sender one who sends message
	 * @param message
	 */
	public synchronized static void updateSubList(String operator_01, String operator_02, 
			String sender, String message) {
		
		ArrayList<String>conversation=searchSubElement(operator_01,operator_02);
		if(conversation==null) {
			conversation=new ArrayList<String>();
			conversation.add(operator_01+","+operator_02);
			records.add(conversation);
			updateSubList(operator_01,operator_02,sender, message);
			return;
		}else {
			String fullMessage=sender+"|||||"+message;
			conversation.add(fullMessage);
		}
		
	}
	
	
	/**
	 * Search for sub list that contains both users names in the first element
	 * @param operator_01 first user
	 * @param operator_02 second user
	 * @return sub list
	 */
	public static ArrayList<String> searchSubElement(String operator_01, String operator_02){
		
		ArrayList<String> toReturn=null;
		
		loop:for(ArrayList<String>element:records)
			if(element.size()>0)
				if(element.get(0).toString().contains(operator_01) &&
						element.get(0).toString().contains(operator_02)) {
					toReturn=element;
					break loop;
				}
		
		return toReturn;
	}
	
}