const board = document.getElementById("board");
const moveCountEl = document.getElementById("moveCount");

let tiles = [];
let moveCount = 0;

function init() {
  tiles = [...Array(15).keys()].map(x => x + 1);
  tiles.push(0); // 빈칸
  shuffle(tiles);
  moveCount = 0;
  updateMoveCount();
  render();
}

function shuffle(array) {
  for (let i = array.length - 1; i > 0; i--) {
    let j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }

  if (!isSolvable(array)) shuffle(array);
}

function isSolvable(arr) {
  let inversions = 0;
  for (let i = 0; i < arr.length; i++) {
    for (let j = i + 1; j < arr.length; j++) {
      if (arr[i] && arr[j] && arr[i] > arr[j]) inversions++;
    }
  }
  const emptyRow = Math.floor(arr.indexOf(0) / 4);
  return (emptyRow % 2 === 0) ? inversions % 2 !== 0 : inversions % 2 === 0;
}

function render() {
  board.innerHTML = "";
  tiles.forEach((value, index) => {
    const tile = document.createElement("div");
    tile.className = value === 0 ? "tile empty" : "tile";
    tile.textContent = value === 0 ? "" : value;

    tile.addEventListener("click", () => move(index));
    board.appendChild(tile);
  });
}

function move(index) {
  const emptyIndex = tiles.indexOf(0);
  const validMoves = [ 
    index - 4, index + 4,
    (index % 4 !== 0) ? index - 1 : -1,
    (index % 4 !== 3) ? index + 1 : -1
  ];

  if (validMoves.includes(emptyIndex)) {
    [tiles[index], tiles[emptyIndex]] = [tiles[emptyIndex], tiles[index]];
    countMoves();
    render();

    if (checkWin()) {
      setTimeout(() => {
        alert(`🎉 퍼즐을 맞췄습니다! 이동 횟수: ${moveCount}`);
      }, 100);
    }
  }
}

function countMoves() {
  moveCount++;
  updateMoveCount();
}

function updateMoveCount() {
  moveCountEl.textContent = `이동 횟수: ${moveCount}`;
}

function checkWin() {
  for (let i = 0; i < 15; i++) {
    if (tiles[i] !== i + 1) return false;
  }
  return true;
}

init();
