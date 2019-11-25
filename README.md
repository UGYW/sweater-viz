# sweater-viz

Multithreading visualizer for CPSC410-2019W1 by Coffee Ground Hounds.

![Example of Running Program](screencap.png)

## FOR CPSC410 TA

### Dynamic Analysis
Dynamic analysis works by injecting analytic code into original code base. The injected code is a monitoring thread running in the background.
This monitor thread will get all running thread id, name and status information every 100 milliseconds.
After the injection finished, our program will recompile the injected java code file, then run it.
The dynamic analytic data will be generated while the code this running. After the run, the information we need will be output to analysisData.txt file.
David and Andrew will parse this file to get data UI needs to display.

### Task Breakdown
* Dynamic Data Analysis - Andrew
    + Andrew is going to be deriving information from data Charlie has gathered
        + Total time the thread has run/locked
        + Total number of times the thread has run/locked
* Dynamic Data Gathering - Charlie
    + Charlie will write the dynamic analysis that detects which threads 
        are running/locked at what time
* Merge - David
    + David will take the data that Andrew and Charlie get from their analysis 
    and make sure it is delivered to the UI correctly. In more technical terms, David will work on what is probably a Singleton class that the UI and the analysis components access.
        + More specifically, David parses the logging output 
        as injected by Charlie's program and turns it into an UI-friendly dataset
UI/Setup - Uma 
    + I will work on the Gantt diagram display and onclick events
    + I created the video from the the team members' voice clips
    + I also worked on the documentation that you are reading rn :)

### Gestures
**Click and Drag**
* To the _Right_: Zoom In (to selected area)
* To the _Left_: Zoom Out

**Click on Displayed Bar**
* Pop-up will display with relevant info

## Setup
(Assuming everyone is using IntelliJ to run this. 
The instructions are the same as the DSL project.)
1. Go into Project Structure > Project and check you have the following:
    + Project SDK is set. I have it set on 1.8.
    + Project Language Level is set correspondingly to SDK - 
        if you have 1.8, set it to 8.
    + Project Compiler Output is set to 
        the absolute path to the repo followed by /out. 
    Mine was “<dir_containing_repo>/sweater-viz/out”. 
2. Download all five .jar files uploaded into the Discord chat and 
    put them somewhere NOT in the repository.
3. Go into Project Structure > Modules and check you have the following:
    + Under the Sources tab, 
        check you have the src folder set as a Source folder 
        like in the following screenshot. 
        If you don’t see src there, 
        click on the src folder on the left and 
            select Sources beside “Mark as: ”
    + Under the Paths tab, 
        make sure the “Inherit Project Compile Output Path” option is selected
    + Under the Dependencies tab, 
        click on the little “+” either 
            to the right or below the list of dependencies, 
        select the “Jars or directories” option, and
        add the five .jar files you downloaded in Step 2. 
        
       


    