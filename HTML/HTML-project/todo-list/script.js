const taskInput = document.getElementById('task-input');
const dueDateInput = document.getElementById('due-date');
const addBtn = document.getElementById('add-btn');
const taskList = document.getElementById('task-list');
const filterRadios = document.querySelectorAll('input[name="filter"]');

let tasks = JSON.parse(localStorage.getItem('tasks')) || [];
let filter = 'all';

function renderTasks() {
  taskList.innerHTML = '';
  const filtered = tasks.filter(task => {
    if (filter === 'active') return !task.completed;
    if (filter === 'completed') return task.completed;
    return true;
  });

  filtered.forEach((task, index) => {
    const li = document.createElement('li');
    li.className = 'task' + (task.completed ? ' completed' : '');
    li.innerHTML = `
      <input type="checkbox" ${task.completed ? 'checked' : ''} data-index="${index}">
      <div class="task-info">
        <span>${task.text}</span>
        <div class="task-date">${task.dueDate ? `📅 ${task.dueDate}` : ''}</div>
      </div>
      <button data-index="${index}">삭제</button>
    `;
    taskList.appendChild(li);
  });
}

addBtn.addEventListener('click', () => {
  const text = taskInput.value.trim();
  const dueDate = dueDateInput.value;
  if (text) {
    tasks.push({ text, dueDate, completed: false });
    taskInput.value = '';
    dueDateInput.value = '';
    updateLocalStorage();
    renderTasks();
  }
});

taskList.addEventListener('click', (e) => {
  const index = e.target.getAttribute('data-index');
  if (e.target.tagName === 'BUTTON') {
    tasks.splice(index, 1);
  } else if (e.target.tagName === 'INPUT') {
    tasks[index].completed = e.target.checked;
  }
  updateLocalStorage();
  renderTasks();
});

filterRadios.forEach(radio => {
  radio.addEventListener('change', (e) => {
    filter = e.target.value;
    renderTasks();
  });
});

function updateLocalStorage() {
  localStorage.setItem('tasks', JSON.stringify(tasks));
}

renderTasks();
