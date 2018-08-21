/*Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
}


So Machine 1 does:

string encoded_string = encode(strs);


and Machine 2 does:

vector<string> strs2 = decode(encoded_string);


strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringEncode {

	private static final char DELIMITER = '$';

	static String encode(List<String> input) {
		if(input.size() ==0){
			return null;
		}
		StringBuilder output = new StringBuilder();
		for(String val : input){
			int length = val.length();
			output.append(String.valueOf(length));
			output.append(DELIMITER);
			output.append(val);
		}
		System.out.println("Encoded String: " + output.toString());
		return output.toString();
	}

	static void decode(String input){
		if(input == null)
			return;
		System.out.println("Encoded String: " + input );
		List<String> outputStrings = new ArrayList<>();
		int i=0;
		while(i<input.length()){
			int j=i;
			while(j<input.length() && input.charAt(j) != DELIMITER)
				j++;

			int length = Integer.parseInt(input.substring(i,j));
			i=j+1;

				outputStrings.add(input.substring(i, i+length));
				i = i+length;
		}

		for(String val : outputStrings) {
			System.out.println("Decoded String: " + val);
		}
	}

	static void callEncoder(){
		List<String> inputStrings = new ArrayList<>();
		inputStrings.add("Hi/");
		inputStrings.add("How are you");
		inputStrings.add("$Wassup$$_");
		decode(encode(inputStrings));
	}

	public static boolean isOneEditDistance(String s, String t) {
		if((s==null) || (t==null))
			return false;
		if(s.length() - t.length() > 1)
			return false;
		int count =0;
		int i=0, j=0;

		while(i<s.length() && j<t.length()) {
			if (s.charAt(i) == t.charAt(j)) {
				i++;
				j++;
			} else {
				count++;

				if (count > 1)
					return false;

				if (s.length() > t.length())
					i++;
				if (t.length() > s.length())
					j++;
			}
		}

		if(i<s.length()||j<t.length())
			count++;

		if(count==1)
			return true;
		return false;
	}

	private static void reverseString(String s) {
		if (s == null || s.isEmpty())
			return;
		String[] words = s.trim().split("\\s+");
		if (words.length == 1) {
			System.out.println("revered string: " + words[0]);
			return;
		}

		StringBuilder sb = new StringBuilder();
		int j = words.length - 1;
		while (j >= 0) {
			sb.append(words[j--]);
			sb.append(" ");
		}

		System.out.println("revered string: " + sb.toString().trim());
	}

	private static void reverseStringInPlace(String input){
		char[] s = input.toCharArray();
		int i=0, j=i;
		while(j<s.length){
			if(s[j] == ' '){
				reverseWord(s, i, j);
				i =j+1;
			}
			j++;
		}
		//Because last word won't be reversed
		reverseWord(s, i, s.length);
		reverseWord(s, 0, s.length);
		System.out.println("reveredString() " +  new String(s));
	}

	private static void reverseWord(char[] s, int startIndex, int endIndex){

		while(startIndex<endIndex){
			char temp = s[startIndex];
			s[startIndex] = s[endIndex-1];
			s[endIndex-1] = temp;
			startIndex++;
			endIndex--;
		}
		System.out.println("reveredString() " +  new String(s));
	}

	public static void main(String[] args){
//		callEncoder();
//		System.out.println(isOneEditDistance("", "a"));
//		reverseString("the sky is blue");
//		reverseStringInPlace("the sky is blue");
//		splitConcatenatedString();
//		boldTag();

//		groupStrings();
//		findContestMatches(8);
		logStorageSystem();
	}

	private static void logStorageSystem() {
		input.put("2017:01:01:23:59:59", 1);
		input.put("2017:01:01:22:59:59", 2);
		input.put("2016:01:01:00:00:00", 3);
		retriveLogs("2016:01:01:01:01:01","2017:01:01:23:00:00","Year");
	}
	private static HashMap<String, Integer> input = new HashMap();
	private final static String[] timeMap = "Year:Month:Day:Hour:Minute:Second".split(":");
	private final static int[] size = {4,7,10,13,16,19};


	private static String getGran(String timeVal, String gran){

		for(int i=0; i< timeMap.length;i++){
			if(gran.equals(timeMap[i]))
				return timeVal.substring(0,size[i]);
		}
		return timeVal;
	}
	private static void retriveLogs(String s, String e, String gran) {

		String start = getGran(s,gran);
		String end = getGran(e, gran);

		for(String timeStamp: input.keySet()){
			String timeWithGran = getGran(timeStamp,gran);
			if((timeWithGran.compareTo(start) >=0) && (end.compareTo(timeWithGran) >=0)){
				System.out.println("Keys: " + input.get(timeStamp));
			}
		}

	}

	private static void findContestMatches(int n) {
//		if(n==1){
//			System.out.println("Output matches: (" + 1 + ")");return;
//		}


		String[] res = new String[n];
		int i=1;
		while(i<=n){
			res[i-1]=""+i;
			i++;
		}
		while(n>1){
			for(i=1;i<=n/2;i++){
				res[i-1] = "("+res[i-1]+","+res[n-i]+")";
			}
			n = n/2;
		}

		System.out.println("Output matches: (" + res[0] + ")");

	}

	private static void groupStrings() {

		int ALPHA_LENGTH = 26;
		String str[] = {"acd", "dfg", "wyz", "yab", "mop",
			"bdfh", "a", "x", "moqs", "abd"
		};

		HashMap<String,ArrayList<String>> results = new HashMap();

		for(int i=0; i<str.length;i++){
			String key = new String();
			ArrayList<String> values;
			for(int j=1;j<str[i].length();j++){
				String val = str[i];
				int diff = val.charAt(j) - val.charAt(j-1);
				if(diff<0)
					diff = diff + ALPHA_LENGTH;

				key = key + diff;
			}
			//TODO: to check if key exist
			if(results.containsKey(key)){
				values = results.get(key);
			} else {
				values = new ArrayList();
			}
			values.add(str[i]);
			results.put(key,values);
		}

		//TODO: iterate over all keys
		for(String key : results.keySet()){
			System.out.println("Key: " + key + " values: " + results.get(key).toString());
		}
	}

	private static void boldTag() {

		String s = "abcxyz123";
		String[] dict = new String[]{"abc","123"};
		boolean[] bold = new boolean[s.length()];
			for(String word : dict) {
				for(int i=0;i<=(s.length()-word.length());i++){
					if(s.substring(i,i+word.length()).equals(word)){
						int j=i;
						while(j<i+word.length()){
							bold[j++] = true;
						}
					}
				}
		}

		StringBuilder res = new StringBuilder();
		for(int j=0;j<bold.length;){
			if(bold[j]){
				res.append("<br>");
				while(j<bold.length && bold[j]){
					res.append(s.charAt(j++));
				}
				res.append("</br>");
			}
			else {
				res.append(s.charAt(j++));
			}
		}
		System.out.println("boldTagString: "+ res.toString());

	}

	private static void splitConcatenatedString() {
		List<String> strArray = new ArrayList();
		strArray.add("lc");
		strArray.add("love");
		strArray.add("ydc");
		String res = "";
		for(int i=0;i<strArray.size();i++) {
			int j;
			String rev = new StringBuilder(strArray.get(i)).reverse().toString(); //generate reverse
			if(strArray.get(i).compareTo(rev) <0 ) {
				strArray.set(i, rev);
			}
		}

		for(int i=0;i<strArray.size();i++){
			int j;
			String rev = new StringBuilder(strArray.get(i)).reverse().toString(); //generate reverse
			for(String s : new String[]{strArray.get(i),rev}){ //between it n reverse
				for(int k=0;k<s.length();k++){
					StringBuilder t = new StringBuilder(); //make the cut at each point n check
					String sub = s.substring(k);
					t.append(sub);
					for(j=i+1;j<strArray.size();j++){
						t.append(strArray.get(j)); //append strings after it
					}
					for(j=0;j<i;j++){
						t.append(strArray.get(j)); //append strings before it
					}
					t.append(s.substring(0,k));
					if(t.toString().compareTo(res) >0){
						res = t.toString();

					}
				}
			}
		}
		System.out.println("SplitAndConcateString: "+ res);
	}
}
