const calendar = document.getElementById('calendar');
const monthYear = document.getElementById('month-year');
const prevBtn = document.getElementById('prev');
const nextBtn = document.getElementById('next');

const modal = document.getElementById('eventModal');
const selectedDateElem = document.getElementById('selected-date');
const eventText = document.getElementById('eventText');
const saveEvent = document.getElementById('saveEvent');
const closeModal = document.getElementById('closeModal');

let currentDate = new Date();
let selectedDate = null;

// 🔁 캘린더 그리기
function renderCalendar() {
  calendar.innerHTML = '';
  const year = currentDate.getFullYear();
  const month = currentDate.getMonth();

  const firstDay = new Date(year, month, 1).getDay();
  const daysInMonth = new Date(year, month + 1, 0).getDate();

  monthYear.textContent = `${year}년 ${month + 1}월`;

  for (let i = 0; i < firstDay; i++) {
    const empty = document.createElement('div');
    calendar.appendChild(empty);
  }

  for (let day = 1; day <= daysInMonth; day++) {
    const dayElem = document.createElement('div');
    dayElem.className = 'day';
    dayElem.innerHTML = `<span>${day}</span>`;

    const key = `${year}-${month + 1}-${day}`;
    const event = localStorage.getItem(key);
    if (event) {
      const eventDiv = document.createElement('div');
      eventDiv.className = 'event';
      eventDiv.textContent = event;
      dayElem.appendChild(eventDiv);
    }

    dayElem.addEventListener('click', () => {
      selectedDate = key;
      selectedDateElem.textContent = `${year}년 ${month + 1}월 ${day}일`;
      eventText.value = event || '';
      modal.style.display = 'flex';
    });

    calendar.appendChild(dayElem);
  }
}

saveEvent.addEventListener('click', () => {
  if (selectedDate) {
    localStorage.setItem(selectedDate, eventText.value);
    modal.style.display = 'none';
    renderCalendar();
  }
});

closeModal.addEventListener('click', () => {
  modal.style.display = 'none';
});

prevBtn.addEventListener('click', () => {
  currentDate.setMonth(currentDate.getMonth() - 1);
  renderCalendar();
});

nextBtn.addEventListener('click', () => {
  currentDate.setMonth(currentDate.getMonth() + 1);
  renderCalendar();
});

window.addEventListener('click', (e) => {
  if (e.target === modal) {
    modal.style.display = 'none';
  }
});

renderCalendar();
