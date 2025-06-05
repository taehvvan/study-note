let stack = [];
let currentBlock = null;
let animationId = null;
let direction = 1;
let gameWidth = 300;
let blockHeight = 20;
let blockSpeed = 2;
let score = 0;

const stackEl = document.getElementById("stack");
const movingBlockEl = document.getElementById("movingBlock");
const scoreEl = document.getElementById("score");

function startGame() {
  cancelAnimationFrame(animationId);
  stack = [];
  score = 0;
  direction = 1;
  stackEl.innerHTML = "";
  movingBlockEl.innerHTML = "";
  scoreEl.textContent = "점수: 0";

  addBlock(gameWidth / 2 - 50, 100); // 바닥 블록
  newMovingBlock();
  animate();
}

function addBlock(left, width) {
  const y = stack.length * blockHeight;
  const block = document.createElement("div");
  block.className = "block";
  block.style.left = `${left}px`;
  block.style.width = `${width}px`;
  block.style.bottom = `${y}px`;
  stackEl.appendChild(block);
  stack.push({ left, width });
}

function newMovingBlock() {
  const last = stack[stack.length - 1];
  currentBlock = {
    left: 0,
    width: last.width,
    bottom: stack.length * blockHeight
  };
  renderMovingBlock();
}

function renderMovingBlock() {
  movingBlockEl.innerHTML = "";
  const block = document.createElement("div");
  block.className = "block";
  block.style.left = `${currentBlock.left}px`;
  block.style.width = `${currentBlock.width}px`;
  block.style.bottom = `${currentBlock.bottom}px`;
  movingBlockEl.appendChild(block);
}

function animate() {
  const max = gameWidth - currentBlock.width;
  currentBlock.left += blockSpeed * direction;

  if (currentBlock.left >= max || currentBlock.left <= 0) {
    direction *= -1;
  }

  renderMovingBlock();
  animationId = requestAnimationFrame(animate);
}

window.addEventListener("keydown", (e) => {
  if (e.code === "Space") stopBlock();
});

function stopBlock() {
  cancelAnimationFrame(animationId);
  const last = stack[stack.length - 1];
  const overlapStart = Math.max(currentBlock.left, last.left);
  const overlapEnd = Math.min(currentBlock.left + currentBlock.width, last.left + last.width);
  const overlapWidth = overlapEnd - overlapStart;

  if (overlapWidth <= 0) {
    alert("❌ 게임 오버! 점수: " + score);
    return;
  }

  addBlock(overlapStart, overlapWidth);
  score++;
  scoreEl.textContent = `점수: ${score}`;
  newMovingBlock();
  animate();
}
