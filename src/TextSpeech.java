// Java code to convert text to speech
// Code from: https://www.geeksforgeeks.org/converting-text-speech-java/
  
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
  
public class TextSpeech {
  
    public static void main(String[] args)
    {
  
        try {
            // Set property as Kevin Dictionary
            System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");
  
            // Register Engine
            Central.registerEngineCentral(
                "com.sun.speech.freetts"
                + ".jsapi.FreeTTSEngineCentral");
  
            // Create a Synthesizer
            Synthesizer synthesizer
                = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));
  
            // Allocate synthesizer
            synthesizer.allocate();
  
            // Resume Synthesizer
            synthesizer.resume();
  
            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                "GeeksforGeeks", null);
            synthesizer.waitEngineState(
                Synthesizer.QUEUE_EMPTY);
  
            // Deallocate the Synthesizer.
            synthesizer.deallocate();
        }
  
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}