# sweater-viz

Multithreading visualizer for CPSC410-2019W1 by Coffee Ground Hounds.

<!--TODO: add diagrams of prototype-->

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

## Instructions

<!-- Add running instructions, if applicable-->

### Gestures
**Click and Drag**
* To the _Right_: Zoom In (to selected area)
* To the _Left_: Zoom Out

**Click on Displayed Bar**
* Pop-up will display with relevant info