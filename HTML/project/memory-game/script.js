const gameBoard = document.getElementById('gameBoard');
const statusText = document.getElementById('status');
const restartBtn = document.getElementById('restartBtn');

const emojis = ['🐶', '🐱', '🐭', '🐹', '🦊', '🐻', '🐸', '🐵'];
let cards = [];
let firstCard, secondCard;
let lockBoard = false;
let tries = 0;

function setupGame() {
  tries = 0;
  statusText.textContent = "5초 동안 이모지를 기억하세요!";
  cards = [...emojis, ...emojis]
    .sort(() => 0.5 - Math.random())
    .map(createCard);

  gameBoard.innerHTML = '';
  cards.forEach(card => gameBoard.appendChild(card));

  lockBoard = true;

  // 1. 처음부터 보여줌
  cards.forEach(card => card.classList.add('flipped'));

  // 2. 타이머 표시
  let countdown = 5;
  const timerInterval = setInterval(() => {
    statusText.textContent = `카드를 기억하세요! (${countdown}초)`;
    countdown--;

    if (countdown < 0) {
      clearInterval(timerInterval);
      cards.forEach(card => card.classList.remove('flipped'));
      statusText.textContent = "이제 시작하세요!";
      lockBoard = false;
    }
  }, 1000);
}

function createCard(emoji) {
  const card = document.createElement('div');
  card.classList.add('card', 'flipped'); // 시작 시 보여주기 위해 flipped 추가

  card.innerHTML = `
    <div class="front">${emoji}</div>
    <div class="back">?</div>
  `;

  card.dataset.emoji = emoji;
  card.addEventListener('click', onCardClick);
  return card;
}

function onCardClick(e) {
  const clickedCard = e.currentTarget;
  if (lockBoard || clickedCard === firstCard || clickedCard.classList.contains('matched')) return;

  clickedCard.classList.add('flipped');

  if (!firstCard) {
    firstCard = clickedCard;
  } else {
    secondCard = clickedCard;
    lockBoard = true;
    tries++;
    statusText.textContent = `시도 횟수: ${tries}`;
    checkMatch();
  }
}

function checkMatch() {
  const isMatch = firstCard.dataset.emoji === secondCard.dataset.emoji;

  if (isMatch) {
    firstCard.classList.add('matched');
    secondCard.classList.add('matched');
    resetBoard();
    checkGameOver();
  } else {
    setTimeout(() => {
      firstCard.classList.remove('flipped');
      secondCard.classList.remove('flipped');
      resetBoard();
    }, 1000);
  }
}

function resetBoard() {
  [firstCard, secondCard] = [null, null];
  lockBoard = false;
}

function checkGameOver() {
  const matchedCards = document.querySelectorAll('.card.matched');
  if (matchedCards.length === cards.length) {
    setTimeout(() => {
      alert(`🎉 축하합니다! ${tries}번 만에 성공했어요!`);
    }, 500);
  }
}

restartBtn.addEventListener('click', setupGame);

setupGame(); // 첫 실행
