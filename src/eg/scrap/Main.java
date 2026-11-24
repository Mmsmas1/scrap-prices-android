package eg.scrap;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.widget.*;
import android.view.Gravity;
import android.graphics.Color;
import android.view.KeyEvent;
import android.content.Intent;
import android.net.Uri;
public class Main extends Activity {
  static final String LIVE = "https://mmsmas1.github.io/scrap-prices-egypt/";
  WebView w;
  @Override protected void onCreate(Bundle b) {
    super.onCreate(b);
    w = new WebView(this);
    LinearLayout box = new LinearLayout(this);
    box.setOrientation(LinearLayout.VERTICAL);
    box.addView(w, new LinearLayout.LayoutParams(-1, 0, 1f));
    TextView t = new TextView(this);
    t.setText("تطبيق من تطوير محمد أبو سليمان · المصدر: khurda.net (غير رسمي)");
    t.setTextSize(11); t.setGravity(Gravity.CENTER); t.setPadding(8,8,8,8);
    t.setTextColor(Color.parseColor("#5B6B75")); t.setBackgroundColor(Color.parseColor("#EEF2F4"));
    box.addView(t, new LinearLayout.LayoutParams(-1, -2));
    setContentView(box);
    WebSettings s = w.getSettings();
    s.setJavaScriptEnabled(true);
    s.setDomStorageEnabled(true);
    s.setCacheMode(WebSettings.LOAD_DEFAULT);
    w.setWebViewClient(new WebViewClient() {
      @Override public void onReceivedError(WebView v, WebResourceRequest r, WebResourceError e) {
        if (r.isForMainFrame()) v.loadUrl("file:///android_asset/index.html");
      }
      @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest r) {
        Uri u = r.getUrl();
        if (u.getHost() != null && u.getHost().endsWith("github.io")) return false;
        startActivity(new Intent(Intent.ACTION_VIEW, u)); return true;
      }
    });
    w.loadUrl(LIVE);
  }
  @Override public boolean onKeyDown(int k, KeyEvent e) {
    if (k == KeyEvent.KEYCODE_BACK && w.canGoBack()) { w.goBack(); return true; }
    return super.onKeyDown(k, e);
  }
}
