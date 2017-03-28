/**
 * Propietary.
 * Copyright (c) 2017 iMover.
 */
package xyz.imover.android.teleop;

import imover_msgs.MissionActionGoal;
import imover_msgs.Odometry;
import imover_msgs.Path;
import org.ros.message.MessageFactory;
import org.ros.message.Time;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

/**
 * Rosjava node to publish a heartbeat messages.
 * @author Ernesto Corbellini, Ekumen, Inc.
 */
public class PublisherNode extends AbstractNodeMain {
  private static final String TAG = PublisherNode.class.getSimpleName();

  private String mTopic1;
  private String mTopic2;
  private Publisher<imover_msgs.Path> mPublisher1;
  private Publisher<imover_msgs.MissionActionGoal> mPublisher2;
  private MessageFactory mMessageFactory;

  public PublisherNode(String topic1, String topic2) {
    mTopic1 = topic1;
    mTopic2 = topic2;
  }

  @Override
  public GraphName getDefaultNodeName() {
    return GraphName.of(TAG);
  }

  public void onStart(ConnectedNode connectedNode) {
    mMessageFactory = connectedNode.getTopicMessageFactory();
    mPublisher1 = connectedNode.newPublisher(mTopic1, Path._TYPE);
    mPublisher2 = connectedNode.newPublisher(mTopic2, MissionActionGoal._TYPE);
  }

  public void publish() {
    Path message1 = mPublisher1.newMessage();
    MissionActionGoal message2 = mPublisher2.newMessage();
    Odometry odom = mMessageFactory.newFromType(Odometry._TYPE);

    message1.getOdom().add(odom);
    //message2.getGoal().getPath().getOdom().add(odom);
    //message2.getGoal().getPath().getHeader().setFrameId("testing_frame");
    
    mPublisher1.publish(message1);
    mPublisher2.publish(message2);
  }
}
