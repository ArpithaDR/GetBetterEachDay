import java.io.*;
import java.util.*;


class Solution {




  private static void  lookUpUsers(int[][] users) {
    List results = new ArrayList();
    HashMap<Integer, Integer> hotelVisitCount = new HashMap<Integer, Integer>();
    for(int i=0; i<users.length; i++){

      Set<Integer> hotelIds = new HashSet<Integer>();

      for(int j=0; j<users[i].length; j++){
        hotelIds.add(users[i][j]);
      }

      for(Integer hotelId : hotelIds) {
        if(hotelVisitCount.containsKey(hotelId)) {
          hotelVisitCount.put(hotelId, hotelVisitCount.get(hotelId) + 1);
        } else {
          hotelVisitCount.put(hotelId,1);
        }
      }
    }

    if(hotelVisitCount.containsValue(users.length)) {
      for(Integer hotelId : hotelVisitCount.keySet()) {
        if(hotelVisitCount.get(hotelId) == users.length){
          results.add(hotelId);
        }

      }
    }
    System.out.println("Results: " + results.toString());

  }

  private static String[] createDummyData(){
    String[] dummyData = {
      // First line,space-separated set of words
      "price accommodation view low best",
      // Second line, one integer which is the number of reviews
      "4",
      // From this point, we need to have as many hotel IDs and Reviews as we defined in the line above
      // Hotel ID->1
      "1",
      // Review for Hotel ID->1
      "Do not make a reservation in this hotel even if has low low low price low price",
      // Hotel ID->2
      "2",
      // Review for Hotel ID->1
      "Great view and accommodation, best option for the price price price",
      // Hotel ID->3
      "3",
      // Review for Hotel ID->3
      "Great view and accommodation, best option for the price",
      // Hotel ID->4
      "4",
      // Review for Hotel ID->4
      "Best option for the price price price price low"
    };
    return dummyData;
  }



  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // If line arguments are empty, let's create some dummy data

//    String[] input = createDummyData();
////    String[] keyWordsList =input[0].split(" ");
////    int numberOfReviews = Integer.valueOf(input[1]);
////    sortAndShowHotelList(numberOfReviews, input, keyWordsList);


//    getMaxDays();

//    DisplayWordCountWithCharProcessing();

//    System.out.println(getMinSquares(10));

    System.out.println(checkIfStringBalanced("sfdzvfd[sfvf(fv)([fv])hbnm((f)]sffdv"));
  }


  static boolean checkIfStringBalanced(String input) {

    Stack<Character> holder = new Stack<Character>();

    for (char c : input.toCharArray()) {
      if (c == '{' || c == '(' || c == '[') {
        holder.push(c);
      } else {
        if (c == '}' || c == ')' || c == ']') {
          char element = holder.pop();
          if (!((c == '}' && element == '{') ||
            (c == ')' && element == '(') ||
            (c == ']' && element == '['))) {

            return false;
          }

        }
      }
  }
      if(!holder.isEmpty())
        return false;

      return true;
    }



  private static final HashMap<String, Integer> wordCount = new HashMap();
  private static final HashMap<String, StringBuilder> buffer = new HashMap();

  static void DisplayWordCountWithCharProcessing(){

    String input = "acacabcatghhellomvnsdb";
    String[] keywords = {"aca","cat","hello","world"};


    for(String word : keywords) {
      buffer.put(word, new StringBuilder());
      wordCount.put(word, 0);
    }

    for(char c : input.toCharArray()){
      processCharacters(c);
    }

    for(String word : wordCount.keySet()) {
      System.out.println(word + " : " + wordCount.get(word));
    }
  }

  private static void processCharacters(char c) {

    for(String word : wordCount.keySet()) {
      buffer.get(word).append(c);

      if(buffer.get(word).toString().equalsIgnoreCase(word)){
        wordCount.put(word, 1+wordCount.get(word));
      }

      if(buffer.get(word).length() == word.length()){
        buffer.get(word).deleteCharAt(0);
      }
    }
  }

  static void DisplayWordCount( ) {

    String input = "acacabcatghhellomvnsdb";
    String[] keywords = {"aca","cat","hello","world"};
    HashMap<String, Integer> wordCount = new HashMap();

    for(String word : keywords){

      wordCount.put(word, 0);
      int len = word.length();
      int index = input.indexOf(word.charAt(0));
      while(0 <= index && index < input.length()-len) {
          String subString = input.substring(index, index+len);
          if(subString.equals(word)) {
            wordCount.put(word, wordCount.get(word) +1);
          }
          index++;
        }
      }

      for(String word: wordCount.keySet()) {
        System.out.println("\"" + word + "\"" + ":" + wordCount.get(word));
      }
    }


    static int getMinSquares(int value)
  {
    //DP
    int dp[] = new int[value+1];

    //Initialise
    dp[0] = 0;
    dp[1]=1;
    dp[2]=2;
    dp[3]=3;

    for(int i=4;i<=value;i++){

      dp[i]=i; //max number

      for(int j=1;j<=i;j++){

        int temp = j*j;
        if(temp> i)
          break;
        dp[i] = Math.min(dp[i], 1+ dp[i-temp]);

      }

    }

    return dp[value];

  }


  //recursive
//    //Base case
//    if(value<=3)
//      return value;
//    int res= value;
//
//    for(int i=1;i<=value;i++){
//
//      int temp = i*i;
//      if(temp >value)
//        break;
//      res = Math.min(res, 1+getMinSquares(value-temp));
//
//    }
//    return res;

//  }


  static void getMaxDays(){

    int[] freq = new int[31];
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    for(int n = 0; n < N; n++){
      int start = scanner.nextInt();
      int finish = scanner.nextInt();
      for(int i = start; i <= finish; i++){
        freq[i]++;
      }
    }

    int max= Integer.MIN_VALUE;
    int max_day = Integer.MIN_VALUE;
    for(int i=0; i< 31; i++) {
      if(freq[i] > max ) {
        max = freq[i];
        max_day = i;
      }
    }

    System.out.println(max_day);
  }


  private static void sortAndShowHotelList(int numberOfReviews, String[] reviews, String[] keywords) {
    HashMap<Integer,Integer> hotelKeywordsHit = new HashMap<>();

    for(int i=2;i<=2*numberOfReviews; i+=2) {
      int counter =0;
      int hotelId = Integer.valueOf(reviews[i]);
      String review = reviews[i+1];
      for( String word : keywords) {
        if(review.contains(word)) {
          counter++;
        }
      }
      hotelKeywordsHit.put(hotelId, counter);
    }

    //Basically uses collection.sort - get the list of keys and use it
    //that is why we need to say which map to use
    Comparator<Object> sortedValues = new Comparator<Object>() {
      @Override
      public int compare(Object s1, Object s2) {
        int compareValue = hotelKeywordsHit.get(s2).compareTo(hotelKeywordsHit.get(s1));
        if (compareValue == 0)
          return 1;
        return compareValue;
      }
    };

    Map<Integer, Integer> sortedMap = new TreeMap(new MyComparator(hotelKeywordsHit));
//    Map<Integer, Integer> sortedMap = new TreeMap(sortedValues);

    sortedMap.putAll(hotelKeywordsHit);

    //Original List  - getbykey() else will return null
    for(Integer hotelId: sortedMap.keySet()) {
      System.out.println("Sorted with hotelId: " + hotelId + " and its rating: " + hotelKeywordsHit.get(hotelId));
    }
  }

  //Play with this and you will know how it behaves why return 1 is expliciy used
  static class MyComparator implements Comparator {
    Map<Integer, Integer> map;

    MyComparator(Map<Integer, Integer> map){
      this.map = map;
    }
    @Override
    public int compare (Object s1, Object s2){
      int compareValue = map.get(s2).compareTo(map.get(s1));
      if (compareValue == 0)
        return 1;
      return compareValue;

    }
  }
}


