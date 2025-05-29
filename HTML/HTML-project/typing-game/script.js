const sentences = [
    "타자를 빠르게 입력해 보세요",
    "JavaScript는 웹의 심장입니다",
    "코딩은 재미있는 도전입니다",
    "프론트엔드는 사용자 경험을 만듭니다",
    "함수는 재사용 가능한 코드입니다"
  ];
  
  let currentSentence = "";
  let startTime = null;
  let correctChars = 0;
  let totalTyped = 0;
  let username = "";
  
  const sentenceEl = document.getElementById("sentence");
  const inputEl = document.getElementById("input");
  const wpmEl = document.getElementById("wpm");
  const accuracyEl = document.getElementById("accuracy");
  const restartBtn = document.getElementById("restart");
  const usernameInput = document.getElementById("username");
  const startBtn = document.getElementById("startBtn");
  const gameArea = document.getElementById("gameArea");
  const scoreboard = document.getElementById("scoreboard");
  
  startBtn.addEventListener("click", () => {
    username = usernameInput.value.trim();
    if (!username) {
      alert("이름을 입력하세요!");
      return;
    }
    document.querySelector(".username").style.display = "none";
    gameArea.style.display = "block";
    resetGame();
    showHighScores();
  });
  
  function getRandomSentence() {
    return sentences[Math.floor(Math.random() * sentences.length)];
  }
  
  function resetGame() {
    currentSentence = getRandomSentence();
    sentenceEl.textContent = currentSentence;
    inputEl.value = "";
    inputEl.focus();
    startTime = null;
    correctChars = 0;
    totalTyped = 0;
    wpmEl.textContent = "0";
    accuracyEl.textContent = "100";
  }
  
  inputEl.addEventListener("input", () => {
    const typed = inputEl.value;
    if (!startTime) startTime = new Date();
  
    totalTyped++;
    correctChars = 0;
  
    for (let i = 0; i < typed.length; i++) {
      if (typed[i] === currentSentence[i]) correctChars++;
    }
  
    const elapsed = (new Date() - startTime) / 1000 / 60;
    const words = typed.trim().split(/\s+/).length;
    const wpm = Math.round(words / elapsed);
    const accuracy = Math.round((correctChars / totalTyped) * 100);
  
    wpmEl.textContent = isNaN(wpm) ? 0 : wpm;
    accuracyEl.textContent = isNaN(accuracy) ? 100 : accuracy;
  
    if (typed === currentSentence) {
      saveScore(username, wpm, accuracy);
      setTimeout(() => {
        resetGame();
        showHighScores();
      }, 1000);
    }
  });
  
  function saveScore(name, wpm, accuracy) {
    const scores = JSON.parse(localStorage.getItem("typingScores") || "[]");
    scores.push({ name, wpm, accuracy });
    localStorage.setItem("typingScores", JSON.stringify(scores));
  }
  
  function showHighScores() {
    const scores = JSON.parse(localStorage.getItem("typingScores") || "[]");
    const sorted = scores.sort((a, b) => b.wpm - a.wpm).slice(0, 5);
    scoreboard.innerHTML = "";
    sorted.forEach(score => {
      const li = document.createElement("li");
      li.textContent = `${score.name} - ${score.wpm} WPM (${score.accuracy}%)`;
      scoreboard.appendChild(li);
    });
  }
  
  restartBtn.addEventListener("click", resetGame);
  