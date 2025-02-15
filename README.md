MCQ-Based Quiz App in Android (Java):

Objective:

Create a fully functional MCQ-based Quiz App in Android (Java) that demonstrates the use of:

      • Splash Screen with Animations

      • Activities & Navigation

      • Explicit & Implicit Intents

Assignment Requirements:

1. Splash Screen with Animations
   
      • Create a Splash Screen that appears when the app is launched.

      • Add animations (e.g., fading, scaling, or translating the logo).

      • After 2-3 seconds, navigate to the Main Quiz Screen automatically.

2. Quiz Interface (MainActivity & QuizActivity)


   MainActivity (Start Screen)

       • Displays a text field for entering the user’s name.
   
       • A "Start Quiz" button navigates to QuizActivity (Explicit Intent).


   QuizActivity (MCQ Screen)

       • Shows one MCQ at a time with four answer options.

       • Includes two buttons: "Previous" & "Next"

       ▪ Next Button: Moves to the next question.

       ▪ Previous Button: Moves to the previous question (disabled on the first question).

       ▪ Implements score tracking and shows the final result after the last question.

3. Explicit Intent

     • When the quiz starts, navigate from MainActivity → QuizActivity (Explicit Intent).

     • Pass the user’s name to QuizActivity using an Intent Extra.

4. Implicit Intent

     • After the quiz ends, navigate to ResultActivity (Explicit Intent).

     • Add a "Share Score" button that uses an Implicit Intent to share the result via WhatsApp, Gmail, etc.
   
5. Design

   Splash Screen:

   Start Screen:

   Quiz Screen:

   Result Screen:

Additional Instructions:

  ● Consistency: Maintain design consistency across all screens in terms of color, fonts, and spacing etc
  
  ● Usability: Ensure that all interactive elements (buttons, forms) are user-friendly and clearly labeled.
  
  ● Testing: Test the navigation flow between screens to ensure it functions as described.
  
  ● The logo and the Background used in the design is given.
