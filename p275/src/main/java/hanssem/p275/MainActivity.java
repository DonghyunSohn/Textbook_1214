package hanssem.p275;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    TextView textView1;

    private GestureDetector mGestures = null; //GestureDetector 객체 선언

    public boolean onTouchEvent(MotionEvent event) {
        if (mGestures != null) {
            return mGestures.onTouchEvent(event);
        } else {
            return super.onTouchEvent(event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);

        // 텍스트뷰를 가지고 있는 레이아웃
        View rootLayout = findViewById(R.id.rootLayout);

        // 터치 모드가 바뀌는 경우 알 수 있도록 표시합니다.
        ViewTreeObserver observer = rootLayout.getViewTreeObserver();
        observer.addOnTouchModeChangeListener(new ViewTreeObserver.OnTouchModeChangeListener() {
            public void onTouchModeChanged(boolean isTouchMode) {
                textView1.append("\n터치 모드가 바뀌었습니다. : " + isTouchMode);
            }
        });

        textView1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                textView1.append("\nonLongClick: " + v.toString());
                return true;
            }
        });

        mGestures = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    // fling 이벤트가 발생할 때 처리합니다.
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                        textView1.append("\nonFling \n\tvelocityX = " + velocityX + "\n\tvelocityY=" + velocityY);

                        return super.onFling(e1, e2, velocityX, velocityY);
                    }

                    // scroll 이벤트가 발생할 때 처리합니다.
                    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                        textView1.append("\nonScroll \n\tdistanceX = " + distanceX + "\n\tdistanceY = " + distanceY);

                        return super.onScroll(e1, e2, distanceX, distanceY);
                    }
                });

    // 포커스가 바뀌었을 때 알 수 있도록 리스너를 설정합니다.
    textView1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        // focus가 바뀌었을 때 호출됩니다.
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                textView1.append("\nonFocusChange, hasFocus : " + hasFocus);
            } else {
                textView1.append("\nonFocusChange, hasFocus : " + hasFocus);
            }
        }

    });

}
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
