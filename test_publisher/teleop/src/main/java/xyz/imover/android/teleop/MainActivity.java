/*
 * Copyright (C) 2013 OSRF.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package xyz.imover.android.teleop;

// android
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.io.IOException;

// ros
import com.github.rosjava.android_remocons.common_tools.apps.RosAppActivity;
import org.ros.namespace.NameResolver;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

/**
 * @author murase@jsk.imi.i.u-tokyo.ac.jp (Kazuto Murase)
 */
public class MainActivity extends RosAppActivity {
  private Button backButton;
  private Context context;
  PublisherNode mPublisherNode;

  public MainActivity() {
    // The RosActivity constructor configures the notification title and ticker messages.
    super("android teleop", "android teleop");
  }

  @SuppressWarnings("unchecked")
  @Override
  public void onCreate(Bundle savedInstanceState) {

    context = getApplicationContext();

    setDashboardResource(R.id.top_bar);
    setMainWindowResource(R.layout.main);
    super.onCreate(savedInstanceState);

    backButton = (Button) findViewById(R.id.back_button);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  @Override
  protected void init(NodeMainExecutor nodeMainExecutor) {
    super.init(nodeMainExecutor);

    try {
      java.net.Socket socket = new java.net.Socket(getMasterUri().getHost(),
          getMasterUri().getPort());
      java.net.InetAddress local_network_address = socket.getLocalAddress();
      socket.close();
      NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(
          local_network_address.getHostAddress(), getMasterUri());

      String testTopic1 = remaps.get("/test_topic_1");
      String testTopic2 = remaps.get("/test_topic_2");

      NameResolver appNameSpace = getMasterNameSpace();

      mPublisherNode = new PublisherNode(testTopic1.toString(), testTopic2.toString());

      final Activity context = this;

      nodeMainExecutor.execute(mPublisherNode, nodeConfiguration
          .setNodeName("/test_publisher"));
    } catch (IOException e) {
      // Socket problem
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(0, 0, 0, R.string.stop_app);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);
    switch (item.getItemId()) {
      case 0:
        onDestroy();
        break;
    }
    return true;
  }

  public void publishClicked(View view) {
    mPublisherNode.publish();
  }
}
