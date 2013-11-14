package ru.qzenn.robotiumexperiments;

import java.util.Calendar;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

@SuppressWarnings("unchecked")
public class TwitterTest extends ActivityInstrumentationTestCase2 {

    private static final String TARGET_PACKAGE_ID = "com.twitter.android";
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.twitter.android.StartActivity";

    private static Class<?> launcherActivityClass;
    static {
        try {
            launcherActivityClass = Class
                    .forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public TwitterTest() throws ClassNotFoundException {
        super(TARGET_PACKAGE_ID, launcherActivityClass);
    }

    private Solo solo;

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

  @Smoke
  public void testSwithAirplaineMode() throws Exception {
//      solo.
      solo.clickOnText("Connect");
      solo.sleep(1000);
      solo.clickOnImageButton(1);
      solo.clickOnEditText(0);
//      Calendar calend = Calendar.getInstance();
      solo.typeText(0, "Hello World! Hello World!" + String.valueOf(Calendar.getInstance().get(Calendar.SECOND)));
      solo.sleep(2000);
      solo.clickOnText("Tweet");
      solo.sleep(1000);
      solo.clickOnText("Home");
      solo.sleep(2000);
      
//      solo.clickOnView((solo.getView(R.id.title_button_3));
//      ("Mentions");
      // Assert that NoteEditor activity is opened
//      solo.assertCurrentActivity("Expected NoteEditor activity", "SubSettings");
//      solo.clickOnMenuItem("Airplane Mode");
//      solo.goBack();
      // Takes a screenshot and saves it in "/sdcard/Robotium-Screenshots/".
      solo.takeScreenshot();
//      boolean expected = true;
//      boolean actual = solo.waitForText("Hello World!"); // && solo.searchText("Password");
//      // Assert that Note 1 & Note 2 are found
//      assertEquals("Note 1 and/or Note 2 are not found", expected, actual);

  }
  
  public void testSearchByHashtag() throws Exception {
      solo.clickOnText("Discover");
      solo.sleep(500);
      solo.clickOnText("#hashtag");
      solo.sleep(1000);
      solo.typeText(0, "#twitter");
      solo.sendKey(66);
      solo.sleep(5000);
  }
  
  public void testChangeFontSize() throws Exception {
      solo.clickOnText("Me");
      solo.sleep(1000);
      solo.clickOnText("Settings");
      solo.sleep(500);
      solo.clickOnMenuItem("Font size");
      solo.sleep(500);
      solo.clickOnText("18 pt");
      solo.sleep(500);
      
      boolean expected = true;
      boolean actual = solo.searchText("18 pt"); // && solo.searchText("Password");
      // Assert that Note 1 & Note 2 are found
      assertEquals("Note 1 and/or Note 2 are not found", expected, actual);
  }
    
    
//    @Smoke
//    public void testAddNote() throws Exception {
//        solo.clickOnMenuItem("Add note");
//        // Assert that NoteEditor activity is opened
//        solo.assertCurrentActivity("Expected NoteEditor activity", "NoteEditor");
//        // In text field 0, add Note 1
//        solo.enterText(0, "Note 11111111111");
//        solo.goBack();
//        // Clicks on menu item
//        solo.clickOnMenuItem("Add note");
//        // In text field 0, add Note 2
//        solo.enterText(0, "Note 2");
//        // Go back to first activity named "NotesList"
//        solo.goBackToActivity("NotesList");
//        // Takes a screenshot and saves it in "/sdcard/Robotium-Screenshots/".
//        solo.takeScreenshot();
//        boolean expected = true;
//        boolean actual = solo.searchText("Note 1") && solo.searchText("Note 2");
//        // Assert that Note 1 & Note 2 are found
//        assertEquals("Note 1 and/or Note 2 are not found", expected, actual);
//
//    }
//
//    @Smoke
//    public void testEditNote() throws Exception {
//        // Click on the second list line
//        solo.clickInList(2);
//        // Change orientation of activity
//        solo.setActivityOrientation(Solo.LANDSCAPE);
//        // Change title
//        solo.clickOnMenuItem("Edit title");
//        // In first text field (0), add test
//        solo.enterText(0, " test");
//        solo.goBack();
//        boolean expected = true;
//        // (Regexp) case insensitive
//        boolean actual = solo.waitForText("(?i).*?note 1 test");
//        // Assert that Note 1 test is found
//        assertEquals("Note 1 test is not found", expected, actual);
//
//    }
//
//    @Smoke
//    public void testRemoveNote() throws Exception {
//        // (Regexp) case insensitive/text that contains "test"
//        solo.clickOnText("(?i).*?test.*");
//        // Delete Note 1 test
//        solo.clickOnMenuItem("Delete");
//        // Note 1 test & Note 2 should not be found
//        boolean expected = false;
//        boolean actual = solo.searchText("Note 1 test");
//        // Assert that Note 1 test is not found
//        assertEquals("Note 1 Test is found", expected, actual);
//        solo.clickLongOnText("Note 2");
//        // Clicks on Delete in the context menu
//        solo.clickOnText("(?i).*?Delete.*");
//        actual = solo.searchText("Note 2");
//        // Assert that Note 2 is not found
//        assertEquals("Note 2 is found", expected, actual);
//    }

    @Override
    public void tearDown() throws Exception {
        // Robotium will finish all the activities that have been opened
        solo.finishOpenedActivities();
    }

}
