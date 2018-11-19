import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 
public class TextToSpeechActivity extends Activity implements TextToSpeech.OnInitListener {
    private String TAG = this.getClass().getSimpleName();
    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        tts = new TextToSpeech(this, this);
 
        btnSpeak = findViewById(R.id.btnSpeak);
        txtText = findViewById(R.id.txtText);
 
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }
         });
    }
 
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
 
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA){
                Log.d(TAG, "LANG_MISSING_DATA: No TTS Data available.");
            }else if(result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.d(TAG, "LANG_NOT_SUPPORTED: This Language is not supported");
            } else {
                btnSpeak.setEnabled(true);
                speakTTS();
            } 
        } else {
            Log.d(TAG, "Initilization Failed!");
        } 
    }
 
    private void speakTTS() { 
        String text = txtText.getText().toString();
     
        // TextToSppech.QUEUE_FLUSH: Clear the queue.
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
