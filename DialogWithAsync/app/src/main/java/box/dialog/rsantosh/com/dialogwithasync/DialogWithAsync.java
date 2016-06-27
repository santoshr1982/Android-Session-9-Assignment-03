package box.dialog.rsantosh.com.dialogwithasync;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DialogWithAsync extends AppCompatActivity {

    private Button mButtonOk;
    private Button mButtonCancel;
    private EditText mEditText;
    private ProgressBar mProgressBar;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_with_async);


        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Enter a number");

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View dialog = layoutInflater.inflate(R.layout.item_dialog,null,false);

        alertDialog.setView(dialog);

        alertDialog.show();



        mButtonOk = (Button) dialog.findViewById(R.id.ok);
        mButtonCancel = (Button) dialog.findViewById(R.id.cancel);
        mEditText = (EditText) dialog.findViewById(R.id.number);

        mProgressBar = (ProgressBar) findViewById(R.id.progressDialog);

        mProgressBar.setVisibility(View.GONE);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mEditText.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Please Enter a valid integer.", Toast.LENGTH_SHORT).show();

                }else{

                    int i = (Integer.parseInt(mEditText.getText().toString()));
                    count = 1;


                    alertDialog.dismiss();
                    new MyAsync().execute(6);

                }

            }
        });

    }

    class MyAsync extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... integers) {

            for ( ; count <= integers[0]; count++){

                try {

                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                    publishProgress(count);
                }

            }

            return "Success";
        }

        @Override
        protected void onPreExecute() {

            mProgressBar.setVisibility(View.VISIBLE);

            count=1;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            mProgressBar.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {

            final AlertDialog.Builder alBuilder1 = new AlertDialog.Builder(DialogWithAsync.this);
            alBuilder1.setTitle("Successful");
            alBuilder1.setMessage("Completed");
            alBuilder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            alBuilder1.show();


            mProgressBar.setVisibility(View.GONE);




        }
    }

}
