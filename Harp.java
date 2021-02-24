
public class Harp {

    private static String NOTE_MAPPING = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static double FREQUENCY_FACTOR = 440.0;
    
    public static void main(String[] args) {
        // create array of HarpString objects
        HarpString[] stringArray = new HarpString[NOTE_MAPPING.length()];
        for (int i = 0; i < NOTE_MAPPING.length(); i++) {
            double frequency = FREQUENCY_FACTOR * Math.pow(2, (i-24)/12);
            stringArray[i] = new HarpString(frequency);
        }

        // infinite loop to check if a key is pressed
        // and play the associated note
        while (true) {
            // check if the user has typed a key; if so, process it   
            if (PennDraw.hasNextKeyTyped()) {
                char key = PennDraw.nextKeyTyped();  // which key was pressed?
                if (NOTE_MAPPING.indexOf(key) != -1) {
                    stringArray[NOTE_MAPPING.indexOf(key)].pluck();
                }
            }
            
            // compute the combined sound of all harp strings
            double sample = 0.0;
            for (int i = 0; i < NOTE_MAPPING.length(); i++) {
                sample += stringArray[i].sample();
            }
  
            // play the sample on standard audio
            StdAudio.play(sample);
  
            // advance the simulation of each harp string by one step   
            for (int i = 0; i < NOTE_MAPPING.length(); i++) {
                stringArray[i].tic();
            }

        }
    }
}
 