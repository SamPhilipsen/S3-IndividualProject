import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import "./App.css"
// Set the backend location
const ENDPOINT = "http://localhost:8080/ws";
var randomColor = require('randomcolor')



function App() {

  const [msgUser, setMsgUser] = useState("Username")
  const [msgToSend, setSendMessage] = useState("Enter your message here!");
  const [chatHistory, setChatHistory] = useState([]);
  const [stompClient, setStompClient] = useState(null);
  const [myTextColor, setMyTextColor] = useState();

  useEffect(() => {
    const socket = SockJS(ENDPOINT);
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
        stompClient.subscribe('/topic/greetings', (data) => {
            console.log(data);
            onMessageReceived(data);
        });
    });
    setStompClient(stompClient);
  }, []);

  function sendMessage() {
      let messageUser = msgUser
      let messageText = msgToSend;
      let messageTextColor = randomColor();

      if(myTextColor) {
          messageTextColor = myTextColor;
      } else {
          setMyTextColor(messageTextColor)
      }

      const message = {
          'username': messageUser,
          'text': messageText,
          'textcolor': messageTextColor
      }

      stompClient.send("/app/hello", {}, JSON.stringify(message));
  };

  function onMessageReceived(data)
  {
    const result = JSON.parse(data.body);

    setChatHistory(chatHistory => [
        ...chatHistory, {
            textcolor: result.textcolor,
            timeStamp: result.timeStamp,
            content: result.content
        }
    ])
  };

  return (
    <div className="App">
      <div className="chatBox">
          <select
              className="chatBox"
              name="chatBox"
              size={chatHistory.length + 1}
          >
              {chatHistory.map((message) => {
                  return (<option key={message.timeStamp} style={{color: message.textcolor}}>{message.content}</option>)
              })}
          </select>
          <br />
          <br />
          <input
              onChange={(event) => setMsgUser(event.target.value)}
              placeholder="Username"
          ></input>
          <textarea
              onChange={(event) => setSendMessage(event.target.value)}
              placeholder="Text you want to send"
          ></textarea>
          <button  onClick={sendMessage}>Send Message</button>
      </div>
  </div>
  );

}

export default App;