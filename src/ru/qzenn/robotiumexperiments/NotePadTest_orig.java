/*
 * This is an example test project created in Eclipse to test NotePad which is a sample 
 * project located in AndroidSDK/samples/android-11/NotePad
 * Just click on File --> New --> Project --> Android Project --> Create Project from existing source and
 * select NotePad.
 * 
 * Then you can run these test cases either on the emulator or on device. You right click
 * the test project and select Run As --> Run As Android JUnit Test
 * 
 * @author Renas Reda, renas.reda@jayway.com
 * 
 */

package ru.qzenn.robotiumexperiments;

import java.lang.reflect.Field;
import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;
import android.widget.EditText;


// Model of importing resources into test project instead of 
// using reflection to get link to the first activity.
//
//
//import com.example.android.notepad.NotesList;
//
//public class NotePadTest_orig extends
//		ActivityInstrumentationTestCase2<NotesList> {
//
//	private Solo solo;
//	private static final String TARGET_PACKAGE_ID = "com.example.android.notepad";
//
//	public NotePadTest_importsources() {
//		super("com.example.android.notepad", NotesList.class);
//	}
//
//	@Override
//	public void setUp() throws Exception {
//		solo = new Solo(getInstrumentation(), getActivity());
//	}
//
//	@Smoke
//	public void testAddNote() throws Exception {
//		boolean expected = true;
//		boolean actual = solo.searchText("Note 1") && solo.searchText("Note 2");
//		solo.sleep(500);
//		assertEquals("Note 1 and/or Note 2 are not found", expected, actual);
//	}
//
//	@Override
//	public void tearDown() throws Exception {
//		// Robotium will finish all the activities that have been opened
//		solo.finishOpenedActivities();
//	}
//}

@SuppressWarnings("unchecked")
public class NotePadTest_orig extends ActivityInstrumentationTestCase2{
    
    private static final String TARGET_PACKAGE_ID = "com.example.android.notepad";
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.example.android.notepad.NotesList";

    private static Class<?> launcherActivityClass;
    static{
            try {
                    launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
            } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
            }
    }
    
    @SuppressWarnings("unchecked")
    public NotePadTest_orig() throws ClassNotFoundException {
            super(TARGET_PACKAGE_ID, launcherActivityClass);
    }
    
    private Solo solo;
    
    @Override
    protected void setUp() throws Exception {
            solo = new Solo(getInstrumentation(), getActivity());
    }

    @Smoke
	public void testAddNote_orig() throws Exception {
		solo.clickOnMenuItem("Add note");
		//Assert that NoteEditor activity is opened
		solo.assertCurrentActivity("Expected NoteEditor activity", "NoteEditor"); 
		//In text field 0, add Note 1
		solo.enterText(0, "Note 1");
		solo.goBack(); 
		//Clicks on menu item
		solo.clickOnMenuItem("Add note");
		//In text field 0, add Note 2
		solo.enterText(0, "Note 2");
		//Go back to first activity named "NotesList"
		solo.goBackToActivity("NotesList"); 
		//Takes a screenshot and saves it in "/sdcard/Robotium-Screenshots/".
		solo.takeScreenshot();
		boolean expected = true;
		boolean actual = solo.searchText("Note 1") && solo.searchText("Note 2");
		//Assert that Note 1 & Note 2 are found
		assertEquals("Note 1 and/or Note 2 are not found", expected, actual); 

	}

	@Smoke 
	public void testEditNote_orig() throws Exception {
		// Click on the second list line
		solo.clickInList(2); 
		// Change orientation of activity
		solo.setActivityOrientation(Solo.LANDSCAPE);
		// Change title
		solo.clickOnMenuItem("Edit title");
		//In first text field (0), add test
		solo.enterText(0, " test");  
		solo.goBack();
		boolean expected = true;
		// (Regexp) case insensitive
		boolean actual = solo.waitForText("(?i).*?note 1 test"); 
		//Assert that Note 1 test is found
		assertEquals("Note 1 test is not found", expected, actual); 

	}


	@Smoke
	public void testRemoveNote_orig() throws Exception {
		//(Regexp) case insensitive/text that contains "test"
		solo.clickOnText("(?i).*?test.*");
		//Delete Note 1 test
		solo.clickOnMenuItem("Delete");
		//Note 1 test & Note 2 should not be found
		boolean expected = false;   
		boolean actual = solo.searchText("Note 1 test");
		//Assert that Note 1 test is not found
		assertEquals("Note 1 Test is found", expected, actual);  
		solo.clickLongOnText("Note 2");
		//Clicks on Delete in the context menu
		solo.clickOnText("(?i).*?Delete.*");  
		actual = solo.searchText("Note 2");
		//Assert that Note 2 is not found
		assertEquals("Note 2 is found", expected, actual);  
	}
	
	public int getRid(String stringId) throws Exception {
        int returnid = 0;
        Class<?>[] classes = Class.forName(TARGET_PACKAGE_ID + ".R").getClasses();
        for (Class<?> inst : classes){
            Log.v("ROBO_TEST", inst.getName());
//            assertFalse("ID found!",inst.getName().equals("com.android.settings.R$id"));
            if (inst.getName().equals(TARGET_PACKAGE_ID + ".R$id")){
                for (Field field : inst.getFields()){
//                    Log.v("ROBO_TEST", Integer.toString(field.getInt(null)) + " - " + field.getName());
                    if (field.getName().equalsIgnoreCase(stringId)){
                        returnid = field.getInt(null);
                        Log.v("ROBO_TEST", Integer.toString(returnid) + " - " + field.getName());
                    }
                }
            }
        }
        
        return returnid;
    }


	@Smoke
	public void t1estAddNote() throws Exception {
	    
//	    solo.sendKey(Solo.MENU);
	    solo.sleep(500);
	    int ident  = getRid("text1");
	    Log.v("ROBO", String.valueOf(ident));
	    
	    assertNotNull("NULL",solo.getCurrentActivity().findViewById(ident));
	    solo.clickOnView((solo.getCurrentActivity().findViewById(ident)));
	    solo.sleep(500);
//		solo.clickOnMenuItem("Add note");
		//Assert that NoteEditor activity is opened
		solo.assertCurrentActivity("Expected NoteEditor activity", "NoteEditor"); 
		//In text field 0, add Note 1
//		solo.enterText(0, "Note 1");
		
		
		EditText text1 = (EditText)solo.getCurrentActivity().findViewById(getRid("note"));
		
		
		assertNotNull(text1);
		TouchUtils.tapView(this, text1);
		solo.sleep(250);
//		sendKeys(KeyEvent.KEYCODE_5);
//		solo.sleep(250);
		
		getInstrumentation().sendStringSync("ID works!");
//		solo.typeText(text1, "ID works!");
		
		solo.goBackToActivity("NotesList"); 
		//Clicks on menu item
		solo.clickOnMenuItem("Add note");
		//In text field 0, add Note 2
		solo.enterText(0, "Note 2");
		//Go back to first activity named "NotesList"
		solo.goBackToActivity("NotesList"); 
		//Takes a screenshot and saves it in "/sdcard/Robotium-Screenshots/".
		solo.takeScreenshot();
		boolean expected = true;
		boolean actual = solo.searchText("Note 1") && solo.searchText("Note 2");
		//Assert that Note 1 & Note 2 are found
		assertEquals("Note 1 and/or Note 2 are not found", expected, actual); 

	}

	@Smoke 
	public void t1estEditNote() throws Exception {
		// Click on the second list line
		solo.clickInList(2); 
		// Change orientation of activity
		solo.setActivityOrientation(Solo.LANDSCAPE);
		// Change title
		solo.clickOnMenuItem("Edit title");
		//In first text field (0), add test
		solo.enterText(0, " test");  
		solo.goBack();
		boolean expected = true;
		// (Regexp) case insensitive
		boolean actual = solo.waitForText("(?i).*?note 1 test"); 
		//Assert that Note 1 test is found
		assertEquals("Note 1 test is not found", expected, actual); 

	}


	@Smoke
	public void t1estRemoveNote() throws Exception {
		//(Regexp) case insensitive/text that contains "test"
		solo.clickOnText("(?i).*?test.*");
		//Delete Note 1 test
		solo.clickOnMenuItem("Delete");
		//Note 1 test & Note 2 should not be found
		boolean expected = false;   
		boolean actual = solo.searchText("Note 1 test");
		//Assert that Note 1 test is not found
		assertEquals("Note 1 Test is found", expected, actual);  
		solo.clickLongOnText("Note 2");
		//Clicks on Delete in the context menu
		solo.clickOnText("(?i).*?Delete.*");  
		actual = solo.searchText("Note 2");
		//Assert that Note 2 is not found
		assertEquals("Note 2 is found", expected, actual);  
	}


	@Override
	public void tearDown() throws Exception {
		//Robotium will finish all the activities that have been opened
		solo.finishOpenedActivities();
	}
}
