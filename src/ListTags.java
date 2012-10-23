import com.sun.javadoc.*;

public class ListTags {
	public static boolean start(RootDoc root){ 
		String tagName = "mytag";
		writeContents(root.classes(), tagName);
		return true;
	}

	private static void writeContents(ClassDoc[] classes, String tagName) {
		for (int i=0; i < classes.length; i++) {
			boolean classNamePrinted = false;
			MethodDoc[] methods = classes[i].methods();
			for (int j=0; j < methods.length; j++) {
				Tag[] tags = methods[j].tags(tagName);
				if (tags.length > 0) {
					if (!classNamePrinted) {
						System.out.println("\n" + classes[i].name() + "\n");
						classNamePrinted = true;
					}
					System.out.println(methods[j].name());
					for (int k=0; k < tags.length; k++) {
						System.out.println("   " + tags[k].name() + ": " 
								+ tags[k].text());
					}
				} 
			}
		}
	}

	private static String readOptions(String[][] options) {
		String tagName = null;
		for (int i = 0; i < options.length; i++) {
			String[] opt = options[i];
			if (opt[0].equals("-tag")) {
				tagName = opt[1];
			}
		}
		return tagName;
	}

	public static int optionLength(String option) {
		if(option.equals("-tag")) {
			return 2;
		}
		return 0;
	}

	public static boolean validOptions(String options[][], 
			DocErrorReporter reporter) {
		boolean foundTagOption = false;
		for (int i = 0; i < options.length; i++) {
			String[] opt = options[i];
			if (opt[0].equals("-tag")) {
				if (foundTagOption) {
					reporter.printError("Only one -tag option allowed.");
					return false;
				} else { 
					foundTagOption = true;
				}
			} 
		}
		if (!foundTagOption) {
			reporter.printError("Usage: javadoc -tag mytag -doclet ListTags ...");
		}
		return foundTagOption;
	}

}

