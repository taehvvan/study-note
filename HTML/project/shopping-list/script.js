const products = [
    { id: 1, name: "무선 이어폰", price: 99000, category: "electronics", image: "img/earphone.jpeg" },
    { id: 2, name: "청바지", price: 45000, category: "clothing", image: "img/jeans.jpg" },
    { id: 3, name: "스마트 워치", price: 149000, category: "electronics", image: "img/watch.png" },
    { id: 4, name: "후드 티셔츠", price: 39000, category: "clothing", image: "img/hood.jpg" },
    { id: 5, name: "노트북", price: 850000, category: "electronics", image: "img/notebook.jpg" },
    { id: 6, name: "운동화", price: 69000, category: "clothing", image: "img/sneakers.jpeg" },
  ];
  
  let cart = [];
  
  const productList = document.getElementById("productList");
  const cartList = document.getElementById("cartList");
  const categoryFilter = document.getElementById("categoryFilter");
  const sortPrice = document.getElementById("sortPrice");
  const searchInput = document.getElementById("searchInput");
  
  function displayProducts(data) {
    productList.innerHTML = "";
    data.forEach(p => {
      productList.innerHTML += `
        <div class="card">
          <img src="${p.image}" alt="${p.name}" />
          <h3>${p.name}</h3>
          <p>${p.price.toLocaleString()}원</p>
          <button onclick="addToCart(${p.id})">장바구니 담기</button>
        </div>
      `;
    });
  }
  
  function addToCart(productId) {
    const product = products.find(p => p.id === productId);
    const existing = cart.find(item => item.id === productId);
    if (existing) {
      existing.quantity++;
    } else {
      cart.push({ ...product, quantity: 1 });
    }
    displayCart();
  }
  
  function displayCart() {
    cartList.innerHTML = "";
    let total = 0;
  
    cart.forEach(item => {
      const itemTotal = item.price * item.quantity;
      total += itemTotal;
  
      cartList.innerHTML += `
        <li>
          ${item.name} - ${item.price.toLocaleString()}원 x ${item.quantity}
          <button onclick="changeQuantity(${item.id}, 1)">＋</button>
          <button onclick="changeQuantity(${item.id}, -1)">－</button>
          <button onclick="removeFromCart(${item.id})">삭제</button>
        </li>
      `;
    });
  
    cartList.innerHTML += `<li><strong>총액: ${total.toLocaleString()}원</strong></li>`;
  }
  
  function changeQuantity(productId, change) {
    const item = cart.find(p => p.id === productId);
    if (item) {
      item.quantity += change;
      if (item.quantity <= 0) {
        removeFromCart(productId);
      }
      displayCart();
    }
  }
  
  function removeFromCart(productId) {
    cart = cart.filter(p => p.id !== productId);
    displayCart();
  }
  
  function applyFilters() {
    let filtered = [...products];
  
    const category = categoryFilter.value;
    const sort = sortPrice.value;
    const keyword = searchInput.value.trim().toLowerCase();
  
    if (category !== "all") {
      filtered = filtered.filter(p => p.category === category);
    }
  
    if (keyword) {
      filtered = filtered.filter(p => p.name.toLowerCase().includes(keyword));
    }
  
    if (sort === "low") {
      filtered.sort((a, b) => a.price - b.price);
    } else if (sort === "high") {
      filtered.sort((a, b) => b.price - a.price);
    }
  
    displayProducts(filtered);
  }
  
  categoryFilter.addEventListener("change", applyFilters);
  sortPrice.addEventListener("change", applyFilters);
  searchInput.addEventListener("input", applyFilters);
  
  displayProducts(products);
  