package ru.qzenn.robotiumexperiments;

import java.lang.reflect.Field;
import java.util.Calendar;

import com.jayway.android.robotium.solo.Solo;


import android.content.ComponentName;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;


@SuppressWarnings("unchecked")
public class SettingsTest extends ActivityInstrumentationTestCase2 {

    private static final String TARGET_PACKAGE_ID = "com.android.settings";
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.android.settings.Settings";

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
    public SettingsTest() throws ClassNotFoundException {
        super(TARGET_PACKAGE_ID, launcherActivityClass);
    }

    private Solo solo;

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }


  
  public void t1estSoundTest() throws Exception {
      solo.clickOnText("Sound");
      if (!solo.isCheckBoxChecked(0)){
          solo.clickOnText("Silent mode");
      }
      
      solo.sleep(1000);
      solo.clickOnText("Silent mode");
      solo.clickOnText("Vibrate");
      solo.clickOnView(solo.getView(android.widget.CheckedTextView.class, 2));
      solo.clickOnText("Volume");
      solo.setProgressBar(0, 2);
      solo.sleep(1000);
//      solo.getCurrentProgressBars().get(0).getProgress();
        {
            int expected = 2;
            int actual = solo.getCurrentProgressBars().get(0).getProgress();
            //
            assertEquals("Volume set incorrectly", expected, actual);
        }
      
      
      solo.setProgressBar(1, 2);
      solo.sleep(1000);
//      solo.getCurrentProgressBars().get(0).getProgress();
        {
            int expected = 2;
            int actual = solo.getCurrentProgressBars().get(1).getProgress();
            //
            assertEquals("Volume set incorrectly", expected, actual);
        }
        solo.sleep(1000);
        solo.goBack();
        
        solo.clickOnCheckBox(4);
        solo.clickOnCheckBox(2);
        solo.clickOnCheckBox(1);
        solo.clickOnCheckBox(0);
        
//        if (solo.isCheckBoxChecked(4)) {
//            solo.clickOnCheckBox(4);
//        }
//        assertTrue(!solo.isCheckBoxChecked(4));
//      android.widget.Toast toast = android.widget.Toast.makeText(
//              solo.getText("Silent mode").getContext(),
//              "it works", android.widget.Toast.LENGTH_SHORT);
//      toast.setText("it works!");
//      toast.show();
//      
//        for (int j = 0; j < 3; j++) {
//            solo.sendKey(android.view.KeyEvent.KEYCODE_DPAD_DOWN);
//            solo.sleep(1000);
//            for (int ii = 0; ii < solo.getCurrentViews().size(); ii++) {
//                if (solo.getCurrentViews().get(ii).hasFocus()) {
//                    solo.getCurrentViews().get(ii).performClick();
//                    android.widget.Toast toast2 = android.widget.Toast.makeText(
//                            solo.getCurrentViews().get(ii).getContext(),
//                            "it is focused", android.widget.Toast.LENGTH_SHORT);
//                    toast2.setText("it is focused!");
//                    toast2.show();
//                }
//            }
//        }
  }
  
  public void t1estSecurity() throws Exception {
    solo.clickOnText("security");
    
//    solo.clickOnCheckBox(0);
//    solo.clickOnCheckBox(1);
//    solo.clickOnText("Set up screen lock");
//    solo.clickOnText("None");
//    solo.clickOnText("Set up SIM card lock");
//    solo.sleep(1000);
    
    //solo.clickOnCheckBox(0);
    solo.clickOnText("Lock SIM card");
    
    solo.typeText(0, "00000000");
    solo.clickOnButton("OK");
    solo.clickOnImage(0);
    solo.typeText(0, "12345678");
    solo.clickOnButton("OK");
    solo.typeText(0, "00000000");
    solo.clickOnButton("OK");
    solo.typeText(0, "00000000");
    solo.clickOnButton("OK");
    solo.goBack();
    solo.clickOnText("administators");
    solo.clickOnCheckBox(0);
    solo.clickOnButton("Activate");
    assertTrue(solo.isCheckBoxChecked(0));
    solo.goBack();
    solo.clickOnText("Intall from SD card");
    solo.clickOnText("Set password");
    solo.typeText(0, "1234567890");
    solo.typeText(1, "1234567890");
    solo.clickOnButton(0);
    solo.clickOnText("Clear storage");
    solo.clickOnButton(0);
    assertTrue(solo.waitForText("erased"));
  }
  
  public void t1estMobileData() throws Exception {
//      solo.clickOnText("Data usage");
//      solo.clickOnView(solo.getView(Switch.class, 0));
//      solo.sleep(500);
//      if (solo.searchText("OK")){
//          solo.clickOnButton("OK");
//      }
//      solo.goBack();
//      Intent intent = new Intent;

//      solo.clickOnText("More");
//      solo.clickOnText("Mobile networks");
//      solo.clickOnText("Data enabled");
//      solo.sleep(1500);
//      solo.clickOnText("Data enabled");
//      solo.sleep(1500);
//      solo.clickOnText("Data roaming");
//      solo.clickOnText("OK");
//      solo.clickOnText("Data roaming");
//      solo.clickOnText("Access Point Names");
      solo.sleep(1500);
      Intent intent = new Intent("android.intent.action.MAIN");
      intent.setComponent(ComponentName.unflattenFromString("com.android.settings/com.android.settings.ApnSettings"));
      intent.addCategory("android.intent.category.LAUNCHER");
      solo.getAllOpenedActivities().get(0).startActivity(intent);
      
      solo.clickOnMenuItem("Reset to default");
      solo.clickOnText("TelKila");
      solo.clickOnMenuItem("Delete APN");
      solo.clickOnMenuItem("New APN");
      solo.clickOnText("Name");
      solo.sleep(500);
      solo.typeText(0, "Beeline internet");
      solo.clickOnButton("OK");
//      solo.clickOnMenuItem("Save");
      solo.sleep(500);
      solo.clickOnText("APN");
      solo.sleep(500);
      solo.typeText(0, "internet.beeline.ru");
      solo.clickOnButton("OK");
      solo.clickOnText("Proxy");
      solo.typeText(0, "192.168.1.112");
      solo.clickOnButton("OK");
      solo.clickOnText("Port");
      solo.typeText(0, "8080");
      solo.clickOnButton("OK");
      solo.clickOnText("Username");
      solo.typeText(0, "beeline");
      solo.clickOnButton("OK");
      solo.clickOnText("Password");
      solo.typeText(0, "beeline");
      solo.clickOnButton("OK");
      solo.clickOnText("Server");
      solo.typeText(0, "server.domain.com");
      solo.clickOnButton("OK");
      solo.clickOnText("MMSC");
      solo.typeText(0, "mmsc.beeline.ru");
      solo.clickOnButton("OK");
//      solo.clickOnText("MCC");
//      solo.clearEditText(0);
//      solo.typeText(0, "250");
//      solo.clickOnButton("OK");
//      solo.clickOnText("MNC");
//      solo.clearEditText(0);
//      solo.typeText(0, "99");
//      solo.clickOnButton("OK");
      solo.clickOnText("Authentication type");
      solo.clickOnView(solo.getView(android.widget.CheckedTextView.class, 2));  //
      solo.clickOnText("APN type");
      solo.typeText(0, "default");
      solo.clickOnButton("OK");
      solo.sleep(500);
      
      
      solo.clickOnMenuItem("Save");
      solo.sleep(500);
      solo.clickOnRadioButton(0);
      solo.sleep(2000);
      
      
      // Takes a screenshot and saves it in "/sdcard/Robotium-Screenshots/".
      solo.takeScreenshot();
      boolean expected = true;
      boolean actual = solo.waitForText("Beeline internet") && solo.searchText("internet.beeline.ru");
//       Assert that Note 1 & Note 2 are found
      assertEquals("APN not created", expected, actual);
      solo.clickOnMenuItem("Reset to default");
      solo.sleep(2000);
//      solo.clickOnView(solo.getView(((android.view.View)solo.getText("Beeline internet").getParent()).getNextFocusRightId()));
//      solo.sleep(3000);
  }
  
  public void t1estApplicationsManager() throws Exception {
      solo.clickOnText("Applications");
      
      if (!solo.isCheckBoxChecked(0)){
          solo.clickOnCheckBox(0);
          solo.sleep(500);
          solo.clickOnButton(0);
      }
      solo.clickOnCheckBox(0);
      
      solo.clickLongOnText("Manage applications");
      solo.clickOnImage(3);
      solo.clickOnImage(4);
      solo.assertCurrentActivity("RunningServiceDetails activity is not displayed", 
              Class.forName("com.android.settings.applications.RunningServiceDetails"));
      assertTrue(solo.getButton("Stop").isEnabled());
      solo.clickOnButton(1);
      assertFalse(solo.getButton("Report").isEnabled());
      solo.goBack();
      solo.clickOnImage(2);
      solo.clickOnImage(1);
      solo.clickOnText("Email");
      solo.assertCurrentActivity("Details activity is not opened", Class.forName("com.android.settings.applications.InstalledAppDetails"));
      solo.clickOnButton(0);
      solo.clickOnButton(0);
      solo.clickOnButton("Clear data");
      solo.clickOnButton(0);
      solo.goBack();
      solo.clickOnImage(0);
      solo.clickOnImage(6);
      solo.assertCurrentActivity("Details activity is not opened", Class.forName("com.android.settings.applications.InstalledAppDetails"));
      solo.goBack();
//      solo.assertCurrentActivity("Details activity is not opened", Class.forName("com.android.settings.ManageApplications")); // bug classnotfound exception
      solo.goBack();
      solo.clickOnText("Development");
      solo.clickOnCheckBox(2);
       
           
       }
       
       public void testId() throws Exception {
           solo.clickOnText("Display");
           solo.clickOnView(solo.getCurrentActivity().findViewById(getRid("checkbox")));
//           solo.clickOnMenuItem("Add note");
//           solo.typeText(text1, "ID works!");
           
//      solo.clickOnView(solo.getView(Class.forName("com.android.settings.R").getClasses()));
//       getField("checkbox").getInt(null)  
//           solo.clickOnView();
//           solo.clickInList(2);
//           solo.getString(0);
        }
       
       public int getRid(String stringId) throws Exception {
           int returnid = 0;
           Class<?>[] classes = Class.forName(TARGET_PACKAGE_ID + ".R").getClasses();
           for (Class<?> inst : classes){
//               Log.v("ROBO_TEST", inst.getName());
//               assertFalse("ID found!",inst.getName().equals("com.android.settings.R$id"));
               if (inst.getName().equals(TARGET_PACKAGE_ID + ".R$id")){
                   for (Field field : inst.getFields()){
                       if (field.getName().equalsIgnoreCase(stringId)){
//                           Log.v("ROBO_TEST", Integer.toString(field.getInt(null)) + " - " + field.getName());
                           returnid = field.getInt(null);
                       }
                   }
               }
           }
           
           return returnid;
       }
      
  
      
      
//      solo.sleep(1000);
//      solo.clickOnImageButton(1);
//      solo.clickOnEditText(0);
////      Calendar calend = Calendar.getInstance();
//      solo.typeText(0, "Hello World! Hello World!" + String.valueOf(Calendar.getInstance().get(Calendar.SECOND)));
//      solo.sleep(2000);
//      solo.clickOnText("Tweet");
//      solo.sleep(1000);
//      solo.clickOnText("Home");
//      solo.sleep(2000);
      
//      solo.clickOnView((solo.getView(R.id.title_button_3));
//      ("Mentions");
      // Assert that NoteEditor activity is opened
//      solo.assertCurrentActivity("Expected NoteEditor activity", "SubSettings");
//      solo.clickOnMenuItem("Airplane Mode");
//      solo.goBack();
      // Takes a screenshot and saves it in "/sdcard/Robotium-Screenshots/".
//      solo.takeScreenshot();
//      boolean expected = true;
//      boolean actual = solo.waitForText("Hello World!"); // && solo.searchText("Password");
//      // Assert that Note 1 & Note 2 are found
//      assertEquals("Note 1 and/or Note 2 are not found", expected, actual);

//  }
  
//  public void testSearchByHashtag() throws Exception {
//      solo.clickOnText("Discover");
//      solo.sleep(500);
//      solo.clickOnText("#hashtag");
//      solo.sleep(1000);
//      solo.typeText(0, "#twitter");
//      solo.sendKey(66);
//      solo.sleep(5000);
//  }
//  
//  public void testChangeFontSize() throws Exception {
//      solo.clickOnText("Me");
//      solo.sleep(1000);
//      solo.clickOnText("Settings");
//      solo.sleep(500);
//      solo.clickOnMenuItem("Font size");
//      solo.sleep(500);
//      solo.clickOnText("18 pt");
//      solo.sleep(500);
//      
//      boolean expected = true;
//      boolean actual = solo.searchText("18 pt"); // && solo.searchText("Password");
//      // Assert that Note 1 & Note 2 are found
//      assertEquals("Note 1 and/or Note 2 are not found", expected, actual);
//  }
    
    
//    

    @Override
    public void tearDown() throws Exception {
        // Robotium will finish all the activities that have been opened
        solo.finishOpenedActivities();
    }

}
