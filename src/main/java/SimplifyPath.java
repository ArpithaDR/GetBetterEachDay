
/*Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/

import java.util.Stack;

public class SimplifyPath {

	public static String simplifyPath(String path) {

		if(path==null || path.isEmpty())
			return "";

		Stack<String> dirEntries = new Stack();

		String[] values = path.split("/");
		for(String entry: values){
			if(entry.length() > 0) {
				if(entry.equals("."))
					continue;
				if(entry.equals("..")){
					if(dirEntries.isEmpty())
						continue;
					dirEntries.pop();
				}
				else {
					dirEntries.add(entry);
				}
			}
		}

		if(dirEntries.isEmpty())
			return "/";

		StringBuilder absolutePath = new StringBuilder();
		while(!dirEntries.isEmpty()){
			absolutePath.insert(0, "/"+ dirEntries.pop());
		}

		return absolutePath.toString();


	}

	public static void main(String[] args){
		System.out.println(simplifyPath("/a/./b/../../c/"));
	}
}
