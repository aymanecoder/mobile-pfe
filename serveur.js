const admin = require("firebase-admin");

// Initialize Firebase Admin SDK
const serviceAccount = require("path/to/your/serviceAccountKey.json");
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://console.firebase.google.com/u/0/project/pfemobile-bcf39/database/pfemobile-bcf39-default-rtdb/data/~2F"
});

// Replace "deviceToken" with the FCM token you want to target
const deviceToken = "your_device_token";

// Notification payload
const payload = {
  notification: {
    title: "Friend Request",
    body: "You have received a friend request."
  },
  data: {
    // You can include additional data if needed
    // For example, to open a specific screen when the notification is clicked
    screen: "notification"
  }
};

// Send the notification
admin.messaging().sendToDevice(deviceToken, payload)
  .then(response => {
    console.log("Successfully sent message:", response);
  })
  .catch(error => {
    console.error("Error sending message:", error);
  });
