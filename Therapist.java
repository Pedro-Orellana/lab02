import java.util.Scanner;

public class Therapist {

    public static Scanner scanner = new Scanner(System.in);
    public static Utils utils = new Utils();

    public static String[] questions = utils.getQuestions();

    public static void main(String[] args) {

        displayGreeting();

        String response = getInitialResponse();
        Boolean wantsToContinue = therapySession(response);

        while (wantsToContinue) {
            System.out.print("What else are you feeling today? (happy, sad, anxious, excited) ");
            response = utils.normalizeResponse(scanner.nextLine());
            wantsToContinue = therapySession(response);
        }

    }

    public static void displayGreeting() {
        System.out.println("Hello! This is Dr. Starks and we are going to start our session today.");
    }

    public static String getInitialResponse() {
        System.out.print("How are you feeling today? (happy, sad, anxious, excited) ");
        String response = scanner.nextLine();
        return utils.normalizeResponse(response);
    }

    public static Boolean therapySession(String response) {

        int i = 0;
        int x = 0;
        while (!response.equals("no") || !response.contains("goodbye")) {
            
            String currentQuestion = questions[i].replace("replace", response);
            System.out.print(currentQuestion + " ");
            response = utils.normalizeResponse(scanner.nextLine());
            if(i == 3 || i == 4) {
                //the last two questions, can only get a "yes" or a "no" for answer
                if(!response.equals("yes") && !response.equals("no")) {
                    continue;
                } else {
                    break;
                }        
            }
            if(i == 1) {
                //know if the follow up question is happy or sad
                x = utils.getCorrectFollowUpQuestion(response);
            }

            if (i == 2) {
                //skip the happy follow up question if necessary
                i = i + x;
            }
            i++;

            if (i >= questions.length) {
                break;
            }
        }
        // TODO: check if the user really wants to continue or if they input an invalid

        // if (response.equals("no") || response.equals("goodbye")) {
        //     System.out.println("Okay then, this is the end of our session, have a great day!");
        //     return false;
        // } else if (response.equals("yes")) {
        //     return true;
        // } else {
        //     System.out.println("I am sorry, did you want to continue with your session? (yes, no)");

        //     return true;
        // }


        return checkIfWantsToContinue(response);

    }

    public static Boolean checkIfWantsToContinue (String response) {
        if (response.equals("no") || response.equals("goodbye")) {
            System.out.println("Okay then, this is the end of our session, have a great day!");
            return false;
        } else if (response.equals("yes")) {
            return true;
        } else {
            System.out.println("I am sorry, did you want to continue with your session? (yes, no)");
            response = utils.normalizeResponse(scanner.nextLine());
            checkIfWantsToContinue(response);
            return true;
        }
    }


    

}