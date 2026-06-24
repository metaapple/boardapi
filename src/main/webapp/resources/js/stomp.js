// StompJs.Client 객체 생성
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat-app'
});

//웹 소켓 에러, 메시지 에러났을 때 자동으로 동일하게 처리하는 콜백함수 정의
// 웹 소켓 에러 발생시 콜백
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.log("stomp 메시지 에러 " + frame.headers['message']);
    console.log("stomp 메시지 에러 " + frame.body);
};

//연결되었을 때 콜백됨(자동으로 호출됨)
stompClient.onConnect = (frame) => {

    //연결되었을 때 전송되는 프레임을 프린트
    console.log(frame)
    //연결버튼 비활성화, 끊기버튼 활성화함수 호출
    setConnected(true);

    //가입
    stompClient.subscribe("/topic/greetings", (greeting) => {
        //두번째 파라메터 화살표함수는 브로드캐스팅될때마다 자동으로 호출됨.
        //전달된 메시지는 지정한 변수안에 들어감.
        console.log('/topic/greetings', greeting.body); //stomp의 body에 전달되는 메시지가 들어가 있음.
        showMessage(JSON.parse(greeting.body).name + '님이 입장했습니다.');
    });

    // 토픽 문자열: '/topic/chat' - chat 메시지
    stompClient.subscribe('/topic/chat', (chat) => {
        console.log('/topic/chat', chat.body)
        const message = JSON.parse(chat.body);
        showMessage(`${message.name}:${message.content}`);
    });

    // 연결 성공시 입장 메시지 보내기
    const name = document.getElementById('name').value;
    stompClient.publish({
        destination: '/app/hello',
        body: JSON.stringify({name}) // GreetingMessage에 대응
    });
}
    // 연결되었을 때 엘리먼트 프로퍼티 변경 --> 버튼 활성화/비활성화 시키는 함수
    function setConnected(connected) {
        const connectBtn = document.getElementById('connect');
        const disconnectBtn = document.getElementById('disconnect');
        const messages = document.getElementById('chat-messages');
        connectBtn.disabled = connected;
        disconnectBtn.disabled = !connected;
        messages.innerHTML = '';
    }

    // 연결하기
    function connect() {
        stompClient.activate();
    }

// 연결 끊기
    function disconnect() {
        stompClient.deactivate();
        setConnected(false);
        console.log('Disconnected');
    }

    // 메시지 전송하기
    function sendMessage() {
        const name = document.getElementById('name').value;
        const content = document.getElementById('content').value;
        console.log({name, content})
        stompClient.publish({
            destination: '/app/chat',
            body: JSON.stringify({name, content})   // ChatMessage에 대응
        });
    }

    // 수신 메시지 출력하기
    function showMessage(message) {
        const messages = document.getElementById('chat-messages');
        messages.innerHTML += '<tr><td>' + message + '</td></tr>'
    }

// 이벤트 핸들러 설정
    window.addEventListener("DOMContentLoaded", (event) => {
        const forms = document.querySelectorAll('.form-inline');
        const connectBtn = document.getElementById('connect');
        const disconnectBtn = document.getElementById('disconnect');
        const sendBtn = document.getElementById('send');
        connectBtn.addEventListener('click', () => connect());
        disconnectBtn.addEventListener('click',  () => disconnect());
        sendBtn.addEventListener('click', () => sendMessage());
        for(const form of forms) {
            console.log(form)
            form.addEventListener('submit', (e) => e.preventDefault());
        }
    });

