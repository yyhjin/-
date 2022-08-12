<template>
    <div id="main-container" class="container">
        <div id="not-logged" class="vertical-center">
            <div id="img-div"><img src="https://localhost:5000/images/openvidu_grey_bg_transp_cropped.png" /></div>
            <form class="form-group jumbotron" onsubmit="return false">
                <p><label>User</label><input class="form-control" type="text" id="user" v-model="user" required /></p>
                <p><label>Pass</label><input class="form-control" type="password" id="pass" v-model="pass" required /></p>
                <p class="text-center">
                    <button class="btn btn-lg btn-info" @click="logIn()">Log in</button>
                </p>
            </form>
            <table class="table">
                <tr>
                    <th>User</th>
                    <th>Pass</th>
                    <th>
                        Role<i
                            data-toggle="tooltip"
                            data-placement="bottom"
                            title=""
                            data-original-title="<div id='tooltip-div'>PUBLISHER<div>Send and receive media<hr></div>SUBSCRIBER<div>Receive media</div></div>"
                            class="glyphicon glyphicon-info-sign"
                        ></i>
                    </th>
                </tr>
                <tr>
                    <td>publisher1</td>
                    <td>pass</td>
                    <td>PUBLISHER</td>
                </tr>
                <tr>
                    <td>publisher2</td>
                    <td>pass</td>
                    <td>PUBLISHER</td>
                </tr>
                <tr>
                    <td>subscriber</td>
                    <td>pass</td>
                    <td>SUBSCRIBER</td>
                </tr>
            </table>
        </div>

        <div id="logged" hidden>
            <div id="join" class="vertical-center">
                <div id="img-div"><img src="https://localhost:5000/images/openvidu_grey_bg_transp_cropped.png" /></div>
                <div id="join-dialog" class="jumbotron">
                    <h1>Join a video session</h1>
                    <form class="form-group" onsubmit="return false">
                        <p>
                            <label>Participant</label>
                            <input class="form-control" type="text" id="nickName" v-model="nickName" required />
                        </p>
                        <p>
                            <label>Session</label>
                            <input class="form-control" type="text" id="sessionName" v-model="sessionName" required />
                        </p>
                        <p class="text-center">
                            <button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
                        </p>
                    </form>
                    <hr />
                    <div id="login-info">
                        <div>Logged as <span id="name-user"></span></div>
                        <button id="logout-btn" class="btn btn-warning" @click="logOut()">Log out</button>
                    </div>
                </div>
            </div>

            <div id="session" style="display: none">
                <div id="session-header">
                    <h1 id="session-title"></h1>
                    <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" onmouseup="removeUser(); leaveSession()" value="Leave session" />
                </div>
                <div id="main-video" class="col-md-6">
                    <p class="nickName"></p>
                    <p class="userName"></p>
                    <video autoplay playsinline="true"></video>
                </div>
                <div id="video-container" class="col-md-6"></div>
            </div>
        </div>
    </div>
</template>

<script>
//import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import { ref } from "vue";
import $ from "jquery";
//var OV;
//var session;

//var sessionName; // Name of the video session the user will connect to
//var token; // Token retrieved from OpenVidu Server

window.onbeforeunload = () => {
    // Gracefully leave session
    if (this.session) {
        this.removeUser();
        this.leaveSession();
    }
};
/* OPENVIDU METHODS */

export default {
    setup() {
        const OV = ref();
        const session = ref();
        const user = ref();
        const pass = ref();
        const sessionName = ref();
        const nickName = ref();
        const token = ref();
        return { user, pass, sessionName, nickName, OV, session, token };
    },
    methods: {
        joinSession() {
            this.getToken((token) => {
                // --- 1) Get an OpenVidu object ---
                console.log("hi");
                this.OV = new OpenVidu();

                // --- 2) Init a session ---

                this.session = this.OV.initSession();

                // --- 3) Specify the actions when events take place in the session ---

                // On every new Stream received...
                this.session.on("streamCreated", (event) => {
                    // Subscribe to the Stream to receive it
                    // HTML video will be appended to element with 'video-container' id
                    var subscriber = this.session.subscribe(event.stream, "video-container");

                    // When the HTML video has been appended to DOM...
                    subscriber.on("videoElementCreated", (event) => {
                        // Add a new HTML element for the user's name and nickname over its video
                        this.appendUserData(event.element, subscriber.stream.connection);
                    });
                });

                // On every Stream destroyed...
                this.session.on("streamDestroyed", (event) => {
                    // Delete the HTML element with the user's name and nickname
                    this.removeUserData(event.stream.connection);
                });

                // On every asynchronous exception...
                this.session.on("exception", (exception) => {
                    console.warn(exception);
                });

                // --- 4) Connect to the session passing the retrieved token and some more data from
                //        the client (in this case a JSON with the nickname chosen by the user) ---

                this.session
                    .connect(token, { clientData: this.nickName })
                    .then(() => {
                        // --- 5) Set page layout for active call ---

                        //var userName = $("#user").val();
                        $("#session-title").text(this.sessionName);
                        $("#join").hide();
                        $("#session").show();
                        console.log(1);
                        // Here we check somehow if the user has 'PUBLISHER' role before
                        // trying to publish its stream. Even if someone modified the client's code and
                        // published the stream, it wouldn't work if the token sent in Session.connect
                        // method is not recognized as 'PUBLIHSER' role by OpenVidu Server
                        if (this.isPublisher(this.userName)) {
                            // --- 6) Get your own camera stream ---
                            console.log(2);
                            var publisher = this.OV.initPublisher("video-container", {
                                audioSource: undefined, // The source of audio. If undefined default microphone
                                videoSource: undefined, // The source of video. If undefined default webcam
                                publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                                publishVideo: true, // Whether you want to start publishing with your video enabled or not
                                resolution: "640x480", // The resolution of your video
                                frameRate: 30, // The frame rate of your video
                                insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                                mirror: false, // Whether to mirror your local video or not
                            });
                            console.log(3);
                            // --- 7) Specify the actions when events take place in our publisher ---

                            // When our HTML video has been added to DOM...
                            publisher.on("videoElementCreated", (event) => {
                                // Init the main video with ours and append our data
                                var userData = {
                                    nickName: this.nickName,
                                    userName: this.userName,
                                };
                                console.log(4);
                                this.initMainVideo(event.element, userData);
                                this.appendUserData(event.element, userData);
                                $(event.element).prop("muted", true); // Mute local video
                                console.log(5);
                            });

                            // --- 8) Publish your stream ---
                            console.log(6);
                            this.session.publish(publisher);
                            console.log(7);
                        } else {
                            console.warn("You don't have permissions to publish");
                            this.initMainVideoThumbnail(); // Show SUBSCRIBER message in main video
                            console.log(8);
                        }
                    })
                    .catch((error) => {
                        console.log(9);
                        console.warn("There was an error connecting to the session:", error.code, error.message);
                    });
            });
            console.log(10);
            return false;
        },

        leaveSession() {
            // --- 9) Leave the session by calling 'disconnect' method over the Session object ---

            this.session.disconnect();
            this.session = null;

            // Removing all HTML elements with the user's nicknames
            this.cleanSessionView();

            $("#join").show();
            $("#session").hide();
        },

        /* OPENVIDU METHODS */

        /* APPLICATION REST METHODS */

        logIn() {
            /*
            if (this.pass == "1") {
                $("#name-user").text(this.user);
                $("#not-logged").hide();
                $("#logged").show();
                // Random nickName and session
                this.sessionName = "Session " + Math.floor(Math.random() * 10);
                this.nickName = "Participant " + Math.floor(Math.random() * 100);
            }*/

            this.httpPostRequest("http://localhost:8080/api-login/login", { user: this.user, pass: this.pass }, "Login WRONG", (response) => {
                console.log(response);
                $("#name-user").text(this.user);
                $("#not-logged").hide();
                $("#logged").show();
                // Random nickName and session
                $("#sessionName").val("Session " + Math.floor(Math.random() * 10));
                $("#nickName").val("Participant " + Math.floor(Math.random() * 100));
            });
        },

        logOut() {
            this.httpPostRequest("http://localhost:8080/api-login/logout", {}, "Logout WRONG", (response) => {
                console.log(response);
                $("#not-logged").show();
                $("#logged").hide();
            });
        },

        getToken(callback) {
            // Video-call chosen by the user

            this.httpPostRequest("http://localhost:8080/api-sessions/get-token", { sessionName: this.sessionName, user: this.user }, "Request of TOKEN gone WRONG:", (response) => {
                this.token = response[0]; // Get token from response
                console.warn("Request of TOKEN gone WELL (TOKEN:" + this.token + ")");
                callback(this.token); // Continue the join operation
            });
        },

        removeUser() {
            this.httpPostRequest("http://localhost:8080/api-sessions/remove-user", { sessionName: this.sessionName, token: this.token }, "User couldn't be removed from session", (response) => {
                console.log(response);
                console.warn("You have been removed from session " + this.sessionName);
            });
        },

        httpPostRequest(url, body, errorMsg, callback) {
            var http = new XMLHttpRequest();
            http.open("POST", url, true);
            http.setRequestHeader("Content-type", "application/json");
            http.addEventListener("readystatechange", processRequest, false);
            http.send(JSON.stringify(body));

            function processRequest() {
                if (http.readyState == 4) {
                    console.log(http.status);
                    if (http.status == 200) {
                        try {
                            callback(JSON.parse(http.responseText));
                        } catch (e) {
                            callback();
                        }
                    } else {
                        console.warn(errorMsg);
                        console.warn(http.responseText);
                    }
                }
            }
        },

        /* APPLICATION REST METHODS */

        /* APPLICATION BROWSER METHODS */

        appendUserData(videoElement, connection) {
            var clientData;
            var serverData;
            var nodeId;
            if (connection.nickName) {
                // Appending local video data
                clientData = connection.nickName;
                serverData = connection.userName;
                nodeId = "main-videodata";
            } else {
                clientData = JSON.parse(connection.data.split("%/%")[0]).clientData;
                serverData = JSON.parse(connection.data.split("%/%")[1]).serverData;
                nodeId = connection.connectionId;
            }
            var dataNode = document.createElement("div");
            dataNode.className = "data-node";
            dataNode.id = "data-" + nodeId;
            dataNode.innerHTML = "<p class='nickName'>" + clientData + "</p><p class='userName'>" + serverData + "</p>";
            videoElement.parentNode.insertBefore(dataNode, videoElement.nextSibling);
            this.addClickListener(videoElement, clientData, serverData);
        },

        removeUserData(connection) {
            var userNameRemoved = $("#data-" + connection.connectionId);
            if ($(userNameRemoved).find("p.userName").html() === $("#main-video p.userName").html()) {
                this.cleanMainVideo(); // The participant focused in the main video has left
            }
            $("#data-" + connection.connectionId).remove();
        },

        removeAllUserData() {
            $(".data-node").remove();
        },

        cleanMainVideo() {
            $("#main-video video").get(0).srcObject = null;
            $("#main-video p").each(function () {
                $(this).html("");
            });
        },

        addClickListener(videoElement, clientData, serverData) {
            videoElement.addEventListener("click", function () {
                var mainVideo = $("#main-video video").get(0);
                if (mainVideo.srcObject !== videoElement.srcObject) {
                    $("#main-video").fadeOut("fast", () => {
                        $("#main-video p.nickName").html(clientData);
                        $("#main-video p.userName").html(serverData);
                        mainVideo.srcObject = videoElement.srcObject;
                        $("#main-video").fadeIn("fast");
                    });
                }
            });
        },

        initMainVideo(videoElement, userData) {
            $("#main-video video").get(0).srcObject = videoElement.srcObject;
            $("#main-video p.nickName").html(userData.nickName);
            $("#main-video p.userName").html(userData.userName);
            $("#main-video video").prop("muted", true);
        },

        initMainVideoThumbnail() {
            $("#main-video video").css("background", "url('images/subscriber-msg.jpg') round");
        },

        isPublisher(userName) {
            return userName.includes("publisher");
            // return userName.includes("1");
        },

        cleanSessionView() {
            this.removeAllUserData();
            this.cleanMainVideo();
            $("#main-video video").css("background", "");
        },
    },
};
</script>
