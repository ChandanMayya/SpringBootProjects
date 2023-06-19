
    var rootDiv = document.createElement("div");
    rootDiv.setAttribute("id", "userName");
    rootDiv.setAttribute("class", "d-flex justify-content-between");

    var userNameText = document.createElement("p");
    userNameText.setAttribute("id", "userName");
    userNameText.setAttribute("class", "small mb-1");
    userNameText.setAttribute("text","Shankare")

    var timeText = document.createElement("p");
    timeText.setAttribute("id", "time");
    timeText.setAttribute("class", "small mb-1 text-muted");
    timeText.setAttribute("text","just now");

    rootDiv.appendChild(userNameText);
    rootDiv.appendChild(timeText);

    var containerDiv = document.createElement("div");
    containerDiv.setAttribute("class", "d-flex flex-row justify-content-start");

    var image = document.createElement("img");
    image.setAttribute("src", "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp");
    image.setAttribute("alt", "avatar");
    image.setAttribute("style", "width: 45px; height: 100%;");


    var message = document.createElement("p");
    message.setAttribute("class", "small p-2 ms-3 mb-3 rounded-3");
    message.setAttribute("style", "background-color: #f5f6f7;");
    message.setAttribute("text","tryambakam yajamahet sukhandim pustivardhanam urvarukamivavandanat maammrutyurmikshiyamaamrdaat om");

    containerDiv.appendChild(image);
    containerDiv.appendChild(message);

    var display = document.createElement("div");
    display.appendChild(rootDiv);
    display.appendChild(containerDiv);

    document.getElementById("messageBody").appendChild(display);
